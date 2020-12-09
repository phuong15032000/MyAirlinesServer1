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
public class FlightRoute implements Serializable{
    private static final long serialVersionUID = -6500665823330706018L;
    private int flightRouteId;
    private String departurePlace;
    private String arrivalPlace;
    private int standardPrice;
    
    public FlightRoute() {
    }

    public int getFlightRouteId() {
        return flightRouteId;
    }

    public FlightRoute(String departurePlace, String arrivalPlace, int standardPrice) {
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.standardPrice = standardPrice;
    }

    public void setFlightRouteId(int flightRouteId) {
        this.flightRouteId = flightRouteId;
    }

    

    public int getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(int standardPrice) {
        this.standardPrice = standardPrice;
    }

    public FlightRoute(int flightRouteId, String departurePlace, String arrivalPlace, int standardPrice) {
        this.flightRouteId = flightRouteId;
        this.departurePlace = departurePlace;
        this.arrivalPlace = arrivalPlace;
        this.standardPrice = standardPrice;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    
}
