package it.polito.tdp.corsi;

import it.polito.tdp.corsi.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EntryPoint extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		// CREIAMO L'OGGETTO FXML LOADER
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
		// USIAMO I DATI CONTENUTI IN ESSO PER CREARE LA SCENA
		Parent root = loader.load();
		Scene scene = new Scene(root);
		// ANDIAMO A SALVARE IL CONTROLLER
		FXMLController controller = loader.getController();
		// COSI DA POTER AGGIUNGERE IL MODEL AL CONTROLLER
		Model model = new Model();
		controller.setModel(model);
		stage.setTitle("Gestore Corsi");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application. main()
	 * serves only as fallback in case the application can not be launched through
	 * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
	 * main().
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
