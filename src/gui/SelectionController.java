package gui;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Blacklist;

public class SelectionController implements Initializable{
	private Blacklist blacklist = new Blacklist();
	private ObservableList<String> obsList;

    @FXML
    private Button addBlacklistButton;

    @FXML
    private Button addPhonesButton;

    @FXML
    private TextField blacklistTextField;

    @FXML
    private ListView<String> blacklistView;

    @FXML
    private Button executeButton;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button removeBlacklistButton;

    @FXML
    private Button searchBlacklistButton;

    @FXML
    private Button searchPhoneButton;

    @FXML
    public void addBlacklistButtonAction(ActionEvent event) {

    }

    @FXML
    public void addPhonesButtonAction(ActionEvent event) {

    }

    @FXML
    public void executeButtonAction(ActionEvent event) {

    }

    @FXML
    public void removeBlacklistButtonAction(ActionEvent event) {
    	
    }

    @FXML
    public void searchBlacklistButtonAction(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
    	ExtensionFilter csv = new ExtensionFilter("Arquivo de valores separados por ponto e vírgula", "*.csv");
    	ExtensionFilter txt = new ExtensionFilter("Arquivo de texto", "*.txt");
    	
    	chooser.setTitle("Selecione o arquivo de contatos telefones WhatsApp");
    	chooser.getExtensionFilters().addAll(csv, txt);
    	
    	File file = chooser.showOpenDialog(Utils.getElementWindow(event));
    	
    	if(file != null) {
    		blacklist.setPhoneFile(file);
    		phoneTextField.setText(file.getAbsolutePath());
    	}
    }

    @FXML
    public void searchPhoneButtonAction(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
    	ExtensionFilter csv = new ExtensionFilter("Arquivo de valores separados por ponto e vírgula", "*.csv");
    	ExtensionFilter txt = new ExtensionFilter("Arquivo de texto", "*.txt");
    	
    	chooser.setTitle("Selecione o arquivo de blacklist");
    	chooser.getExtensionFilters().addAll(csv, txt);
    	
    	List<File> files = chooser.showOpenMultipleDialog(Utils.getElementWindow(event));
    	
    	if(!files.isEmpty()) {
    		for(File f : files) {
    			blacklist.addBlacklistFiles(f);
    			obsList.add(f.getAbsolutePath());
    		}
    		blacklistView.refresh();
    	}
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		blacklistView.setItems(obsList);
	}

}
