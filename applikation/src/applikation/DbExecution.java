package applikation;

import java.sql.*;

public class DbExecution {
    //String JDBC_DRIVER;
    //String url;
    Connection con = null;

    // Username and password to access DB
    // Custom initialization
    //String user;
    //String pass;

    public DbExecution() {
        //this.JDBC_DRIVER = driver;
        //this.url = url;
        //this.user = user;
        //this.pass = password;
    }
    
    public void connectToDB(String driver, String url, String user, String password) {
    	try {
	    	// Registering drivers
	        Class.forName(driver);	
	        // Reference to connection interface
	        con = DriverManager.getConnection(url, user, password);        
	       System.out.println("Es wurde sich erfolgreich mit der Datenbank verbunden");
	       
    	} catch (Exception ex) {
            // Display message when exceptions occurs
    		System.out.println("Fehler: Es konnte sich nicht mit der Datenbank nicht verbunden werden\n Überprüfe, ob die Login-Daten korrekt sind und die Datenbank zugänglich ist");
            System.exit(0);
    		//System.err.println(ex);
        }
    }
    
    public void disconnect() {
    	try {
    		con.close();
    	} catch (Exception ex) {
    		System.err.println(ex);
    	}
    }
    
    public void newUser(String newuser, String newpass) {
        String sql = String.format("insert user values('%s', '%s')", newuser, newpass);

        // Connection class object
        //Connection con = null;

        // Try block to check for exceptions
        try {

            // Registering drivers
            //Class.forName(JDBC_DRIVER);

            // Reference to connection interface
            //con = DriverManager.getConnection(url, this.user, this.pass);

            // Creating a statement
            Statement st = con.createStatement();

            // Executing query
            int m = st.executeUpdate(sql);
            if (m == 1)
                System.out.printf(
                        "Der User %s wurde erfolgreich hinzugefügt \n", newuser);
            else
                System.out.println("Der User konnte nicht hinzugefügt werden");

            // Closing the connections
            //con.close();
        }

        // Catch block to handle exceptions
        catch (Exception ex) {
            // Display message when exceptions occurs
            System.err.println(ex);
        }
    }

    public void getPass(String puser) {
        String sql = String.format("select password from user where name='%s'", puser);

        //Connection con = null;

        // Try block to check for exceptions
        try {

            // Registering drivers
            //Class.forName(JDBC_DRIVER);

            // Reference to connection interface
            //con = DriverManager.getConnection(url, this.user, this.pass);

            // Creating a statement
           Statement st = con.createStatement();

            // Executing query
            ResultSet rs = st.executeQuery(sql);
            //while (rs.next()) {
            //	  String x = rs.getString(1);
            //	  System.out.println(x);
            //}
            rs.next();
            if(rs.getString(1) == null) {
            	System.out.println("Passwort konnte nicht abgerufen werden oder es wurde kein Passwort für diesen User festgelegt.");
            }
            else {
            System.out.printf("Passwort vom User ist: %s \n", rs.getString(1));
            }

            // Closing the connections
            //con.close();
        }

        // Catch block to handle exceptions
        catch (Exception ex) {
            // Display message when exceptions occurs
        	System.out.println("Fehler: Bei der Passwortabfrage ist etwas schiefgelaufen \nÜberprüfe, ob der User tatsächlich existiert");
            //System.err.println(ex);
        }
    }
    
    public void getUsers() {
        String sql = String.format("select name from user");

        //Connection con = null;

        // Try block to check for exceptions
        try {

            // Registering drivers
            //Class.forName(JDBC_DRIVER);

            // Reference to connection interface
            //con = DriverManager.getConnection(url, this.user, this.pass);

            // Creating a statement
           Statement st = con.createStatement();

            // Executing query
            System.out.println("Die Datenbank enthält folgende User: ");
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
            	  String x = rs.getString(1);
            	  System.out.println(x);
            }

            // Closing the connections
            //con.close();
        }

        // Catch block to handle exceptions
        catch (Exception ex) {
            // Display message when exceptions occurs
        	System.out.println("Fehler: Bei der Userabfrage ist etwas schiefgelaufen");
            //System.err.println(ex);
        }
    }
    
    
}
