package view.menus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class LanguageMenu extends TitledPane {
	public static final String LANGUAGE_MENU_KEY = "LanguageMenu";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public static final String DEFAULT_RESOURCE_FOLDER = "./src/resources/languages/";
	public static final String DEFAULT_LANGUAGE = "English";
	private ResourceBundle myResources;

	public LanguageMenu() {
		setLanguage(DEFAULT_LANGUAGE);
		setupLanguageMenu();
		setupButtonBox();
//		addObservable();
	}
	
	private void setupLanguageMenu() {
		this.setText(myResources.getString(LANGUAGE_MENU_KEY));
		this.setExpanded(false);
	}
	
	private void setupButtonBox() {
		ListView<Button> buttonDisplay = new ListView<>();
		buttonDisplay.setItems(FXCollections.observableArrayList(setupAllButtons()));
		this.setContent(buttonDisplay);
	}
	
	private List<Button> setupAllButtons() {
		List<Button> buttonList = new ArrayList<>();
		File languageFile = new File(DEFAULT_RESOURCE_FOLDER);
		for (File f: languageFile.listFiles()) {
			if (!f.getName().equals("Syntax.properties")) {
				buttonList.add(setupButton(f.getName().split(".properties")[0]));
			}
		}
		return buttonList;
	}
	
	private Button setupButton(String language) {
		Button button = new Button();
		button.setText(language);
		button.setOnAction(e -> setLanguage(language));
		button.setMaxWidth(Double.MAX_VALUE);
		return button;
	}
	
	private void setLanguage(String language) {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
				
	}

	public ResourceBundle getLanguage() {
		return myResources;
	}
}
