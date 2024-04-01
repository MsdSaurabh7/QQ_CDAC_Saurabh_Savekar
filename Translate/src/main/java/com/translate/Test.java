package com.translate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        try {
            // Load PDF document
            PDDocument document = PDDocument.load(new File("input.pdf"));

            // Extract text from specific rows
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(1); // Adjust as per your PDF structure
            pdfStripper.setEndPage(1); // Adjust as per your PDF structure
            String pageText = pdfStripper.getText(document);

            String[] rows = pageText.split("\n");

            // Extract text from row no. (4), (7), and (8)
            String row4Text = rows.length > 3 ? rows[3] : "";
            String row7Text = rows.length > 6 ? rows[6] : "";
            String row8Text = rows.length > 7 ? rows[7] : "";

            // Translate Marathi text to English
            String translatedRow4 = translateText(row4Text);
            String translatedRow7 = translateText(row7Text);
            String translatedRow8 = translateText(row8Text);

            // Write translated text to CSV file
            try (PrintWriter writer = new PrintWriter(new FileWriter("output.csv"))) {
                writer.println("Row No.,Marathi Text,Translated Text");
                writer.println("4," + row4Text + "," + translatedRow4);
                writer.println("7," + row7Text + "," + translatedRow7);
                writer.println("8," + row8Text + "," + translatedRow8);
            }

            System.out.println("Translation and CSV creation completed successfully!");

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String translateText(String text) throws IOException {
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage("en"));
        return translation.getTranslatedText();
    }
}

