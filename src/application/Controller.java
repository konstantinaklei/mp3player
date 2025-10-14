package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Controller implements Initializable{


	@FXML
	
	private Pane pane;
	
	@FXML
	
	private Label songLabel;
	@FXML
	
	private Label lrec;

    @FXML
    private Button heart;
	@FXML
	
	private Button play,pause,previous,next,reset;
	@FXML
	
	private ProgressBar prog;
	@FXML
	private Slider vol;
	@FXML
	
	private ComboBox<String> speed;
	@FXML
	private ImageView mdm;
	@FXML
	private ImageView myimage;
	@FXML 
	private ImageView hut;
	@FXML 
	private Rectangle rec;
	@FXML 
	ImageView info;
	private Media media;
	private MediaPlayer mediaPlayer;
	boolean agapi=false;
	private File dir;
	private File[] files;
	private ArrayList<File> songs;
	private ArrayList<File> songs2;
	String name;
	public int epilogi;
	public boolean liked=false;
	@FXML
	private ImageView about;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	private int songnum; // <<--------------------------------------songnum
	private int songnum2;
	private int songnum3;
	int currentIndex;
	private int[] speeds = {25,50,75,100,125,150,175,200};
	private Timer timer;
	private TimerTask task;
	private boolean running;
	
	
	private File dir2;
	private File[] files2;
	
	private ArrayList<File> favs = new ArrayList<>();
	public int selectedAlbum ;
	
	public interface OnAlbumChangeListener {
	    void onAlbumChanged(int newAlbum, int oldAlbum);
	}

	private OnAlbumChangeListener albumChangeListener;
	public void setOnAlbumChangeListener(OnAlbumChangeListener listener) {
	    this.albumChangeListener = listener;
	}
	
	
	public interface OnSongChangeListener { //**********************************************************************************
	    void onSongChanged(boolean isFavorite);
	}
	private OnSongChangeListener songChangeListener;

	public void setOnSongChangeListener(OnSongChangeListener listener) {
	    this.setSongChangeListener(listener);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1 ) {

		// TODO Auto-generated method stub

		songs = new ArrayList<File>();
		songs2 = new ArrayList<File>();
		
		dir = new File("music");
		files= dir.listFiles();
		
		dir2 = new File("songs2");
		files2= dir2.listFiles();
		
		if(files!=null) {
			for(File file : files ) {
				songs.add(file);
				
			}
			
		}
		if(files2!=null) {
			for(File file : files2 ) {
				songs2.add(file);
				
			}
			
		}
		vol.getStyleClass().add("slider");
	
		//---------------------------------------------------------------------------------------------------------
		
		 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		handleImageViewClick2();
		handleImageViewClick();
		
		for(int i=0;i<speeds.length;i++) {
			speed.getItems().add(Integer.toString(speeds[i]) + "%");
			
			
		}
		speed.setOnAction(this::changespeed);
		vol.valueProperty().addListener(new ChangeListener<Number>() {
		

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				mediaPlayer.setVolume(vol.getValue()*0.01);
			}}   );
		prog.setStyle("-fx-accent: #124A55");
		Image about2=new Image(getClass().getResourceAsStream("n2.png"));
		about.setImage(about2);
		about.setOnMouseClicked(e -> {
			ScaleTransition pulse = new ScaleTransition(Duration.seconds(0.5), about);
		 	pulse.setFromX(1.0);
		 	pulse.setFromY(1.0);
		 	pulse.setToX(1.2); // Αύξηση μεγέθους
		 	pulse.setToY(1.2);
		 	pulse.setCycleCount(2); // 4 παλμοί
		 	pulse.setAutoReverse(true);
		 	pulse.play();
		 	
		    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
		    
		    //Image infoim=new Image(getClass().getResourceAsStream("n2.png"));
		    
		    alert3.setTitle("Programmer");
		    alert3.setHeaderText("About This Application");
		    //ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("n2.png")));
		    ImageView icon2 = new ImageView(new Image(getClass().getResourceAsStream("konklei.jpg")));
		    //info.setImage(infoim);
		    alert3.setGraphic(icon2);
		    
		    // Δημιουργία περιεχομένου με VBox
		    VBox content = new VBox();
		    content.setSpacing(10); // Κενό μεταξύ στοιχείων
		    
		    // Προσθήκη μηνύματος
		    Label message = new Label("HELLO WORLD USER!\n" +
		    	    "My name is Konstantina and I created this music player\n" +
		    	    "for my User Interface class. I study Information and\n" +
		    	    "Electronic Engineering at International Hellenic University.\n" +
		    	    "For more info visit my personal site:");
		    
		    // Δημιουργία Hyperlink
		    Hyperlink link = new Hyperlink("Visit my site");
		    Stage stage = (Stage) alert3.getDialogPane().getScene().getWindow();
		    stage.setX(1250); // Συντεταγμένη X (οριζόντια θέση)
		    stage.setY(30); // Συντεταγμένη Y (κατακόρυφη θέση)

		    link.setOnAction((ActionEvent event) -> {
		        try {
		            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://users.iee.ihu.gr/~konsklei/gti_lab1/mysite.html"));
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    });
		    
		    // Προσθήκη του Label και του Hyperlink στο VBox
		    content.getChildren().addAll(message, link);
		    
		    // Ορισμός του VBox ως περιεχόμενο του Alert
		    alert3.getDialogPane().setContent(content);
		    alert3.getDialogPane().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    alert3.showAndWait();
		});

		
	}
	private void handleImageViewClickall() {
		mdm.setOnMouseClicked(event -> {
		    selectAlbum1(); // Όταν πατηθεί το πρώτο κουμπί
		    epilogi=2;
		});

		hut.setOnMouseClicked(event -> {
		    selectAlbum2(); // Όταν πατηθεί το δεύτερο κουμπί
		    epilogi=1;
		});
		
	}
	public void selectAlbum1() {
		 int oldAlbum = selectedAlbum; // Αποθηκεύουμε την τρέχουσα τιμή
	    selectedAlbum = 1; // Ορίζουμε τον 1ο πίνακα ως ενεργό
	    songnum = 0; // Προαιρετικά, αρχικοποίηση στο πρώτο τραγούδι
	    if (albumChangeListener != null) {
	        albumChangeListener.onAlbumChanged(selectedAlbum, oldAlbum);
	    }
	    //mediaPlayer.stop();
	    
	}

	public void selectAlbum2() {
		 int oldAlbum = selectedAlbum; // Αποθηκεύουμε την τρέχουσα τιμή
	    selectedAlbum = 2; // Ορίζουμε τον 2ο πίνακα ως ενεργό
	    songnum2 = 0; // Προαιρετικά, αρχικοποίηση στο πρώτο τραγούδι
	    if (albumChangeListener != null) {
	        albumChangeListener.onAlbumChanged(selectedAlbum, oldAlbum);
	    }
	    //mediaPlayer.stop();
	    //playmedia(songs2.get(songnum2)); // Παίζει το πρώτο τραγούδι του δεύτερου πίνακα
	}
	public void selectAlbum3() {
	    selectedAlbum = 3; // Ορίζουμε τον 2ο πίνακα ως ενεργό
	    currentIndex = 0; // Προαιρετικά, αρχικοποίηση στο πρώτο τραγούδι
	    //mediaPlayer.stop();
	    //playmedia(songs2.get(songnum2)); // Παίζει το πρώτο τραγούδι του δεύτερου πίνακα
	}
	private void handleImageViewClick2() {
		 hut.setOnMouseClicked(event -> {
			 	//settimer();
			 	ScaleTransition pulse = new ScaleTransition(Duration.seconds(0.5), hut);
			 	pulse.setFromX(1.0);
			 	pulse.setFromY(1.0);
			 	pulse.setToX(1.2); // Αύξηση μεγέθους
			 	pulse.setToY(1.2);
			 	pulse.setCycleCount(2); // 4 παλμοί
			 	pulse.setAutoReverse(true);
			 	pulse.play();
			 	selectAlbum2();
		        System.out.println("ImageView clicked!");
		        switchMedia(songs2, songnum2);
		        // Παράδειγμα: αλλαγή opacity
		        hut.setOpacity(0.5);
		        mdm.setOpacity(1);
		        
		        // Παράδειγμα: αλλαγή εικόνας
		        hut.setImage(new Image("huti.png"));
		        // Προσθήκη επιπλέον λειτουργικότητας
		    });
		
	}


	public void handleImageViewClick() { //handle11111111111111111111111111111111111111111111111111111111111
	    mdm.setOnMouseClicked(event -> {
	    	//settimer();
	    	ScaleTransition pulse = new ScaleTransition(Duration.seconds(0.5), mdm);
		 	pulse.setFromX(1.0);
		 	pulse.setFromY(1.0);
		 	pulse.setToX(1.2); // Αύξηση μεγέθους
		 	pulse.setToY(1.2);
		 	pulse.setCycleCount(2); // 4 παλμοί
		 	pulse.setAutoReverse(true);
		 	pulse.play();
		 	
	    	
	        System.out.println("ImageView clicked!");
	        //media = new Media(songs.get(songnum).toURI().toString());
			//mediaPlayer = new MediaPlayer(media);
			
			//songLabel.setText(songs.get(songnum).getName());
	        switchMedia(songs, songnum);
	        selectAlbum1();
	        // Παράδειγμα: αλλαγή opacity
	        mdm.setOpacity(0.5);
	        hut.setOpacity(1);
	      
	        // Παράδειγμα: αλλαγή εικόνας
	        mdm.setImage(new Image("mdmi.png"));
	        // Προσθήκη επιπλέον λειτουργικότητας
	    });
	}
	public ArrayList<File> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<File> songs) {
		this.songs = songs;
	}

	public int getSongnum() {
		return songnum;
	}

	public void setSongnum(int songnum) {
		this.songnum = songnum;
	}
TranslateTransition translate =new TranslateTransition();
RotateTransition rotate = new RotateTransition();

public boolean agapistatus;



	public void playmedia() {
		if(isAgapi()==false) {
			liked=false;
		}else {
			liked=true;
		}
		elegxos(songs.get(songnum));
		elegxos(songs2.get(songnum2));
		/*
		translate.setNode(myimage);
		translate.setDuration(Duration.millis(2000));
		translate.setCycleCount(TranslateTransition.INDEFINITE);
		//translate.setByX(600);
		translate.setByX(translate.INDEFINITE);
		translate.setAutoReverse(true);
		translate.play();
		*/
		if (mediaPlayer != null) {
		    Duration currentTime = mediaPlayer.getCurrentTime();
		    System.out.println("Current time: " + currentTime);
		} else {
		    System.out.println("MediaPlayer is not initialized.");
		    Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Προειδοποίηση");
	        alert.setHeaderText("Δεν υπάρχει επιλεγμένο τραγούδι!");
	        alert.setContentText("Παρακαλώ επιλέξτε playlist πριν πατήσετε play/previous.");
	        alert.showAndWait();
	        return;
		}
	    if (songs.isEmpty() || songnum < 0 || songnum >= songs.size()) {
	        // Εμφάνιση ειδοποίησης αν δεν έχει επιλεχθεί τραγούδι
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Προειδοποίηση");
	        alert.setHeaderText("Δεν υπάρχει επιλεγμένο τραγούδι!");
	        alert.setContentText("Παρακαλώ επιλέξτε ένα τραγούδι πριν πατήσετε play.");
	        alert.showAndWait();
	        return;
	    }

	    // Εμφάνιση ειδοποίησης αν δεν έχει επιλεχθεί άλμπουμ
	    if (songs2.isEmpty() || songnum2 < 0 || songnum2 >= songs2.size()) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Προειδοποίηση");
	        alert.setHeaderText("Δεν υπάρχει επιλεγμένο άλμπουμ!");
	        alert.setContentText("Παρακαλώ επιλέξτε ένα άλμπουμ πριν πατήσετε play.");
	        alert.getDialogPane().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        alert.showAndWait();
	        return;
	    }
		
		  rotate.setNode(myimage);
		  rotate.setDuration(Duration.millis(1000));
		  rotate.setCycleCount(TranslateTransition.INDEFINITE);
		  rotate.setInterpolator(Interpolator.LINEAR);
		  rotate.setByAngle(360);
		  rotate.setAxis(Rotate.Z_AXIS);
		  rotate.play();
		elegxos(songs.get(songnum));
		elegxos(songs2.get(songnum2));
		settimer();
		changespeed(null);
		mediaPlayer.setVolume(vol.getValue() * 0.01);
		handleImageViewClick2();
		handleImageViewClick();
		
		mediaPlayer.play();
		
	}
	public void resetmedia() {
		
		prog.setProgress(0);
		mediaPlayer.seek(Duration.seconds(0));
	}
	
	public void pausemedia() {
		//translate.stop();
		rotate.stop();
		stoptimer();
		mediaPlayer.pause();
		
	}
	public void handleImageViewClick3(List<File> playlist,int currentIndex) { //////////////3-----------------------------------------------------------------------------------
		selectedAlbum=3;
		playlist= retplaylist(playlist);
		media = new Media(playlist.get(currentIndex).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		songLabel.setText(playlist.get(currentIndex).getName());
		settimer();
		mediaPlayer.play();
		pausemedia();
		nextmedia();
		previousmedia();
	}
	public List<File> retplaylist(List<File> playlist){
		return playlist;
	}
	
	public void nextmedia() {
		 boolean agapistatus;
     	
     	//if(agapiStatus==false) {
     	//	 heartShape.setFill(Color.TRANSPARENT);
     	//}
     	if(isAgapi()== true) {
     		
     		agapistatus=false;
     	}else {
     		
     		agapistatus=true;
     	}
     	System.out.println("------------"+agapistatus);
		File currentSong =songs.get(songnum);
		File currentSong2 =songs2.get(songnum2);
		elegxos(songs.get(songnum));
		elegxos(songs2.get(songnum2));
		
		 if (songChangeListener != null) { //*************************************************************************************************************
		        songChangeListener.onSongChanged(isAgapi()); // isAgapi() επιστρέφει true/false
		    }
		
		handleImageViewClick2();
		handleImageViewClick();
		 if (selectedAlbum == 1) {
		        songnum = (songnum + 1) % songs.size(); // Loop to the start
				elegxos(songs.get(songnum));
				elegxos(songs2.get(songnum2));
		        switchMedia(songs, songnum);
		    } else if (selectedAlbum == 2) {
		        songnum2 = (songnum2 + 1) % songs2.size(); // Loop to the start
				elegxos(songs.get(songnum));
				elegxos(songs2.get(songnum2));
		        switchMedia(songs2, songnum2);
		    }
		
	}
	public void previousmedia() {
		if(isAgapi()==false) {
			liked=false;
		}else {
			liked=true;
		}
		//File currentSong =songs.get(songnum);
		//File currentSong2 =songs2.get(songnum2);
		//elegxos(currentSong);
		//elegxos(currentSong2);
		handleImageViewClick2();
		handleImageViewClick();
		//playmedia();
	
		//mediaPlayer.play();
		
		if (selectedAlbum == 1) {
		if(songnum > 0) {
			
			songnum--;
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			mediaPlayer.stop();
			rotate.stop();
			handleImageViewClick2();
			handleImageViewClick();
			
			if(running) {
				
				stoptimer();
				handleImageViewClick2();
				handleImageViewClick();
			}
			
			media = new Media(songs.get(songnum).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs.get(songnum).getName());
			handleImageViewClick2();
			handleImageViewClick();
			playmedia();
			
		}
		else {
			
			songnum = songs.size() - 1;
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			mediaPlayer.stop();
			handleImageViewClick2();
			handleImageViewClick();
			
			if(running) {
				
				stoptimer();
				
				handleImageViewClick2();
				handleImageViewClick();
			}
			
			
			media = new Media(songs.get(songnum).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs.get(songnum).getName());
			handleImageViewClick2();
			handleImageViewClick();
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			playmedia();
			
		}
		mediaPlayer.play();
		 } else if (selectedAlbum == 2) {
		
		if(songnum2 > 0) {
			
			songnum2--;
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			mediaPlayer.stop();
			rotate.stop();
			handleImageViewClick2();
			handleImageViewClick();
			
			if(running) {
				
				stoptimer();
				handleImageViewClick2();
				handleImageViewClick();
			}
			
			media = new Media(songs2.get(songnum2).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs2.get(songnum2).getName());
			handleImageViewClick2();
			handleImageViewClick();
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			playmedia();
			
		}
		else {
			
			songnum2 = songs2.size() - 1;
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			mediaPlayer.stop();
			rotate.stop();
			handleImageViewClick2();
			handleImageViewClick();
			
			if(running) {
				
				stoptimer();
				handleImageViewClick2();
				handleImageViewClick();
			}
			
			media = new Media(songs2.get(songnum2).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			
			songLabel.setText(songs2.get(songnum2).getName());
			handleImageViewClick2();
			handleImageViewClick();
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			playmedia();
		}
		 }
		
		/*
			media = new Media(songs.get(songnum).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			songLabel.setText(songs.get(songnum).getName());*/
			handleImageViewClick2();
			handleImageViewClick();
			//toggleFavorite();
			elegxos(songs.get(songnum));
			elegxos(songs2.get(songnum2));
			playmedia();
		
		
			
		}
	
	
	
	public void changespeed(ActionEvent event ) {
		if(speed.getValue()==null) {
			mediaPlayer.setRate(1);
			
		}else {
			mediaPlayer.setRate(Integer.parseInt(speed.getValue().substring(0, speed.getValue().length() - 1)) * 0.01);
			//mediaPlayer.setRate(Integer.parseInt(speed.getValue())*0.01);
		}
		
		
	}
	public void settimer() {
		timer = new Timer();
		task = new TimerTask() {
			
			public void run() {
				
				running=true;
				double current= mediaPlayer.getCurrentTime().toSeconds();
				double end = mediaPlayer.getTotalDuration().toSeconds();
				System.out.println(current/end);
				prog.setProgress(current/end);
				if(current/end==1) {
					stoptimer();
					rotate.stop();
				}
			}	
		};
		timer.scheduleAtFixedRate(task ,0,1000);
		
		
	}
	public void stoptimer() {
		running=false;
		rotate.stop();
		timer.cancel();
		
	}
	
	public void elegxos(File currentSong) {
		
		if (favs.contains(currentSong)) {
            System.out.println("Το τραγούδι υπάρχει στα αγαπημένα!");
            agapi=true;
            /*heart.setOnAction(event -> {
            	 heart.setStyle("-fx-background-color: transparent;");
            });*/
        } else {
            System.out.println("Το τραγούδι δεν υπάρχει στα αγαπημένα.");
            agapi=false;
        }
    }
	 public boolean isAgapi() {
		
	        return agapi;
	        
	    }
	 private void switchMedia(List<File> playlist, int index) {
		 if (timer != null) {
			 
		    mediaPlayer.stop();
		    media = new Media(playlist.get(index).toURI().toString());
		    mediaPlayer = new MediaPlayer(media);
		    
		    songLabel.setText(playlist.get(index).getName());
		    playmedia();
		}
		 else if(timer == null) {
			 media = new Media(playlist.get(index).toURI().toString());
			    mediaPlayer = new MediaPlayer(media);
			    elegxos(songs.get(songnum));
				elegxos(songs2.get(songnum2));
			    songLabel.setText(playlist.get(index).getName());
			    playmedia();
			 }
		 }

	public OnSongChangeListener getSongChangeListener() {
		return songChangeListener;
	}

	public void setSongChangeListener(OnSongChangeListener songChangeListener) {
		this.songChangeListener = songChangeListener;
	}

	public OnAlbumChangeListener getAlbumChangeListener() {
		return albumChangeListener;
	}

	public void setAlbumChangeListener(OnAlbumChangeListener albumChangeListener) {
		this.albumChangeListener = albumChangeListener;
	}
	 
	 //----------------------------------------------------------------------------------------------------------------------


}