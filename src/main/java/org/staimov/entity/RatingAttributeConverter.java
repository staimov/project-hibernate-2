package org.staimov.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RatingAttributeConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null)
            return null;

        return rating.getLabel();
    }

    @Override
    public Rating convertToEntityAttribute(String s) {
        return Rating.getByLabel(s);
    }
}
