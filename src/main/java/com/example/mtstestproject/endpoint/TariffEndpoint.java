package com.example.mtstestproject.endpoint;


import com.example.mtstestproject.endpoint.dto.TariffResponseDto;
import com.example.mtstestproject.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/tariff")
public class TariffEndpoint {
    TariffService tariffService;

    @Autowired
    public void setTariffService(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/{updateId}")
    public ResponseEntity<List<TariffResponseDto>> getTariffsByUpdateId(@PathVariable Long updateId) {
        return ResponseEntity.ok(tariffService.getTariffsByUpdateId(updateId));
    }
}
