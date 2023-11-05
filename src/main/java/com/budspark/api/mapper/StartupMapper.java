package com.budspark.api.mapper;

import com.budspark.api.dto.*;
import com.budspark.api.exception.EntityNotExistsException;
import com.budspark.api.model.Category;
import com.budspark.api.model.Image;
import com.budspark.api.model.Startup;
import com.budspark.api.model.User;
import com.budspark.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, FinanceMapper.class, CategoryRepository.class})
public abstract class StartupMapper {

    protected UserMapper userMapper;
    protected FinanceMapper financeMapper;
    protected CategoryRepository categoryRepository;

//    @Mapping(target = "existenceTime", expression = "java(startup.getExistenceTime())")
    @Mapping(target = "category", expression = "java(startup.getCategory().getName())")
    @Mapping(target = "image", expression = "java((startup.getImage() != null) ? " +
            "com.budspark.api.util.ImageUtil.decompressImage(startup.getImage().getImageData()) : null)")
    public abstract StartupCard startupToCard(Startup startup);

    public StartupView startupToView(Startup startup) {
        UserCard userCard = userMapper.userToCard(startup.getOwner());
        byte[] image = (startup.getImage() != null) ?
                com.budspark.api.util.ImageUtil.decompressImage(startup.getImage().getImageData())
                : null;
        FinanceDto finance = financeMapper.financeToDto(startup.getFinance());

        return new StartupView(
                startup.getTitle(),
                userCard,
                image,
                startup.getDescription(),
                startup.getStatus(),
                startup.getCategory().getName(),
                finance,
                startup.getCreatedAt());
    }

    public Startup newToStartup(NewStartup newStartup){
        Startup startup = new Startup();
        Category category = categoryRepository.findById(newStartup.categoryId()).orElseThrow(()->
                new EntityNotExistsException(String.format("Category with id:%s not found", newStartup.categoryId())));
        Image image = null;
        if(newStartup.image()!=null) {
            image = new Image(newStartup.image().getOriginalFilename(),
                    newStartup.image().getContentType(),
                    ImageUtil.compressImage(newStartup.image().getBytes()));
        }


        startup.setTitle(newStartup.title());
        startup.setDescription(newStartup.description());
        startup.setStatus(newStartup.status());
        startup.setCategory(category);
        startup.setFinance(financeMapper.dtoToFinance(newStartup.finance()));
        startup.setImage();
    }

    @Autowired
    public StartupMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public FinanceMapper getFinanceMapper() {
        return financeMapper;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
