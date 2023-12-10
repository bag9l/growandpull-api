package com.growandpull.api.mapper;

import com.growandpull.api.dto.location.CityDto;
import com.growandpull.api.dto.location.CountryDto;
import com.growandpull.api.model.entity.City;
import com.growandpull.api.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CityMapper {

    @Mapping(target = "country", expression = "java(countryToDto(city.getCountry()))")
    public abstract CityDto cityToDto(City city);

    public abstract CountryDto countryToDto(Country country);

}
