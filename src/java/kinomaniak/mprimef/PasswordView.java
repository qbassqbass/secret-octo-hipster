/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kinomaniak.mprimef;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Adam
 */
public class PasswordView {

    /**
     * Creates a new instance of PasswordView
     */
    public PasswordView() {
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private String password;
}
