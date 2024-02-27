/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs342_project;
//http://ucanaccess.sourceforge.net/site.html
//https://webdevdesigner.com/q/manipulating-an-access-database-from-java-without-odbc-35914/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author htoun
 */
public class CS342_PROJECT extends JFrame implements ActionListener {
private JLabel id, arrivalST, departureST, passengersMax;
    private JButton addButton, cancelButton;
    private JComboBox arrivalList, departureList;
    private JTextField idField, psngField;
    private String cities[] = {"Buraydah", "Unayzah", "Bukairiyah", "Al Badaya", "Oyun Al Jiwaa", "Al Khabraa", "Ar Rass"};
    BufferedWriter trainsFile;
    static Train trainArray[] = new Train[60];
    static int trainCount = 0;
    
    public CS342_PROJECT(){
        
        this.setTitle("Add Train");
        this.setSize(400, 250);
        JPanel panel1 = (JPanel)getContentPane();
        panel1.setLayout(null);
       
        
        id = new JLabel("Train ID:");
        id.setBounds(0, 0, 80, 25);
        panel1.add(id);
        
        idField = new JTextField("");
        idField.setBounds(150, 0,  100, 25);
        panel1.add(idField);
        
        departureST = new JLabel("Departure Station:");
        departureST.setBounds(0, 30, 110, 25);
        panel1.add(departureST);
        
        departureList = new JComboBox(cities);
        departureList.setBounds(150, 30, 100, 25);
        panel1.add(departureList);
        
        
        arrivalST = new JLabel("Arrival Station:");
        arrivalST.setBounds(0, 60, 100, 25);
        panel1.add(arrivalST);
        
        arrivalList = new JComboBox(cities);
        arrivalList.setBounds(150, 60, 100, 25);
        panel1.add(arrivalList);
        
        passengersMax = new JLabel("Max Passengers:");
        passengersMax.setBounds(0, 90, 100, 25);
        panel1.add(passengersMax);
        
        psngField = new JTextField("");
        psngField.setBounds(150, 90,  100, 25);
        panel1.add(psngField);
        
        addButton = new JButton("Add");
        addButton.setBounds(60, 120, 75, 25);
        addButton.addActionListener(this);
        panel1.add(addButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBounds(140, 120, 80, 25);
        
        panel1.add(cancelButton);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
       
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String str0 = idField.getText();
        String str1 = String.valueOf(departureList.getSelectedItem());
        String str2 = String.valueOf(arrivalList.getSelectedItem());
        String str3 =psngField.getText();
        int intgr2;
        Train enteredTrain,c;
        
        if(e.getSource() == addButton){
            
        try{
             FileWriter w= new FileWriter("TrainFile.txt",true);
             PrintWriter file=new PrintWriter(w); 
             ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream("TrainData.txt",true));
             FileInputStream f = new FileInputStream("TrainData.txt");
             ObjectInputStream readfile= new ObjectInputStream(f);
             
           if(str0.equals("") || str3.equals("")){
                    JOptionPane.showMessageDialog(null, "Check inputs!", "Input Error", JOptionPane.ERROR_MESSAGE);
                     System.out.println("Error: check inputs");}
             else  
               if(str1.equals(str2) ){ // dept==arraival
                    System.out.println("Error: check inputs");
                    JOptionPane.showMessageDialog(null, "Check inputs!", "Input Error", JOptionPane.ERROR_MESSAGE);}
          
            else {
             intgr2 = Integer.parseInt(str3);
                do{
                    c=(Train)readfile.readObject();
                    if(!c.getID().equals(str0) ) {
                   enteredTrain =new Train(str0, intgr2, str2, str1);
                   obj.writeObject(enteredTrain);
                   file.println(str0+";"+ intgr2+";"+str2+ ";"+str1);
                     idField.setText("");
                        psngField.setText("");
                    JOptionPane.showMessageDialog(null, "a new Train is added"," ",JOptionPane.INFORMATION_MESSAGE);}
                else { JOptionPane.showMessageDialog(null, "Train exists"," ",JOptionPane.ERROR_MESSAGE);
                }}while(f.available()>1);
                 /*  if(trainCount == 0){
                        
                        trainArray[trainCount++] = enteredTrain;
                        trainCount++;
                        System.out.println("Array was empty, element added " + trainCount);
                         
                      
                       

                   }
                   
                   else {
                    for(int i = 0; i < trainCount; i++){
                        
                       if(trainArray[i].getID().equals(str0)){
                            JOptionPane.showMessageDialog(null, "Train exists"," ",JOptionPane.ERROR_MESSAGE);
                                break;}
                       else{
                                trainArray[trainCount++] = new Train(str0, intgr2, str2, str1);
                                System.out.println("Array not empty, element added " + trainCount);
                   JOptionPane.showMessageDialog(null, "a new Train is added"," ",JOptionPane.INFORMATION_MESSAGE);
                                 idField.setText("");
                                   psngField.setText("");
                                break; }   
                       
                    }   
                   } */           
                   
             } }      
        
        catch(FileNotFoundException err){
            System.out.println("FileNotFoundException !");                 
        }
        catch(IOException err){
              System.out.println("IOException!! ");      
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(CS342_PROJECT.class.getName()).log(Level.SEVERE, null, ex);
            }}
        
        else if(e.getSource() == cancelButton){
            //AddToFile();
          
            System.exit(0);  
     } }
    
    
public static void main(String[] args) {
      CS342_PROJECT   n= new CS342_PROJECT();
      
}

}