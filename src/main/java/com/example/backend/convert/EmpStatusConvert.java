package com.example.backend.convert;

import com.example.backend.enums.EmployeeStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmpStatusConvert implements AttributeConverter<EmployeeStatus,Integer> {

    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus employeeStatus) {
        if (employeeStatus == null) return null;
        return employeeStatus.getValue();
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer integer) {
        if (integer == -1) return EmployeeStatus.TERMINATED;
        if (integer == 0) return  EmployeeStatus.IN_ACTIVE;
        if (integer == 1) return  EmployeeStatus.ACTIVE;
        return null;
    }
}
