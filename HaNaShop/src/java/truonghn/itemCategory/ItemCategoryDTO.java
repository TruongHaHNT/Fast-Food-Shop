/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.itemCategory;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class ItemCategoryDTO implements Serializable{
    private int categoryID;
    private String category;

    public ItemCategoryDTO() {
    }

    public ItemCategoryDTO(int categoryID, String category) {
        this.categoryID = categoryID;
        this.category = category;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
}
