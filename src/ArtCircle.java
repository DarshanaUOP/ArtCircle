import javax.swing.*;

/**
 * Created by acer on 23-Dec-17.
 */
public class ArtCircle {
    public static void main(String[] args) {

        dbLogging dbLogging = new dbLogging();
        dbLogging.dbExists();

        dbAccount dbAccount = new dbAccount();
        dbAccount.dbArtCircleExists();

        LoginForm loginForm = new LoginForm();
        loginForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginForm.setSize(960,580);
        loginForm.setVisible(true);
        loginForm.setResizable(false);
        loginForm.setTitle("Log in");


    }
}
