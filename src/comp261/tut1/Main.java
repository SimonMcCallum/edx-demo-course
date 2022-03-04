package comp261.tut1;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Graph graph;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // load the lanugage file - supported "en" English and "mi" Maori
        Locale locale = new Locale("en", "NZ");
        ResourceBundle bundle = ResourceBundle.getBundle("comp261/tut1/strings", locale);
        
        // load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MapView.fxml"),bundle);
        Parent root = loader.load();

        primaryStage.setTitle(bundle.getString("title")); //uses the localisation for the string. 
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });

        // To prevent crashing incase the files are not in the right location load them and check for null
        File nodeFile = new File("data/node.csv");
        File edgeFile = new File("data/edge.csv");
        if (!nodeFile.exists() || !edgeFile.exists()) {
            System.out.println("Files not found");
        } else {
            graph = new Graph(nodeFile, edgeFile);
            //           loader.getController().drawGraph();  // we have to cast to get to the Controller code
            ((GraphController)loader.getController()).drawGraph();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
