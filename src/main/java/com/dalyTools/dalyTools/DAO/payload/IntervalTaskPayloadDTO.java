package com.dalyTools.dalyTools.DAO.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IntervalTaskPayloadDTO {
    @JsonProperty(value = "first")
    private String firstDate;

    @JsonProperty(value = "last")
    private String lastDate;
}
