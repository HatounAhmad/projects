/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs342_project;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author htoun
 */

public class UserSearch extends JFrame implements ActionListener{
    
    private JLabel depStation, arriveStation, numPassengers;
    private JComboBox depList, arrList;
    private JButton SearchButton, CancelButton;
    private JTextField numP;
    private String cities[] = {"Buraydah", "Unayzah", "Bukairiyah", "Al Badaya", "Oyun Al Jiwaa", "Al Khabraa", "Ar Rass"};
    private JOptionPane eMessage;
    public BufferedReader ReadTrain;
    public String array[], line, departure, arrival;
    public String ID, maxPass;
    
    
    
    public UserSearch(){
        JPanel panel1 = (JPanel)getContentPane();
        panel1.setLayout(null);
        setTitle("Search A Train");
        setLocation(800,500);
        setSize(300, 170);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        depStation = new JLabel("Departure Station");
        depStation.setBounds(0, 0, 110, 25);
        panel1.add(depStation);
        
        depList = new JComboBox(cities);
        depList.setBounds(130, 0, 148, 25);
        panel1.add(depList);
        
        arriveStation = new JLabel("Arrival Station");
        arriveStation.setBounds(0, 25, 110, 25);
        panel1.add(arriveStation);
        
        arrList = new JComboBox(cities);
        arrList.setBounds(130, 25, 148, 25);
        panel1.add(arrList);
        
        numPassengers = new JLabel("Number of Passengers");
        numPassengers.setBounds(0, 50, 130, 25);
        panel1.add(numPassengers);
        
        numP = new JTextField("");
        numP.setBounds(130, 50, 148, 25);
        panel1.add(numP);
        
        SearchButton = new JButton("Search");
        SearchButton.setBounds(50, 80, 80, 25);
        panel1.add(SearchButton);
        
        CancelButton = new JButton("Cancel");
        CancelButton.setBounds(130, 80, 80, 25);
        panel1.add(CancelButton);
        
        SearchButton.addActionListener(this);
        CancelButton.addActionListener(this);
        this.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String str1 = String.valueOf(depList.getSelectedItem());
        String str2 = String.valueOf(arrList.getSelectedItem());
        String mp = numP.getText();
     
        int flag = 0;
        
          if(e.getSource() == CancelButton){
            System.exit(0);
        }
      else  if(e.getSource() == SearchButton){
          if(str1.equals(str2)||mp.equals("")){
                //check inputs dialog
                System.out.println("check input");
                JOptionPane.showMessageDialog(null, "Check inputs!", "Input Error", JOptionPane.ERROR_MESSAGE); //
            }
          else  if(!str1.equals(str2)){
                try{
                    
                    ReadTrain = new BufferedReader(new FileReader("TrainFile.txt"));
                    while((line = ReadTrain.readLine()) != null){ //Train constuctor(String id, int mxP, String dest, String dep)
                        array = line.split(";");
                        ID = array[0];
                        System.out.println("ID = " + ID);
                        departure = array[3];
                        System.out.println("departure = " + departure);
                        arrival = array[2];
                        System.out.println("Arrival = " + arrival);
                        maxPass = array[1];
                        System.out.println("Max Passengers = " + maxPass);
                        if(departure.equals(str1) && arrival.equals(str2)){
                            int maxPassengers=Integer.parseInt(maxPass);
                            int passengers=Integer.parseInt(numP.getText()); 
                              if(passengers>maxPassengers){ 
                            JOptionPane.showMessageDialog(null, "Check number of passengers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                                flag=2; break;
                            }  
                                else {
                                Reservation res1 = new Reservation(ID,departure,arrival,maxPass,mp);
                               //    res1.show();
                                    flag = 0;
                                     break;          }
                           
                      }// end if
                        else if(!departure.equals(str1) || !arrival.equals(str2)){
                            System.out.println("Train not found");
                            flag = 1;
                        }
                    }
                        
                         
                        
                 
                
            } catch (FileNotFoundException ex) {
                    System.out.println("FileNotFound Exception");
              } catch (IOException ex) {
                    System.out.println("IO Exception");
              }
           
            
            if(flag == 1){
                //dialog: not found
                System.out.println(flag);
                JOptionPane.showMessageDialog(null, "No Train Exists", "Results not found", JOptionPane.WARNING_MESSAGE);
            }
        }
       
    }
       else if(e.getSource() == CancelButton){
            System.exit(0);
        }
     
    }
    public static void main(String[] args) {
        UserSearch r= new UserSearch();
        
    }
}