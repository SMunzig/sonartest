package applikation;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Applikation {

    public static void main(String args[]) {
        // Creating the connection using MariaDB DB
        String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mariadb://localhost:3306/usrdb";

        // User and password to access DB
        //String user = "root";
        //String pass = "root";
        
        //Anmelden stattdessen
        Scanner k = new Scanner(System.in);
        System.out.print("Login \nUsername: ");
        String user = k.next();
        System.out.print("Password: ");
        String pass = k.next();

        DbExecution execute = new DbExecution();
        execute.connectToDB(JDBC_DRIVER, url, user, pass);

        while(true) {
	        System.out.print("1. User anlegen \n2. Passwort überprüfen \n3. User-Liste \n4. Beenden\nAuswahl: ");
	        int option = k.nextInt();
	
	        if (option == 1) {
	        	System.out.println("Eingabe der User-Daten");
	            System.out.println("Username des neuen Users: ");
	            String name = k.next();
	            System.out.println("\nPassword des neuen Users: ");
	            String password = k.next();
	            execute.newUser(name, password);
	        } else if (option == 2) {
	        	System.out.println("Eingabe des Users für die Passwortprüfung");
	            System.out.println("\nUsername: ");
	            String name = k.next();
	            execute.getPass(name);
	        } else if (option == 3) {
	        	execute.getUsers();
	        } else {
	        	System.out.println("Die Applikation wird beendet");
	        	execute.disconnect();
	        	k.close();
	            System.exit(0);
	        }
        }
    }
}
