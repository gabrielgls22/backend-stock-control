package com.xts.stock.control.dataprovider.writeoff.entity;

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
@Document("write_off")
public class WriteOffEntity {

    @Id
    @JsonProperty("writeOffDate")
    private String id;

    private List<WriteOffDetailsEntity> writeOffList;
}
