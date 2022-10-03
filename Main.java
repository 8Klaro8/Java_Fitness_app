import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {

        String PASSWORD = System.getenv("PASSWORD");

        // MyFrame frame = new MyFrame();

        ConnectToDB db = new ConnectToDB();
        Connection conn = db.connect_to_db("tutDB", "postgres", PASSWORD);
        // db.createTable(conn, "employee");
        // db.insert_row(conn, "employee", "employee_2", "Jani street 40.");
        // db.get_by_name(conn, "employee", "employee_3");
        // db.delete_row(conn, "employee", 2);
        // db.read_data(conn, "employee");
        // db.update_name(conn, "employee", "employee_1", "employee_8");

    }
}