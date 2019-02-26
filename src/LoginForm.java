import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by acer on 24-Dec-17.
 */
public class LoginForm extends JFrame{

    private JPanel backpan;
    private ImageIcon bgImg;
    private JLabel imgLable,lblartCircle,lblUser,lblPass;
    private JTextField userName;
    private JPasswordField password;
    private JButton login,createNew;

    GridBagConstraints con1= new GridBagConstraints();


    public LoginForm(){

        Font font = new Font(null,Font.PLAIN,18);
        Font lblFont = new Font(null,Font.BOLD,16);
        Color fontColor = Color.orange;

        bgImg = new ImageIcon(getClass().getResource("artCircle.png"));
        backpan = new JPanel(new GridBagLayout());
        add(backpan);

        imgLable = new JLabel();
        imgLable.setLayout(new GridBagLayout());
        imgLable.setIcon(bgImg);
        con1.gridx = 0;
        con1.gridy = 0;
        con1.weightx = 4;
        con1.weighty = 4;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill=GridBagConstraints.BOTH;
        backpan.add(imgLable,con1);

        lblartCircle = new JLabel("Art Circle - Faculty of Engineering");
        lblartCircle.setFont(new Font(null,Font.BOLD,30));
        lblartCircle.setForeground(Color.BLUE);
        con1.gridx = 0;
        con1.gridy = 0;
        con1.weightx = 1;
        con1.weighty = 1;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill = GridBagConstraints.HORIZONTAL;
        imgLable.add(lblartCircle,con1);

        lblUser = new JLabel("User Name");
        lblUser.setFont(lblFont);
        lblUser.setForeground(fontColor);
        con1.gridx = 1;
        con1.gridy = 1;
        con1.weightx = 0;
        con1.weighty = 1;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill = GridBagConstraints.HORIZONTAL;
        imgLable.add(lblUser,con1);

        userName = new JTextField("");
        userName.setVisible(true);
        userName.setFont(font);
        con1.gridx = 2;
        con1.gridy = 1;
        con1.weightx = 0;
        con1.weighty = 1;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill = GridBagConstraints.HORIZONTAL;
        imgLable.add(userName,con1);

        lblPass = new JLabel("Password");
        lblPass.setFont(lblFont);
        lblPass.setForeground(fontColor);
        con1.gridx = 1;
        con1.gridy = 2;
        con1.weightx = 1;
        con1.weighty = 1;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill = GridBagConstraints.HORIZONTAL;
        imgLable.add(lblPass,con1);

        password = new JPasswordField("");
        password.setVisible(true);
        password.setEnabled(true);
        password.setFont(font);
        con1.gridx = 2;
        con1.gridy = 2;
        con1.weightx = 1;
        con1.weighty = 1;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill = GridBagConstraints.HORIZONTAL;
        imgLable.add(password,con1);


        login = new JButton("Log in");
        login.setVisible(true);
        con1.gridx = 1;
        con1.gridy = 3;
        con1.weightx = 1;
        con1.weighty = 1;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill = GridBagConstraints.HORIZONTAL;
        imgLable.add(login,con1);

        createNew = new JButton("Create New Account");
        login.setVisible(true);
        con1.gridx = 2;
        con1.gridy = 3;
        con1.weightx = 1;
        con1.weighty = 1;
        con1.gridheight = 1;
        con1.gridwidth = 1;
        con1.fill = GridBagConstraints.HORIZONTAL;
        imgLable.add(createNew,con1);

        createNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CreateNew createNew = new CreateNew();
                createNew.setDefaultCloseOperation(HIDE_ON_CLOSE);
                createNew.setSize(500,300);
                createNew.setVisible(true);
                createNew.setResizable(false);
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String uName,pass;
                uName = userName.getText();
                pass = String.copyValueOf(password.getPassword());

                System.out.println(pass+","+uName);

                boolean logKey= dbLogging.checkAccount(uName,pass);
                if (logKey){

                    password.setText(null);

                    GUI gui= new GUI();
                    gui.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    gui.setSize(1000,700);
                    gui.setEnabled(true);
                    gui.setVisible(true);
                    gui.setResizable(false);
                    gui.setTitle("Art Circle");

                }else {
                    JOptionPane.showMessageDialog(null,"wrong user name or password ","Access Denied",JOptionPane.WARNING_MESSAGE);
                    userName.requestFocus();
                }
            }
        });
    }
}
