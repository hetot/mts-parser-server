package com.example.mtstestproject.endpoint.dto;

import com.example.mtstestproject.model.TariffEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TariffResponseDto {
    private long id;
    private String type;
    private String name;
    private String description;
    private String fee;
    private String discountFee;
    private String feeAnnotation;
    private List<TariffCharacteristicResponseDto> characteristics;
    private List<TariffBenefitResponseDto> benefits;

    public static TariffResponseDto fromEntity(TariffEntity entity) {
        var tariff = new TariffResponseDto();
        tariff.setId(entity.getId());
        tariff.setType(entity.getType());
        tariff.setName(entity.getName());
        tariff.setDescription(entity.getDescription());
        tariff.setFee(entity.getFee());
        tariff.setDiscountFee(entity.getDiscountFee());
        tariff.setFeeAnnotation(entity.getFeeAnnotation());
        tariff.setCharacteristics(entity.getCharacteristics().stream().map(TariffCharacteristicResponseDto::fromEntity).toList());
        tariff.setBenefits(entity.getBenefits().stream().map(TariffBenefitResponseDto::fromEntity).toList());
        return tariff;
    }
}