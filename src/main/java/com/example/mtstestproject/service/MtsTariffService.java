package com.example.mtstestproject.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MtsTariffService {
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${app.mtsUrl}")
    private String MTS_URL;

    public JsonNode getActualTariffs() {
        try {
            var document = Jsoup
                    .connect(MTS_URL)
                    .get();
            var jsonString = document
                    .selectXpath("//script[contains(text(),'window.globalSettings.tariffs')]")
                    .get(0)
                    .html()
                    .replaceFirst("window.globalSettings.tariffs = ", "");
            jsonString = jsonString.substring(0, jsonString.length() - 1);
            return mapper.readTree(jsonString).get("actualTariffs");
        } catch (Exception e) {
            return null;
        }
    }
}
