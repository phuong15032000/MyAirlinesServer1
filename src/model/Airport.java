/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Airport implements Serializable{
    private static final long serialVersionUID = -6500665823330706018L;
    private int airportId;
    private String airportName;
    private String cityName;

    public Airport() {
    }

    public Airport(int airportId, String airportName, String cityName) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.cityName = cityName;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    
    
}
