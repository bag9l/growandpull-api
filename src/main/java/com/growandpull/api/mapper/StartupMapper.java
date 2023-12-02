package com.growandpull.api.mapper;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.dto.startup.StartupCard;
import com.growandpull.api.dto.startup.StartupCreationRequest;
import com.growandpull.api.dto.startup.StartupView;
import com.growandpull.api.dto.user.UserCard;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.model.entity.Category;
import com.growandpull.api.model.entity.Image;
import com.growandpull.api.model.entity.Startup;
import com.growandpull.api.repository.CategoryRepository;
import com.growandpull.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class, FinanceMapper.class, CategoryRepository.class, FileMapper.class})
public abstract class StartupMapper {

    protected UserMapper userMapper;
    protected FinanceMapper financeMapper;
    protected FileMapper fileMapper;
    protected CategoryRepository categoryRepository;

    @Mapping(target = "finance", expression = "java(financeMapper.financeToDto(startup.getFinance()))")
    @Mapping(target = "category", expression = "java(startup.getCategory().getName())")
    @Mapping(target = "image", expression = "java((startup.getImage() != null) ? " +
            "com.growandpull.api.util.ImageUtil.decompressImage(startup.getImage().getImageData()) : null)")
    public abstract StartupCard startupToCard(Startup startup);

    public StartupView startupToView(Startup startup) {
        UserCard userCard = userMapper.userToCard(startup.getOwner());
        byte[] image = (startup.getImage() != null) ?
                ImageUtil.decompressImage(startup.getImage().getImageData())
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

    public Startup newToStartup(StartupCreationRequest newStartup) throws IOException {
        Startup startup = new Startup();
        Category category = categoryRepository.findById(newStartup.getCategoryId()).orElseThrow(() ->
                new EntityNotExistsException(String.format("Category with id:%s not found", newStartup.getCategoryId())));

        Image image = (newStartup.getImage() != null) ? fileMapper.multiPartFileToImage(newStartup.getImage()) : null;

        startup.setTitle(newStartup.getTitle());
        startup.setDescription(newStartup.getDescription());
        startup.setStatus(newStartup.getStatus());
        startup.setCategory(category);
        startup.setFinance(financeMapper.dtoToFinance(newStartup.getFinance()));
        startup.setImage(image);

        return startup;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setFinanceMapper(FinanceMapper financeMapper) {
        this.financeMapper = financeMapper;
    }

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
