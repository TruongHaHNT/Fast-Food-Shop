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
public class HaNaUserDTO implements Serializable{
    private String mail;
    private String name;
    private String password;
    private String role;
    private String status;

    public HaNaUserDTO() {
    }

    public HaNaUserDTO(String mail, String name, String password, String role, String status) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
