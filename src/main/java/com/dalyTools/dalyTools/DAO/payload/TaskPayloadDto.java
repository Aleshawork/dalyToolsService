package com.dalyTools.dalyTools.DAO.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskPayloadDto {

    @JsonProperty("date")
    private String date;

    @JsonProperty("fr_id")
    private Integer fr_id;
}
