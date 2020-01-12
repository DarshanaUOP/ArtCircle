import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by acer on 20-Jan-18.
 */
public class checkBalance extends JFrame{

    private JPanel pnBack;
    private JLabel lbimg,lbincome,lbOutGoing,lbTot,lbincomeRs,lbOutGoingRs,lbTotRs;
    private JButton btOk;

    GridBagConstraints g=new GridBagConstraints();

    public checkBalance(){
        super("Account Balance");
        ImageIcon img = new ImageIcon(getClass().getResource("checkBalance.png"));
        Font lbFont =new Font(null,Font.BOLD,22);
        pnBack = new JPanel(new GridBagLayout());
        add(pnBack);

        lbimg=new JLabel("Account Balance");
        lbimg.setFont(new Font(null,Font.BOLD,18));
        lbimg.setIcon(img);
        lbimg.setFont(lbFont);
        g.gridx = 0;
        g.gridy = 0;
        g.weighty = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(lbimg,g);

        lbincome=new JLabel("Deposits");
        lbincome.setFont(lbFont);
        g.gridx = 0;
        g.gridy = 1;
        g.weighty = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(lbincome,g);

        lbincomeRs=new JLabel("");
        lbincomeRs.setFont(lbFont);
        g.gridx = 1;
        g.gridy = 1;
        g.weighty = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(lbincomeRs,g);

        lbOutGoing=new JLabel("Withdrawals");
        lbOutGoing.setFont(lbFont);
        g.gridx = 0;
        g.gridy = 2;
        g.weighty = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(lbOutGoing,g);

        lbOutGoingRs=new JLabel("");
        lbOutGoingRs.setFont(lbFont);
        g.gridx = 1;
        g.gridy = 2;
        g.weighty = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(lbOutGoingRs,g);

        lbTot=new JLabel("Total Balance");
        lbTot.setFont(lbFont);
        lbTot.setForeground(Color.red);
        g.gridx = 0;
        g.gridy = 3;
        g.weighty = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(lbTot,g);

        lbTotRs=new JLabel("");
        lbTotRs.setFont(lbFont);
        lbTotRs.setForeground(Color.red);
        g.gridx = 1;
        g.gridy = 3;
        g.weighty = 1;
        g.weightx = 1;
        g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(lbTotRs,g);

        btOk = new JButton("Check Balance");
        g.gridx = 0;
        g.gridy = 4;
        g.weighty = 1;
        g.weightx = 1;
        //g.fill = GridBagConstraints.HORIZONTAL;
        pnBack.add(btOk,g);

        btOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int balanceArrayLength=dbAccount.checkBalanceLength();
                Double[][] balance=new Double[2][balanceArrayLength];

                balance = dbAccount.checkAccountBalance();

                Double[][] finalBalance = balance;
                new Thread(new Runnable() {
                    Double in= Double.valueOf(0),out= Double.valueOf(0),tot=Double.valueOf(0);
                    double deposit,withdrawals;

                    @Override
                    public void run() {
                        for (int i=0;i<=balanceArrayLength-1;i++){

                            in = finalBalance[0][i];
                            out =finalBalance[1][i];
                            if(in<=0){
                                withdrawals=withdrawals+in;
                            }else {
                                deposit=deposit+in;
                            }
                            deposit=deposit+out;

                            tot=Math.abs(deposit+withdrawals);

                            lbincomeRs.setText("Rs "+String.valueOf(deposit));
                            lbOutGoingRs.setText("Rs "+String.valueOf(-1*withdrawals));
                            lbTotRs.setText("Rs "+tot.toString());
                            lbTot.setText("Total Balance ");

                            try{Thread.sleep(100);} catch (Exception e){}
                        }

                    }
                }).start();

            }
        });

    }

}
