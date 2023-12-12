package com.growandpull.api.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.time.LocalDate;
@Convert(converter = DateConverter.class)
//@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate entityValue) {
        return java.sql.Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        return (databaseValue == null) ? null
                : databaseValue.toLocalDate();
    }
}
