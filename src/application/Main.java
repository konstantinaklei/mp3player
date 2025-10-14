package application;
	
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class Main extends Application {
	
	private File dir2;
	private File[] files2;
	private ArrayList<File> favs;
	//Button heartButton = new Button();
	

	
	
	
    private List<File> playlist = new ArrayList<>();
    private int currentIndex = 0;
	
	public ArrayList<File> getFavs() {
		return favs;
	}

	public void setFavs(ArrayList<File> favs) {
		this.favs = favs;
	}

	SVGPath heartShape = new SVGPath();
	public void start(Stage primaryStage) {
		
		try {
			boolean flag=false;
			favs = new ArrayList<File>();
			
			System.out.println(getClass().getResource("view.fxml"));

			
			 FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view.fxml"));
		     Pane root = fxmlLoader.load(); // Φόρτωσε τη ρίζα από το FXML
		     Controller controller = fxmlLoader.getController();
		     //File currentSong = controller.getSongs().get(controller.getSongnum());
		     
		        // Δημιουργία της σκηνής με το root
		     Scene scene = new Scene(root, 645, 460);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("My mp3 Player");
			Image icon =new Image("/2Logo.png");
			primaryStage.getIcons().add(icon);
			
			ImageView icon2 = new ImageView(new Image("/2Logo.png"));
			
			
	        //heartShape.setContent("M50 15 Q 35 -10 20 15 Q 0 40 25 65 Q 50 90 75 65 Q 100 40 80 15 Q 65 -10 50 15 Z" );
	        //heartShape.setContent("M 22.5 6.75 Q 15.75 0 9 6.75 Q 0 15.75 11.25 27 Q 22.5 38.25 33.75 27 Q 45 15.75 36 6.75 Q 29.25 0 22.5 6.75 Z");
			heartShape.setContent("M 21 7 Q 15 0 9 7 Q 0 15 11 27 Q 21 42 31 27 Q 42 15 33 7 Q 27 0 21 7 Z");
			
	        heartShape.setFill(Color.TRANSPARENT);
	        heartShape.setStroke(Color.web("#ff8da1"));


	        // Δημιaυργία κουμπιού
	        Button heartButton = new Button();
	        heartButton.setStyle("-fx-background-color: transparent;"); // Αφαιρεί το default background του κουμπιού
	        heartButton.setGraphic(heartShape);
	    

	       
	       
	      
		    heartButton.setLayoutX(278);
            heartButton.setLayoutY(170);
	        //heartButton.getStyleClass().add("heart");


			Rectangle rec =new Rectangle();
			rec.setWidth(200); // Ορίστε πλάτος
            rec.setHeight(150); // Ορίστε ύψος
            rec.setStyle("-fx-fill: #8e70cf; -fx-stroke: #734ada; -fx-stroke-width: 1; -fx-arc-width: 5; -fx-arc-height: 5;");
            rec.setLayoutX(425); // Ρυθμίστε την οριζόντια θέση
            rec.setLayoutY(293); // Ρυθμίστε την κάθετη θέση

            root.getChildren().add(rec);  // Προσθήκη πρώτα του Rectangle
            rec.setVisible(true);
            rec.setOpacity(1.0);
            Label plusLabel = new Label("+"); // Περιεχόμενο του Label
            plusLabel.setStyle("-fx-background-color: #8e70cf; " +
                               "-fx-text-fill: #391c83; " +
                               "-fx-font-size: 96; " +
                               "-fx-alignment: center;");
            plusLabel.setPrefWidth(66); // Ορίζουμε πλάτος
            plusLabel.setPrefHeight(141); // Ορίζουμε ύψος
            plusLabel.setLayoutX(492); // Ορίζουμε X θέση
            plusLabel.setLayoutY(298); // Ορίζουμε Y θέση

            // Προσθήκη στο root
            root.getChildren().add(plusLabel);

            ArrayList<File> songs = controller.getSongs();
	        boolean n1 = controller.agapistatus;
	        if( heartShape.getFill().equals(Color.web("E85F78"))) {
	        	n1 =true;
	        	heartShape.setFill(Color.TRANSPARENT);
	        }else {
	        	n1=false;
	        }
        	System.out.println("------------"+n1);
        	
            heartButton.setOnAction(event -> {
            	//System.out.println("------------"+n1);
                if (heartShape.getFill().equals(Color.TRANSPARENT)) {
                	
                	Alert alert = new Alert(Alert.AlertType.WARNING);
                	
                	alert.setGraphic(icon2);
                	icon2.setFitWidth(50); // Πλάτος εικόνας
                    icon2.setFitHeight(50); // Ύψος εικόνας
                    icon2.setPreserveRatio(true); // Διατήρηση αναλογίας αν χρειάζεται
                    alert.setTitle("Προειδοποίηση");
                    alert.setHeaderText("Προστέθηκε στα αγαπημένα:");
                    File currentSong = controller.getSongs().get(controller.getSongnum());
                    String name = controller.getName();
                    elegxos(currentSong);
                	alert.setContentText(name);
                	
                	favs.add(currentSong);
                    heartShape.setFill(Color.web("E85F78"));
                    System.out.println("Προστέθηκε στα αγαπημένα: " + name);
                    alert.getDialogPane().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    alert.showAndWait();
                    
                } else {
                	
                		
                			//String name = controller.getName();
                			Alert alert2 = new Alert(Alert.AlertType.WARNING);
                			alert2.setGraphic(icon2);
                			icon2.setFitWidth(50); // Πλάτος εικόνας
                			icon2.setFitHeight(50); // Ύψος εικόνας
                			icon2.setPreserveRatio(true); // Διατήρηση αναλογίας αν χρειάζεται
                			alert2.setTitle("Προειδοποίηση");
                			alert2.setHeaderText("Αφαιρέθηκε από τα αγαπημένα:");
                			File currentSong = controller.getSongs().get(controller.getSongnum());
                			elegxos(currentSong);
                			
                	
                			heartShape.setFill(Color.TRANSPARENT);
                			favs.remove(currentSong);
                			System.out.println("Αφαιρέθηκε από τα αγαπημένα: " + currentSong);
                			alert2.getDialogPane().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                			alert2.showAndWait();
                		}
                	
                	
                	
                	
                    
                
                createFireworks(root, heartButton.getLayoutX() + 50, heartButton.getLayoutY() + 50);
            });/*
            int ep=controller.epilogi;
            System.out.println("epilogi"+ep);
            int epi=controller.selectedAlbum;
            System.out.println("epii"+epi);
            if (ep!=epi ) {
           	 heartShape.setFill(Color.TRANSPARENT); // Διαφανής καρδιά
           }
*/
            controller.setOnSongChangeListener(isFavorite -> {
                if (isFavorite) {
                    heartShape.setFill(Color.web("E85F78")); // Γεμισμένη καρδιά
                } else {
                    heartShape.setFill(Color.TRANSPARENT); // Διαφανής καρδιά
                }
            });
            
            controller.setOnAlbumChangeListener((newAlbum, oldAlbum) -> {
                if (controller.epilogi != newAlbum) {
                    heartShape.setFill(Color.TRANSPARENT); // Διαφανής καρδιά
                } else {
                    heartShape.setFill(Color.web("E85F78")); // Κόκκινη καρδιά αν ταιριάζει
                }
                System.out.println("Album changed from " + oldAlbum + " to " + newAlbum);
            });
           
	        root.getChildren().add(heartButton);

        	//if(agapiStatus==false) {
        	//	 heartShape.setFill(Color.TRANSPARENT);
        	//}
  

	        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        plusLabel.setOnMouseClicked(e -> {
	        	
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("MP3 Files", "*.mp3"));
                List<File> selectedFiles = fileChooser.showOpenMultipleDialog(primaryStage);
                if (selectedFiles != null) {
                	for(File file : selectedFiles) {
        				playlist.addAll(selectedFiles);
        				
        			}
                	
                    
                    controller.selectAlbum3();
                    controller.handleImageViewClick3(playlist,currentIndex); 
                    
                }
                createFireworks(root, plusLabel.getLayoutX() + 50, plusLabel.getLayoutY() + 50);
            });
	       
   	 
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					Platform.exit();
					System.exit(0);
				}
				
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void elegxos(File currentSong) {
		boolean isInFavs = favs.stream()
                .anyMatch(file -> file.getAbsolutePath().equals(currentSong.getAbsolutePath()));
		if (isInFavs) {
		    System.out.println("Το τραγούδι υπάρχει στα αγαπημένα!");
		} else {
			heartShape.setFill(Color.TRANSPARENT);
		    System.out.println("Το τραγούδι δεν υπάρχει στα αγαπημένα.");
		}
		
	}

	private void createFireworks(Pane root, double d, double e) {
	
		 for (int i = 0; i < 10; i++) {
	            Circle spark = new Circle(d, e, 5,  Color.web("#391c83")); //8e70cf
	            root.getChildren().add(spark);
	           
	            
	            
	            // Δημιουργία animation για "εξάπλωση" σπιθών
	            FadeTransition fade = new FadeTransition(Duration.seconds(1), spark);
	            fade.setFromValue(1.0);
	            fade.setToValue(0.0);
	            fade.setOnFinished(event -> root.getChildren().remove(spark));
	            fade.play();
	        
	            // Τοποθέτηση σε τυχαία κατεύθυνση
	            spark.setTranslateX(Math.random() * 200 - 100);
	            spark.setTranslateY(Math.random() * 200 - 100);
	        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
