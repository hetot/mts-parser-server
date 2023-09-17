package com.example.mtstestproject.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tariff")
public class TariffEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "fee")
    private String fee;

    @Column(name = "discount_fee")
    private String discountFee;

    @Column(name = "fee_annotation")
    private String feeAnnotation;

    @OneToMany(mappedBy = "tariff")
    private List<TariffCharacteristicEntity> characteristics = new ArrayList<>();

    @OneToMany(mappedBy = "tariff")
    private List<TariffBenefitEntity> benefits = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_id")
    private UpdateEntity update;

    public static TariffEntity entityFromJsonNode(JsonNode node, UpdateEntity update) {
        var tariff = new TariffEntity();
        tariff.setUpdate(update);
        tariff.setName(node.get("title").asText());
        tariff.setType(node.get("tariffType").asText());
        if (node.has("description"))
            tariff.setDescription(node.get("description").asText());
        if (node.has("subscriptionFee") && node.get("subscriptionFee").has("numValue"))
            tariff.setFee(node.get("subscriptionFee").get("numValue").asText() + " " + node.get("subscriptionFee").get("displayUnit").asText());
        if (node.has("discountFee"))
            tariff.setDiscountFee(node.get("discountFee").get("numValue").asText() + " " + node.get("discountFee").get("displayUnit").textValue());
        if (node.has("subscriptionFeeAnnotationSettings") && node.get("subscriptionFeeAnnotationSettings").has("text"))
            tariff.setFeeAnnotation(node.get("subscriptionFeeAnnotationSettings").get("text").asText());

        return tariff;
    }
}
