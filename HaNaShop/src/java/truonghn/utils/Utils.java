/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truonghn.utils;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import truonghn.imageUI.ImageUIDAO;

/**
 *
 * @author SE130204
 */
public class Utils implements Serializable{
    public static Integer totalContentPerPage = 2;
    public static Integer recommended = 5;
    public static String minDate = "1753-01-01";
    public static String maxDate = LocalDate.now().toString();
    public static String path = "image/";
    public static String logPath = "C:\\LogLab\\HaNaShop.log";
    public static Connection makeConnection() 
            throws SQLException, NamingException{
        
    //1. lay context hien hanh.
        Context context = new InitialContext();
    //2. lay context o phia server.    
        Context tomcatCtx = (Context)context.lookup("java:comp/env");
    //3. get data source
        DataSource ds = (DataSource)tomcatCtx.lookup("DS007");
    //4. get connection
        Connection con = ds.getConnection();
        return con;
//        //1.Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.create connection String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=UserWeb";
//        //3.open connection
//        Connection con = DriverManager.getConnection(url,"sa","Okecongaden123");
//        
//        return con;
    }
    
    public static String encryptPass(String base) throws NoSuchAlgorithmException, UnsupportedEncodingException {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
    }
    static String alphaBet="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";
    public static String activeAcountCode(String email){
        StringBuilder s = new StringBuilder(6);
        int y;
        for ( y = 0; y < 6; y++) {
        // generating a random number
        int index = (int)(alphaBet.length() * Math.random());
        // add Character one by one in end of s
        s.append(alphaBet.charAt(index));
        }
        mailValidCode.MailValidCode.sendCodeToUser(s.toString(), email.trim());
        return s.toString();
    }
    
    public static String encodeMail(String email){
        if(email != null){
            email = email.replaceAll("@", "Ged2uMA423232IL29d");
            email = email.replaceAll("\\.", "364828482dot9443");
            email = email.replaceAll("\\-", "nfow47fivnmfl3n4");
            email = email.replaceAll("\\_", "923yr78dnc4nb53m");
            return email;
        }
        return "";
    }
    public static String decodeMail(String email){
        if(email != null){
            email = email.replaceAll("Ged2uMA423232IL29d","@" );
            email = email.replaceAll("364828482dot9443", "\\.");
            email = email.replaceAll("nfow47fivnmfl3n4","\\-");
            email = email.replaceAll("923yr78dnc4nb53m","\\_");
            return email;
        }
        return "";
    }
    static HashMap<Integer, String> calendar = new HashMap<>();
    
    public static void setCalendar(){
        calendar.put(1, "January");
        calendar.put(2, "February");
        calendar.put(3, "March");
        calendar.put(4, "April");
        calendar.put(5, "May");
        calendar.put(6, "June");
        calendar.put(7, "July");
        calendar.put(8, "August");
        calendar.put(9, "September");
        calendar.put(10, "October");
        calendar.put(11, "November");
        calendar.put(12, "December");
    }
    
    public static String timeFormat(Timestamp stamp){
        String Calformat = stamp.toString();
//        yyyy-mm-dd hh:mm:ss.fffffffff
        String[] cal = Calformat.split(" ");
        String date = cal[0];
//        yyyy-mm-dd
        
        String time = cal[1];
//        hh:mm:ss.fffffffff
        
        String dateFormat[] = date.split("-");
        int year = Integer.parseInt(dateFormat[0]);
        int month = Integer.parseInt(dateFormat[1]);
        int day = Integer.parseInt(dateFormat[2]); 
         
        
        String timeFormat[] = time.split(":");
        String hour = timeFormat[0];
        String min = timeFormat[1];
        String secformat[] = timeFormat[2].split("\\.");
      
        String AlphaMonth = calendar.get(month);
        String result = AlphaMonth+" "+day+", "+year+" "+hour+":"+min+":"+secformat[0];
        return result;
    } 
    
    public static Timestamp getTime(){
        Date date= new Date();
         //getTime() returns current time in milliseconds
	 long time = date.getTime();
         //Passed the milliseconds to constructor of Timestamp class 
	 Timestamp ts = new Timestamp(time);
         return ts;
    }  
   /* Get actual class name to be printed on */
    public static void myBlogFile(String message) {
        Logger logger = Logger.getLogger("MyLog");
        Appender fh = null;
            try {
            fh = new FileAppender(new SimpleLayout(), logPath, true);
            logger.addAppender(fh);
            fh.setLayout(new SimpleLayout());
            logger.info(message);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getimage(String name) throws SQLException, NamingException{
        ImageUIDAO dao = new ImageUIDAO();
        String image = dao.getImage(name);
        String imageLink = path + image;
        return imageLink;
    }
    
    public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
