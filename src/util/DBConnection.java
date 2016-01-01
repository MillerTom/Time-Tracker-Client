/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tmiller
 */
public class DBConnection {

    public static Connection connect() {
        Connection conn=null;
        XMLHandler xmlhandler = new XMLHandler();
        String url = xmlhandler.getUrl()+":"+xmlhandler.getPort()+File.separator+xmlhandler.getSchema();
        String driver = xmlhandler.getDriver();
        String userName = xmlhandler.getUserName();
        String userPassword = xmlhandler.getPassword();
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, userName, userPassword);
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver Class Not Found!!!");
            return conn;
        } catch (IllegalAccessException e) {
            System.out.println("Connection Failed Due to: "+e.getMessage());
            return conn;
        } catch (InstantiationException e) {
            System.out.println("Connection Failed Due to: "+e.getMessage());
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection Failed Due to: "+e.getMessage());
            return conn;
        }
    }
}
