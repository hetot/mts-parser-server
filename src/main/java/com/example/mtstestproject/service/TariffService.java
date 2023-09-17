package com.example.mtstestproject.service;

import com.example.mtstestproject.endpoint.dto.TariffResponseDto;
import com.example.mtstestproject.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {
    TariffRepository tariffRepository;

    @Autowired
    public void setTariffRepository(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public List<TariffResponseDto> getTariffsByUpdateId(long updateId) {
        var tariffs = tariffRepository.findByUpdateId(updateId);
        return tariffs.stream().map(TariffResponseDto::fromEntity).toList();
    }
}
