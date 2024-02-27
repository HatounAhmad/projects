/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs342_project;

/**
 *
 * @author htoun
 */
import java.io.*;

/*
 * @author xlena
 */
public class Train implements Serializable{
    private int MaxPassengers;
    private String destination, departure, trainID;
    
    public Train(String id, int mxP, String dest, String dep){
        trainID = id;
        MaxPassengers = mxP;
        destination = dest;
        departure = dep;
        
    }

    public String getID() {
        return trainID;
    }

    public int getMaxPassengers() {
        return MaxPassengers;
    }

    public String getDestination() {
        return destination;
    }

    public String getDeparture() {
        return departure;
    }
    
    
    @Override
    public String toString() {
        return trainID + ";" + departure + ";" + destination + ";" + MaxPassengers;}
}