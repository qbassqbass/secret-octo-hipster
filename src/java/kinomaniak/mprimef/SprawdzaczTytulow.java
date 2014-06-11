/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.mprimef;

import java.util.ArrayList;
import java.util.List;
import kinomaniak.beans.Res;
import kinomaniak.beans.Show;
import kinomaniak.controllers.BeanManager;

/**
 *
 * @author Adam
 */

public class SprawdzaczTytulow {

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }
    private BeanManager beanManager;
    /**
     * Creates a new instance of SprawdzaczTytulow
     */
    public String sprawdzTytul(int id){
        for (Show s: this.getBeanManager().getShows())
            if(id == s.getID())
                return s.getMov().getName();
        return null;
    }
    public String sprawdzDate(int id){
        for (Show s: this.getBeanManager().getShows())
            if(id == s.getID())
                return s.getFormatted();
        return null;
    }
    
    public List<Integer> sprawdzMiejsca(int id){
        List<Integer> list = new ArrayList<Integer>();
        for (Res r: this.getBeanManager().getRes())
            if(id == r.getShowid())
                for(int s[]: r.getSeats()) list.add(s[0]*10+s[1]);
        return list;
    }
    public SprawdzaczTytulow() {
    }
    
}
