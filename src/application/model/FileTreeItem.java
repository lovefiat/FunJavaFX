package application.model;

import java.io.File;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FileTreeItem {
	
	File file;
	
	private StringProperty name;
	private StringProperty parent;
	private StringProperty attributes;
	
	public FileTreeItem(String path) {
		this(new File(path));
	}
	
	public FileTreeItem(File file) {
		this.file = file;
		
		this.name = new SimpleStringProperty();
		this.parent = new SimpleStringProperty();
		this.attributes = new SimpleStringProperty();
		
		
		this.name.set(file.getName());
		this.parent.set(file.getParent());

		StringBuilder sb = new StringBuilder();
		if (file.isDirectory()) {
			sb.append("D");
		} else {
			sb.append(" ");
		}
		if (file.isFile()) {
			sb.append("F");
		} else {
			sb.append(" ");
		}
		if (file.isHidden()) {
			sb.append("H");
		} else {
			sb.append(" ");
		}
		this.attributes.set(sb.toString());
	}

	@Override
	public String toString() {
		String val = "";
		if (file != null) {
			val = file.getName();
		}
		return val;
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
	

	public final StringProperty parentProperty() {
		return this.parent;
	}
	

	public final String getParent() {
		return this.parentProperty().get();
	}
	

	public final void setParent(final String parent) {
		this.parentProperty().set(parent);
	}

	public final StringProperty attributesProperty() {
		return this.attributes;
	}
	

	public final String getAttributes() {
		return this.attributesProperty().get();
	}
	

	public final void setAttributes(final String attrbutes) {
		this.attributesProperty().set(attrbutes);
	}
	
}
