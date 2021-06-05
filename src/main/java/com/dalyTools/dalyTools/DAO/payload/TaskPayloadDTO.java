package com.dalyTools.dalyTools.DAO.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskPayloadDTO {

    @JsonProperty(value = "date")
    private String date;

    @JsonProperty(value="priority")
    private int priority;

    @JsonProperty(value="task")
    private String task;
}
