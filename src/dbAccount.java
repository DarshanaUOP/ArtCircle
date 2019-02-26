import javax.swing.*;
import java.security.interfaces.RSAKey;
import java.sql.*;

/**
 * Created by acer on 16-Jan-18.
 */
public class dbAccount {

    static Connection conAccount;
    static PreparedStatement psAccount;
    static ResultSet rsAccount;
    static Statement stmtAccount;
    static String dbName = "ArtCircle";
    static String pwd = "root";
    boolean notExist=true;

    public void dbArtCircleExists(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName, pwd, "");
            notExist = false;
            System.out.println("1." +notExist);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        if (notExist){
            int createOK=JOptionPane.showConfirmDialog(null,"Do you need to create "+dbName+"?","DB does not exists.",JOptionPane.YES_NO_OPTION);
            System.out.println(createOK);


            if (createOK==0){
                try {
                    conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
                    stmtAccount = conAccount.createStatement();
                    stmtAccount.executeUpdate("CREATE DATABASE "+dbName);
                    stmtAccount.executeUpdate("USE "+dbName);

                    stmtAccount.execute("create table ArtCircleAccount(TRANSACTIONUMBER int(11) unsigned auto_increment primary key not null,NAMEGIVEN varchar(50) not null,EVENTFOR varchar(50) not null,AMOUNT DOUBLE not null,SUBJECT VARCHAR(50) NOT NULL,DATEGIVEN VARCHAR(50) NOT NULL,TIMEGIVEN VARCHAR(50) NOT NULL,BILLSTATUS VARCHAR(50) NOT NULL,DESCRIPTION VARCHAR(50) NOT NULL,RETURNAMOUNT INT(11) NOT NULL,ENTEREDBY VARCHAR(50) NOT NULL)");

                    JOptionPane.showMessageDialog(null,"DB "+dbName+" Created","Done",JOptionPane.PLAIN_MESSAGE);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Error at Create Database "+dbName,JOptionPane.PLAIN_MESSAGE);
                }
            }

        }
        System.out.println("55");
        this.checkTableAccount();
        System.out.println("88");
        this.checkTableEvents();
    }

    public void checkTableAccount(){
        boolean createTable = true;
        try {
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);

            rsAccount = stmtAccount.executeQuery("SHOW TABLES");
            while (rsAccount.next()){
                if (rsAccount.getString(1).equals("ArtCircleAccount")){
                    createTable = false;
                }

            }

            System.out.println(createTable);
            if (createTable){
                try {
                    stmtAccount.execute("create table ArtCircleAccount(TRANSACTIONUMBER int(11) unsigned auto_increment primary key not null,NAMEGIVEN varchar(50) not null,EVENTFOR varchar(50) not null,AMOUNT DOUBLE not null,SUBJECT VARCHAR(50) NOT NULL,DATEGIVEN VARCHAR(50) NOT NULL,TIMEGIVEN VARCHAR(50) NOT NULL,BILLSTATUS VARCHAR(50) NOT NULL,DESCRIPTION VARCHAR(50) NOT NULL,RETURNAMOUNT INT(11) NOT NULL,ENTEREDBY VARCHAR(50) NOT NULL)");
                    System.out.println("ddd");
                    JOptionPane.showMessageDialog(null,"the table ArtCircleAccount has created.","Done",JOptionPane.PLAIN_MESSAGE );
                }catch (Exception e){
                    //JOptionPane.showMessageDialog(null,e.getMessage(),"Error at Create tables "+dbName,JOptionPane.PLAIN_MESSAGE);
                }
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void checkTableEvents() {
        boolean createTable = true;
        try {
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE " + dbName);

            rsAccount = stmtAccount.executeQuery("SHOW TABLES");

            while (rsAccount.next()) {
                if ( rsAccount.getString(1).equals("tableEvent") ) {
                    createTable = false;
                }
            }

            System.out.println("Event tab "+createTable);
            if (createTable) {
                try {
                    stmtAccount.execute("create table tableEvent(EventindexNum int(10) unsigned auto_increment primary key not null,EventName varchar(50) not null)");
                    System.out.println("eee");
                    JOptionPane.showMessageDialog(null,"the table tableEvent has created.","Done",JOptionPane.PLAIN_MESSAGE );
                } catch (Exception e) {
                    //JOptionPane.showMessageDialog(null, e.getMessage(), "Error at Create tables " + dbName, JOptionPane.PLAIN_MESSAGE);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static int getIndex(String collumnName,String tableName){
        int indexIs=1;
        try {
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);

            rsAccount= stmtAccount.executeQuery("SELECT '"+collumnName+"' FROM "+tableName+"");

            while (rsAccount.next()){
                indexIs++;
            }

        }catch (Exception e){

        }
    return indexIs;}

    //+++++++++++++++++++++++++++++++++++++++++
    public static int checkEventcount(){

        int eventCount=0;
        try {

            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);
            rsAccount = stmtAccount.executeQuery("SELECT * FROM tableevent ");

            while(rsAccount.next()){

                eventCount++;
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
        return eventCount;}

    //+++++++++++++++++++++++++++++++++++++++

    public static String[] getEvents(){
        String[] eventNames = new String[1000];
        int i=0;
        try {
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);
            rsAccount = stmtAccount.executeQuery("SELECT * FROM tableevent ");

            while (rsAccount.next()){
                eventNames[i]=rsAccount.getString(2);
                i++;
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }

    return eventNames;
    }

    public static boolean addEntry(int transactionNum,String nameofPerson,String evnt,Double amount,String Subject,String date,String time,String billstatus,String Description,Double returnAmount,String enteredBy){
        boolean saved=true;
        System.out.println("internal ,"+saved);
        try {

            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);
            saved = stmtAccount.execute("INSERT INTO artcircleaccount(TRANSACTIONUMBER, NAMEGIVEN, EVENTFOR, AMOUNT, SUBJECT, DATEGIVEN, TIMEGIVEN, BILLSTATUS, DESCRIPTION, RETURNAMOUNT, ENTEREDBY) VALUES ('"+transactionNum+"','"+nameofPerson+"','"+evnt+"','"+amount+"','"+Subject+"','"+date+"','"+time+"','"+billstatus+"','"+Description+"','"+returnAmount+"','"+enteredBy+"')");

            System.out.println("internal ,"+saved);

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error in Saving Data "+e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
        return saved;
    }

    public static boolean addNewEvent(String eventName){
        boolean saved = true,SaveOk=false;
        try {
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);

            rsAccount = stmtAccount.executeQuery("SELECT * FROM tableevent WHERE EventName LIKE '"+eventName+"' ORDER BY EventindexNum ASC ");
            //System.out.println("Events : "+rsAccount.next());

            while (rsAccount.next()){
                String a = rsAccount.getString("EventName");
                //System.out.println("Events : "+a);
                if (a.equals(eventName)){
                    SaveOk=true;
                    System.out.println("Save OK "+ SaveOk);
                }
            }


            if (!SaveOk){
                saved = stmtAccount.execute("INSERT INTO tableevent (EventName) VALUES ('"+eventName+"')");
                JOptionPane.showMessageDialog(null,"Event "+eventName+" Added.","Done",JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null,"Event "+eventName+" already exists.","Error",JOptionPane.WARNING_MESSAGE);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return saved;
    }

    public static String[] getSearchResults(String eventName,String subjectName){
        String[] results = new String[1000];
        int i0 = 0;
        try {

            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);

            rsAccount = stmtAccount.executeQuery("SELECT SUBJECT FROM artcircleaccount WHERE EVENTFOR LIKE '"+eventName+"' AND SUBJECT LIKE '%"+subjectName+"%' ");
            while (rsAccount.next()){
                results[i0] = rsAccount.getString(1);
                i0++;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    return results;
    }

    public static String[] getDetails(String EventName,String subjectName){
        String retValue[]=new String[1000];
        try {
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);

            if (!(EventName.equals(null))) {
                rsAccount = stmtAccount.executeQuery("SELECT * FROM artcircleaccount WHERE EVENTFOR LIKE '" + EventName + "' AND SUBJECT LIKE '" + subjectName + "' ");
            }
            boolean x = rsAccount.next();
            for (int i=0;i<=10;i++){
                retValue[i] = rsAccount.getString(i+1);
            }

        }catch (Exception e){
            //JOptionPane.showMessageDialog(null,e.getMessage(),"Error getData",JOptionPane.PLAIN_MESSAGE);
        }
        return retValue;
    }

    public static int updateData(String name,String event, Double Amount,String Subject,String BillInfo,String Description,Double returnAmount,String UserName,int TransactionNum){
        int retStat=1;
        try {
            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);
            stmtAccount.executeUpdate("UPDATE artcircleaccount SET NAMEGIVEN = '"+name+"',EVENTFOR = '"+event+"', AMOUNT = '"+Amount+"', SUBJECT = '"+Subject+"', BILLSTATUS = '"+BillInfo+"', DESCRIPTION = '"+Description+"', RETURNAMOUNT = '"+returnAmount+"', ENTEREDBY = '"+UserName+"' WHERE artcircleaccount.TRANSACTIONUMBER = "+TransactionNum);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    return retStat;}

    public static Double[][] checkAccountBalance(){
        Double[][] retVals=new Double[2][10000];
        int numberOfEntrys=0;

        try {

            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);
            rsAccount = stmtAccount.executeQuery("SELECT AMOUNT, RETURNAMOUNT FROM artcircleaccount WHERE 1");

            //retVals[0] contains AMOUNT and
            //retVals[1] contains RETAMOUNT;

            while(rsAccount.next()){
                retVals[0][numberOfEntrys]= Double.valueOf(rsAccount.getString(1));
                retVals[1][numberOfEntrys]= Double.valueOf(rsAccount.getString(2));

                numberOfEntrys++;
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    return retVals;}

    public static int checkBalanceLength(){

        int balanceLength=0;
        try {

            conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
            stmtAccount = conAccount.createStatement();
            stmtAccount.executeUpdate("USE "+dbName);
            rsAccount = stmtAccount.executeQuery("SELECT AMOUNT, RETURNAMOUNT FROM artcircleaccount WHERE 1");

            while(rsAccount.next()){

                balanceLength++;
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    return balanceLength;}


}
