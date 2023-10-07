package com.xts.stock.control.dataprovider.stock.entity;

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
@Document("stock")
public class StockEntity {

    @Id
    @JsonProperty("supplierName")
    private String id;
    private List<StockMaterialEntity> materialList;
}
