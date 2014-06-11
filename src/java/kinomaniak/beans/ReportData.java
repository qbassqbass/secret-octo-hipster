/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Qbass
 */
public class ReportData implements Serializable{
    
    private int id;
    private int userId;
    private int type;
    private Date timestamp;
    private Object object;
    private int objectId;

    public Object getObject() {
        return object;
    }
    
    public int getObjectId(){
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public ReportData() {
    }
    
    public ReportData(Date stamp){
        this.timestamp = stamp;
        this.userId = -1;
    }
    public ReportData(Date stamp, int uid, Object obj){
        this.timestamp = stamp;
        this.userId = uid;
        this.object = obj;
    }
    
    public int checkType(){
        int type = 0;
        
        return type;
    }
    @Override
    public String toString(){
        return ""+getId()+"|"+getTimestamp()+"|"+getUserId()+"|"+getType()+"|"+getObjectId();
    }
    
    public boolean saveReport(String filename){
        boolean saved = false;
        
        return saved;
    }
}
