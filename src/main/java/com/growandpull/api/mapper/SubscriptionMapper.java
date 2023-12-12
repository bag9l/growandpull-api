package com.growandpull.api.mapper;

import com.growandpull.api.dto.subscription.SubscriptionCard;
import com.growandpull.api.dto.subscription.SubscriptionDto;
import com.growandpull.api.model.entity.SubscriptionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {FinanceMapper.class})
public abstract class SubscriptionMapper {

    protected FinanceMapper financeMapper;

    @Mapping(target = "type", source = "appIdentifier")
    @Mapping(target = "price",
    expression = "java(financeMapper.financeToDto(subscription.getPrice()))")
    public abstract SubscriptionDto subscriptionTypeToDto(SubscriptionType subscription);

    @Mapping(target = "id", source = "appIdentifier")
    @Mapping(target = "price",
            expression = "java(financeMapper.financeToDto(subscription.getPrice()))")
    public abstract SubscriptionCard subscriptionTypeTOCard(SubscriptionType subscription);

    @Autowired
    public void setFinanceMapper(FinanceMapper financeMapper) {
        this.financeMapper = financeMapper;
    }
}
