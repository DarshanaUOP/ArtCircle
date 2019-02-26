import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import static javax.swing.JFileChooser.DIRECTORIES_ONLY;

/**
 * Created by acer on 10-Mar-18.
 */
public class printFinaleReport extends JFrame{

    /**
     * Items For DataBase
     */
    static Connection conAccount;
    static PreparedStatement psAccount;
    static ResultSet rsAccount;
    static Statement stmtAccount;
    static String dbName = "ArtCircle";
    static String pwd = "root";

    static String[] eventName=new String[1000];
    static double[] income=new double[1000];
    static double[] cost=new double[1000];

    /**
     * Items for GUI
     */

    private JPanel pnBack;
    private JTextField txtPath;
    private JButton btStPath,btExit,btPrint;
    private JLabel lblStatus;
    GridBagConstraints g=new GridBagConstraints();

    static java.io.File fileName;


    public printFinaleReport(){

        Font font=new Font(null,Font.PLAIN,12);

        pnBack = new JPanel(new GridBagLayout());
        add(pnBack);

        txtPath = new JTextField("");
        txtPath.setFont(font);
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth=3;
        g.gridheight = 1;
        g.weightx=3;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnBack.add(txtPath,g);

        btStPath = new JButton("Set Path");
        btStPath.setFont(font);
        g.gridx = 4;
        g.gridy = 0;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnBack.add(btStPath,g);

        btPrint = new JButton("Print");
        btPrint.setFont(font);
        btPrint.setEnabled(false);
        g.gridx = 4;
        g.gridy = 1;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnBack.add(btPrint,g);

        lblStatus = new JLabel("Select a path to print Account");
        lblStatus.setVisible(true);
        lblStatus.setForeground(Color.red);
        lblStatus.setFont(font);
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth=1;
        g.gridheight = 3;
        g.weightx=1;
        g.weighty=3;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnBack.add(lblStatus,g);

        btExit = new JButton("Exit");
        btExit.setFont(font);
        g.gridx = 4;
        g.gridy = 2;
        g.gridwidth=1;
        g.gridheight = 3;
        g.weightx=1;
        g.weighty=3;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnBack.add(btExit,g);

        btStPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblStatus.setForeground(Color.BLACK);
                lblStatus.setText(dbLogging.UserName+" is selecting a path");
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(DIRECTORIES_ONLY);
                int response = fc.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION){
                    txtPath.setText(fc.getSelectedFile().toString());
                    fileName = fc.getSelectedFile();
                    btPrint.setEnabled(true);
                    lblStatus.setText(dbLogging.UserName+" was selected the path to "+fileName);
                }else {
                    JOptionPane.showMessageDialog(null,"File selection canceled","Canceled",JOptionPane.PLAIN_MESSAGE);
                    lblStatus.setForeground(Color.red);
                    lblStatus.setText("Select a path to print Account");
                }
            }

        });

        btPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblStatus.setForeground(Color.BLUE);
                lblStatus.setText("Initializing...");
                double[] income = new double[1000];
                double[] cost = new double[1000];
                double[] tot = new double[1000];

                String dateTime =new SimpleDateFormat("dd").format(new java.util.Date())+"_"+
                        new SimpleDateFormat("MMMM").format(new java.util.Date())+"_"+
                        new SimpleDateFormat("yyyy").format(new java.util.Date()) +" at "+
                        new SimpleDateFormat("HH").format(new java.util.Date()) +"_"+
                        new SimpleDateFormat("mm").format(new java.util.Date())+"_"+
                        new SimpleDateFormat("ss").format(new java.util.Date());
                double totCost=0,totInc=0,totAll=0;

                int d=0;
                double ammount,retAmount;

                FileWriter streem = null;

                try {
                    streem = new FileWriter(fileName + "\\artCircleAccount on "+dateTime+".txt");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"Error Creating File","Error",JOptionPane.ERROR_MESSAGE);
                }
                BufferedWriter out = new BufferedWriter(streem);

                lblStatus.setText("Printing to "+fileName);
                int count=dbAccount.checkEventcount();
                System.out.println("event number "+count);

                String[] events=dbAccount.getEvents();
                for (int x=0;x<count;x++){
                    System.out.println(x+","+events[x]);
                }

                try {
                    conAccount = DriverManager.getConnection("jdbc:mysql://localhost/", pwd, "");
                    stmtAccount = conAccount.createStatement();
                    stmtAccount.executeUpdate("USE "+dbName);

                    for (int x1=0;x1<count;x1++) {

                        rsAccount = stmtAccount.executeQuery("SELECT AMOUNT, RETURNAMOUNT FROM artcircleaccount WHERE EVENTFOR LIKE '"+events[x1]+"'");

                        while (rsAccount.next()) {
                            //System.out.println(events[x1]+","+ rsAccount.getString(1) + "," + rsAccount.getString(2));
                            ammount = rsAccount.getDouble(1);
                            retAmount = rsAccount.getDouble(2);

                            if (ammount<0){
                                cost[x1]=cost[x1]+ammount;
                            }else {
                                income[x1]=income[x1]+ammount;
                            }
                            cost[x1]=cost[x1]+retAmount;
                            tot[x1]=cost[x1]+income[x1];
                            //System.out.println(events[x1]+","+ ammount + "," + retAmount);
                        }
                        //System.out.println(">>"+events[x1]+","+ cost[x1] + "," + income[x1]);
                    }

                    String wordPrint;
                    wordPrint="Event\tCost (Rs)\tIncome (Rs)\tAmount (Rs)";
                    out.write(wordPrint);

                    for (int x2=0;x2<count;x2++) {
                        out.newLine();
                        wordPrint=events[x2]+"\t"+ (-1*cost[x2]) + "\t" + income[x2]+"\t"+(-1*tot[x2]);
                        out.write(wordPrint);
                        System.out.println(wordPrint);
                    }

                    for (int x2=0;x2<count;x2++) {
                        totCost+=cost[x2];
                        totInc+=income[x2];
                    }
                    totAll=totCost+totInc;
                    wordPrint="Total\t"+-1*(totCost)+"\t"+totInc+"\t"+-1*(totAll);
                    System.out.println(wordPrint);
                    out.newLine();
                    out.newLine();
                    out.write(wordPrint);
                    out.newLine();
                    out.newLine();
                    out.write("© Darshana Ariyaratna | darshana.uop@gmail.com | +94774901245");
                    out.close();

                    lblStatus.setText("Printed to "+fileName);

                }catch (Exception e1){
                    JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    lblStatus.setForeground(Color.red);
                    lblStatus.setText("Select a path to print Account");
                }
            }
        });
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
