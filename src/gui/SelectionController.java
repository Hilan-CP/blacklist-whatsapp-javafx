package gui;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import model.Blacklist;

public class SelectionController implements Initializable{
	private Blacklist blacklist = new Blacklist();
	private ObservableList<File> obsList;

    @FXML
    private Button addBlacklistButton;

    @FXML
    private Button addPhonesButton;

    @FXML
    private TextField blacklistTextField;

    @FXML
    private ListView<File> blacklistView;

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
    	String path = blacklistTextField.getText().trim();
    	File file = new File(path);
    	
    	if(file.isFile()) {
    		blacklist.addBlacklistFiles(file);
    		updateListView();
    	}
    	else {
    		Utils.showAlert("Arquivo inválido", "O nome do arquivo de blacklist é inválido", AlertType.WARNING);
    	}
    }

    @FXML
    public void addPhonesButtonAction(ActionEvent event) {
    	String path = phoneTextField.getText().trim();
    	File file = new File(path);
    	
    	if(file.isFile()) {
    		blacklist.setPhoneFile(file);
    	}
    	else {
    		Utils.showAlert("Arquivo inválido", "O nome do arquivo com telefones é inválido", AlertType.WARNING);
    	}
    }

    @FXML
    public void executeButtonAction(ActionEvent event) {
    	// chamar metodos de operação do this.blacklist
    }

    @FXML
    public void removeBlacklistButtonAction(ActionEvent event) {
    	int index = blacklistView.getSelectionModel().getSelectedIndex();
    	
    	if(index >= 0) {
    		blacklist.removeBlacklistFiles(index);
        	updateListView();
    	}
    }

    @FXML
    public void searchBlacklistButtonAction(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
    	ExtensionFilter csv = new ExtensionFilter("Arquivo de valores separados por ponto e vírgula", "*.csv");
    	ExtensionFilter txt = new ExtensionFilter("Arquivo de texto", "*.txt");
    	
    	chooser.setTitle("Selecione o arquivo de blacklist");
    	chooser.getExtensionFilters().addAll(csv, txt);
    	
    	List<File> files = chooser.showOpenMultipleDialog(Utils.getElementWindow(event));
    	
    	if(files != null) {
    		for(File f : files) {
    			blacklist.addBlacklistFiles(f);
    		}
    		updateListView();
    	}
    }

    @FXML
    public void searchPhoneButtonAction(ActionEvent event) {
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		blacklistView.setCellFactory(new Callback<ListView<File>, ListCell<File>>(){
			@Override
			public ListCell<File> call(ListView<File> arg0) {
				ListCell<File> cell = new ListCell<>() {

					@Override
					protected void updateItem(File item, boolean empty) {
						super.updateItem(item, empty);
						if(empty || item == null) {
							setText(null);
							setGraphic(null);
						}
						else {
							setText(item.getAbsolutePath());
						}
					}
				};
				return cell;
			}
		});
		
		obsList = FXCollections.observableArrayList();
		blacklistView.setItems(obsList);
	}

	private void updateListView() {
		obsList.clear();
		obsList.addAll(blacklist.getBlacklistFiles());
		blacklistView.refresh();
	}
}
