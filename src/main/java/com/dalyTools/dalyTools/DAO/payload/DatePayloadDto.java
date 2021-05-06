package com.dalyTools.dalyTools.DAO.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DatePayloadDto {

    @JsonProperty("date")
    private String date;
}
