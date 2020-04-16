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
public class SearchError implements Serializable{
    private String moneyRangeErr;

    public SearchError() {
    }

    public String getMoneyRangeErr() {
        return moneyRangeErr;
    }

    public void setMoneyRangeErr(String moneyRangeErr) {
        this.moneyRangeErr = moneyRangeErr;
    }
}
