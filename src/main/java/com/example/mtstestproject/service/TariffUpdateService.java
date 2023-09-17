package com.example.mtstestproject.service;

import com.example.mtstestproject.endpoint.dto.UpdateResponseDto;
import com.example.mtstestproject.exception.NoUpdateFoundException;
import com.example.mtstestproject.model.TariffBenefitEntity;
import com.example.mtstestproject.model.TariffCharacteristicEntity;
import com.example.mtstestproject.model.TariffEntity;
import com.example.mtstestproject.model.UpdateEntity;
import com.example.mtstestproject.repository.TariffBenefitRepository;
import com.example.mtstestproject.repository.TariffCharacteristicRepository;
import com.example.mtstestproject.repository.TariffRepository;
import com.example.mtstestproject.repository.UpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class TariffUpdateService {
    TariffRepository tariffRepository;
    MtsTariffService mtsTariffService;
    UpdateRepository updateRepository;
    TariffBenefitRepository tariffBenefitRepository;
    TariffCharacteristicRepository tariffCharacteristicRepository;

    @Value("${app.timeout}")
    private Long TIMEOUT_IN_SECONDS;

    @Autowired
    public void setTariffCharacteristicRepository(TariffCharacteristicRepository tariffCharacteristicRepository) {
        this.tariffCharacteristicRepository = tariffCharacteristicRepository;
    }

    @Autowired
    public void setTariffBenefitRepository(TariffBenefitRepository tariffBenefitRepository) {
        this.tariffBenefitRepository = tariffBenefitRepository;
    }

    @Autowired
    public void setTariffRepository(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Autowired
    public void setMtsTariffService(MtsTariffService mtsTariffService) {
        this.mtsTariffService = mtsTariffService;
    }

    @Autowired
    public void setUpdateRepository(UpdateRepository updateRepository) {
        this.updateRepository = updateRepository;
    }

    @Transactional
    public UpdateResponseDto updateTariffs() {
        var json = mtsTariffService.getActualTariffs();
        var lastUpdate = updateRepository.findTopByOrderByIdDesc();
        if (lastUpdate != null && ChronoUnit.SECONDS.between(lastUpdate.getCreatedDate(), LocalDateTime.now()) < TIMEOUT_IN_SECONDS) {
            return UpdateResponseDto.fromEntity(lastUpdate);
        }
        var newUpdate = updateRepository.save(new UpdateEntity());
        for (var node : json) {
            var tariff = tariffRepository.save(TariffEntity.entityFromJsonNode(node, newUpdate));
            for (var characteristicNode : node.get("productCharacteristics")) {
                if (!characteristicNode.has("title"))
                    continue;
                var characteristic = new TariffCharacteristicEntity();
                characteristic.setTariff(tariff);
                characteristic.setTitle(characteristicNode.get("title").textValue());
                tariff.getCharacteristics().add(characteristic);
                tariffCharacteristicRepository.save(characteristic);
            }
            if (node.has("benefitsDescription") && node.get("benefitsDescription").has("description")) {
                var benefit = new TariffBenefitEntity();
                benefit.setTariff(tariff);
                benefit.setDescription(node.get("benefitsDescription").get("description").textValue());
                tariff.getBenefits().add(benefit);
                tariffBenefitRepository.save(benefit);
            }
        }
        return UpdateResponseDto.fromEntity(newUpdate);
    }

    public UpdateResponseDto getLastUpdate() {
        var update = updateRepository.findTopByOrderByIdDesc();
        return update != null ? UpdateResponseDto.fromEntity(update) : null;
    }
}
