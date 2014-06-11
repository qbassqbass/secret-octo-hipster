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
@Deprecated
public class Report implements Serializable{
    public static enum element{
        USERID, ATTRID
    };
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setRange(Date[] range) {
        this.range = range;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
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

    public Date[] getRange() {
        return range;
    }

    public int getAttractionId() {
        return attractionId;
    }

    public int getUserId() {
        return userId;
    }

    public Report() {
    }
    private int type;
    private Date[] range = new Date[2];
    private int attractionId;
    private int userId;
    
    public Report(Date[] range){
        this.range = range;
        this.attractionId = -1;
        this.userId = -1;
    }
    public Report(Date[] range, element el, int id){
        this.range = range;
        if(el == element.ATTRID){            
            this.attractionId = id;
            this.userId = -1;
        }else if(el == element.USERID){
            this.attractionId = -1;
            this.userId = id;
        }
    }
    
    public boolean saveReport(String filename){
        boolean saved = false;
        
        return saved;
    }
}
