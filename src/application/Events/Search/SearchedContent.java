package application.Events.Search;

import java.util.Collection;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import application.TransformRegex;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Functie care afiseaza diferit textu cautat.
 * 
 * <ol>
 * Contine functiile:
 * <li>{@link #createInterface(Entry, TextField)}</li>
 * <li>{@link #computeHighlighting(String, Pattern)}</li>
 * </ol>
 * 
 * @author Kocsis Lorand
 *
 */
public class SearchedContent {

	/**
	 * Functie care seteaza style-u pe cuvintele cautate.
	 * @param entry
	 * @param searchfield
	 */
	public void createInterface(Entry<String, String> entry, TextField searchfield) {
		try {
			final Pane root = new Pane();
			root.setStyle("-fx-background-color: #212121;");
			final String content = entry.getValue();
			final TransformRegex transform = new TransformRegex();

			final String regex = transform.transform(searchfield.getText());
			final String sampleCode = String.join("\n", new String[] { content });
			final String KEYWORD_PATTERN = regex;
			final CodeArea codeArea = new CodeArea();

			final Pattern PATTERN = Pattern.compile("(?<KEYWORD>" + KEYWORD_PATTERN + ")");
			// codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

			codeArea.setStyle("-fx-background-color: #212121;-fx-text-fill: green;");

			codeArea.richChanges().filter(ch -> !ch.getInserted().equals(ch.getRemoved())) // XXX
					.subscribe(change -> {
						codeArea.setStyleSpans(0, computeHighlighting(codeArea.getText(), PATTERN));
					});
			codeArea.replaceText(0, 0, sampleCode);

			final Scene scene = new Scene(new StackPane(new VirtualizedScrollPane<>(codeArea)), 800, 500);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			final Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle(entry.getKey());

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Functie care returneaza style-ul necesar pentru setare in functie de pozitii
	 * @param text Stringul care trebuie dat in alt design
	 * @param PATTERN  
	 * @return Style-ul modificat
	 */
	private static StyleSpans<Collection<String>> computeHighlighting(String text, Pattern PATTERN) {
		final Matcher matcher = PATTERN.matcher(text);
		int lastKwEnd = 0;
		final StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		while (matcher.find()) {
			String styleClass = matcher.group("KEYWORD") != null ? "keyword" : null;
			/* never happens */ assert styleClass != null;
			spansBuilder.add(Collections.singleton("white"), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
			lastKwEnd = matcher.end();
		}
		spansBuilder.add(Collections.singleton("white"), text.length() - lastKwEnd);
		return spansBuilder.create();
	}

}
