package com.xts.stock.control.dataprovider.supplier.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xts.stock.control.usecase.supplier.domain.MaterialDomain;
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
public class SupplierRequestEntity {

    @Id
    private String id;
    private String supplierName;
    private List<MaterialEntity> materialList;
}
