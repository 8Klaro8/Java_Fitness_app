import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.IIOException;
import javax.swing.JFrame;

public class LogoutFunction {
    public void logout_from_app(JFrame thisFrame) throws IOException {
        thisFrame.dispose();
        new MyFrame();
        Path fileName = Path.of("current_user/current_user.txt");
        Files.writeString(fileName, "");
    }
}
