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
public class CreateNewError implements Serializable{
    private String itemIDLengthError;
    private String itemIDExisted;
    private String itemNameLengthError;
    private String itemCategoryError;
    private String itemCategoryExisted;
    private String itemDescriptionLengthError;
    private String imagePathError;
    

    public CreateNewError() {
    }

    public String getItemIDLengthError() {
        return itemIDLengthError;
    }

    public void setItemIDLengthError(String itemIDLengthError) {
        this.itemIDLengthError = itemIDLengthError;
    }

    public String getItemIDExisted() {
        return itemIDExisted;
    }

    public void setItemIDExisted(String itemIDExisted) {
        this.itemIDExisted = itemIDExisted;
    }

    public String getItemNameLengthError() {
        return itemNameLengthError;
    }

    public void setItemNameLengthError(String itemNameLengthError) {
        this.itemNameLengthError = itemNameLengthError;
    }

    public String getItemCategoryError() {
        return itemCategoryError;
    }

    public void setItemCategoryError(String itemCategoryError) {
        this.itemCategoryError = itemCategoryError;
    }

    public String getItemCategoryExisted() {
        return itemCategoryExisted;
    }

    public void setItemCategoryExisted(String itemCategoryExisted) {
        this.itemCategoryExisted = itemCategoryExisted;
    }

    public String getItemDescriptionLengthError() {
        return itemDescriptionLengthError;
    }

    public void setItemDescriptionLengthError(String itemDescriptionLengthError) {
        this.itemDescriptionLengthError = itemDescriptionLengthError;
    }

    public String getImagePathError() {
        return imagePathError;
    }

    public void setImagePathError(String imagePathError) {
        this.imagePathError = imagePathError;
    }
    
    
    
}
