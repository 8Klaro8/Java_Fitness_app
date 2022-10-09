import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ChangeProfile extends JFrame {

    // labels & buttons
    JLabel testLabel;
    JScrollPane scrPanel;

    ChangeProfile() {

        JPanel container = new JPanel();
        scrPanel = new JScrollPane(container);
        this.getContentPane().add(scrPanel);
        container.setLayout(new GridBagLayout());

        // this.frame settngs
        this.setTitle("My IT app");
        this.setVisible(true);
        this.setBounds(10, 10, 370, 500);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);

        // labels & buttons
        testLabel = new JLabel("TEST");

        // set icon
        ImageIcon image = new ImageIcon("Logo/lgo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.WHITE);

        LoginFrame();

    }

    // Sets up login screen/ frame
    public void LoginFrame() {
        this.setLocationAndSize();
    }



    // Sets the components size and location
    public void setLocationAndSize() {
        testLabel.setSize(new Dimension(100, 50));
        testLabel.setLocation(145, 100);

    }





}
