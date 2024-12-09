import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void saveToFile(String data) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                writer.write(data);
            }
        }
    }
}
