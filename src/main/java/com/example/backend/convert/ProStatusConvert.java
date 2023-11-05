package com.example.backend.convert;

import com.example.backend.enums.EmployeeStatus;
import com.example.backend.enums.ProductStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProStatusConvert implements AttributeConverter<ProductStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProductStatus productStatus) {
        if (productStatus == null) return null;
        return productStatus.getValue();
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer integer) {
        if (integer == -1) return ProductStatus.TERMINATED;
        if (integer == 0) return  ProductStatus.IN_ACTIVE;
        if (integer == 1) return  ProductStatus.ACTIVE;
        return null;
    }
}
