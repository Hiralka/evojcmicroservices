/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hiraru.weather.model;

import java.util.ArrayList;

/**
 *
 * @author hiraru
 */
import lombok.Data;

@Data
public class Root {
    Coord coord;
    ArrayList weather;
    String base;
    Main main;
    int visibility;
    Wind wind;
    Clouds clouds;
    int dt;
    Sys sys;
    int timezone;
    int id;
    String name;
    int cod;
}
