package application;
	
import java.io.IOException;

import application.model.TableViewRow;
import application.view.ContentViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<TableViewRow> tableViewData = FXCollections.observableArrayList();
	

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Fun JavaFX");
		
		loadTableViewData();
		
		initRootLayout();
		
		showContentView();
	}
	
	private void loadTableViewData() {
		this.tableViewData.add(new TableViewRow("Papa"));
		this.tableViewData.add(new TableViewRow("Mama"));
		this.tableViewData.add(new TableViewRow("Uta"));
		this.tableViewData.add(new TableViewRow("Kaho"));
	}
	/**
	 * ルートレイアウトを初期化
	 */
	private void initRootLayout() {
		try  {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * コンテンツビューを初期化
	 */
	private void showContentView() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ContentLayout.fxml"));
			AnchorPane contentView = (AnchorPane) loader.load();
			
			rootLayout.setCenter(contentView);
			// View Controller
			ContentViewController controller = loader.getController();
			controller.setApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public ObservableList<TableViewRow> getTableViewData() {
		return tableViewData;
	}
	
	/**
	 * エントリポイント
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
