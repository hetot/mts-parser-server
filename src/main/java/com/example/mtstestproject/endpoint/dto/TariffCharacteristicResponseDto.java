package com.example.mtstestproject.endpoint.dto;

import com.example.mtstestproject.model.TariffCharacteristicEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TariffCharacteristicResponseDto {
    private String title;

    public static TariffCharacteristicResponseDto fromEntity(TariffCharacteristicEntity tariffCharacteristicEntity) {
        var characteristic = new TariffCharacteristicResponseDto();
        characteristic.setTitle(tariffCharacteristicEntity.getTitle());
        return characteristic;
    }
}
