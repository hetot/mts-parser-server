package com.example.mtstestproject.endpoint;

import com.example.mtstestproject.endpoint.dto.UpdateResponseDto;
import com.example.mtstestproject.service.TariffUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/update")
public class TariffUpdateEndpoint {
    TariffUpdateService tariffUpdateService;

    @Autowired
    public void setTariffUpdateService(TariffUpdateService tariffUpdateService) {
        this.tariffUpdateService = tariffUpdateService;
    }

    @GetMapping
    public ResponseEntity<UpdateResponseDto> getLastUpdate() {
        var response = tariffUpdateService.getLastUpdate();
        if (response == null)
            ResponseEntity.badRequest();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UpdateResponseDto> update() {
        return ResponseEntity.ok(tariffUpdateService.updateTariffs());
    }
}
