package com.dalyTools.dalyTools.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class AllTaskDto {
    private Date date;
    private Map<Integer,String> mapOfTask;
}
