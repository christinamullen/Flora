/**********************************************************************
 * Programmer: Christina Mullen
 * 0861078
 * Advanced Java Programming
 * Final Project: Flora
 * Copyright 12/04/2022
 *************************************************************************/
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import application.PlantView.E_AddNewPlant;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PlantController {
	Alert a = new Alert(AlertType.NONE);
	private PlantView theView;
	private PlantModel theModel;
	protected Stage primaryStage;
	private User currentUser;
	protected Number plantListCount;
	 

	public PlantController(Stage aPrimaryStage) throws FileNotFoundException {
		primaryStage = aPrimaryStage;
		theView = new PlantView(this);
		theModel = PlantModel.getInstance();
	}

	public void handleEnterButton() {
		theView.showB(primaryStage);
	}

	public void handleLoginButton(String u, String p) {
		if(u.equals("") || p.equals("")) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Username and password cannot be null. Please enter something");
			a.show();
			
		}
		else {
			//validate credentials
			User aUser = theModel.getUser(u);
			if ((aUser == null) || (!aUser.getPassword().equals(p))){
				a.setAlertType(AlertType.ERROR);
				a.setContentText("Invalid credentials");
				a.show();
			}
			else {
				currentUser = aUser;
				//
				theView.showC(primaryStage);
			}
		}
		
	}

	public void showA() {
		theView.showA(primaryStage);

	}

	public void handleSignUpButton() {
		theView.showB_1(primaryStage);

	}

	public void handleCreateUser(String username, String pw, String pw2) {
		if (!pw.equals(pw2)) {
			a.setAlertType(AlertType.ERROR);
			a.setContentText("Passwords don't match, try again..");
			a.show();
			//
			
		} else {
			//Test if there is already a user
			
			try {
				User aUser = theModel.getUser(username);
				if(aUser != null) {
					a.setAlertType(AlertType.ERROR);
					a.setContentText("Username already exists, try again..");
					a.show();
					
				}
				else {
					theModel.addNewUser(username, pw);
					a.setAlertType(AlertType.CONFIRMATION);
					a.setContentText("New account created successfully");
					a.show();
					theView.showC(primaryStage);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
	}

	public void handleAddNewButton() {
		theView.showE(primaryStage);
		// TODO Make add new plant interface
		System.out.println("Add New button");

	}

	public void handleIcView() {
		theView.showD_Indoor(primaryStage);
		// TODO
		// lists all items in collection,
		// last one is always add new
		System.out.println("Indoor Collection");

	}

	public void handleOcView() {
		theView.showD_Outdoor(primaryStage);
		// TODO
		// lists all items in collection,
		// last one is always add new
		System.out.println("Outdoor Collection");
	}

	public void handleStoreView() {
		theView.showG(primaryStage);
		// TODO Auto-generated method stub
		System.out.println("Storefront");

	}

	public void handleBack() {
		// TODO Auto-generated method stub
		System.out.println("back button pressed");

	}

	public void handleAddImageButton() throws MalformedURLException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Image File");
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if (selectedFile != null) {
			theView.imageFile = selectedFile;
			System.out.println(theView.imageFile);
			// getImageView(imageFile);
		} else {
			// need to add Label selectAFile
			theView.selectAfile.setText("Image File selection cancelled");
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Please Select a File");
			/* alert.setContentText("You didn't select a file!"); */
			alert.showAndWait();
		}

	}
	// ImageView imageView;

	public void handleAddNewPlant(E_AddNewPlant view) {
		Plant aPlant = new Plant();
		aPlant.setOwnerID(currentUser.getId());
		aPlant.setCommon_Name(view.aTfcommonName.getText());
		aPlant.setGenus(view.aTfgenus.getText());
		aPlant.setSpecies_Name(view.aTfSpecies.getText());
		
		RadioButton x = (RadioButton)view.aRgRbSize.getSelectedToggle();
		aPlant.setSize(x.getText());

		RadioButton y = (RadioButton)view.aTgRbAquisitionType.getSelectedToggle();
		aPlant.setPlant_type(y.getText());
		
		aPlant.setAquired_from(view.aAqFromtf.getText());
		aPlant.setDate_ofAquisition(view.aDateOfAqDP.getValue());
		
		aPlant.setCost(view.aPricetf.getText());
		aPlant.setPlant_notes(view.aNotestf.getText());
		aPlant.setSoil_type(view.aSoilTypetf.getText());
		
		aPlant.setLastWatered(view.aLastWateringDP.getValue());
		
		aPlant.setWater_quantity(view.aSlHWater.getValue());
		aPlant.setSun_quantity(view.aSlHSun.getValue());
		aPlant.setWater_interval(view.aSlHwaterFreq.getValue());
		
		aPlant.setLastWatered(view.aFirstWaterDP.getValue());

		RadioButton z = (RadioButton)view.aTgRbInOut.getSelectedToggle();
		aPlant.setInorOut(z.getText());
		theModel.addNewPlantToDB(aPlant);
	}


	public void updateList(ObservableList<Plant> olp, String inOut) {
		try {
			theModel.getPlantListFromDB(olp, currentUser, inOut);
			System.out.println("updated");
		} catch (SQLException e) {
			System.out.println("boom");
			e.printStackTrace();
		}

	}

	public void handleViewPlant(Plant st) {
		theView.showF(primaryStage, st);
		
	}

	public void handleUpdatePlant(Plant aPlant) {
		theModel.updatePlantInDB(aPlant);
		
	}
}


