package Controllers;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Model.TraitementFichier;
import Model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SettingsController {

	@FXML
	private Button selectSourceDirectory, selectImageDirectory;

	@FXML
	private Button sauvegarderButton;

	@FXML
	private TextField graphName;

	private String sourceFolderPath;
	
	private String imagesFolderPath;

	private User user;


	public SettingsController(User user) {
		this.user = user;

	}

	@FXML
	public void initialize() {

	
		String[] folderName = user.getSourceFolder().split("/");
		selectSourceDirectory.setText("/" + folderName[folderName.length - 1]);
		
		String[] folderName2 = user.getImagesFolder().split("/");
		selectImageDirectory.setText("/" + folderName2[folderName2.length - 1]);
		
		
		graphName.appendText(user.getGraphTitle());
		
		selectSourceDirectory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				selectSourceRepertory(e);
				
			}
		});
		
		selectImageDirectory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				selectImagesRepertory(e);
				
			}
		});
		

		sauvegarderButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				enregistrerXML();
				Stage stage = (Stage) sauvegarderButton.getScene().getWindow();
				// do what you have to do
				stage.close();

			}
		});

	}
	

	public void selectSourceRepertory(ActionEvent event) {

		DirectoryChooser directoryChooser = new DirectoryChooser();

		// get the file selected
		File file = directoryChooser.showDialog(null);

		if (file != null) {

			sourceFolderPath = file.getAbsolutePath();
			String[] folderName = this.sourceFolderPath.split("/");
			selectSourceDirectory.setText("/" + folderName[folderName.length - 1]);
			user.setSourceFolder(sourceFolderPath);
			System.out.println("votre répertoire de base est mtn : /" + folderName[folderName.length - 1]);
		}
	}
	
	public void selectImagesRepertory(ActionEvent event) {

		DirectoryChooser directoryChooser = new DirectoryChooser();

		// get the file selected
		File file = directoryChooser.showDialog(null);

		if (file != null) {

			imagesFolderPath = file.getAbsolutePath();
			String[] folderName = this.imagesFolderPath.split("/");
			selectImageDirectory.setText("/" + folderName[folderName.length - 1]);
			user.setImagesFolder(imagesFolderPath);
			
		}
	}

	public void enregistrerXML() {
		
		if(graphName.getText() != null) {
			user.setGraphTitle(graphName.getText());
			System.out.println("le nouveau titre est : "+ user.getGraphTitle());
		}

		File fichier = new File("user.xml");

		XMLEncoder encoder = null;

		try {
			FileOutputStream fos = new FileOutputStream(fichier);
			BufferedOutputStream oos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(oos);
			encoder.writeObject(user);
			encoder.flush();

		} catch (final java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (encoder != null)
				encoder.close();
		}

	}

}
