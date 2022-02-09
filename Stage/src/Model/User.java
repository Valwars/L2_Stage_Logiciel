package Model;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable{

	private String sourceFolder = "aucun";
	private String imagesFolder = "aucun";
	private String graphTitle = "Graphique";
	
	public User() {
		
	}
	
	public String getGraphTitle() {
		return this.graphTitle;
	}
	
	public void setGraphTitle(String title) {
		 this.graphTitle = title;
	}
	
	public String getSourceFolder() {
		return this.sourceFolder;
	}
	
	public void setSourceFolder(String path) {
		 this.sourceFolder = path;
	}
	
	public String getImagesFolder() {
		return this.imagesFolder;
	}
	
	public void setImagesFolder(String path) {
		 this.imagesFolder = path;
	}
	
	

}
