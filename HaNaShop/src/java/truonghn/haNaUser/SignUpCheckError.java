/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.haNaUser;

/**
 *
 * @author SE130204
 */
public class SignUpCheckError {
    private String emailLengthErr;
    private String emailFormatErr;
    private String passwordLengthErr;
    private String passwordFormatErr;
    private String confirmPasswordErr;
    private String nameLengthErr;
    private String nameFormatErr;
    private String emailExistedErr;

    public SignUpCheckError(String emailLengthErr, String emailFormatErr, String passwordLengthErr, String passwordFormatErr, String confirmPasswordErr, String nameLengthErr, String nameFormatErr, String emailExistedErr) {
        this.emailLengthErr = emailLengthErr;
        this.emailFormatErr = emailFormatErr;
        this.passwordLengthErr = passwordLengthErr;
        this.passwordFormatErr = passwordFormatErr;
        this.confirmPasswordErr = confirmPasswordErr;
        this.nameLengthErr = nameLengthErr;
        this.nameFormatErr = nameFormatErr;
        this.emailExistedErr = emailExistedErr;
    }

    public SignUpCheckError() {
    }

    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    public String getEmailFormatErr() {
        return emailFormatErr;
    }

    public void setEmailFormatErr(String emailFormatErr) {
        this.emailFormatErr = emailFormatErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getPasswordFormatErr() {
        return passwordFormatErr;
    }

    public void setPasswordFormatErr(String passwordFormatErr) {
        this.passwordFormatErr = passwordFormatErr;
    }

    public String getConfirmPasswordErr() {
        return confirmPasswordErr;
    }

    public void setConfirmPasswordErr(String confirmPasswordErr) {
        this.confirmPasswordErr = confirmPasswordErr;
    }

    public String getNameLengthErr() {
        return nameLengthErr;
    }

    public void setNameLengthErr(String nameLengthErr) {
        this.nameLengthErr = nameLengthErr;
    }

    public String getNameFormatErr() {
        return nameFormatErr;
    }

    public void setNameFormatErr(String nameFormatErr) {
        this.nameFormatErr = nameFormatErr;
    }

    public String getEmailExistedErr() {
        return emailExistedErr;
    }

    public void setEmailExistedErr(String emailExistedErr) {
        this.emailExistedErr = emailExistedErr;
    }
    
    
}
