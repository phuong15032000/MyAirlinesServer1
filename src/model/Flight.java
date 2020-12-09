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
public class Flight implements Serializable{
    private static final long serialVersionUID = -6500665823330706018L;
    private int flightId;
    private String departureTime;
    private String arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private int orderedSeats;
    private int aircraftId;
    private int flightRouteId;
    private String departurePlace;
    private String arrivalPlace;

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
    public Flight() {
    }

    public Flight(int flightId, String departureTime, String arrivalTime, int totalSeats, int availableSeats, int orderedSeats, int aircraftId, int flightRouteId) {
        this.flightId = flightId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.orderedSeats = orderedSeats;
        this.aircraftId = aircraftId;
        this.flightRouteId = flightRouteId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getOrderedSeats() {
        return orderedSeats;
    }

    public void setOrderedSeats(int orderedSeats) {
        this.orderedSeats = orderedSeats;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public int getFlightRouteId() {
        return flightRouteId;
    }

    public void setFlightRouteId(int flightRouteId) {
        this.flightRouteId = flightRouteId;
    }
    
    
}
