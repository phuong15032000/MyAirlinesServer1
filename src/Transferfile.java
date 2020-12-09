
import function.addAirplaneFunc;
import function.addFlightFunc;
import function.addFlightRouteFunction;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Airbrand;
import model.Aircraft;
import model.Airport;
import model.Flight;
import model.FlightRoute;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
class transferfile extends Thread implements Serializable {

    private static Connection connection = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/myairlines";
    static final String DB_USER = "root";
    static final String DB_PASS = "admin";

    static Socket ClientSoc, dataSoc;
    static ServerSocket DataSoc;
    DataInputStream dinput;
    DataOutputStream doutput;

    transferfile(Socket soc, ServerSocket datasoc) {
        try {
            ClientSoc = soc;
            DataSoc = datasoc;
            dinput = new DataInputStream(ClientSoc.getInputStream());
            doutput = new DataOutputStream(ClientSoc.getOutputStream());
            System.out.println("FTP Client Connected ...");
            String use = dinput.readUTF();
            if (use.compareTo("test") == 0) {
                String pass = dinput.readUTF();
                if (pass.compareTo("test") == 0) {
                    doutput.writeUTF("Success");
                    System.out.println("User logged in successfully");

                } else {
                    doutput.writeUTF("Failure");
                }

            } else {
                doutput.writeUTF("Failure");
            }
            start();

        } catch (Exception ex) {
        }
    }

    void addAirport() throws Exception {

//        String cityName = dinput.readUTF();
//        String airportName = dinput.readUTF();
        ObjectInputStream objectInput = new ObjectInputStream(ClientSoc.getInputStream());
        Object object;
        Airport a = new Airport();
        object = objectInput.readObject();
        a = (Airport) object;

        System.out.println("da nhan dc 2 du lieu");
        String query = ("insert into airport (airportName, cityName) values('" + a.getAirportName() + "','" + a.getCityName() + "')");
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            System.out.println("da nhan dc lenh chay");
            doutput.writeUTF("addAirportSuc");
        } catch (ClassNotFoundException e) {
            doutput.writeUTF("addAirportFail");
            e.printStackTrace();
        } catch (SQLException ex) {
            doutput.writeUTF("addAirportFail");
        } finally {
            connection.close();
        }
    }

    void login() throws IOException, SQLException {

        String username = dinput.readUTF();
        String password = dinput.readUTF();

        System.out.println("da nhan dc du lieu dang nhap username="+username+"pass="+password);
        String query = "select * from admin where username = ? and password= ?";
        
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = (ResultSet) stmt.executeQuery();
            int numberRow = 0;
            while (rs.next()) {
                //doutput.writeUTF("success");
                System.out.println("co 1 truong");
                numberRow++;
            }
            if (numberRow != 0) {doutput.writeUTF("success"); System.out.println("co");}
            if (numberRow == 0) {doutput.writeUTF("fail"); System.out.println("khong");}
        
    }

    void addAirplane() throws Exception {
//        String aircraftName = dinput.readUTF();
//        String airlineBrandId = dinput.readUTF();
//        String model = dinput.readUTF();
//        String seatNumber = dinput.readUTF();
        ObjectInputStream objectInput = new ObjectInputStream(ClientSoc.getInputStream());
        Object object;
        Aircraft a = new Aircraft();
        object = objectInput.readObject();
        a = (Aircraft) object;

        System.out.println("da nhan dc du lieu may bay");
        String query = "INSERT INTO aircraft (airBrandId, airCraftName, model, seatNumber) VALUES(?, ?, ?, ?)";
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, a.getAirbrand());
            preparedStmt.setString(2, a.getAircraftName());
            preparedStmt.setString(3, a.getModel());
            preparedStmt.setInt(4, a.getSeatNumber());
            preparedStmt.execute();
            connection.close();
            System.out.println("da nhan dc lenh chay");
            doutput.writeUTF("addAirplaneSuc");
        } catch (ClassNotFoundException e) {
            doutput.writeUTF("addAirplaneFail");
            e.printStackTrace();
        } catch (SQLException ex) {
            doutput.writeUTF("addAirplaneFail");
        } finally {

            connection.close();
        }

    }

    void addFlightRoute() throws IOException, ClassNotFoundException, SQLException {
        FlightRoute fr = new FlightRoute();
//        fr.setDeparturePlace(dinput.readUTF());
//        fr.setArrivalPlace(dinput.readUTF());
//        fr.setStandardPrice(dinput.readInt());
        ObjectInputStream objectInput = new ObjectInputStream(ClientSoc.getInputStream());
        Object object;
        object = objectInput.readObject();
        fr = (FlightRoute) object;
        System.out.println("da nhan dc du lieu duong bay");
        String query = "INSERT INTO flightroute (departurePlaceId, arrivalPlaceId, standardPrice) VALUES( ?, ?, ?)";
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, fr.getDeparturePlace());
            preparedStmt.setString(2, fr.getArrivalPlace());
            preparedStmt.setInt(3, fr.getStandardPrice());
            preparedStmt.execute();
            connection.close();
            System.out.println("da nhan dc lenh chay");
            doutput.writeUTF("addFlightRouteSuc");
        } catch (ClassNotFoundException e) {
            doutput.writeUTF("addFlightRouteFail");
            e.printStackTrace();
        } catch (SQLException ex) {
            doutput.writeUTF("addFlightRouteFail");
        } finally {

            connection.close();
        }
    }

    void addFlight() throws IOException, ClassNotFoundException, SQLException {
        Flight flight = new Flight();
        ObjectInputStream objectInput = new ObjectInputStream(ClientSoc.getInputStream());
        Object object;
        object = objectInput.readObject();
        flight = (Flight) object;

        System.out.println("da nhan dc du lieu chuyen bay");
        String query = "INSERT INTO flight (departureTime, arrivalTime, totalSeat, availableSeat, orderedSeat, routeId, airCraftId) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, flight.getDepartureTime());
            stmt.setString(2, flight.getArrivalTime());
            stmt.setInt(3, flight.getTotalSeats());
            stmt.setInt(4, flight.getAvailableSeats());
            stmt.setInt(5, flight.getOrderedSeats());
            stmt.setInt(6, flight.getFlightRouteId());
            stmt.setInt(7, flight.getAircraftId());
            stmt.execute();
            connection.close();
            System.out.println("da nhan dc lenh chay");
             
        } catch (ClassNotFoundException e) {
            doutput.writeUTF("addFlightFail");
            e.printStackTrace();
        } catch (SQLException ex) {
            doutput.writeUTF("addFlightFail");
        } finally {

            connection.close();
        }
    }

    void getListAirport() throws SQLException {
        System.out.println("da nhan dc lenh getlistairport");
        ArrayList<Airport> airportLists = new ArrayList<Airport>();
        String query = ("select * from airport");
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            System.out.println("da ket noi dc sql");
            ResultSet rs = (ResultSet) preparedStmt.executeQuery();
            while (rs.next()) {
                Airport a = new Airport();
                a.setAirportId(rs.getInt("airportId"));
                a.setAirportName(rs.getString("airportName"));
                a.setCityName(rs.getString("cityName"));
                airportLists.add(a);
                //System.out.println(a.getAirportName());
            }
            System.out.println("da lay dc listairport");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connection.close();
        }

        ObjectOutputStream objectOutput;
        try {
            objectOutput = new ObjectOutputStream(ClientSoc.getOutputStream());

            objectOutput.writeObject(airportLists);

            System.out.println("da gui list len client");
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getListAircraft() throws SQLException, ClassNotFoundException {
        System.out.println("da nhan dc lenh getListAircraft");
        List<Aircraft> aircraftList = new ArrayList<Aircraft>();
        String query = ("select * from aircraft");
        addAirplaneFunc f = new addAirplaneFunc();
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            System.out.println("da ket noi dc sql");
            ResultSet rs = (ResultSet) preparedStmt.executeQuery();
            while (rs.next()) {
                Aircraft a = new Aircraft();
                a.setAircraftId(rs.getInt("airCraftId"));
                String temp = rs.getString("airBrandId");
                a.setAirbrand(f.getAirbrandNameById(temp).getAirbrandName());
                //a.setAirbrand(rs.getString("airBrandId"));
                a.setAircraftName(rs.getString("airCraftName"));
                a.setModel(rs.getString("model"));
                a.setSeatNumber(rs.getInt("seatNumber"));
                aircraftList.add(a);
                //System.out.println(a.getAirportName());
            }
            System.out.println("da lay dc listaircraft");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        List<Airbrand> airbrandList = new ArrayList<Airbrand>();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt1 = connection.prepareStatement("SELECT * FROM AirBrand");
            System.out.println("da ket noi dc sql");
            ResultSet rs1 = (ResultSet) preparedStmt1.executeQuery();
            while (rs1.next()) {
                Airbrand ab = new Airbrand();
                ab.setAirbrandId(rs1.getInt("airBrandId"));
                ab.setAirbrandName(rs1.getString("nameAirBrand"));
                airbrandList.add(ab);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        ObjectOutputStream objectOutput;
        try {
            objectOutput = new ObjectOutputStream(ClientSoc.getOutputStream());
            objectOutput.writeObject(aircraftList);

            objectOutput.writeObject(airbrandList);
            System.out.println("da gui list len client");
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getListFlightRoute() throws ClassNotFoundException, SQLException {
        List<FlightRoute> flightRouteList = new ArrayList<FlightRoute>();
        List<Airport> airportList = new ArrayList<Airport>();
        addFlightRouteFunction func = new addFlightRouteFunction();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt1 = connection.prepareStatement("SELECT * FROM flightroute");
            System.out.println("da ket noi dc sql");
            ResultSet rs1 = (ResultSet) preparedStmt1.executeQuery();
            while (rs1.next()) {
                FlightRoute f = new FlightRoute();
                f.setFlightRouteId(rs1.getInt("routeId"));
                f.setDeparturePlace(func.getAirportById(rs1.getInt("departurePlaceId")).getCityName());
                f.setArrivalPlace(func.getAirportById(rs1.getInt("arrivalPlaceId")).getCityName());
                f.setStandardPrice(rs1.getInt("standardPrice"));
                flightRouteList.add(f);
            }
            System.out.println("da lay dc list flightroute");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt = connection.prepareStatement("SELECT * FROM airport");
            System.out.println("da ket noi dc sql");
            ResultSet rs1 = (ResultSet) preparedStmt.executeQuery();
            while (rs1.next()) {
                Airport a = new Airport();
                a.setAirportId(rs1.getInt("airportId"));
                a.setAirportName(rs1.getString("airportName"));
                a.setCityName(rs1.getString("cityName"));
                airportList.add(a);
            }
            System.out.println("da lay dc list airport");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObjectOutputStream objectOutput;
        try {
            objectOutput = new ObjectOutputStream(ClientSoc.getOutputStream());
            objectOutput.writeObject(flightRouteList);
            objectOutput.writeObject(airportList);
            System.out.println("da gui list len client");
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            connection.close();
        }
    }

    void getListFLight() throws SQLException, ClassNotFoundException {
        List<Aircraft> aircraftList = new ArrayList<Aircraft>();
        List<FlightRoute> flightRouteList = new ArrayList<FlightRoute>();
        List<Flight> flightList = new ArrayList<Flight>();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt1 = connection.prepareStatement("SELECT * FROM aircraft");
            System.out.println("da ket noi dc sql");
            ResultSet rs = (ResultSet) preparedStmt1.executeQuery();
            while (rs.next()) {
                Aircraft ac = new Aircraft();
                ac.setAircraftId(rs.getInt("airCraftId"));
                ac.setAirbrand(rs.getString("airBrandId"));
                ac.setAircraftName(rs.getString("airCraftName"));
                ac.setModel(rs.getString("model"));
                ac.setSeatNumber(rs.getInt("seatNumber"));
                aircraftList.add(ac);
            }
            System.out.println("da lay dc list aircraft");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }

        addFlightRouteFunction func = new addFlightRouteFunction();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt1 = connection.prepareStatement("SELECT * FROM flightroute");
            System.out.println("da ket noi dc sql");
            ResultSet rs1 = (ResultSet) preparedStmt1.executeQuery();
            while (rs1.next()) {
                FlightRoute f = new FlightRoute();
                f.setFlightRouteId(rs1.getInt("routeId"));
                f.setDeparturePlace(func.getAirportById(rs1.getInt("departurePlaceId")).getCityName());
                f.setArrivalPlace(func.getAirportById(rs1.getInt("arrivalPlaceId")).getCityName());
                f.setStandardPrice(rs1.getInt("standardPrice"));
                flightRouteList.add(f);
            }
            System.out.println("da lay dc list flightroute");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }

        addFlightFunc func2 = new addFlightFunc();
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement preparedStmt1 = connection.prepareStatement("SELECT * FROM flight");
            System.out.println("da ket noi dc sql");
            ResultSet rs2 = (ResultSet) preparedStmt1.executeQuery();
            while (rs2.next()) {
                Flight fl = new Flight();
                fl.setDeparturePlace(func2.getDepartureByRouteId(rs2.getInt("routeId")));
                fl.setArrivalPlace(func2.getArrivalByRouteId(rs2.getInt("routeId")));
                fl.setFlightId(rs2.getInt("flightId"));
                fl.setDepartureTime(rs2.getString("departureTime"));
                fl.setArrivalTime(rs2.getString("arrivaltime"));
                fl.setTotalSeats(rs2.getInt("totalSeat"));
                fl.setAvailableSeats(rs2.getInt("availableSeat"));
                fl.setOrderedSeats(rs2.getInt("orderedSeat"));
                fl.setFlightRouteId(rs2.getInt("routeId"));
                fl.setAircraftId(rs2.getInt("airCraftId"));
                flightList.add(fl);
            }
            System.out.println("da lay dc list flight");
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        ObjectOutputStream objectOutput;
        try {
            objectOutput = new ObjectOutputStream(ClientSoc.getOutputStream());
            objectOutput.writeObject(aircraftList);

            objectOutput.writeObject(flightRouteList);

            objectOutput.writeObject(flightList);
            System.out.println("da gui list len client");
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(transferfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {

        while (true) {
            try {

                //System.out.println("Waiting for Command ...");
                String Command = dinput.readUTF();
                if (Command.compareTo("addAirport") == 0) {
                    System.out.println("\taddAirport Command Received ...");
                    addAirport();
                    continue;
                } else if (Command.compareTo("getListAirport") == 0) {
                    System.out.println("\tgetListAirport Command Received ...");
                    getListAirport();
                    continue;
                } else if (Command.compareTo("getListAircraft") == 0) {
                    System.out.println("\tgetListAircraft Command Received ...");
                    getListAircraft();
                    continue;
                } else if (Command.compareTo("addAirplane") == 0) {
                    System.out.println("\taddAirplane Command Received ...");
                    addAirplane();
                    continue;
                } else if (Command.compareTo("getListFlightRoute") == 0) {
                    System.out.println("\tgetListFlightRoute Command Received ...");
                    getListFlightRoute();
                    continue;
                } else if (Command.compareTo("addFlightRoute") == 0) {
                    System.out.println("\taddFlightRoute Command Received ...");
                    addFlightRoute();
                    continue;
                } else if (Command.compareTo("getListFLight") == 0) {
                    System.out.println("\tgetListFLight Command Received ...");
                    getListFLight();
                    continue;
                } else if (Command.compareTo("addFlight") == 0) {
                    System.out.println("\taddFLight Command Received ...");
                    addFlight();
                    continue;
                } else if (Command.compareTo("login") == 0) {
                    System.out.println("\tlogin Command Received ...");
                    login();
                    continue;
                }

            } catch (Exception ex) {
            }
        }
    }

}
