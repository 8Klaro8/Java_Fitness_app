import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {

        // initialize container
        Container container;

        //labels & buttons
        JLabel userLabel;
        JLabel passwordLabel;
        JTextField userTextfield;
        JPasswordField passwordTextfield;
        JButton loginButton;
        JButton registerButton;
        JCheckBox showPassword;

    MyFrame(){

        container = getContentPane();

        // this.frame settngs
        this.setTitle("My IT app");
        this.setVisible(true);
        this.setBounds(10,10,370,500);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);

        //labels & buttons
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

    // sets the layout to null
    public void setLayoutManager() {
        this.container.setLayout(null);
    }

    // Sets the components size and location
    public void setLocationAndSize() {
        this.userLabel.setBounds(140,100,200,150);
        this.passwordLabel.setBounds(140,150,200,150);
        this.userTextfield.setBounds(120, 190, 100, 20);
        this.passwordTextfield.setBounds(120, 240, 100, 20);
        this.loginButton.setBounds(130,300,80,30);
        this.registerButton.setBounds(111,350,120,30);
        this.showPassword.setBounds(230,235,120,30);

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
            String pwdText = "";
            char[] pwdCharArray;
            userText = userTextfield.getText();
            pwdCharArray = passwordTextfield.getPassword();
            for (int i = 0; i < pwdCharArray.length; i++) {
                pwdText += pwdCharArray[i];
            }
            if (userText.equals("lol") && pwdText.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                userTextfield.setText("");
                passwordTextfield.setText("");
            }
            else{
                JOptionPane.showMessageDialog(this, "Wrong username or password");
                userTextfield.setText("");
                passwordTextfield.setText("");
            }
        }
        else if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordTextfield.setEchoChar((char)0);
            }
            else{
                passwordTextfield.setEchoChar('*');
            }
        }
    }
}


/* Refresh window -> */
// SwingUtilities.updateComponentTreeUI(this);
