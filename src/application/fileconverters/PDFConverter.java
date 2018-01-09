package application.fileconverters;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFConverter implements ParserINTF {

	private String content = "";

	/**
	 * 
	 */
	public void setContent(String path) {
		File file = new File(path);
		PDDocument document;
		try {
			document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			this.content = pdfStripper.getText(document);
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 */
	public String getContent() {
		return this.content;
	}

}
