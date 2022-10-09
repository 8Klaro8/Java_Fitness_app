import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.plaf.metal.OceanTheme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.awt.image.BufferedImage;

public class HomeSite extends JFrame implements ActionListener {

    // initialize container
    Container container;
    JLabel profPicLabel;
    JLabel labelImage;
    SetProfileImage setProf = new SetProfileImage();


    HomeSite() throws IOException {

        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));

        // this.frame settngs
        this.setTitle("My IT app");
        this.setVisible(true);
        this.setBounds(10, 10, 370, 500);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);

        // labels & buttons
        profPicLabel = new JLabel();

        // add image to label
        // TODO insert here current prof pic path from db
        ConnectToDB db = new ConnectToDB();
        Connection conn = db.connect_to_db("accounts", "postgres", System.getenv("PASSWORD"));
        // read current user from txt file
        Path fileName = Path.of("current_user/current_user.txt");
        String currentUser = Files.readString(fileName);
        // TODO get current user's username who is logged in
        String baseProfPic = db.get_prof_pic_path(conn, "my_users", currentUser);
        profPicLabel = setProf.setBasicProfPic(profPicLabel, baseProfPic);

        // set icon
        ImageIcon image = new ImageIcon("Logo/lgo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.WHITE);

        HomeSiteFrame();

    }

    // Sets up login screen/ frame
    public void HomeSiteFrame() {
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
        profPicLabel.setSize(new Dimension(100, 100));
        profPicLabel.setLocation(135, 30);
    }

    // Adds all components to 'container'
    public void addComponentsToContainer() {
        this.container.add(profPicLabel);
    }

    // add action event
    public void addActionEvent() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

/* Refresh window -> */
// SwingUtilities.updateComponentTreeUI(this);
