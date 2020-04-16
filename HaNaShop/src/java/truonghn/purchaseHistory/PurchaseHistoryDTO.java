/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.purchaseHistory;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class PurchaseHistoryDTO implements Serializable{
    private String mail;
    private String foodID;
    private String image;
    private String name;
    private int amount;
    private float price;
    private String date;

    public PurchaseHistoryDTO(String mail, String foodID, String image, String name, int amount, float price, String date) {
        this.mail = mail;
        this.foodID = foodID;
        this.image = image;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
