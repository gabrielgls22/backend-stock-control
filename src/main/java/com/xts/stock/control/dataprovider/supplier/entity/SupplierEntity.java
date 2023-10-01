package com.xts.stock.control.dataprovider.supplier.entity;

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
@Document("supplier")
public class SupplierEntity {

    @Id
    @JsonProperty("supplierCnpj")
    private String id;
    private String supplierName;
    private List<MaterialEntity> materialList;
}
