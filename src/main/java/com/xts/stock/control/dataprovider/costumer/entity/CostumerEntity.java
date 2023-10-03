package com.xts.stock.control.dataprovider.costumer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("costumer")
public class CostumerEntity {

    @Id
    @JsonProperty("costumerCnpj")
    private String id;
    private String costumerName;
    private List<TagEntity> tagList;
}
