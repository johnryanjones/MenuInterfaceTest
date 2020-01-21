import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MenuInterface extends Application {
	
	@Override
	public void start(Stage arg0) {
	    MenuBar menuBar = new MenuBar();
	    menuBar.prefWidthProperty().bind(arg0.widthProperty());
		
		// File menu
	    Menu fileMenu = new Menu("File");
	    MenuItem option1 = new MenuItem("Option 1");
	    MenuItem option2 = new MenuItem("Option 2");
	    MenuItem option3 = new MenuItem("Option 3");
	    MenuItem option4 = new MenuItem("Option 4");
	    
	    //add the menu items to the file menu
	    fileMenu.getItems().addAll(option1, option2, option3, option4);
	    
	    VBox root = new VBox(menuBar);
	    root.setPadding(new Insets(10, 10, 10, 10));
	    root.setSpacing(10);

		Scene scene = new Scene(root, 400, 400, Color.WHITE);
		
		TextField outputField = new TextField();
		outputField.setEditable(false);
		outputField.setPrefColumnCount(10);
		
		root.getChildren().add(outputField);
		
		menuBar.getMenus().addAll(fileMenu);
		
		arg0.setScene(scene);
		arg0.setTitle("CSC372 Module 3 Option 1");
		arg0.show();
		
		option1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//display the current date and time within the TextField
				SimpleDateFormat sf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				outputField.setText(sf.format(cal.getTime()));
			}
		});
		
		option2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					//write the TextField object to a text file
					FileOutputStream fileStream = new FileOutputStream("log.txt");
					ObjectOutputStream outputFS =  new ObjectOutputStream(fileStream);
					outputFS.writeObject(outputField.getText());
					fileStream.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
		});
		
		option3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//create an array of different shades of green
				Color[] colors = new Color[] {Color.DARKGREEN, Color.DARKOLIVEGREEN, Color.DARKSEAGREEN, Color.FORESTGREEN, Color.GREEN,
						Color.GREENYELLOW, Color.LAWNGREEN, Color.LIGHTGREEN, Color.LIGHTSEAGREEN, Color.LIMEGREEN, Color.MEDIUMSEAGREEN, Color.MEDIUMSPRINGGREEN,
						Color.PALEGREEN, Color.SEAGREEN, Color.SPRINGGREEN, Color.YELLOWGREEN};
				//create a random number generator using the array length and assigning the background color
				Random rand = new Random();
				Color c = colors[rand.nextInt(colors.length)];
				Background color = new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY));
				root.setBackground(color);
			}
		});
		
		option4.setOnAction(actionevent->Platform.exit());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
