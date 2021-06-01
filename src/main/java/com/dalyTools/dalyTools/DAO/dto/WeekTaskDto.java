package com.dalyTools.dalyTools.DAO.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.net.DatagramSocketImpl;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@Data
public class WeekTaskDto {

    private HashMap<Date, ArrayList<String>> weekmap;
}
