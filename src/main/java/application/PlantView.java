/**********************************************************************
 * Programmer: Christina Mullen
 * 0861078
 * Advanced Java Programming
 * Final Project: Flora
 * Copyright 12/04/2022
 *************************************************************************/

package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Arrays;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PlantView {

	private Image landingPageImage;
	protected Button loginButton;
	protected Button enterButton;
	protected Button signUpButton;
	protected Button backButton;
	protected File imageFile;

	private Image placeHolderImage;

	private ImageView icon1;
	private ImageView icon2;
	private ImageView icon3;
	private ImageView icon4;
	private ImageView iconAddNew;
	private ImageView iconAddNew2;
	private ImageView iconOutdoor;
	private ImageView iconIndoor;
	private ImageView iconStorefront;
	private ImageView iconStorefront2;
	private ImageView waterDropsImg;
	private ImageView sunImg;
	private ImageView plantImageView;
	private ImageView pageBreakImg;

	protected Label selectAfile;

	protected ObservableList<Plant> aOLIndoorPlants;
	protected ObservableList<Plant> aOLOutdoorPlants;

	protected TableView<Plant> tvOutdoor;

	protected CycledView g;
	protected CycledView f;
	protected CycledView e_add;
	protected CycledView d;
	protected CycledView d_1;
	protected CycledView c;
	protected CycledView b;
	protected CycledView b_1;
	protected CycledView a;
	protected String styleSheet;
	DropShadow dropShadow;
	protected PlantController theController;



	public PlantView(PlantController aController) {
		theController = aController;

		dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));

		landingPageImage = getImage("/images/FloraTitle1.png");
		icon1 = getImageViewAsResource("/images/Icon1.png");
		icon2 = getImageViewAsResource("/images/Icon2.png");
		icon3 = getImageViewAsResource("/images/Icon3.png");
		icon4 = getImageViewAsResource("/images/Icon4.png");
		iconAddNew = getImageViewAsResource("/images/addNewPlantTile.png");
		iconAddNew2 = getImageViewAsResource("/images/addNewPlantTile.png");
		iconOutdoor = getImageViewAsResource("/images/collection_outdoorTile.png");
		iconIndoor = getImageViewAsResource("/images/collection_indoorTile.png");
		iconStorefront = getImageViewAsResource("/images/storefrontTile.png");
		iconStorefront2 = getImageViewAsResource("/images/storefrontTile.png");

		placeHolderImage = getImage("/images/placeHolderImage.png");
		plantImageView = new ImageView(placeHolderImage);

		waterDropsImg = getImageViewAsResource("/images/waterDrops.png");
		sunImg = getImageViewAsResource("/images/sun.png");
		pageBreakImg = getImageViewAsResource("/images/pageBreak.png");

		iconAddNew.setEffect(dropShadow);
		iconAddNew2.setEffect(dropShadow);
		iconIndoor.setEffect(dropShadow);
		iconOutdoor.setEffect(dropShadow);
		iconStorefront.setEffect(dropShadow);
		iconStorefront2.setEffect(dropShadow);

		icon1.setEffect(dropShadow);
		icon2.setEffect(dropShadow);
		icon3.setEffect(dropShadow);
		icon4.setEffect(dropShadow);

		styleSheet = getClass().getResource("/application.css").toExternalForm();

		g = new G_Store(aController);
		//f = new F_Item(aController);
		e_add = new E_AddNewPlant(aController);
		d = new D_indoor(aController);
		d_1 = new D_Outdoor(aController);
		c = new C_Dashboard(aController);
		b = new B_Login(aController);
		b_1 = new B_signUp(aController);
		a = new A(aController);

	}

	private ImageView getImageViewAsResource(String aFilepath) {
		Image anImage = getImage(aFilepath);

		ImageView result = new ImageView(anImage);

		return result;
	}
	private Image getImageFromFile(File aFilepath) {
		try {
			InputStream instream = new FileInputStream(aFilepath);
			Image theImage = new Image(instream);

			instream.close();
			return theImage;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	private ImageView getImageViewFromFile(File aFilepath) {
		try {
			InputStream instream = new FileInputStream(aFilepath);
			Image theImage = new Image(instream);
			ImageView result = new ImageView(theImage);
			instream.close();
			return result;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	private Image getImage(String filename) {
		try {
			InputStream instream = this.getClass().getResourceAsStream(filename);
			Image theImage = new Image(instream);
			instream.close();
			return theImage;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public abstract class CycledView extends StackPane {
		public static final String NOTES = "Notes";
		public static final String DESIRED_PRICE_IF_SOLD = "Desired Price if Sold";
		public static final String DATE_OF_AQUISITION = "Date of Aquisition";
		public static final String AQUIRED_FROM = "Aquired From";
		public static final String MOST_RECENT_WATERING = "Most recent watering";
		public static final String SOIL_TYPE = "Soil Type";
		public static final String PLANT_SIZE = "Plant Size";
		public static final String GENUS = "Genus";
		public static final String SPECIES = "Species";
		public static final String COMMON_NAME = "Common Name";
		
		protected PlantController controller;
		protected Scene theScene;
		
		public CycledView() {
			
		}

		public CycledView(PlantController aController) {
			controller = aController;
			createGUI();
			buildScene();
		}

		abstract void createGUI();

		protected void buildScene() {
			theScene = new Scene(this, 650, 850);
			theScene.getStylesheets().add(styleSheet);
		}

		public Scene getTheScene() {
			return theScene;
		}

	}

// Here you implement diffent GUIs for A, B and C
	public class A extends CycledView {

		public A(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			BackgroundImage coverPane_Fill = new BackgroundImage(landingPageImage, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

			enterButton = new Button("Enter");
			enterButton.getStyleClass().add("enter-button");
			GridPane eGrid = new GridPane();
			HBox hbox1 = new HBox();
			hbox1.setSpacing(4);
			hbox1.setAlignment(Pos.BOTTOM_CENTER);

			eGrid.setHgap(8);
			eGrid.setVgap(8);

			eGrid.setPadding(new Insets(20));
			eGrid.add(hbox1, 34, 58);
			eGrid.setBackground(new Background(coverPane_Fill));

			getChildren().add(eGrid);
			hbox1.getChildren().add(enterButton);

			enterButton.setOnMouseClicked(e -> {
				controller.handleEnterButton();
			});
		}

	}

	public class B_Login extends CycledView {
		protected Label userName;
		protected Label password;
		protected TextField tfUser;
		protected PasswordField tfPW;

		public B_Login(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			userName = new Label("Username");
			password = new Label("Password");
			tfUser = new TextField();
			tfPW = new PasswordField();
			Label signIn = new Label("Sign In");
			Label signUpLabel = new Label("Don't have an account?");
			signUpButton = new Button("Create Account");
			loginButton = new Button("Log In");
			signIn.setFont(Font.font("Kollektif", FontWeight.BOLD, 25));
			signIn.setTextFill(Color.rgb(137, 161, 98));
			signUpLabel.setTextFill(Color.rgb(137, 161, 98));

			BorderPane pane = new BorderPane();
			VBox vbox = new VBox(15);

			tfUser.setMinWidth(75);
			tfUser.setMaxWidth(250);
			tfPW.setMinWidth(75);
			tfPW.setMaxWidth(250);

			loginButton.getStyleClass().add("green-button");
			signUpButton.getStyleClass().add("green-button");

			vbox.setPadding(new Insets(10, 10, 20, 10));
			vbox.getChildren().addAll(signIn, userName, tfUser, password, tfPW, loginButton, signUpLabel, signUpButton);
			pane.setCenter(vbox);
			getChildren().add(pane);
			vbox.setAlignment(Pos.CENTER);

			loginButton.setOnMouseClicked(e -> {
				controller.handleLoginButton(tfUser.getText(), tfPW.getText());

			});
			signUpButton.setOnMouseClicked(e -> {
				controller.handleSignUpButton();
			});

		}
	}

	public class B_signUp extends CycledView {
		protected Label userName;
		protected Label password;
		protected Label password2;
		protected TextField tfUser;
		protected PasswordField tfPW;
		protected PasswordField tfPW2;

		public B_signUp(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			Label createAccount = new Label("Create an Account");
			loginButton = new Button("Create Account");
			userName = new Label("Username");
			password = new Label("Password");
			password2 = new Label("Re-Enter Password");
			tfUser = new TextField();
			tfPW = new PasswordField();
			tfPW2 = new PasswordField();

			// Label signUpLabel = new Label("Don't have an account?");
			createAccount.setFont(Font.font("Kollektif", FontWeight.BOLD, 30));
			createAccount.setTextFill(Color.rgb(137, 161, 98));
			// signUpLabel.setTextFill(Color.rgb(137, 161, 98));

			BorderPane pane = new BorderPane();
			VBox vbox = new VBox(8);

			tfUser.setMinWidth(75);
			tfUser.setMaxWidth(300);
			tfPW.setMinWidth(75);
			tfPW.setMaxWidth(300);
			tfPW2.setMinWidth(75);
			tfPW2.setMaxWidth(300);

			loginButton.getStyleClass().add("green-button");

			vbox.setPadding(new Insets(10, 10, 10, 10));
			vbox.getChildren().addAll(createAccount, userName, tfUser, password, tfPW, password2, tfPW2, loginButton);
			pane.setCenter(vbox);
			getChildren().add(pane);
			vbox.setAlignment(Pos.CENTER);

			loginButton.setOnMouseClicked(e -> {
				controller.handleCreateUser(tfUser.getText(), tfPW.getText(), tfPW2.getText());
			});

		}
	}

	public class C_Dashboard extends CycledView {

		public C_Dashboard(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			GridPane gridC = new GridPane();

			gridC.setHgap(35);
			gridC.setVgap(35);
			gridC.setPadding(new Insets(20));

			gridC.add(icon1, 0, 0);
			gridC.add(icon3, 0, 1);
			gridC.add(icon2, 1, 0);
			gridC.add(icon4, 1, 1);
			gridC.setAlignment(Pos.CENTER);

			getChildren().addAll(gridC);
			setStyle("-fx-background-color: lightgray;");

			icon1.setOnMouseClicked((MouseEvent e) -> {
				controller.handleIcView();
			});
			icon2.setOnMouseClicked(e -> {
				controller.handleOcView();
			});
			icon3.setOnMouseClicked(e -> {
				controller.handleAddNewButton();
			});
			icon4.setOnMouseClicked(e -> {
				controller.handleStoreView();
			});
		}
	}

	public class D_indoor extends CycledView {

		public D_indoor(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			aOLIndoorPlants = FXCollections.observableArrayList();
			TableView<Plant> tv = new TableView<Plant>();
			tv.setPlaceholder(new Label("No Plants to display. Add A Plant!"));
         tv.setItems(aOLIndoorPlants);

         buildPlantTableView(tv);

			IntegerBinding aListSizeProperty = Bindings.size(aOLIndoorPlants);
			Label total = new Label("Indoor Collection: "+ aOLIndoorPlants.size() +" plants");
	      total.setFont(Font.font("Kollektif", FontWeight.BOLD, 25));
	      total.setTextFill(Color.rgb(137, 161, 98));

			aListSizeProperty.addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					//int pCount = (int) newValue;
					tv.refresh();
					total.setText("Indoor Collection: "+ newValue + " plants");

				}
			});


			VBox vbox = new VBox(2);
			BorderPane pane = new BorderPane();
			vbox.setPadding(new Insets(10, 10, 10, 10));
			vbox.getChildren().addAll(total, tv, iconAddNew2, iconOutdoor, iconStorefront2);
			pane.setCenter(vbox);
			vbox.setAlignment(Pos.CENTER);
			setStyle("-fx-background-color: lightgray;");
			getChildren().add(pane);

			iconAddNew2.setOnMouseClicked(e -> {
				controller.handleAddNewButton();
			});
			iconOutdoor.setOnMouseClicked(e -> {
				controller.handleOcView();
			});
			iconStorefront2.setOnMouseClicked(e -> {
				
				controller.handleStoreView();
			});

		}


	}

	public class D_Outdoor extends CycledView {

		public D_Outdoor(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			aOLOutdoorPlants = FXCollections.observableArrayList();
			TableView<Plant> tv = new TableView<Plant>();
         tv.setItems(aOLOutdoorPlants);

			tv.setPlaceholder(new Label("No Plants to display. Add A Plant!"));
         buildPlantTableView(tv);


			IntegerBinding aListSizeProperty = Bindings.size(aOLOutdoorPlants);
			Label total = new Label("Outdoor Collection: "+ aOLOutdoorPlants.size() +" plants");
			aListSizeProperty.addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					tv.refresh();
					total.setText("Outdoor Collection: "+ newValue + " plants");

				}
			});

			VBox vbox = new VBox(2);
			BorderPane pane = new BorderPane();

			total.setFont(Font.font("Kollektif", FontWeight.BOLD, 25));
			total.setTextFill(Color.rgb(137, 161, 98));

			vbox.setPadding(new Insets(10, 10, 10, 10));
			vbox.getChildren().addAll(total, tv, iconAddNew2, iconIndoor, iconStorefront);
			pane.setCenter(vbox);
			vbox.setAlignment(Pos.CENTER);
			setStyle("-fx-background-color: lightgray;");
			getChildren().add(pane);

			iconAddNew2.setOnMouseClicked(e -> {
				
				controller.handleAddNewButton();
			});
			iconIndoor.setOnMouseClicked(e -> {
				controller.handleIcView();
			});
			iconStorefront.setOnMouseClicked(e -> {
				controller.handleStoreView();
			});

		}
	}

	public class E_AddNewPlant extends CycledView {

		public TextField aTfcommonName;
		public TextField aTfSpecies;
		public TextField aTfgenus;
		public ToggleGroup aRgRbSize;
		public TextField aAqFromtf;
		public DatePicker aDateOfAqDP;
		public TextField aPricetf;
		public TextField aNotestf;
		public TextField aSoilTypetf;
		public DatePicker aLastWateringDP;
		public Slider aSlHWater;
		public Slider aSlHSun;
		public Slider aSlHwaterFreq;
		public DatePicker aFirstWaterDP;
		public ToggleGroup aTgRbInOut;
		public ToggleGroup aTgRbAquisitionType;

		public E_AddNewPlant(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			//Plant aPlant = new Plant();
			VBox vboxOuter = new VBox(3);
			vboxOuter.setAlignment(Pos.CENTER);

			Label addNewHeader = new Label("Add New Plant");
			addNewHeader.setFont(Font.font("Kollektif", FontWeight.BOLD, 25));
			addNewHeader.setTextFill(Color.rgb(137, 161, 98));

			Button btAddNew = new Button("Add New");
			btAddNew.getStyleClass().add("green-button");

			VBox vboBottomButton = new VBox(5);
			// vboBottomButton.setPadding(new Insets(10, 10, 10, 10));
			vboBottomButton.getChildren().add(btAddNew);
			vboBottomButton.setAlignment(Pos.CENTER);

			// Top Half
			HBox hboTop = new HBox(45);
			hboTop.setAlignment(Pos.CENTER);

			// Top Left
			VBox vboTopLeft = new VBox(5);
			vboTopLeft.setPadding(new Insets(5, 5, 5, 5));
			// Top Right
			VBox vboTopRight = new VBox(5);
			vboTopRight.setPadding(new Insets(5, 5, 5, 5));

			HBox hboRadInOrOut = new HBox(4);
			hboRadInOrOut.setPadding(new Insets(10, 10, 10, 10));
			// hboRadInOrOut.setStyle("−fx−border−width: 2px; −fx−border−color: #a1a19f;");
			RadioButton rbIndoor = new RadioButton("Indoor");
			RadioButton rbOutdoor = new RadioButton("Outdoor");
			aTgRbInOut = new ToggleGroup();

			rbIndoor.setToggleGroup(aTgRbInOut);
			rbOutdoor.setToggleGroup(aTgRbInOut);
			rbIndoor.setSelected(true);

			hboRadInOrOut.getChildren().addAll(rbIndoor, rbOutdoor);
			hboRadInOrOut.setAlignment(Pos.CENTER);


			// plant info tfs and labels
			Label commonNamelbl = new Label(COMMON_NAME);
			aTfcommonName = new TextField();
			//String commonName = aTfcommonName.getText();

			Label specieslbl = new Label(SPECIES);
			aTfSpecies = new TextField();

			Label genuslbl = new Label(GENUS);
			aTfgenus = new TextField();

			aTfcommonName.setMinWidth(250);
			aTfSpecies.setMinWidth(250);
			aTfgenus.setMinWidth(250);

			HBox hboTopButtonGroups = new HBox(5);
			hboTopButtonGroups.getStyleClass().add("top-button-group");
			Label lblSizeHeader = new Label(PLANT_SIZE);
			lblSizeHeader.setFont(Font.font("Kollektif", FontWeight.BOLD, 15));
			lblSizeHeader.setTextFill(Color.rgb(137, 161, 98));

			VBox vboRbSize = new VBox(5);
			// vboRbSize.setStyle("−fx−border−width: 2px; −fx−border−color: #a1a19f;");
			vboRbSize.setPadding(new Insets(5, 5, 5, 5));

			RadioButton rbXSmall = new RadioButton("x-small");
			RadioButton rbSmall = new RadioButton("Small");
			RadioButton rbMed = new RadioButton("Medium");
			RadioButton rbLrg = new RadioButton("Large");

			aRgRbSize = new ToggleGroup();

			aRgRbSize.getSelectedToggle();
			rbXSmall.setToggleGroup(aRgRbSize);
			rbSmall.setToggleGroup(aRgRbSize);
			rbMed.setToggleGroup(aRgRbSize);
			rbLrg.setToggleGroup(aRgRbSize);
			rbXSmall.setSelected(true);

			VBox vboAquisitionType = new VBox(5);
			vboAquisitionType.setPadding(new Insets(5, 5, 5, 5));
			Label lblAquisitionType = new Label("Type");
			lblAquisitionType.setFont(Font.font("Kollektif", FontWeight.BOLD, 15));
			lblAquisitionType.setTextFill(Color.rgb(137, 161, 98));

			RadioButton rbPotted = new RadioButton("Potted Plant");
			RadioButton rbSeed = new RadioButton("Seed");
			RadioButton rbCutting = new RadioButton("Cutting/Clone");
			RadioButton rbPup = new RadioButton("Pup");

			aTgRbAquisitionType = new ToggleGroup();
			rbPotted.setToggleGroup(aTgRbAquisitionType);
			rbSeed.setToggleGroup(aTgRbAquisitionType);
			rbCutting.setToggleGroup(aTgRbAquisitionType);
			rbPup.setToggleGroup(aTgRbAquisitionType);
			rbPotted.setSelected(true);

			vboTopRight.getChildren().add(plantImageView);

			hboTop.getChildren().addAll(vboTopLeft, vboTopRight);
			vboTopRight.setAlignment(Pos.CENTER);



			vboAquisitionType.getChildren().addAll(lblAquisitionType, rbPotted, rbSeed, rbCutting, rbPup);

			vboRbSize.getChildren().addAll(lblSizeHeader, rbXSmall, rbSmall, rbMed, rbLrg);

			hboTopButtonGroups.getChildren().addAll(vboRbSize, vboAquisitionType);

			vboTopLeft.getChildren().addAll(commonNamelbl, aTfcommonName, specieslbl, aTfSpecies, genuslbl, aTfgenus,
					hboTopButtonGroups);


			plantImageView.setOnMouseClicked(e -> {
				try {
					controller.handleAddImageButton();
					//userSelectedImgFile = getImageView(imageFile);
					System.out.println(imageFile);
					plantImageView.setImage(getImageFromFile(imageFile));
					//vboTopRight.getChildren().add(getImageViewFromFile(imageFile));
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});

			btAddNew.setOnMouseClicked(e -> {
				if (rbIndoor.isSelected()) {

					controller.handleAddNewPlant(this);
					controller.handleIcView();
				} else {
					// TODO add to Outdoor collection DB
					controller.handleAddNewPlant(this);
					controller.handleOcView();
				}
			});


			/*****************************************
			 * Bottom Half
			 *
			 *
			 *****************************************/

			HBox hboBott = new HBox(45);
			vboxOuter.getChildren().addAll(addNewHeader, hboRadInOrOut, hboTop, hboBott, vboBottomButton);
			hboBott.setAlignment(Pos.CENTER);

			// Bottom Left
			VBox vboBottL_Aqui = new VBox(8);
			vboBottL_Aqui.setPadding(new Insets(5, 5, 5, 5));

			Label lblAquisition = new Label("Aquisition");
			lblAquisition.setAlignment(Pos.CENTER);
			lblAquisition.setFont(Font.font("Kollektif", FontWeight.BOLD, 20));
			lblAquisition.setTextFill(Color.rgb(137, 161, 98));

			Label aqFromlbl = new Label(AQUIRED_FROM);
			aAqFromtf = new TextField();

			Label dateOfAqlbl = new Label(DATE_OF_AQUISITION);
			aDateOfAqDP = new DatePicker();
			aDateOfAqDP.setValue(LocalDate.now());

			Label desiredpricelbl = new Label(DESIRED_PRICE_IF_SOLD);
			aPricetf = new TextField("$");

			Label noteslbl = new Label(NOTES);
			aNotestf = new TextField();

			aAqFromtf.setMinWidth(75);
			aAqFromtf.setMaxWidth(250);

			aDateOfAqDP.setMinWidth(75);
			aDateOfAqDP.setMaxWidth(250);

			aPricetf.setMinWidth(75);
			aPricetf.setMinWidth(250);

			aNotestf.setMinWidth(75);
			aNotestf.setMinWidth(250);
			aNotestf.setMinHeight(60);

			vboBottL_Aqui.getChildren().addAll(lblAquisition, aqFromlbl, aAqFromtf, dateOfAqlbl, aDateOfAqDP,
						desiredpricelbl, aPricetf, noteslbl, aNotestf);

			// Bottom Right
			VBox vboBottR_enviro = new VBox(10);

			vboBottR_enviro.setPadding(new Insets(10, 10, 10, 10));

			hboBott.getChildren().addAll(vboBottL_Aqui, vboBottR_enviro);

			Label enviroHeader = new Label("Enviroment");
			enviroHeader.setFont(Font.font("Kollektif", FontWeight.BOLD, 20));
			enviroHeader.setTextFill(Color.rgb(137, 161, 98));
			enviroHeader.setAlignment(Pos.CENTER);

			//HBox hboSoilCalendar = new HBox(4);
			// Soil and sliders "middle"
			VBox vboSoilType = new VBox(5);

			Label soilTypelbl = new Label(SOIL_TYPE);
			aSoilTypetf = new TextField();
			vboSoilType.getChildren().addAll(soilTypelbl, aSoilTypetf);

			Label lastWatering = new Label(MOST_RECENT_WATERING);
			aLastWateringDP = new DatePicker();
			aLastWateringDP.setValue(LocalDate.now());
			// TextField iWateringtf = new TextField();
			VBox vboIniWatering = new VBox(5);
			vboIniWatering.getChildren().addAll(lastWatering, aLastWateringDP);

			HBox hboWaterImgSlid = new HBox(25);
			aSlHWater = new Slider(1,5,1);

			aSlHWater.setMinorTickCount(1);
			aSlHWater.setMajorTickUnit(1);
			aSlHWater.setShowTickMarks(true);
			aSlHWater.setShowTickLabels(true);
			hboWaterImgSlid.getChildren().addAll(waterDropsImg, aSlHWater);

			HBox hboSunImgSlid = new HBox(25);
			aSlHSun = new Slider(1,5,1);

			aSlHSun.setMinorTickCount(1);
			aSlHSun.setMajorTickUnit(1);
			aSlHSun.setShowTickMarks(true);
			aSlHSun.setShowTickLabels(true);
			hboSunImgSlid.getChildren().addAll(sunImg, aSlHSun);

			Label waterFreqlbl = new Label("Watering Interval (Days until next watering)");
			aSlHwaterFreq = new Slider(0,28,1);
			aSlHwaterFreq.setMajorTickUnit(7);
			aSlHwaterFreq.setMinorTickCount(1);
			aSlHwaterFreq.setShowTickMarks(true);
			aSlHwaterFreq.setShowTickLabels(true);

			VBox vboWaterFreq = new VBox(5);
			vboWaterFreq.getChildren().addAll(waterFreqlbl, aSlHwaterFreq);
			aFirstWaterDP = new DatePicker();
			aFirstWaterDP.setValue(LocalDate.now());

			aSoilTypetf.setMinWidth(75);
			aSoilTypetf.setMaxWidth(250);

			aLastWateringDP.setMinWidth(75);
			aLastWateringDP.setMaxWidth(250);

			aSlHWater.setMinWidth(75);
			aSlHWater.setMaxWidth(250);

			aSlHSun.setMinWidth(75);
			aSlHSun.setMaxWidth(250);

			aSlHwaterFreq.setMinWidth(55);
			aSlHwaterFreq.setMinWidth(250);

			vboBottR_enviro.getChildren().addAll(enviroHeader, vboSoilType, vboIniWatering, vboWaterFreq, hboWaterImgSlid, hboSunImgSlid);

			getChildren().addAll(vboxOuter);

		}
	}

	public class F_Item extends CycledView {

		private Plant aPlant;

		public F_Item(PlantController aController, Plant aPlant) {
			this.aPlant = aPlant;
			controller = aController;
			createGUI();
			buildScene();
			
		}

		@Override void createGUI() {

			buildPlantView(aPlant);
			setStyle("-fx-background-color: lightgray;");

		}
		public void buildPlantView(Plant aPlant) {
			VBox vboxOuter = new VBox(3);
			getChildren().add(vboxOuter);
			vboxOuter.setAlignment(Pos.CENTER);
			
			Button btBack = new Button("Back");
			Button btUpdate = new Button("Update");
			//Button btStore = new Button("Shop");
			btBack.getStyleClass().add("green-button");
			btUpdate.getStyleClass().add("green-button");
			//btStore.getStyleClass().add("green-button");

			HBox hboBottomButtons = new HBox(5);
			hboBottomButtons.getChildren().addAll(btBack, btUpdate);
			hboBottomButtons.setAlignment(Pos.CENTER);

			// Top Half
			HBox hboTop = new HBox(15);
			hboTop.setAlignment(Pos.CENTER);

			// Top Left
			VBox vboTopLeft = new VBox(5);
			vboTopLeft.setPadding(new Insets(5, 5, 5, 5));
			// Top Right
			VBox vboTopRight = new VBox(5);
			vboTopRight.setPadding(new Insets(5, 5, 5, 5));
			
			String header = new String(aPlant.getCommon_Name()+ " " + aPlant.getPlantID());
			Label plantHeader = new Label(header);
			plantHeader.setFont(Font.font("Kollektif", FontWeight.BOLD, 40));
			plantHeader.setTextFill(Color.rgb(137, 161, 98));
			
			String inOrOut = aPlant.getInorOut();
			Label lblInOut = new Label("in "+ inOrOut +" Collection");
			lblInOut.setFont(Font.font("Kollektif", FontWeight.BOLD, 20));
			lblInOut.setTextFill(Color.rgb(137, 161, 98));


			//TextField tfInOut = new TextField(inOrOut);
			/**********************************************************************
			 * Make constant field name labels
			 * 
			 * Add field name labels for these items
			 *************************************************************************/
			String species = aPlant.getSpecies_Name();
			Label specieslbl = new Label(SPECIES);
			TextField tfspecies = new TextField(species);

			String genus = aPlant.getGenus();
			Label genuslbl = new Label(GENUS);
			TextField tfgenus = new TextField(genus);
			
			String size = aPlant.getSize();
			Label lblSize = new Label(PLANT_SIZE);
			TextField tfsize = new TextField(size);
			
			String type = aPlant.getType();
			Label lblType = new Label(SOIL_TYPE);
			TextField tftype = new TextField(type);
			
			//Aquisition
			
			String aqFrom = aPlant.getAquired_from();
			Label lblAqFrom = new Label(AQUIRED_FROM);
			TextField tfAqFrom = new TextField(aqFrom);
			
			tfAqFrom.setMinWidth(150);
			//tfAqFrom.setMaxWidth(250);
			
			String dateOfAq = aPlant.getDate_ofAquisition().toString();
			Label lbldateOfAq = new Label(DATE_OF_AQUISITION);
			TextField tfDateOfAq = new TextField(dateOfAq);
			
			tfDateOfAq.setMinWidth(150);
			//tfDateOfAq.setMaxWidth(250);
			
			String price = aPlant.getCost();
			Label lblPrice = new Label(DESIRED_PRICE_IF_SOLD);
			TextField tfprice = new TextField(price);
			
			tfprice.setMinWidth(150);
			//tfprice.setMaxWidth(250);
			
			String notes = aPlant.getPlant_notes();
			Label lblnotes = new Label(NOTES);
			TextField tfnotes = new TextField(notes);
			
			tfnotes.setMinWidth(200);
			//tfnotes.setMaxWidth(250);
			
			
			/**********************************************************************
			 * Make constant field name labels
			 * 
			 * 
			 *************************************************************************/
			//Environment
			
			String soilType = aPlant.getSoil_type();
			Label lblST = new Label(SOIL_TYPE);
			TextField tfST = new TextField(soilType);
			
			tfST.setMinWidth(200);
			//tfST.setMaxWidth(300);
			
			String lastWater = aPlant.getLastWatered().toString();
			Label lblLw = new Label("Last Watering Date");
			Label lblLwDate = new Label(lastWater);
			lblLw.setFont(Font.font("Kollektif", FontWeight.BOLD, 16));
			lblLw.setTextFill(Color.rgb(137, 161, 98));
			
			String waterInt = ("Interval: "+ aPlant.getIntWater_interval()+ " days");
			Label lblWI = new Label(waterInt);
			//TextField tfWI = new TextField(waterInt);
			
			//String nextWater = aPlant.getNextWateringDate().toString();
			
			Label lblNW = new Label("Next Watering");
			lblNW.setFont(Font.font("Kollektif", FontWeight.BOLD, 16));
			lblNW.setTextFill(Color.rgb(137, 161, 98));
			
			Label lblWaterdisplay = new Label(aPlant.getNextWateringDateDisplay());
			lblWaterdisplay.setFont(Font.font("Kollektif", FontWeight.BOLD, 16));
			
			RadioButton rbDeadPlant = new RadioButton("Plant Died");
			RadioButton rbForSale = new RadioButton("For Sale");
			RadioButton rbWateredToday = new RadioButton("Watered Today");
			
			hboTop.getChildren().addAll(vboTopLeft, vboTopRight);
			vboTopRight.setAlignment(Pos.CENTER);
			
			vboTopLeft.getChildren().addAll(genuslbl, tfgenus, specieslbl, tfspecies, lblSize, tfsize, lblType, tftype);
			vboTopRight.getChildren().add(plantImageView);
			
			HBox hboBott = new HBox(45);
			vboxOuter.getChildren().addAll(plantHeader, lblInOut, hboTop, hboBott, hboBottomButtons);
			hboBott.setAlignment(Pos.CENTER);

			// Bottom Left
			VBox vboBottL_Aqui = new VBox(8);
			vboBottL_Aqui.setPadding(new Insets(5, 5, 5, 5));
			
			
			Label lblAquisition = new Label("Aquisition");
			lblAquisition.setAlignment(Pos.CENTER);
			lblAquisition.setFont(Font.font("Kollektif", FontWeight.BOLD, 20));
			lblAquisition.setTextFill(Color.rgb(137, 161, 98));
		
			
			// Bottom Right
			VBox vboBottR_enviro = new VBox(8);
			vboBottR_enviro.setPadding(new Insets(10, 10, 10, 10));

			vboBottL_Aqui.getChildren().addAll(lblAqFrom, tfAqFrom, lbldateOfAq, tfDateOfAq, lblPrice, tfprice, rbForSale, rbDeadPlant, lblnotes, tfnotes);
			
			vboBottR_enviro.getChildren().addAll(lblST, tfST, lblLw, lblLwDate, lblNW, lblWaterdisplay, lblWI, rbWateredToday);
			
			hboBott.getChildren().addAll(vboBottL_Aqui, vboBottR_enviro);


			/*****************************************
			 * Buttons
			 *
			 *****************************************/
			btBack.setOnMouseClicked(e -> {
				if("Indoor".equals(inOrOut)) {
					controller.handleIcView();
				}
				else {
					controller.handleOcView();
				}
				
			});
			btUpdate.setOnMouseClicked(e->{
				if(rbDeadPlant.isSelected()) {
					System.out.println("Plant is no more");
				}
				if(rbForSale.isSelected()) {
					aPlant.setFor_Sale(true);
				}
				if(rbWateredToday.isSelected()) {
					aPlant.setLastWatered(LocalDate.now());
				}
				controller.handleUpdatePlant(aPlant);
	

			});

			
		}
	}

	public class G_Store extends CycledView {

		public G_Store(PlantController controller) {
			super(controller);
		}

		@Override void createGUI() {
			VBox vboxOuter = new VBox(1);
			vboxOuter.setAlignment(Pos.CENTER);
			VBox vboxA_1 = new VBox(8);
			vboxA_1.setAlignment(Pos.TOP_CENTER);

			Label lblHeader = new Label("Store");
			lblHeader.setFont(Font.font("Kollektif", FontWeight.BOLD, 25));
			lblHeader.setTextFill(Color.rgb(137, 161, 98));

			Label lblsubHeader1 = new Label("Your plants awaiting sale");
			lblsubHeader1.setFont(Font.font("Kollektif", FontWeight.BOLD, 18));
			lblsubHeader1.setTextFill(Color.rgb(51, 51, 51));

			VBox vboxInnerBottom = new VBox(8);
			vboxInnerBottom.setAlignment(Pos.CENTER);

			Label lblsubHeader2 = new Label("Neighbors plants for sale");
			lblsubHeader2.setFont(Font.font("Kollektif", FontWeight.BOLD, 18));
			lblsubHeader2.setTextFill(Color.rgb(51, 51, 51));

			HBox hboYourPlantTiles = new HBox(10);
			//StackPane yourPlantTile = new StackPane();

			HBox hboOthersPlantTiles = new HBox(10);
			vboxA_1.getChildren().addAll(lblHeader, lblsubHeader1, hboYourPlantTiles);
			// (lblsubHeader1, hboYourPlantTiles);
			vboxInnerBottom.getChildren().addAll(lblsubHeader2, hboOthersPlantTiles);

			vboxOuter.getChildren().addAll(vboxA_1, pageBreakImg, vboxInnerBottom);

			setStyle("-fx-background-color: lightgray;");
			getChildren().addAll(vboxOuter);

		}
	}


	public void buildPlantTableView(TableView<Plant> tv) {
		
		Callback<TableColumn<Plant, String>, TableCell<Plant, String>> stringCellFactory =
                new Callback<TableColumn<Plant, String>, TableCell<Plant, String>>() {
            @Override
            public TableCell<Plant, String> call(TableColumn<Plant, String> p) {
            	
                TableCell<Plant, String> cell = new TableCell<>();
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

        		    @Override
        		    public void handle(MouseEvent click) {

        		        if (click.getClickCount() == 2) {
        		        	@SuppressWarnings("unchecked")
							TableCell<Plant, String> c = (TableCell<Plant, String>) click.getSource();
        		        	int index = c.getIndex();
        		        	if (index < tv.getItems().size()) {
        		        		Plant st = tv.getItems().get(c.getIndex());
            		        	theController.handleViewPlant(st);
        		        	}
        		        }
        		    }
        		});
                return cell;
            }
        };

      TableColumn<Plant,String> col1 = new TableColumn<>("Next Watering");
      col1.setCellValueFactory(cellData -> cellData.getValue().getNextWateringProperty());
      col1.setPrefWidth(130);


      TableColumn<Plant,String> col2 = new TableColumn<>("Genus");
      col2.setCellValueFactory(cellData -> cellData.getValue().getGenusProperty());
      col2.setPrefWidth(130);

      TableColumn<Plant,String> col3 = new TableColumn<>("Species");
      col3.setCellValueFactory(cellData -> cellData.getValue().getSpeciesProperty());
      col3.setPrefWidth(100);

      TableColumn<Plant,Integer> col4 = new TableColumn<>("Plant ID");
      col4.setCellValueFactory(new PropertyValueFactory<>("plantID"));
      col4.setPrefWidth(100);

      TableColumn<Plant, String> col5 = new TableColumn<>("Click to View");
      col5.setCellFactory(stringCellFactory);
      col5.setPrefWidth(130);
      
      

      tv.getColumns().addAll( Arrays.asList( col1, col2, col3, col4, col5));
      
      /***************************************************
		 * To select a plant from the table
		 *
		 **************************************************

		TableViewSelectionModel<Plant> sm = tv.getSelectionModel();
		sm.setSelectionMode(SelectionMode.SINGLE);

		ObservableList<Plant> selItems = sm.getSelectedItems();
		
		selItems.addListener(new ListChangeListener<Plant>() {
			
		    @Override
		    public void onChanged(Change<? extends Plant> selection) {
		        System.out.println("Item Selected: " + selection.getList().get(0).getPlantID());

		      }

		});
		
		*/


   }
	

   public void showPrevious(Stage previousStage) {
		showView(previousStage, b);
	}

	public void showA(Stage theStage) {
		showView(theStage, a);
	}

	public void showB(Stage theStage) {
		showView(theStage, b);
	}

	public void showB_1(Stage theStage) {
		showView(theStage, b_1);
	}

	public void showC(Stage theStage) {
		showView(theStage, c);
	}

	public void showD_Indoor(Stage theStage) {
		theController.updateList(aOLIndoorPlants, "Indoor");
		showView(theStage, d);
	}

	public void showD_Outdoor(Stage theStage) {
		theController.updateList(aOLOutdoorPlants, "Outdoor");
		showView(theStage, d_1);
	}

	public void showE(Stage theStage) {
		plantImageView.setImage(placeHolderImage);
		showView(theStage, e_add);
	}

	public void showF(Stage theStage, Plant aPlant) {
		f = new F_Item(theController, aPlant);
		showView(theStage, f);
	}

	public void showG(Stage theStage) {
		showView(theStage, g);
	}

	public void showView(Stage theStage, CycledView theView) {
		// Scene scene = new Scene(theView, 650, 800);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		theStage.setScene(theView.getTheScene());
		theStage.show();
	}

}
