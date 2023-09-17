package com.example.mtstestproject.endpoint.dto;

import com.example.mtstestproject.model.TariffBenefitEntity;
import com.example.mtstestproject.model.TariffCharacteristicEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TariffBenefitResponseDto {
    private String description;

    public static TariffBenefitResponseDto fromEntity(TariffBenefitEntity entity) {
        var benefit = new TariffBenefitResponseDto();
        benefit.setDescription(entity.getDescription());
        return benefit;
    }
}
