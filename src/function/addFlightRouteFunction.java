/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.addAirplaneFunc.DB_PASS;
import static function.addAirplaneFunc.DB_URL;
import static function.addAirplaneFunc.DB_USER;
import static function.addAirplaneFunc.JDBC_DRIVER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Airbrand;
import model.Airport;
import model.FlightRoute;

/**
 *
 * @author DELL
 */
public class addFlightRouteFunction {

    private static Connection connection = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/myairlines";
    static final String DB_USER = "root";
    static final String DB_PASS = "admin";

    public Airport getAirportById(int id) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Airport a = new Airport();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM airport where airportId = '" + id + "'");
            ResultSet rs = (ResultSet) stmt.executeQuery();
            while (rs.next()) {
                a.setAirportId(rs.getInt("airportId"));
                a.setAirportName(rs.getString("airportName"));
                a.setCityName(rs.getString("cityName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return a;
    }
}
