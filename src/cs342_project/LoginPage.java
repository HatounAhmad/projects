package cs342_project;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/*
 * @author Htoun
 */

public class LoginPage extends JFrame implements ActionListener{
    private JLabel username1, password1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton enterButton, cancelButton;
    
    public LoginPage(){
        setLocation(800,500);
        JPanel panel1 = (JPanel)getContentPane();
        panel1.setLayout(null);
        this.setTitle("Login Pgae");
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
   String str0 = passwordField.getText();
   String str1 = usernameField.getText();
   int flag=0;
                 
        if(e.getSource() == enterButton){
            
             if(str0.equals("") || str1.equals("")){
            JOptionPane.showMessageDialog(null, "Check inputs!", "Input Error", JOptionPane.ERROR_MESSAGE);}
              //until we make a database, we need to make an if statement to check data
            //Admin
             else if(str1.startsWith("Admin")){
                 try {
                     
                     BufferedReader AdminFile = new BufferedReader (new FileReader("Admin.txt"));
                     String array[],line,userName,password;
                    
                 while((line=AdminFile.readLine())!=null){
                        array=line.split(";");
                         userName=array[0];
                         password=array[1]; 
                  if(str1.equals(userName)&&str0.equals(password)){
                             dispose();
                              AddTrain trainsWindow = new AddTrain();
                              break;
               }
                  else if(str1.equals(userName)&&!str0.equals(password)||!str1.equals(userName)&&str0.equals(password)){
                      flag=1;
                       break;
                      }
                            }
                   if(flag==1){
                     JOptionPane.showMessageDialog(null, "The username or password you typed is incorrect. Please try again.", "Input Error", JOptionPane.ERROR_MESSAGE);    
                      
                      }          

                      //while loop
                     AdminFile.close();
                
                 }
               
          catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException !!");
         } catch (IOException ex) {
            System.out.println("IOException !!");
         }
            }
             else{ 
                 try {
                     BufferedReader UserFile = new BufferedReader (new FileReader("Users.txt"));
                     String array[],line,userName,password;
                     
                 while((line=UserFile.readLine())!=null){
                        array=line.split(";");
                         userName=array[0];
                         password=array[1]; 
                    if(str1.equals(userName)&&str0.equals(password)){
                             dispose();
                             UserSearch window= new UserSearch("");  
                              break;
                    }
                    flag++;
                     } //while
                     UserFile.close();
                     System.out.println(flag);
                  if(flag==9){
                 JOptionPane.showMessageDialog(null, "The username or password you typed is incorrect. Please try again.", "Input Error", JOptionPane.ERROR_MESSAGE);    
                  }
                 }  
     catch (FileNotFoundException ex) {
          System.out.println(" FileNotFoundException !!");
     } catch (IOException ex) {
            System.out.println("IOException !!");    }
             } }
        
        else if(e.getSource() == cancelButton){
            passwordField.setText("");
            usernameField.setText("");
        }
    }
    public static void main(String[] args) {
        LoginPage window = new LoginPage();
    }
}