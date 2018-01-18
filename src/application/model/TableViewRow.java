package application.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableViewRow {
	private static int lastId = 0;
	
	private StringProperty id;
	private StringProperty name;
	private StringProperty description;
	
	public TableViewRow(String name) {
		
		this.id = new SimpleStringProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		
		this.id.set(String.valueOf(++lastId));
		this.name.set(name);
	}

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}
	
	public StringProperty idProperty() {
		return id;
	}

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final StringProperty descriptionProperty() {
		return this.description;
	}
	

	public final String getDescription() {
		return this.descriptionProperty().get();
	}
	

	public final void setDescription(final String description) {
		this.descriptionProperty().set(description);
	}
	
}
