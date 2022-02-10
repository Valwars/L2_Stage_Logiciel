package Controllers;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Model.TraitementFichier;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class Controler {

	@FXML
	private Button tracer;
	@FXML
	private TextField tx1, tx2, ty1, ty2, pas;
	@FXML
	private LineChart<String, Number> linechart;

	@FXML
	private Label selectedFileLabel;

	@FXML
	private Button selectFileButton, settingsButton, genererImg;

	private User user;

	private TraitementFichier traitement;

	public Controler(User user) {
		this.user = user;

	}

	public void agrandir(ActionEvent event) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Grand_Ecran.fxml"));
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				stage.close();

			}
		});

		Parent root;
		try {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

			root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void Test(ActionEvent event) {
		Random rnd = new Random();

		linechart.setTitle(user.getGraphTitle());
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		for (int i = 0; i < 100; i++) {
			series.getData().add(new XYChart.Data<String, Number>(String.valueOf(i), rnd.nextInt(1000)));

		}

		series.setName(user.getGraphTitle());

		linechart.getData().add(series);

	}

	@FXML
	private void initialize() {
		linechart.setTitle(user.getGraphTitle());

		selectFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				searchDirectory(e);
			}
		});

		settingsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/settings.fxml"));

				SettingsController controller = new SettingsController(user);
				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent e) {
						stage.close();

					}
				});

				loader.setController(controller);

				Parent root;
				try {
					root = loader.load();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		genererImg.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				takeAScreen(e);
			}
		});

	}

	public void searchDirectory(ActionEvent event) {

		FileChooser fC = new FileChooser();

		fC.getExtensionFilters().add(new FileChooser.ExtensionFilter("File", "DOSCAR"));

		File userDirectory = new File(user.getSourceFolder());
		fC.setInitialDirectory(userDirectory);

		// get the file selected
		File file = fC.showOpenDialog(null);

		if (file != null) {

			selectedFileLabel.setText(file.getAbsolutePath());
			selectFileButton.setText("Fichier selectionné : ");
			
			try {
				traitement = new TraitementFichier(file.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void takeAScreen(ActionEvent event) {
		try {
			Robot robot = new Robot();
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

			BufferedImage bi = robot.createScreenCapture(screenRect);

			int i = 1;
			boolean drap = false;

			while (drap == false) {
				File file = new File(user.getImagesFolder() + "/Graphique" + i + ".png");
				if (file.exists()) {
					System.out.println("Le fichier existe.");
					i += 1;
				} else {
					// enregistrer l'image
					ImageIO.write(bi, "png", new File(user.getImagesFolder() + "/Graphique" + i + ".png"));
					drap = true;
				}
			}

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
