
import java.awt.Color;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.text.Style;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        // test .env

        // test hash
        // HashPassword hashPASS = new HashPassword();
        // String passWord = "sa";
        // String stroredPass = hashPASS.generateStorngPasswordHash(passWord);
        // try {
        //     boolean valid = hashPASS.validatePassword(passWord, stroredPass);
        //     System.out.println(valid);

        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

        // String PASSWORD = System.getenv("PASSWORD");

        MyFrame frame = new MyFrame();
        // RegisterFrame regFrame = new RegisterFrame();

        ConnectToDB db = new ConnectToDB();
        Connection conn = db.connect_to_db("accounts", "postgres", System.getenv("PASSWORD"));
        String hashedPASS = db.get_hash_by_username(conn, "users", "12");
        System.out.println(hashedPASS);

        // System.getenv("PASSWORD"));
        // db.create_account_table(conn, "users");
        // db.insert_row(conn, "employee", "employee_2", "Jani street 40.");
        // db.get_by_name(conn, "employee", "employee_3");
        // db.delete_row(conn, "employee", 2);
        // db.read_data(conn, "employee");
        // db.update_name(conn, "employee", "employee_1", "employee_8");

    }
}