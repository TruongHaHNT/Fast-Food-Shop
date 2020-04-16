/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.filter;

import java.util.HashMap;
import truonghn.utils.Utils;

/**
 *
 * @author SE130204
 */
public class ServletList {
    public static HashMap<String,String> list = null;
    
    public static void createList(){
        if(list == null){
            list = new HashMap<>();
        //login--logout--sign up------------------------------------------
            list.put("loginPage", "AccountServlet");
            list.put("Login", "LoginServlet");
            list.put("VerifyProccess", "VerifyProccessServlet");
            list.put("SignUpPage", "signUp.html");
            list.put("ShowLogin", "login.html");
            list.put("SignUp", "SignUpServlet");
        //home page function----------------------------------------------
            list.put("homePage", "HomePageServlet");
            list.put("Home", "HomePageServlet");
        //product page function-------------------------------------------
            list.put("CustomerSearch", "ShowProductServlet");
            list.put("Product", "ShowProductServlet");
            //view item function or add to cart-----------------------------------------------
            list.put("viewOrAddToCartItem", "ViewOrAddToCartServlet");
        //view Your Cart=======================================================
            list.put("viewCart", "ViewItemsInCartServlet");
        //cart handller====================================================================
            list.put("cartHandler", "CustomerCartHandllerServlet");
        //Purchase history page==================================================
            list.put("History", "ViewPurchaseHistoryServlet");
            list.put("HistorySearch", "ViewPurchaseHistoryServlet");
        //Manager function------------------------------------------------
            list.put("updateAndCreate", "CheckIsManagerServlet");
        //Merge function=================================================
            list.put("ViewItem", "ViewItemServlet");
            list.put("CheckUserrole", "ErrorHandlerServet");
            
        }
    }
}