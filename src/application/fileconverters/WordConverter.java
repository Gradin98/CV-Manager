package application.fileconverters;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class WordConverter implements ParserINTF {

	private String content = "";

	/**
	 * 
	 */
	public void setContent(String path) {
		File file = null;
		WordExtractor extractor = null;
		try {

			file = new File(path);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			HWPFDocument document = new HWPFDocument(fis);
			extractor = new WordExtractor(document);
			String[] fileData = extractor.getParagraphText();
			for (int i = 0; i < fileData.length; i++) {
				if (fileData[i] != null)
					content += fileData[i] + "\n";
			}
		} catch (Exception exep) {
			exep.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public String getContent() {
		return this.content;
	}
}
