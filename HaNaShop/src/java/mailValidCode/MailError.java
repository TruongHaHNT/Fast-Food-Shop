/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailValidCode;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class MailError implements Serializable{
    private String invalidVerifyCode;

    public MailError() {
    }

    public String getInvalidVerifyCode() {
        return invalidVerifyCode;
    }

    public void setInvalidVerifyCode(String invalidVerifyCode) {
        this.invalidVerifyCode = invalidVerifyCode;
    }
    
    
}
