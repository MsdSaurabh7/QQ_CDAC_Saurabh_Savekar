package com.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileOutputStream;

public class OCRtoDoc {

    public static void main(String[] args) {
        File imageFile = new File("path_to_your_image_file");
        String outputDocFile = "output.docx";

        try {
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("path_to_tessdata_folder");
            String extractedText = tesseract.doOCR(imageFile);

            // Save extracted text to Word document
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(extractedText);

            FileOutputStream fos = new FileOutputStream(outputDocFile);
            document.write(fos);
            fos.close();

            System.out.println("OCR completed and text saved to " + outputDocFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

