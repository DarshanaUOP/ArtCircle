import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by acer on 13-Jan-18.
 */
public class CreateNew extends JFrame{

    private JPanel pnback;
    private JLabel lbImg,lbUser,lbEmail,lbPass,lbReenter;
    private JTextField txtUser,txtEmail;
    private JPasswordField pwPass,pwReEnter;
    private JButton btCreate;

    GridBagConstraints g = new GridBagConstraints();

    public CreateNew(){
        super("Create new Account");
        Font font = new Font(null,Font.PLAIN,14);
        ImageIcon img = new ImageIcon(getClass().getResource("createNew.png"));

        pnback = new JPanel(new GridBagLayout());
        add(pnback);

        lbImg = new JLabel();
        lbImg.setIcon(img);
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.BOTH;
        pnback.add(lbImg,g);

        lbUser = new JLabel("Select a User Name");
        g.gridx = 0;
        g.gridy = 1;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnback.add(lbUser,g);

        txtUser = new JTextField();
        g.gridx = 1;
        g.gridy = 1;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnback.add(txtUser,g);

        lbPass = new JLabel("Enter a password");
        g.gridx = 0;
        g.gridy = 2;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnback.add(lbPass,g);

        pwPass = new JPasswordField();
        g.gridx = 1;
        g.gridy = 2;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnback.add(pwPass,g);

        lbReenter = new JLabel("Re-Enter Password");
        g.gridx = 0;
        g.gridy =3;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnback.add(lbReenter,g);

        pwReEnter = new JPasswordField();
        g.gridx = 1;
        g.gridy = 3;
        g.gridwidth=1;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnback.add(pwReEnter,g);

        btCreate = new JButton("Create Account");
        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth=2;
        g.gridheight = 1;
        g.weightx=1;
        g.weighty=1;
        g.fill=GridBagConstraints.HORIZONTAL;
        pnback.add(btCreate,g);

        btCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName, pasword,reEnteredPassword;

                userName = txtUser.getText();
                pasword = String.copyValueOf(pwPass.getPassword());
                reEnteredPassword =String.copyValueOf(pwReEnter.getPassword());

                System.out.println(pasword+","+reEnteredPassword);

                if (pasword.length()>=4 && reEnteredPassword.length()>=4){

                    if (pasword.equals(reEnteredPassword)){
                        //Create New Account;

                        dbLogging.createAccount(userName,pasword);
                        System.out.println("Done");

                    }else{
                        JOptionPane.showMessageDialog(null,"Please enter correct password","Wrong Password",JOptionPane.PLAIN_MESSAGE);
                        pwPass.requestFocus();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Minimum of 4 characters in length","Wrong Password",JOptionPane.PLAIN_MESSAGE);
                    pwPass.requestFocus();

                }

            }
        });
    }
}
