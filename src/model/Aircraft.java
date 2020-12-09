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
public class Aircraft implements Serializable{
    private static final long serialVersionUID = -6500665823330706018L;
    private int aircraftId;
    private String aircraftName;
    private String model;
    private int seatNumber;
    private String airbrand;

    public Aircraft() {
    }

    public Aircraft(String aircraftName, String model, int seatNumber, String airbrand) {
        this.aircraftName = aircraftName;
        this.model = model;
        this.seatNumber = seatNumber;
        this.airbrand = airbrand;
    }
    
    public Aircraft(int aircraftId, String aircraftName, String model, int seatNumber, String airbrand) {
        this.aircraftId = aircraftId;
        this.aircraftName = aircraftName;
        this.model = model;
        this.seatNumber = seatNumber;
        this.airbrand = airbrand;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getAirbrand() {
        return airbrand;
    }

    public void setAirbrand(String airbrand) {
        this.airbrand = airbrand;
    }


    
    
}
