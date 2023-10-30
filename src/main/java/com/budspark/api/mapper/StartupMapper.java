package com.budspark.api.mapper;

import com.budspark.api.dto.FinanceDto;
import com.budspark.api.dto.StartupCard;
import com.budspark.api.dto.StartupView;
import com.budspark.api.dto.UserCard;
import com.budspark.api.model.Startup;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, FinanceMapper.class})
public abstract class StartupMapper {

    protected UserMapper userMapper;
    protected FinanceMapper financeMapper;

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

    @Autowired
    public StartupMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public FinanceMapper getFinanceMapper() {
        return financeMapper;
    }
}
