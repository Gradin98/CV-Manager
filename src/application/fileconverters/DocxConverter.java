package application.fileconverters;

import java.io.FileInputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocxConverter implements ParserINTF{
	
	private String content = "";

	/**
	 * 
	 */
	public void setContent(String path) {
        try
        {

            FileInputStream fis = new FileInputStream(path);
            XWPFDocument document = new XWPFDocument(fis);
            
            @SuppressWarnings("resource")
			XWPFWordExtractor we = new XWPFWordExtractor(document);
			String fileData = we.getText();
                if (fileData != null)
                    content += fileData;
            
        }
        catch (Exception exep)
        {
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
