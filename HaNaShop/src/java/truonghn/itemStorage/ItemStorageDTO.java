/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.itemStorage;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class ItemStorageDTO implements Serializable{
    private String id;
    private String foodname;
    private String image;
    private String description;
    private float price;
    private String date;
    private int catID;
    private int quantity;
    private String status;
    private int sold;

    public ItemStorageDTO(String id, String foodname, String image, float price, String date, int catID, int quantity, int sold) {
        this.id = id;
        this.foodname = foodname;
        this.image = image;
        this.price = price;
        this.date = date;
        this.catID = catID;
        this.quantity = quantity;
        this.sold = sold;
    }

    public ItemStorageDTO(String id, String foodname, String image, String description, float price, String date, int catID, int quantity, String status, int sold) {
        this.id = id;
        this.foodname = foodname;
        this.image = image;
        this.description = description;
        this.price = price;
        this.date = date;
        this.catID = catID;
        this.quantity = quantity;
        this.status = status;
        this.sold = sold;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ItemStorageDTO() {
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
    
    
}
