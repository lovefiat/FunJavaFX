package application.view;

import java.io.File;

import application.Main;
import application.model.FileTreeItem;
import application.model.TableViewRow;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;

public class ContentViewController {
	
	@FXML
	private static final String ID_BUTTON1 = "button1";
	@FXML
	private static final String ID_TOOL_BUTTON1 = "buttonTool1";
	@FXML
	private static final String ID_TOOL_BUTTON2 = "buttonTool2";
	
	private Main app;
	

	private TreeItem<FileTreeItem> rootFileTree;
	@FXML
	private TreeView<FileTreeItem> treeView;
	@FXML
	private TabPane tabPane;
	@FXML
	private ChoiceBox<String> choiceBox;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private ProgressIndicator progressIndicator;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TableView<TableViewRow> tableView;
	@FXML
	private TreeTableView<FileTreeItem> treeTableView;
	
	public ContentViewController() {
		
	}
	
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		
		((TableColumn<TableViewRow, String>)tableView.getColumns().get(0)).setCellValueFactory(cellData -> cellData.getValue().idProperty());
		((TableColumn<TableViewRow, String>)tableView.getColumns().get(1)).setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
		((TreeTableColumn<FileTreeItem, String>)treeTableView.getColumns().get(0)).setCellValueFactory(new TreeItemPropertyValueFactory<>("parent"));
		((TreeTableColumn<FileTreeItem, String>)treeTableView.getColumns().get(1)).setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		((TreeTableColumn<FileTreeItem, String>)treeTableView.getColumns().get(2)).setCellValueFactory(new TreeItemPropertyValueFactory<>("attributes"));
		
		File currentDir = new File(".");
		rootFileTree = new TreeItem<>(new FileTreeItem(currentDir));
		
		for (File file : currentDir.listFiles()) {
			rootFileTree.getChildren().add(new TreeItem<>(new FileTreeItem(file)));
		}
	}
	
	private void initTreeView() {
		treeView.setRoot(rootFileTree);
	}
	
	private void initChoiceBox() {
		this.choiceBox.getItems().add("選択肢１");
		this.choiceBox.getItems().add("選択肢２");
		this.choiceBox.getItems().add("選択肢３");
	}
	
	private void initComboBox() {
		this.comboBox.getItems().add("候補１");
		this.comboBox.getItems().add("候補２");
		this.comboBox.getItems().add("候補３");
	}
	
	private void initTableView() {
		this.tableView.setItems(this.app.getTableViewData());
	}
	private void initTreeTableView() {
		this.treeTableView.setRoot(this.rootFileTree);
	}
	
	public void setApp(Main app) {
		this.app = app;
		
		initTreeView();
		initChoiceBox();
		initComboBox();
		initTableView();
		initTreeTableView();
		
		processProgressBar();
		processProgressIndicator();
	}
	
	private void showAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION, msg, ButtonType.OK, ButtonType.CANCEL);
		
		alert.setHeaderText("ヘッダテキスト");
//		alert.setContentText("コンテンツテキスト");
		alert.setTitle("アラートタイトル");
		alert.show();
	}
	
	private void processProgressBar() {

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				float [] values = new float[] {-1f, 0f, 0.25f, 0.5f, 0.75f, 1f};
				try {
					while (true) {
						for (int i=0; i<values.length; i++) {
							updateProgress(values[i], 1);
							Thread.sleep(300);
						}
						if (isCancelled()) {
							break;
						}
					}
					
				} catch (Exception e) {
					println(e.getMessage());
				}
				return null;
			}
			
		};

		this.progressBar.progressProperty().bind(task.progressProperty());
		
		Thread thread = new Thread(task);
		thread.start();
	}
	
	private void processProgressIndicator() {
		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				float [] values = new float[] {-1f, 0f, 0.25f, 0.5f, 0.75f, 1f};
				try {
					while (true) {
						for (int i=0; i<values.length; i++) {
							updateProgress(values[i], 1);
							Thread.sleep(300);
						}
						if (isCancelled()) {
							break;
						}
					}
					
				} catch (Exception e) {
					println(e.getMessage());
				}
				return null;
			}
			
		};

		this.progressIndicator.progressProperty().bind(task.progressProperty());
		
		Thread thread = new Thread(task);
		thread.start();
		
	}
	
	@FXML
	private void handleButtonAction(ActionEvent e) {
		println(e.toString());
		
		if (e.getSource() instanceof Button) {
			Button button = (Button)e.getSource();
			switch ( button.getId() ) {
			case ID_BUTTON1:
				showAlert(button.getText());
				break;
			default:
				break;
			}
		}
	}
	private void println(String msg) {
		System.out.println(msg);
	}

}
