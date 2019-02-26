import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by acer on 23-Dec-17.
 */
public class GUI extends JFrame {
    public int slctedOpt;

    private JPanel panBackground,panMenu,panComponents;
    private JMenuBar menuBar;
    private JMenu file,checkBalance,settings,print;
    private JMenuItem addNew,changeUser,logOut,exit;
    private JMenuItem balanceReport;
    private JMenuItem theam,font,colors;
    private JMenuItem fullReport,finaleReport;
    private JLabel lblResult,lblSearch,lbluserName;
    private JLabel lblTransaction,lblPerson,lblFromOrTo,lblHowMutch,lblSubject,lblDate,lblTime,lblEvent,lblBill,lblDescription,lblreturn;
    private JTextField txtSearch, txtJobnum,txtPerson,txtEvent,txtHowMutch,txtSubject,txtDate,txtTime,txtBllInfo,txtDescription,txtReturn;
    private JComboBox cmbEvent,cmbEventSelecter;
    private JList resultList;
    private JButton btSearch,btAddNew,btSave,btAddEvent;
    private JRadioButton rdSelect,rdFrom,rdTo;
    private ButtonGroup groupFromOrTo;

    private GridBagConstraints con = new GridBagConstraints();
    private static String[] name={"Darshana","Ariyarathna","Eranga","Ishara","Danushka","Dananjaya","Chandra","Thomians","Pera","Hanthana","UOP","Colombo","DockYard","PLC","Arduino","PIC","LED","Motor","Radar"};

    public GUI(){

        ImageIcon search = new ImageIcon(getClass().getResource("search.png"));
        ImageIcon add=new ImageIcon(getClass().getResource("add.png"));

        Font lblFont = new Font(null,Font.PLAIN,15);
        Font txtFieldFont = new Font(null,Font.PLAIN,18);

        panBackground = new JPanel(new BorderLayout());
        add(panBackground);

        panMenu = new JPanel(new GridLayout());
        panBackground.add(panMenu,BorderLayout.NORTH);

        panComponents = new JPanel(new GridBagLayout());
        panComponents.setBackground(Color.white);
        panBackground.add(panComponents,BorderLayout.CENTER);

        menuBar = new JMenuBar();
        panMenu.add(menuBar);
        //**************************************
        file = new JMenu("File");
        //file.setBackground(Color.CYAN);
        menuBar.add(file);

        addNew = new JMenuItem("Add New");
        addNew.setEnabled(false);
        file.add(addNew);

        changeUser=new JMenuItem("Change user");
        changeUser.setEnabled(false);
        file.add(changeUser);

        logOut = new JMenuItem("Log out");
        logOut.setEnabled(false);
        file.add(logOut);

        exit = new JMenuItem("Exit");
        file.add(exit);

        //**************************************
        checkBalance =new JMenu("Check Balance");
        menuBar.add(checkBalance);

        balanceReport = new JMenuItem("Balance");
        checkBalance.add(balanceReport);

        //**************************************
        settings = new JMenu("Settings");
        settings.setEnabled(false);
        menuBar.add(settings);

        theam = new JMenuItem("Theam");
        theam.setEnabled(false);
        settings.add(theam);

        font = new JMenuItem("Font");
        font.setEnabled(false);
        settings.add(font);

        colors = new JMenuItem("Colors");
        colors.setEnabled(false);
        settings.add(colors);
        //**************************************
        print = new JMenu("Print");
        menuBar.add(print,2);

        fullReport=new JMenuItem("Full Report");
        fullReport.setEnabled(false);
        print.add(fullReport);

        finaleReport=new JMenuItem("Finale Report");
        print.add(finaleReport);
        //**************************************

                    lblSearch = new JLabel("Search");
                    lblSearch.setIcon(search);
                    panMenu.add(lblSearch);

                    txtSearch = new JTextField("");
                    txtSearch.setSize(10,100);
                    panMenu.add(txtSearch);

                    //btSearch = new JButton("Search");
                    //panMenu.add(btSearch);

                    lbluserName=new JLabel("  Logged as : "+dbLogging.UserName);
                    panMenu.add(lbluserName);

        lblResult = new JLabel("Event");
        lblResult.setEnabled(true);
        lblResult.setVisible(true);
        lblResult.setFont(lblFont);
            con.gridx = 0;
            con.gridy = 0;
            con.weightx = 1;
            con.weighty = 1;
            con.fill = GridBagConstraints.BOTH;
            //panComponents.add(lblResult, con);

        cmbEvent = new JComboBox();
        con.gridx = 0;
        con.gridy = 0;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(cmbEvent,con);

        btAddEvent = new JButton(" Event");
        btAddEvent.setIcon(add);
        btAddEvent.setBackground(panComponents.getBackground());
        btAddEvent.setBorder(null);
        con.gridx = 1;
        con.gridy = 0;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(btAddEvent,con);


        resultList = new JList();
        resultList.setVisible(true);
        resultList.setEnabled(true);
        resultList.setFixedCellWidth(40);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultList.setVisibleRowCount(4);
        resultList.setSelectionBackground(Color.BLACK);
        resultList.setSelectionForeground(Color.white);
        resultList.setFont(new Font(null,Font.PLAIN,18));
        con.gridx = 0;
        con.gridy = 1;
        con.weightx = 2;
        con.weighty = 1;
        con.gridheight = 10;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(new JScrollPane(resultList), con);



        lblTransaction =new JLabel("Transaction Number");
        lblTransaction.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 0;
        con.weightx = 1;
        con.weighty = 1;
        con.gridheight = 1;
        con.gridwidth = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblTransaction,con);

        lblPerson = new JLabel("Name");
        lblPerson.setFont(lblFont);
        lblPerson.setVisible(true);
        con.gridx = 2;
        con.gridy = 1;
        con.weightx = 1;
        con.weighty = 1;
        con.gridheight = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblPerson,con);


        /**
         *Add button Group
         */
        lblFromOrTo = new JLabel("Name");
        lblFromOrTo.setLayout(new FlowLayout());
        lblFromOrTo.setFont(lblFont);
        lblFromOrTo.setBackground(Color.cyan);
        lblFromOrTo.setVisible(false);
        con.gridx = 2;
        con.gridy = 1;
        con.weightx = 1;
        con.weighty = 1;
        con.gridheight = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblFromOrTo,con);

        rdSelect=new JRadioButton("Select",true);
        rdFrom=new JRadioButton("From");
        rdTo=new JRadioButton("To");

        rdSelect.addItemListener(new HabdlerClass("0"));
        rdTo.addItemListener(new HabdlerClass("1"));
        rdFrom.addItemListener(new HabdlerClass("2"));

        groupFromOrTo = new ButtonGroup();
        groupFromOrTo.add(rdSelect);
        groupFromOrTo.add(rdFrom);
        groupFromOrTo.add(rdTo);

        lblFromOrTo.add(rdSelect,0);
        lblFromOrTo.add(rdFrom,1);
        lblFromOrTo.add(rdTo,2);
        /**
         *End Adding button Group
         */


        lblEvent = new JLabel("Event");
        lblEvent.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 2;
        con.weightx = 1;
        con.weighty = 1;
        con.gridheight = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblEvent,con);

        lblHowMutch = new JLabel("Amount Rs.");
        lblHowMutch.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 3;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblHowMutch,con);

        lblSubject = new JLabel("Subject");
        lblSubject.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 4;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblSubject,con);

        lblDate = new JLabel("Date");
        lblDate.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 5;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblDate,con);

        lblTime = new JLabel("Time");
        lblTime.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 6;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblTime,con);

        lblBill = new JLabel("Bill Status");
        lblBill.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 7;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblBill,con);

        lblDescription = new JLabel("Description");
        lblDescription.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 8;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblDescription,con);

        lblreturn = new JLabel("Return Rs");
        lblreturn.setFont(lblFont);
        con.gridx = 2;
        con.gridy = 9;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.BOTH;
        panComponents.add(lblreturn,con);

        //txtJobnum,txtPerson,txtHowMutch,txtSubject,txtDate,txtTime;
        txtJobnum= new JTextField();
        txtJobnum.setFont(txtFieldFont);
        txtJobnum.setEnabled(false);
        txtJobnum.setVisible(true);
        con.gridx = 3;
        con.gridy = 0;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtJobnum,con);

        txtJobnum.setText(String.valueOf(dbAccount.getIndex("TRANSACTIONUMBER","artcircleaccount")));

        txtPerson  = new JTextField();
        txtPerson.setFont(txtFieldFont);
        txtPerson.setEnabled(false);
        txtPerson.setVisible(true);
        con.gridx = 3;
        con.gridy = 1;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtPerson,con);

        txtEvent = new JTextField();
        txtEvent.setEnabled(false);
        txtEvent.setVisible(true);
        txtEvent.setFont(txtFieldFont);
        con.gridx = 3;
        con.gridy = 2;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtEvent,con);

        cmbEventSelecter = new JComboBox();
        cmbEventSelecter.setVisible(false);
        cmbEventSelecter.setEnabled(false);
        con.gridx = 3;
        con.gridy = 2;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(cmbEventSelecter,con);

        int l=dbAccount.getIndex("EventindexNum","tableevent");
        String events[]=dbAccount.getEvents();
        System.out.println(l);

        for (int k=0;k<l-1;k++){
            //System.out.println(k+". "+events[k]);
            cmbEventSelecter.addItem(events[k]);
            cmbEvent.addItem(events[k]);
        }

        try {
            String[] arr = dbAccount.getSearchResults(cmbEvent.getSelectedItem().toString(), "");
            resultList.setListData(arr);
        }catch (Exception e){
            /**
             for (int j1=0;j1<=events.length;j1++){
             cmbEventSelecter.addItem(events[j1]);
             }

             */
        }
        txtHowMutch = new JTextField("0");
        txtHowMutch.setFont(txtFieldFont);
        txtHowMutch.setEnabled(false);
        con.gridx = 3;
        con.gridy = 3;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtHowMutch,con);

        txtSubject= new JTextField();
        txtSubject.setFont(txtFieldFont);
        txtSubject.setEnabled(false);
        txtSubject.setFont(new Font(null,Font.BOLD,18));
        con.gridx = 3;
        con.gridy = 4;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtSubject,con);

        txtDate = new JTextField();
        txtDate.setFont(txtFieldFont);
        txtDate.setEnabled(false);
        con.gridx = 3;
        con.gridy = 5;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtDate,con);

        txtTime = new JTextField();
        txtTime.setFont(txtFieldFont);
        txtTime.setEnabled(false);
        con.gridx = 3;
        con.gridy = 6;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtTime,con);

        //txtBllInfo,txtDescription,txtReturn
        txtBllInfo = new JTextField();
        txtBllInfo.setFont(txtFieldFont);
        txtBllInfo.setEnabled(false);
        con.gridx = 3;
        con.gridy = 7;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtBllInfo,con);

        txtDescription = new JTextField();
        txtDescription.setFont(txtFieldFont);
        txtDescription.setEnabled(false);
        con.gridx = 3;
        con.gridy = 8;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtDescription,con);


        txtReturn = new JTextField("0");
        txtReturn.setFont(txtFieldFont);
        txtReturn.setEnabled(false);
        con.gridx = 3;
        con.gridy = 9;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(txtReturn,con);

        btAddNew = new JButton("Add new Entry");
        btAddNew.setFont(lblFont);
        btAddNew.setBackground(Color.BLACK);
        btAddNew.setForeground(Color.white);
        con.gridx = 2;
        con.gridy = 10;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(btAddNew,con);

        btSave = new JButton("Save");
        btSave.setFont(lblFont);
        btSave.setBackground(Color.BLACK);
        btSave.setForeground(Color.WHITE);
        btSave.setEnabled(false);
        con.gridx = 3;
        con.gridy = 10;
        con.weightx = 1;
        con.weighty = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panComponents.add(btSave,con);

        btAddNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btAddNew.getText().equals("Add new Entry")){

                    txtJobnum.setText(String.valueOf(dbAccount.getIndex("TRANSACTIONUMBER","artcircleaccount")));
                    resultList.setEnabled(false);

                    txtPerson.setEnabled(true);txtPerson.setText("");
                    txtEvent.setVisible(false);txtEvent.setText("");
                    cmbEventSelecter.setVisible(true);
                    cmbEventSelecter.setEnabled(true);
                    txtHowMutch.setEnabled(true);txtHowMutch.setText("");
                    txtSubject.setEnabled(true);txtSubject.setText("");
                    txtBllInfo.setEnabled(true);txtBllInfo.setText("");
                    txtDescription.setEnabled(true);txtDescription.setText("");
                    txtReturn.setEnabled(true);txtReturn.setText("");
                    btSave.setEnabled(true);

                    lblPerson.setVisible(false);
                    lblFromOrTo.setVisible(true);

                    btSave.setText("Save");
                    btAddNew.setText("Cancel");

                    String date = new SimpleDateFormat("yyyy").format(new Date()) +"_"+
                            new SimpleDateFormat("MMMM").format(new Date())+"_"+
                            new SimpleDateFormat("dd").format(new Date());
                    String time = new SimpleDateFormat("HH").format(new Date()) +"_"+
                            new SimpleDateFormat("mm").format(new Date())+"_"+
                            new SimpleDateFormat("ss").format(new Date());

                    txtDate.setText(date);
                    txtTime.setText(time);

                }else if (btAddNew.getText().equals("Cancel")){

                    resultList.setEnabled(true);

                    txtPerson.setEnabled(false);txtPerson.setText("");
                    txtEvent.setVisible(true);txtEvent.setText("");
                    cmbEventSelecter.setVisible(false);
                    cmbEventSelecter.setEnabled(false);
                    txtHowMutch.setEnabled(false);txtHowMutch.setText("");
                    txtSubject.setEnabled(false);txtSubject.setText("");
                    txtBllInfo.setEnabled(false);txtBllInfo.setText("");
                    txtDescription.setEnabled(false);txtDescription.setText("");
                    txtReturn.setEnabled(false);txtReturn.setText("");
                    btSave.setEnabled(false);

                    lblPerson.setVisible(true);
                    lblFromOrTo.setVisible(false);

                    txtDate.setText("");
                    txtTime.setText(null);
                    btAddNew.setText("Add new Entry");
                    btSave.setText("Edit");
                }
            }
        });
        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (btSave.getText().equals("Save")){

                    int lastIndex;
                    String name,Event,Subject,Date,Time,BillStatus,Description;
                    Double Amount= Double.valueOf(0),RetAmount;
                    lastIndex= dbAccount.getIndex("TRANSACTIONUMBER","artcircleaccount");

                    boolean saved;

                    System.out.println("Selected option "+slctedOpt);
                    /**
                     * slctedOpt=0; not selected
                     * slctedOpt=2; from
                     * slctedOpt=1; to
                     */

                    if (slctedOpt==0){
                        JOptionPane.showMessageDialog(null,"Please choose a selection for name","Information",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        if (slctedOpt==1){
                            Amount = -1*Double.valueOf(txtHowMutch.getText());
                            System.out.println(Amount);
                        }if (slctedOpt==2){
                            Amount = Double.valueOf(txtHowMutch.getText());
                            System.out.println(Amount);
                        }
                        try {
                            name = txtPerson.getText();
                            //Amount = Double.valueOf(txtHowMutch.getText());
                            Event = cmbEventSelecter.getSelectedItem().toString();
                            Subject = txtSubject.getText();
                            Date = txtDate.getText();
                            Time = txtTime.getText();
                            BillStatus = txtBllInfo.getText();
                            Description = txtDescription.getText();

                            RetAmount = Double.valueOf(txtReturn.getText());

                            System.out.println(lastIndex + "," + name + "," + Event + "," + Subject + "," +
                                    Date + "," + Time + "," + BillStatus + "," + Description + "," + Amount + "," + RetAmount);

                            saved=dbAccount.addEntry(lastIndex,name,Event,Amount,Subject,Date,Time,BillStatus,Description,RetAmount,dbLogging.UserName);

                            if (!saved){
                                JOptionPane.showMessageDialog(null,"Data saved ","Saved",JOptionPane.PLAIN_MESSAGE);
                                resultList.setEnabled(true);
                                txtJobnum.setText(String.valueOf(dbAccount.getIndex("TRANSACTIONUMBER","artcircleaccount")));
                                resultList.repaint();
                                txtPerson.setEnabled(false);
                                txtPerson.setText(null);

                                txtEvent.setVisible(true);
                                txtEvent.setText(null);

                                cmbEventSelecter.setVisible(false);
                                cmbEventSelecter.setEnabled(false);

                                txtHowMutch.setEnabled(false);
                                txtHowMutch.setText("0");

                                txtSubject.setEnabled(false);
                                txtSubject.setText(null);

                                txtBllInfo.setEnabled(false);
                                txtBllInfo.setText(null);

                                txtDescription.setEnabled(false);
                                txtDescription.setText(null);

                                txtReturn.setEnabled(false);
                                txtReturn.setText("0");

                                btSave.setEnabled(false);

                                lblPerson.setVisible(true);
                                lblFromOrTo.setVisible(false);

                                txtDate.setText("");
                                txtTime.setText(null);

                                rdSelect.setSelected(true);
                                btAddNew.setText("Add new Entry");
                            }


                        }catch (Exception e1){
                            JOptionPane.showMessageDialog(null,"Error in reading data "+e1.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
                        }
                    }

                }else if (btSave.getText().equals("Edit")){

                    txtPerson.setEnabled(true);
                    txtEvent.setVisible(false);
                    cmbEventSelecter.setVisible(true);
                    cmbEventSelecter.setEnabled(true);
                    txtHowMutch.setEnabled(true);
                    txtSubject.setEnabled(true);
                    txtBllInfo.setEnabled(true);
                    txtDescription.setEnabled(true);
                    txtReturn.setEnabled(true);
                    btSave.setEnabled(true);
                    lblPerson.setVisible(false);
                    lblFromOrTo.setVisible(true);

                        if (Double.valueOf(txtHowMutch.getText())<0){
                            rdTo.setSelected(true);
                            Double amount=-1*Double.valueOf(txtHowMutch.getText());
                            txtHowMutch.setText(amount.toString());
                        }else{
                            rdFrom.setSelected(true);

                        }

                    btSave.setText("Update");
                    btAddNew.setText("Cancel");
                }else if (btSave.getText().equals("Update")){
                    Double amount= Double.valueOf(txtHowMutch.getText());
                    if (rdTo.isSelected()){
                        amount=-1*amount;
                        System.out.println("33");
                    }
                   int updt= dbAccount.updateData(txtPerson.getText(),cmbEventSelecter.getSelectedItem().toString(),amount ,txtSubject.getText(),txtBllInfo.getText(),txtDescription.getText(),Double.valueOf(txtReturn.getText()),dbLogging.UserName,Integer.valueOf(txtJobnum.getText()));
                    JOptionPane.showMessageDialog(null,"Your data Updated successfully","Updated",JOptionPane.PLAIN_MESSAGE);

                    btSave.setText("Edit");

                    txtPerson.setEnabled(false);
                    txtEvent.setVisible(true);
                    cmbEventSelecter.setVisible(false);
                    cmbEventSelecter.setEnabled(false);
                    txtHowMutch.setEnabled(false);
                    txtSubject.setEnabled(false);
                    txtBllInfo.setEnabled(false);
                    txtDescription.setEnabled(false);
                    txtReturn.setEnabled(false);
                    btSave.setEnabled(false);
                    lblPerson.setVisible(true);
                    lblFromOrTo.setVisible(false);

                    resultList.repaint();

                }

            }
        });

        btAddEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean seved=true;
                String eventName = JOptionPane.showInputDialog(null,"Enter new Event ","Add event",JOptionPane.QUESTION_MESSAGE);
                System.out.println(eventName);

                if (!( (eventName.equals("")) || (eventName.equals(null))) ){
                    seved= dbAccount.addNewEvent(eventName);
                }else {
                    JOptionPane.showMessageDialog(null,"Not Saved ","Rejected",JOptionPane.PLAIN_MESSAGE);
                }
                System.out.println("Saved "+seved);

                if (!seved){
                    cmbEvent.addItem(eventName);
                    cmbEventSelecter.addItem(eventName);
                }

                /**
                int l=dbAccount.getIndex("EventindexNum","tableevent");
                String events[]=dbAccount.getEvents();
                System.out.println(l);

                cmbEvent.addItemListener(null);
                for (int k=0;k<l-1;k++){
                    //System.out.println(k+". "+events[k]);
                    cmbEventSelecter.addItem(events[k]);
                    cmbEvent.addItem(events[k]);
                }
                 */
            }
        });

        txtSearch.addKeyListener(new KeyAdapter() {
            String[] res=null;
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                res = dbAccount.getSearchResults(cmbEvent.getSelectedItem().toString(),txtSearch.getText());

                    resultList.setListData(res);


            }
        });
        cmbEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] arr = dbAccount.getSearchResults(cmbEvent.getSelectedItem().toString(),txtSearch.getText());
                resultList.setListData(arr);
            }
        });

        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectedString = (String) resultList.getSelectedValue();
                String event = cmbEvent.getSelectedItem().toString();
                String[] data = dbAccount.getDetails(event,selectedString);

                txtJobnum.setText(data[0]);
                txtPerson.setText(data[1]);
                txtEvent.setText(data[2]);
                cmbEventSelecter.setSelectedItem(data[2]);
                txtHowMutch.setText(data[3]);
                txtSubject.setText(data[4]);
                txtDate.setText(data[5]);
                txtTime.setText(data[6]);
                txtBllInfo.setText(data[7]);
                txtDescription.setText(data[8]);
                txtReturn.setText(data[9]);

            }
        });

        resultList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btSave.setText("Edit");
                btSave.setEnabled(true);

                txtPerson.setEnabled(false);
                txtEvent.setVisible(true);
                cmbEventSelecter.setVisible(false);
                cmbEventSelecter.setEnabled(false);
                txtHowMutch.setEnabled(false);
                txtSubject.setEnabled(false);
                txtBllInfo.setEnabled(false);
                txtDescription.setEnabled(false);
                txtReturn.setEnabled(false);
                lblPerson.setVisible(true);
                lblFromOrTo.setVisible(false);

            }
        });

        balanceReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance checkBalance = new checkBalance();
                checkBalance.setVisible(true);
                checkBalance.setResizable(false);
                checkBalance.setSize(600,400);
                checkBalance.setDefaultCloseOperation(HIDE_ON_CLOSE);
            }
        });

        fullReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count=dbAccount.checkEventcount();
                System.out.println("event number "+count);
                String[] events=dbAccount.getEvents();
                for (int x=0;x<count;x++){
                    System.out.println(x+","+events[x]);

                }

            }
        });

        finaleReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printFinaleReport printFinaleReport = new printFinaleReport();
                printFinaleReport.setDefaultCloseOperation(HIDE_ON_CLOSE);
                printFinaleReport.setTitle("Print Finale Report");
                printFinaleReport.setSize(600,200);
                printFinaleReport.setVisible(true);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }

    private class HabdlerClass implements ItemListener{
        private String slcted;
        public HabdlerClass(String selected){
            slcted = selected;
        }
        @Override
        public void itemStateChanged(ItemEvent e) {
            slctedOpt=Integer.valueOf(slcted);
        }
    }
}
