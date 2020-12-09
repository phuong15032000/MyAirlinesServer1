/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.addFlightRouteFunction.DB_PASS;
import static function.addFlightRouteFunction.DB_URL;
import static function.addFlightRouteFunction.DB_USER;
import static function.addFlightRouteFunction.JDBC_DRIVER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aircraft;
import model.Airport;
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addFlightFunc {

    private static Connection connection = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/myairlines";
    static final String DB_USER = "root";
    static final String DB_PASS = "admin";
    addFlightRouteFunction func = new addFlightRouteFunction();

    public String getDepartureByRouteId(int id) throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        FlightRoute fr = new FlightRoute();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM flightroute where routeId = '" + id + "'");
            ResultSet rs = (ResultSet) stmt.executeQuery();
            while (rs.next()) {
                fr.setFlightRouteId(rs.getInt("routeId"));
                fr.setDeparturePlace(rs.getString("departurePlaceId"));
                fr.setArrivalPlace(rs.getString("arrivalPlaceId"));
                fr.setStandardPrice(rs.getInt("standardPrice"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        int intDepart = Integer.parseInt(fr.getDeparturePlace());
        return func.getAirportById(intDepart).getCityName();
    }

    public String getArrivalByRouteId(int id) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        FlightRoute fr = new FlightRoute();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM flightroute where routeId = '" + id + "'");
            ResultSet rs = (ResultSet) stmt.executeQuery();
            while (rs.next()) {
                fr.setFlightRouteId(rs.getInt("routeId"));
                fr.setDeparturePlace(rs.getString("departurePlaceId"));
                fr.setArrivalPlace(rs.getString("arrivalPlaceId"));
                fr.setStandardPrice(rs.getInt("standardPrice"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        int intDepart = Integer.parseInt(fr.getArrivalPlace());
        return func.getAirportById(intDepart).getCityName();
    }

    public Aircraft getAircraftById(String id) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Aircraft ac = new Aircraft();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM aircraft where airCraftId = '" + id + "'");
            ResultSet rs = (ResultSet) stmt.executeQuery();
            while (rs.next()) {
                ac.setAircraftId(rs.getInt("airCraftId"));
                ac.setAirbrand(rs.getString("airBrandId"));
                ac.setAircraftName(rs.getString("airCraftName"));
                ac.setModel(rs.getString("model"));
                ac.setSeatNumber(rs.getInt("seatNumber"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return ac;
    }
}
