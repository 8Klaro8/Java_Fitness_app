import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.*;

public class MyFrame extends JFrame implements ActionListener, LoginFormInterFace {

    // estabilish connection to DB
    ConnectToDB db = new ConnectToDB();
    Connection conn = db.connect_to_db("accounts", "postgres", System.getenv("PASSWORD"));

    // initialize container
    Container container;
    HashPassword hashPWD;
    String name;
    public final String TABLE_NAME = "my_users";
    public final String CURR_USER_FILE_PATH = "current_user/current_user.txt";

    // labels & buttons
    JLabel userLabel;
    JLabel passwordLabel;
    JTextField userTextfield;
    JPasswordField passwordTextfield;
    JButton loginButton;
    JButton registerButton;
    JCheckBox showPassword;

    MyFrame() {

        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));

        // this.frame settngs
        this.setTitle("My IT app");
        this.setVisible(true);
        this.setBounds(10, 10, 370, 500);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);

        // labels & buttons
        userLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        userTextfield = new JTextField();
        passwordTextfield = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        showPassword = new JCheckBox("Show Password");

        // set icon
        ImageIcon image = new ImageIcon("Logo/lgo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.WHITE);

        LoginFrame();

    }

    // Sets up login screen/ frame
    public void LoginFrame() {
        this.setLayoutManager();
        this.setLocationAndSize();
        this.addComponentsToContainer();
        this.addActionEvent();
    }

    // sets the layout to null - NOT USED CURRENLTY
    public void setLayoutManager() {
        this.container.setLayout(null);
    }

    // Sets the components size and location
    public void setLocationAndSize() {
        userLabel.setSize(new Dimension(100, 50));
        userLabel.setLocation(145, 100);
        passwordLabel.setSize(new Dimension(100, 50));
        passwordLabel.setLocation(145, 160);
        userTextfield.setSize(new Dimension(150, 30));
        userTextfield.setLocation(100, 140);
        passwordTextfield.setSize(new Dimension(150, 30));
        passwordTextfield.setLocation(100, 200);
        loginButton.setSize(new Dimension(100, 30));
        loginButton.setLocation(125, 280);
        showPassword.setSize(new Dimension(150, 30));
        showPassword.setLocation(95, 230);
        registerButton.setSize(new Dimension(100, 30));
        registerButton.setLocation(125, 320);
    }

    // Adds all components to 'container'
    public void addComponentsToContainer() {
        this.container.add(userLabel);
        this.container.add(passwordLabel);
        this.container.add(userTextfield);
        this.container.add(passwordTextfield);
        this.container.add(loginButton);
        this.container.add(registerButton);
        this.container.add(showPassword);
    }

    // add action event
    public void addActionEvent() {
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText;
            userText = userTextfield.getText();
            String givenPWD = String.valueOf(passwordTextfield.getPassword());
            hashPWD = new HashPassword();

            if (userText.isEmpty() || givenPWD.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and Password fields can't be empty.");
            }

            String storedPassword = db.get_hash_by_username(conn, TABLE_NAME, userText);
            try {
                if (hashPWD.validatePassword(givenPWD, storedPassword)) {
                    JOptionPane.showMessageDialog(this, "Login Successful!");
                    userTextfield.setText("");
                    passwordTextfield.setText("");
                    // write current user to file
                    Path fileName = Path.of(CURR_USER_FILE_PATH);
                    Files.writeString(fileName, userText);
                    // LOGIN
                    // change to home page
                    this.dispose();
                    new HomeSite();
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong password");
                    userTextfield.setText("");
                    passwordTextfield.setText("");
                }
            } catch (Exception err) {
                // check if user is NOT exists and give respond to it
                try {
                    ResultSet userFount =  db.get_by_name(conn, "my_users", userText);
                    if (userFount == null) {
                        JOptionPane.showMessageDialog(this, "The username: " + userText + " is not registered yet.");
                        userTextfield.setText("");
                        passwordTextfield.setText("");
                    }
                } catch (Exception err_2) {
                    System.out.println(err_2.getMessage());
                }
                System.out.println(err.getMessage());
            }

        } else if (e.getSource() == showPassword) {
            ShowPassword(showPassword);
        } else if (e.getSource() == registerButton) {
            // dispose login page
            this.dispose();
            // creating register frame/page
            new RegisterFrame();
        }
    }

    @Override
    public void ShowPassword(JCheckBox showPassword) {
        if (showPassword.isSelected()) {
            passwordTextfield.setEchoChar((char) 0);
        } else {
            passwordTextfield.setEchoChar('*');
        }
    }
}

/* Refresh window -> */
// SwingUtilities.updateComponentTreeUI(this);
