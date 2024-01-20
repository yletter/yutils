import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class FileToQR {

    public static void main(String[] args) throws IOException, WriterException {
        // Get the file to convert
        File file = Paths.get("C:\\Users\\yuvaraj\\Desktop\\test.txt").toFile();
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\yuvaraj\\Desktop\\test.txt"));

        // Create a StringBuilder object to store the contents of the file
        StringBuilder sb = new StringBuilder();

        // Read the file line by line and append each line to the StringBuilder object
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        // Close the BufferedReader object
        reader.close();

        // Convert the StringBuilder object to a string
        String fileAsString = sb.toString();

        // Generate the QR code
        BitMatrix matrix = new MultiFormatWriter().encode(fileAsString, BarcodeFormat.QR_CODE, 500, 500);

        // Write the QR code to a file
        MatrixToImageWriter.writeToFile(matrix, "PNG", Paths.get("C:\\Users\\yuvaraj\\Desktop\\test.png").toFile());
    }
}
