package kinomaniak.mprimef;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adam
 */
public class AttractionKomunikat {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private String message;
    /**
     * Creates a new instance of AttractionKomunikat
     */
    public void saveMessage(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Uwaga!", "Zatwierdzono sprzedaż biletu na atrakcję"));
    }
    
    public AttractionKomunikat() {
    }
    
}
