package main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import gui.Login;
import java.awt.EventQueue;

/**
 *
 * @author tmiller
 */
public class TimeTrackerClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login=new Login();
                login.setVisible(true);
            }
        });
    }
}
