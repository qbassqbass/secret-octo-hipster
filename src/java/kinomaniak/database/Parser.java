/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kinomaniak.beans.*;

/**
 *
 * @author Qbass
 */
public class Parser {
    
    public boolean save(Connection conn, Object obj){
        boolean done = false;
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(this.save(obj));            
            statement.close();
            done = true;
        } catch (SQLException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return done;
    }
    
    public ArrayList<Object> load(Connection conn, String type){
        return this.load(conn,type,-1);
    }
    /**
     *
     * @param conn
     * @param type
     * @param id
     * @return
     */
    public ArrayList<Object> load(Connection conn, String type, int id){
        ArrayList<Object> arr = new ArrayList<Object>();
        Object obj = null;
        ResultSet result;
        try {
            Statement statement = conn.createStatement();
            if(id==-1)
                result = statement.executeQuery(this.load(type));
            else
                result = statement.executeQuery(this.load(type, id));
            switch(type){
                case "Movie":
                    while(result.next()){
                        obj = new Movie(result.getInt("id"),result.getString("name"),result.getString("genere"),result.getString("rating"),result.getString("descr"));
                        arr.add(obj);
                    }
                    break;
                case "CRoom":
                    while(result.next()){
                        obj = new CRoom(result.getInt("id"));
                        arr.add(obj);
                    }
                    break;
                case "Attraction":
                    while(result.next()){
                        int idd = result.getInt("id");
                        String name = result.getString("name");
                        float price = result.getFloat("price");
                        obj = new Attraction(idd, name, price);
                        arr.add(obj);
                    }
                    break;
                case "GoldCard":
//                    cannot do anything... -.-
                    break;
                case "Product":
                    while(result.next()){
                        obj = new Product(result.getInt("id"), result.getString("name"), result.getInt("typ"), result.getFloat("price"), result.getInt("pcount"));
                        arr.add(obj);
                    }
                    break;
                case "Report":
//                    cannot do anything... -.-
                    break;
                case "Res":
                    while(result.next()){                        
                        int idres = result.getInt("id");
                        String nameres = result.getString("imienazwisko");
                        int showid = result.getInt("showid");
                        boolean checked = result.getBoolean("checked");
                        boolean ok = result.getBoolean("ok");
                        String seat = result.getString("seat");
//                        obj = new Res()
                        String seatString[] = seat.split(",");
                        int seats[][] = new int[seatString.length][2];
                        int i = 0;
                        for(String s : seatString){
                            seats[i][0] = Integer.valueOf(s.split(":")[0]);
                            seats[i][1] = Integer.valueOf(s.split(":")[1]);
                            i++;
                        }
                        obj = new Res(idres, nameres, showid, seatString.length, seats);
                        if(checked)((Res)obj).accept();
                        if(ok)((Res)obj).get();
                        arr.add(obj);
                    }
                    break;
                case "Show":
                    while(result.next()){
                        ArrayList<Object> movies;
                        ArrayList<Object> rooms;
                        ArrayList<Object> times;
                        int showid = result.getInt("id");
                        int movid = result.getInt("mov");
                        int roomid = result.getInt("room");
                        int timeid = result.getInt("timeid");
                        movies = this.load(conn, "Movie", movid);
                        rooms = this.load(conn, "CRoom", roomid);
                        times = this.load(conn, "Time", timeid);
                        obj = new Show(showid, (Movie)movies.get(0), (CRoom)rooms.get(0), (Time)times.get(0));
                        arr.add(obj);
//                        int i = 0;
//                        for(Object m : movies){
//                            Movie mm;
//                            if(m instanceof Movie) mm = (Movie)m;
//                            else return null;
//                            if(mm.getId() == movid){
//                                movid = i;
//                                break;
//                            }
//                            i++;
//                        }
//                        i = 0;
//                        for(Object r : rooms){
//                            CRoom cr;
//                            if(r instanceof CRoom) cr = (CRoom)r;
//                            else return null;
//                            if(cr.getID() == roomid){
//                                roomid = i;
//                                break;
//                            }
//                            i++;
//                        }
//                        i = 0;
//                        for(Object t : times){
//                            Time tm;
//                            if(t instanceof Time) tm = (Time)t;
//                            else return null;
////                            if(tm.get)
//                        }
//                        obj = new Show((Movie)movies.get(movid), (CRoom)rooms.get(roomid), result.getInt("timeid"));
                    }
                    break;
                case "Ticket":
//                    cannot do anything... -.-
                    break;
                case "User":
//                    cannot do anything... -.-
                    while(result.next()){
                        obj = new User(result.getInt("id"), result.getString("name"), result.getString("password"), result.getInt("utype"));
                        arr.add(obj);
                    }
                    break;
                case "Time":
                    while(result.next()){
                        obj = new Time(result.getInt("id"), result.getInt("thour"), result.getInt("tminute"),
                                        result.getByte("tday"), result.getInt("tmonth"), result.getInt("tyear"));
                        arr.add(obj);
                    }
                    break;
                case "ReportData":
                    while(result.next()){
                        ReportData rd = new ReportData();
                        rd.setId(result.getInt("id"));
                        rd.setObjectId(result.getInt("reference"));
                        rd.setType(result.getInt("type"));
                        rd.setUserId(result.getInt("userId"));
                        rd.setTimestamp(result.getTimestamp("timestamp"));
                        arr.add(rd);
                    }
                default:                    
//                    cannot do anything... -.-
            }
        } catch (SQLException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public void update(Connection conn, Object obj){
        try {
            Statement statement = conn.createStatement();
//            ResultSet result;
            statement.executeUpdate(this.update(obj));
        } catch (SQLException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String update(Object obj){
        String query = "";
        if(obj instanceof Movie){
            Movie mov = (Movie) obj;
            query = "UPDATE Movie SET name='" + mov.getName() +"', genere='" + mov.getGenre() + "', rating='" + mov.getRating() + "', descr='" + mov.getDesc() + "' WHERE id='"+ mov.getId()+"';";
        }else if(obj instanceof CRoom){
//            CRoom cr = (CRoom) obj;
//            query = "UPDATE Croom SET id='" + cr.getID() + "');";
        }else if(obj instanceof Attraction){
            Attraction at = (Attraction) obj;
            query = "UPDATE Attraction SET name='" + at.getName() + "', price='" + at.getPrice() + "' WHERE id="+at.getId()+";";
        }else if(obj instanceof Product){
            Product pr = (Product) obj;
            query = "UPDATE Product SET name='" + pr.getName() + "', typ='" + pr.getType() + "', price='" + pr.getPrice() + "', pcount='" + pr.getCount() + "' WHERE id="+pr.getId()+";";
        }else if(obj instanceof Report){
            Report rep = (Report) obj;
            
        }else if(obj instanceof Res){
            Res res = (Res) obj;
            query = "UPDATE Res SET name='" + res.getName() + "', showid='" + res.getShowID() + "', seat='" + res.formatSeatsSQL()+ "', checked=" + res.ischecked() + ", ok=" + res.isok() + " WHERE id="+res.getId()+";";
        }else if(obj instanceof Show){
            Show sh = (Show) obj;
            query = "UPDATE Show SET mov=" + sh.getMovie().getId() + ", room=" + sh.getRoom().getID() + ", timeid=" + sh.getCTime().getId()+ " WHERE id="+sh.getShowid()+";";
        }else if(obj instanceof Ticket){
            Ticket tick = (Ticket) obj;
            
        }else if(obj instanceof Time){
            Time tim = (Time) obj;
            // will not be implemented
        }else if(obj instanceof User){
            User usr = (User) obj;
            query = "UPDATE Users SET name='" + usr.getName() + "', password='" + usr.getPass() + "', utype='" + usr.getUType() + "' WHERE id="+usr.getId()+";";
        }else if(obj instanceof GoldCard){
            GoldCard gc = (GoldCard) obj;
            
        }else if(obj instanceof ReportData){
            ReportData rd = (ReportData) obj;
//            query = "UPDATE ReportData SET "
            //Reports are Read-Only
        }
        return query;
    }
    
    public String delete(String type, int id){
        String query = "";
        switch(type){
            case "Movie":
                query = "DELETE FROM Movie";
                break;
            case "CRoom":
                query = "DELETE FROM CRoom";
                break;
            case "Attraction":
                query = "DELETE FROM Attraction";
                break;
            case "GoldCard":
                query = "DELETE FROM Goldcard";
                break;
            case "Product":
                query = "DELETE FROM Product";
                break;
            case "Report":
                query = "DELETE FROM Report";
                break;
            case "Res":
                query = "DELETE FROM Res";
                break;
            case "Show":
                query = "DELETE FROM Shows";
                break;
            case "Ticket":
                query = "DELETE FROM Ticket";
                break;
            case "User":
                query = "DELETE FROM Users";
                break;
            case "Time":
                query = "DELETE FROM TimeDate";
                break;
            case "ReportData":
//                query = "DELETE FROM ReportData"; 
                // READ-ONLY
                break;
            default:
                query = "DELETE FROM Dummy";
        }
        query += " WHERE id='"+id+"'";
        return query;
    }
    
    public String save(Object obj){
        String query = "";
        if(obj instanceof Movie){
            Movie mov = (Movie) obj;
            query = "INSERT INTO Movie VALUES (NULL, '" + mov.getName() +"', '" + mov.getGenre() + "', '" + mov.getRating() + "', '" + mov.getDesc() + "');";
        }else if(obj instanceof CRoom){
            CRoom cr = (CRoom) obj;
            query = "INSERT INTO Croom VALUES (NULL, '" + cr.getID() + "');";
        }else if(obj instanceof Attraction){
            Attraction at = (Attraction) obj;
            query = "INSERT INTO Attraction VALUES (NULL, '" + at.getName() + "', '" + at.getPrice() + "');";
        }else if(obj instanceof Product){
            Product pr = (Product) obj;
            query = "INSERT INTO Product VALUES (NULL, '" + pr.getName() + "', '" + pr.getType() + "', '" + pr.getPrice() + "', '" + pr.getCount() + "');";
        }else if(obj instanceof Report){
            Report rep = (Report) obj;
            
        }else if(obj instanceof Res){
            Res res = (Res) obj;
            query = "INSERT INTO Res VALUES (NULL, '" + res.getName() + "', '" + res.getShowID() + "', '" + res.formatSeatsSQL()+ "', '" + res.ischecked() + "', '" + res.isok() + "');";
        }else if(obj instanceof Show){
            Show sh = (Show) obj;
            query = "INSERT INTO Show VALUES (NULL, '" + sh.getID() + "', '" + sh.getMovie().getId() + "', '" + sh.getRoom().getID() + "', '" + sh.getFormatted() + "');";
        }else if(obj instanceof Ticket){
            Ticket tick = (Ticket) obj;
            
        }else if(obj instanceof Time){
            Time tim = (Time) obj;
            // will not be implemented
        }else if(obj instanceof User){
            User usr = (User) obj;
            query = "INSERT INTO Users VALUES (NULL, '" + usr.getName() + "', '" + usr.getPass() + "', '" + usr.getUType() + "');";
        }else if(obj instanceof GoldCard){
            GoldCard gc = (GoldCard) obj;
            
        }else if(obj instanceof ReportData){
            ReportData rd = (ReportData) obj;
            query = "INSERT INTO ReportData VALUES (NULL, '"+rd.getUserId()+"', '"+rd.getType()+"', '"+rd.getTimestamp()+"', '"+rd.getObjectId()+"');";
        }
        return query;
    }
    
    public String load(String type, int id){
        String query = "";
        query = load(type)+" WHERE id="+id;
        return query;
    }
    
    public String load(String type){
        Object obj = null;
        String query = "";
        switch(type){
            case "Movie":
                query = "SELECT * FROM Movie";
                break;
            case "CRoom":
                query = "SELECT * FROM CRoom";
                break;
            case "Attraction":
                query = "SELECT * FROM Attraction";
                break;
            case "GoldCard":
                query = "SELECT * FROM Goldcard";
                break;
            case "Product":
                query = "SELECT * FROM Product";
                break;
            case "Report":
                query = "SELECT * FROM Report";
                break;
            case "Res":
                query = "SELECT * FROM Res";
                break;
            case "Show":
                query = "SELECT * FROM Shows";
                break;
            case "Ticket":
                query = "SELECT * FROM Ticket";
                break;
            case "User":
                query = "SELECT * FROM Users";
                break;
            case "Time":
                query = "SELECT * FROM TimeDate";
                break;
            case "ReportData":
                query = "SELECT * FROM ReportData";
                break;
            default:
                query = "SELECT * FROM Dummy";
        }
        return query;
    }
}
