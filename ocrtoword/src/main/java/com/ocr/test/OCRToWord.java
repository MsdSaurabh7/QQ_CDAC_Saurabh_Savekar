package com.ocr.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class OCRToWord {

    public static void main(String[] args) {
        String inputPdfPath = "D:/Java/File For OCR.pdf";
        String outputDocPath = "D:/Java/File for Ocr converted.docx";

        try {
            // Extract text from the input PDF
            String extractedText = extractTextFromPDF(inputPdfPath);

            // Save the extracted text to a Word document
            saveToWord(extractedText, outputDocPath);

            System.out.println("Text extracted from PDF and saved to Word document successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractTextFromPDF(String pdfPath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }

    private static void saveToWord(String text, String outputPath) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.createRun().setText(text);

        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            document.write(out);
        }
    }
}


