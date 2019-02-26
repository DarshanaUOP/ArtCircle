

import javax.swing.*;
import java.sql.*;

/**
 * Created by acer on 15-Jan-18.
 */
public class dbLogging {

    static Connection connLogging;
    static PreparedStatement psLogging;
    static ResultSet rsLogging;
    static Statement stmtLogging;
    static String dbName = "LoggingData";
    static String pwd = "root";
    static boolean notExsist=true;
    static String UserName=null;

    public void dbExists(){
        //THE DATABASE "LoggingData" contains userNames and passwords of users;
        //admin is default user;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connLogging = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, pwd, "");
            notExsist = false;
            System.out.println("###"+notExsist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (notExsist){
            int createOK=JOptionPane.showConfirmDialog(null,"Do you need to create "+dbName+".","DB does not exists.",JOptionPane.YES_NO_OPTION);
            System.out.println(createOK);


            if (createOK==0){
                try {
                    connLogging = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
                    stmtLogging = connLogging.createStatement();
                    stmtLogging.executeUpdate("CREATE DATABASE "+dbName);
                    stmtLogging.executeUpdate("USE "+dbName);
                    stmtLogging.execute("create table accounts(indexNum int(10) unsigned auto_increment primary key not null,userName varchar(50) not null,password varchar(50) not null)");
                    stmtLogging.executeUpdate("INSERT INTO accounts (indexNum, userName, password) VALUES ('1', 'admin', 'admin')");
                    JOptionPane.showMessageDialog(null,"DB "+dbName+" Created","Done",JOptionPane.PLAIN_MESSAGE);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Error at Create Database "+dbName,JOptionPane.PLAIN_MESSAGE);
                }
            }

        }this.checkTable();
    }
    public void checkTable(){
        boolean createTable = true;
        try {
            connLogging = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtLogging = connLogging.createStatement();
            stmtLogging.executeUpdate("USE "+dbName);

            rsLogging = stmtLogging.executeQuery("SHOW TABLES");
            while (rsLogging.next()){
                createTable = false;
            }

            System.out.println(createTable);
            if (createTable){
                try {

                    stmtLogging.execute("create table accounts(indexNum int(10) unsigned auto_increment primary key not null,userName varchar(50) not null,password varchar(50) not null)");
                    stmtLogging.executeUpdate("INSERT INTO accounts (indexNum, userName, password) VALUES ('1', 'admin', 'admin')");
                    JOptionPane.showMessageDialog(null,"Table created.","Done",JOptionPane.PLAIN_MESSAGE);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Error at Create tables "+dbName,JOptionPane.PLAIN_MESSAGE);
                }
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean checkAccount(String userName,String password){
        boolean logOK=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            stmtLogging = connLogging.createStatement();
            stmtLogging.executeUpdate("USE "+dbName);
            rsLogging = stmtLogging.executeQuery("SELECT * FROM accounts WHERE userName LIKE '"+userName+"' AND password LIKE '"+password+"' ");
            UserName = userName;
            logOK = rsLogging.next();
            System.out.println("data "+logOK);

        }catch (Exception e){}

    return logOK;
    }

    public static void createAccount(String userName,String password){
        int count=1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            stmtLogging = connLogging.createStatement();
            stmtLogging.executeUpdate("USE "+dbName);
            rsLogging = stmtLogging.executeQuery("SELECT * FROM accounts");

            while (rsLogging.next()){
                count++;
            }
            System.out.println(count);
            stmtLogging.executeUpdate("INSERT INTO accounts (indexNum, userName, password) VALUES ('"+count+"', '"+userName+"', '"+password+"')");
            JOptionPane.showMessageDialog(null,"Account "+userName +" created.","Done",JOptionPane.PLAIN_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in creating account\n"+e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }

    }

}
