import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeProfile extends JFrame implements ActionListener {
    static final int COLUMNS = 3;
    static final int ROWS = 15;
    JButton profPicButton;
    JButton testButton;
    public final String USER_FILE_PATH = "current_user/current_user.txt";
    JPanel panel;

    public ChangeProfile() throws IOException {

        setTitle("MY IT app");
        setLayout(new BorderLayout());
        panel = createPanel();
        add(BorderLayout.CENTER, new JScrollPane(panel));
        setBounds(10, 10, 370, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // set icon
        ImageIcon image = new ImageIcon("Logo/lgo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.WHITE);
    }

    public JPanel createPanel() {
        // TODO: Display prof. pics in 3 columns
        JPanel panel = new JPanel();
        // getting all prof. pic path
        ArrayList<String> all_prof_path = get_all_prof_pic_path();
        // transform arraylist to object[] then String[]
        Object[] objAllPathArray = all_prof_path.toArray();
        String[] strAllPathArray = new String[objAllPathArray.length];
        int numOfPics = strAllPathArray.length;
        for (int i = 0; i < objAllPathArray.length; i++) {

            strAllPathArray[i] = String.valueOf(objAllPathArray[i]);
        }

        panel.setLayout(new GridLayout(ROWS, COLUMNS, 10, 10));
        // crreating test button
        testButton = new JButton();
        testButton.setSize(new Dimension(100, 100));
        testButton.setLocation(100, 100);
        panel.add(testButton);
        testButton.addActionListener(this);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < numOfPics; j++) {
                profPicButton = new JButton();
                String lastElement = strAllPathArray[strAllPathArray.length - 1];
                String currElement = strAllPathArray[j];
                if (lastElement.equals(currElement)) {
                    return panel;
                }
                profPicButton.setActionCommand("String.valueOf(j)");

                // Set the current image to a JButton and adds it to panel
                profPicButton = set_prof_pic_images(strAllPathArray[j], profPicButton);
                // profPicButton.addActionListener(e -> onButtnClick(profPicButton.getIcon(),
                // profPicButton));
                // profPicButton.addActionListener(numberAction);
                profPicButton.setActionCommand("" + profPicButton.getIcon());
                profPicButton.addActionListener(numberAction);

                panel.add(profPicButton);
            }
        }

        return panel;
    }

    public JButton set_prof_pic_images(String picPath, JButton button) {
        ImageIcon imageIcon = new ImageIcon(picPath);
        Image resizedImage = imageIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resizedImage, imageIcon.getDescription());
        button.setIcon(imageIcon);
        return button;
    }

    // Loop thru profile pics and gets their paths and returns it
    public static ArrayList<String> get_all_prof_pic_path() {
        ArrayList<String> allProfilePicPath = new ArrayList<String>();
        File dir = new File("ProfilePics");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                allProfilePicPath.add(child.getPath());
            }
            return allProfilePicPath;
        } else {
            return allProfilePicPath;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == testButton) {
            System.out.println("YEES");
        }
        if (e.getSource() == profPicButton) {
            // find clicked button
            System.out.println("YES");
            JButton clickedButton = (JButton) e.getSource();
            System.out.println(clickedButton);
            ConnectToDB db = new ConnectToDB();
            Connection conn = db.connect_to_db("accounts", "postgres", System.getenv("PASSWORD"));
            System.out.println("YEEES");

            // TODO: get right image path to update to
            String iconOfButton = profPicButton.getIcon().toString();
            try {
                db.set_prof_pic_path(conn, "my_users", iconOfButton, get_current_user());

            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }

    }

    public String get_current_user() throws IOException {
        Path fileName = Path.of(USER_FILE_PATH);
        return Files.readString(fileName);

    }

    Action numberAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // display.setCaretPosition( display.getDocument().getLength() );
            String profPicPath = e.getActionCommand();
            ConnectToDB db = new ConnectToDB();
            Connection conn = db.connect_to_db("accounts", "postgres", System.getenv("PASSWORD"));
            try {
                db.set_prof_pic_path(conn, "my_users", profPicPath, get_current_user());
                go_back_to_homesite();
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
    };

    public void go_back_to_homesite() throws IOException{
        this.dispose();
        new HomeSite();
    }

}
