/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbweekend;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class MainController implements Initializable {
    
    @FXML
    private Label label;
    @FXML Button btn_connectdb;
    @FXML
    private Button btn_viewusers;
      Connection connection;
    @FXML
    private TextField input_one;
    @FXML
    private Button btnsavetodb;
    
    @FXML
    private void connectDb(ActionEvent event) {
       
        String dbName="db_weekend";
        String userName="root";
        String password="";

        try {
        connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
        label.setText("Connected to database");
        btn_connectdb.setText("connected to db");
        //JOptionPane.showMessageDialog(null, "Connected to database server"); 
        } catch (HeadlessException | SQLException e) {
             label.setText("Failed to connection failed");
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewUsers(ActionEvent event) {
        try {
            
            Statement st = connection.createStatement();
            String sql;
           sql = "SELECT*FROM usersone";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                String name = rs.getString("name");
                JOptionPane.showMessageDialog(null,name);
            }

        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,ex);
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveToDb(ActionEvent event) {
        
        try {
        String inputData = input_one.getText();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usersone VALUES(?)");
            preparedStatement.setString(1, inputData);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Data saved successfully");
            input_one.setText(null);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
