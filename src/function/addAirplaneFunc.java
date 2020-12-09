/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Airbrand;

/**
 *
 * @author DELL
 */
public class addAirplaneFunc {

    private static Connection connection = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/myairlines";
    static final String DB_USER = "root";
    static final String DB_PASS = "admin";

    public Airbrand getAirbrandNameById(String id) throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Airbrand a = new Airbrand();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM airbrand where airBrandId = '" + id + "'");
            ResultSet rs = (ResultSet) stmt.executeQuery();
            while (rs.next()) {
                a.setAirbrandId(rs.getInt("airBrandId"));
                a.setAirbrandName(rs.getString("nameAirBrand"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(addAirplaneFunc.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();

        }
        return a;
    }

}
