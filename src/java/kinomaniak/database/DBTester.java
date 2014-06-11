/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.database;

import java.sql.Date;
import java.util.ArrayList;
import kinomaniak.beans.*;

/**
 *
 * @author Jakub
 */
public class DBTester {
    
    
    public static void main(String[] args){
        DBConnector connector = new DBConnector();
        connector.connect();
        System.out.println(connector.parser.load("Show"));
        ArrayList<Object> movs = connector.load("Movie", 2);
        if(movs.get(0) instanceof Movie){
            Movie m = (Movie)movs.get(0);
            System.out.println(m.getName());
            System.out.println(m.getGenre());
            System.out.println(m.getRating());
            System.out.println(m.getDesc());
            
        }else{
            System.out.println("Not a movie");
        }
        ArrayList<Object> times = connector.load("Time", 2);
        System.out.println(connector.parser.load("Time", 2));
        ((Movie)movs.get(0)).setDesc("Blablubel");
        System.out.println(connector.parser.update(movs.get(0)));
//        connector.update(movs.get(0));
        System.out.println(((Time)times.get(0)).getHour());
        Product pr = (Product)connector.parser.load(connector.getConnection(), "Product", 2).get(0);
        pr.setCount(pr.getCount()-1);
        System.out.println(pr.getCount());
        System.out.println(connector.parser.update(pr));
        Movie m = new Movie();
        m.setName("LOTR");
        m.setGenre("Fantasy");
        m.setDesc("Hello World!");
        m.setRating("Adult");
//        connector.save(m);
        
        ReportData rd = new ReportData();
        rd.setObjectId(1);
        rd.setType(2);
        rd.setTimestamp(new java.sql.Timestamp((new java.util.Date()).getTime()));
        rd.setUserId(1);
//        connector.save(rd);
        for(Object r: connector.parser.load(connector.getConnection(), "ReportData")){
            ReportData rx = (ReportData)r;
            System.out.println(rx);
        }
        
        
    }
}
