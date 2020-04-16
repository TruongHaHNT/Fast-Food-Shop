/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.itemRecord;

import java.io.Serializable;

/**
 *
 * @author SE130204
 */
public class ItemRecordDTO implements Serializable{
    private String itemID;
    private int purchaseRecord;

    public ItemRecordDTO(String itemID, int purchaseRecord) {
        this.itemID = itemID;
        this.purchaseRecord = purchaseRecord;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getPurchaseRecord() {
        return purchaseRecord;
    }

    public void setPurchaseRecord(int purchaseRecord) {
        this.purchaseRecord = purchaseRecord;
    }
    
    
}
