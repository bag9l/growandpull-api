package com.growandpull.api.mapper;

import com.growandpull.api.dto.finance.FinanceDto;
import com.growandpull.api.dto.startup.StartupCard;
import com.growandpull.api.dto.startup.StartupCreationRequest;
import com.growandpull.api.dto.startup.StartupDetailsDto;
import com.growandpull.api.dto.startup.StartupView;
import com.growandpull.api.dto.user.UserCard;
import com.growandpull.api.exception.EntityNotExistsException;
import com.growandpull.api.model.Category;
import com.growandpull.api.model.Image;
import com.growandpull.api.model.Startup;
import com.growandpull.api.repository.CategoryRepository;
import com.growandpull.api.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserMapper.class, FinanceMapper.class, CategoryRepository.class, ImageMapper.class, StartupDetailsMapper.class})
public abstract class StartupMapper {

    protected UserMapper userMapper;
    protected FinanceMapper financeMapper;
    protected ImageMapper imageMapper;

    protected StartupDetailsMapper startupDetailsMapper;
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
        StartupDetailsDto startupDetailsDto = startupDetailsMapper.startupDetailsToDto(startup.getStartupDetails());
        Set<UserCard> collaborators = startup.getCollaborators()
                .stream()
                .map(userMapper::userToCard)
                .collect(Collectors.toSet());

        return new StartupView(
                startup.getTitle(),
                userCard,
                image,
                startup.getDescription(),
                startup.getStatus(),
                startup.getCategory().getName(),
                finance,
                startupDetailsDto,
                startup.getCreatedAt(),
                collaborators);
    }

    public Startup newToStartup(StartupCreationRequest newStartup) throws IOException {
        Startup startup = new Startup();
        Category category = categoryRepository.findById(newStartup.getCategoryId()).orElseThrow(() ->
                new EntityNotExistsException(String.format("Category with id:%s not found", newStartup.getCategoryId())));

        Image image = (newStartup.getImage() != null) ? imageMapper.multiPartFileToImage(newStartup.getImage()) : null;

        startup.setTitle(newStartup.getTitle());
        startup.setDescription(newStartup.getDescription());
        startup.setStatus(newStartup.getStatus());
        startup.setCategory(category);
        startup.setFinance(financeMapper.dtoToFinance(newStartup.getFinance()));
        startup.setStartupDetails(startupDetailsMapper.dtoToStartupDetails(newStartup.getDetails()));
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
    public void setStartupDetailsMapper(StartupDetailsMapper startupDetailsMapper) {
        this.startupDetailsMapper = startupDetailsMapper;
    }

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


}
