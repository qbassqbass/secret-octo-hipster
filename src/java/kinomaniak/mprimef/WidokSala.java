/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.mprimef;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.ws.rs.Produces;
import kinomaniak.beans.Res;
import kinomaniak.controllers.BeanManager;
import org.jboss.logging.Property;

/**
 *
 * @author Adam
 */
@ManagedBean
public class WidokSala {

    public BeanManager getBeanManager() {
        return beanManager;
    }

    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }
        @ManagedProperty("#{beanManager}")
    private BeanManager beanManager;
    /**
     * Creates a new instance of WidokSala
     */
    public List<Integer> getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(List<Integer> selectedOption) {
        this.selectedOption = selectedOption;
    }
    public boolean sprawdz(int par){
        for(int i=0;i<10;i++)
            if(par == selectedOption.get(i))
                return true;
        return false;
    } 
  
    
    List<Integer> selectedOption; 
    public void ustawZaznaczone(){

        selectedOption.add(1);
    }
    public WidokSala(){
    
}
    
}