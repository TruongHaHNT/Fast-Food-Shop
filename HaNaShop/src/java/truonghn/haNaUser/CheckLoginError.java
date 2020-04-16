/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.haNaUser;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class CheckLoginError implements Serializable{
    private String emailLengthErr;
     private String emailFormatErr;
     private String passwordLengthErr;
     private String passwordFormatErr;
     private String notExistErr;
     private String incorrectPassword;

    /**
     * @return the emailLengthErr
     */
    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    /**
     * @param emailLengthErr the emailLengthErr to set
     */
    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    /**
     * @return the emailFormatErr
     */
    public String getEmailFormatErr() {
        return emailFormatErr;
    }

    /**
     * @param emailFormatErr the emailFormatErr to set
     */
    public void setEmailFormatErr(String emailFormatErr) {
        this.emailFormatErr = emailFormatErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the passwordFormatErr
     */
    public String getPasswordFormatErr() {
        return passwordFormatErr;
    }

    /**
     * @param passwordFormatErr the passwordFormatErr to set
     */
    public void setPasswordFormatErr(String passwordFormatErr) {
        this.passwordFormatErr = passwordFormatErr;
    }

    /**
     * @return the notExistErr
     */
    public String getNotExistErr() {
        return notExistErr;
    }

    /**
     * @param notExistErr the notExistErr to set
     */
    public void setNotExistErr(String notExistErr) {
        this.notExistErr = notExistErr;
    }

    /**
     * @return the incorrectPassword
     */
    public String getIncorrectPassword() {
        return incorrectPassword;
    }

    /**
     * @param incorrectPassword the incorrectPassword to set
     */
    public void setIncorrectPassword(String incorrectPassword) {
        this.incorrectPassword = incorrectPassword;
    }
     
}
