package com.example.mtstestproject.endpoint.dto;

import com.example.mtstestproject.model.UpdateEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateResponseDto {
    private long updateId;
    private LocalDateTime createdAt;

    public static UpdateResponseDto fromEntity(UpdateEntity entity) {
        var response = new UpdateResponseDto();
        response.setUpdateId(entity.getId());
        response.setCreatedAt(entity.getCreatedDate());
        return response;
    }
}
