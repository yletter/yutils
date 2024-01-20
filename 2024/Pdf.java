package yuv;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.pdmodel.PDPage;

public class Pdf {

public static final String DIR = "src/main/resources/";

public static final String SRC_FILE = "pdfcoffee.com_imo-class-3-3-pdf-free.pdf";

public static final String TARGET_FILE = "New_" + SRC_FILE;

public static final ArrayList<Integer> array = new ArrayList<Integer>(Arrays.asList(3, 4, 5));

public static void main(String[] args) throws IOException {

File f = new File(DIR + SRC_FILE);

new Pdf()

.processFiles(f, array);

}

private void processFiles(File pdf, ArrayList<Integer> array) throws IOException {

try (PDDocument document = new PDDocument()) {

PDDocument pdDoc = PDDocument.load(pdf);

Integer count = 1;

for (PDPage page: pdDoc.getPages()) {

System.out.println(count); if (!array.contains(count)) {

document.addPage(page);

System.out.println("Inside");

}

count++;

}

File file = outputFile();

document.save(file);

}

}

private File outputFile() {

return new File(DIR + TARGET_FILE);

}

}