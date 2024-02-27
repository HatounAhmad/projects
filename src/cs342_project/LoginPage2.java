package cs342_project;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class DBconnection{
    Connection con ;
    DBconnection() throws ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
            System.out.println("connection ");
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!!!!!1");        
        }
    }
}

public class LoginPage2 extends JFrame implements ActionListener{
    private JLabel username1, password1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton enterButton, cancelButton;

    public LoginPage2(){
        setLocation(800,500);
        JPanel panel1 = (JPanel)getContentPane();
        panel1.setLayout(null);
        this.setTitle("Login Page");
        this.setSize(330, 150);
        username1 = new JLabel("Username:");
        username1.setBounds(1, 10, 75, 25);
        password1 = new JLabel("Password:");
        password1.setBounds(1, 40, 75, 25);
        usernameField = new JTextField("");
        usernameField.setBounds(130, 10, 150, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(130, 40, 150, 25);
        enterButton = new JButton("Enter");
        enterButton.setBounds(70,70,80,25);
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(160,70,80,25);

        panel1.add(username1);
        panel1.add(usernameField);
        panel1.add(password1);
        panel1.add(passwordField);
        panel1.add(enterButton);
        enterButton.addActionListener(this);
        panel1.add(cancelButton);
        cancelButton.addActionListener(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str0 = String.valueOf(passwordField.getPassword());
        String str1 = usernameField.getText();

        if(e.getSource() == enterButton){
            if(str0.equals("") || str1.equals("")){
                JOptionPane.showMessageDialog(null, "Check inputs!", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    DBconnection con=new DBconnection();
                    if(con==null)
                        System.out.println("not connect");
                    else{
                        System.out.println("connect");
                        String sql="select * from LOGIN ";
                        Statement st=con.createStatement();
                        ResultSet rs = st.executeQuery(sql); // Execute the query and obtain the result set
                        while(rs.next()){
                            String usern=rs.getString("username");
                            String pass=rs.getString("password");
                            if(usern.equals(str1)&&pass.equals(str0)){
                                JOptionPane.showMessageDialog(null, "success", "", JOptionPane.INFORMATION_MESSAGE);    
                                // You may want to consider breaking out of the loop once a match is found
                                AddTrain trainsWindow = new AddTrain();
                            } else {
                                System.out.println(str1+"  "+str0);
                                System.out.println("noo");
                            }
                        }
                    }
                } catch (SQLException ex) { 
                    Logger.getLogger(LoginPage2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginPage2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if(e.getSource() == cancelButton){
            passwordField.setText("");
            usernameField.setText("");
        }
    }

    public static void main(String[] args) {
        LoginPage2 window = new LoginPage2();
    }
}
