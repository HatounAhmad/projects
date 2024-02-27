/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs342_project;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author htoun
 */
public class Reservation  extends JFrame{
   private JLabel trainID,depStation, arriveStation, maxPassenger;
    private JTextField idNum,depText,arrivText,numPassengers;
    private int i,j;
    String updatePass;
    private JButton confirm;
    
    public Reservation(String ID,String departure,String arrival,String maxPass,String mp){
          setLocation(800,500);
         this.setTitle("Reservation Pgae");
        this.setSize(350, 200);
        trainID =new JLabel("Train ID ");
       depStation = new JLabel("Departure Station");
     arriveStation=new JLabel("Arrival Station");
     maxPassenger= new JLabel("Max Passengers ");
     
     idNum = new JTextField(ID);
    depText= new JTextField(departure);
    arrivText= new JTextField(arrival);
   numPassengers= new JTextField(maxPass);
     i=Integer.parseInt(maxPass);
     j=Integer.parseInt(mp);// number of passengers
     
     idNum.setEditable(false);
     depText.setEditable(false);
     arrivText.setEditable(false);
     numPassengers.setEditable(false);
     
     
   confirm = new JButton("Confirm");
   
       JPanel p1= new JPanel(new GridLayout(1,2));
       
        p1.add(trainID);
        p1.add(idNum);
        
        

        JPanel p2= new JPanel(new GridLayout(1,2));
        p2.add(depStation);
        p2.add(depText);
        
        
        
        JPanel p3=new JPanel(new GridLayout(1,2));
      p3.add( arriveStation); p3.add(arrivText);
       
       
       
        
        JPanel p4= new JPanel(new GridLayout(1,2));
          p4.add( maxPassenger);
          p4.add(numPassengers);
                
        JPanel p5=new JPanel(new FlowLayout(FlowLayout.CENTER));
        p5.add( confirm);
        JPanel p=(JPanel)this.getContentPane();
        p.setLayout(new GridLayout(5,1));
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        confirm.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae) {
       
        int flag=0;
        updatePass=""+(i-j); 
        
        
         while(flag<=0){
        Reservation reservWindow2 = new Reservation(trainID.getText(),depText.getText(),arrivText.getText(),updatePass,String.valueOf(j));
        JOptionPane.showMessageDialog(null, "Reservation is confirmed","Congratulations",JOptionPane.PLAIN_MESSAGE);
          reservWindow2.confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String str0=idNum.getText();
             
            System.exit(0);
            }
        });
         flag++;
         break;}
       
        } });
                
    

    
}
    public static void main(String[] args) {
       // Reservation r= new Reservation();
    }
}
