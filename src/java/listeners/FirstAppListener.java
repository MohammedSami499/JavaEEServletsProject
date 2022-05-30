/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class FirstAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        java.sql.Statement st = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connect = DriverManager.getConnection("jdbc:derby://localhost:1527/loginDB", "root", "1234");
            st = connect.createStatement();
            sce.getServletContext().setAttribute("Stnt", st);
            System.out.println("Hello listner");
            
        }catch(Exception ex){
            ex.getMessage();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
