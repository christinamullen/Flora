/**********************************************************************
 * Programmer: Christina Mullen
 * 0861078
 * Advanced Java Programming
 * Final Project: Flora
 * Copyright 12/04/2022
 *************************************************************************/
package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sqlite.SQLiteDataSource;

import javafx.collections.ObservableList;

public class PlantModel {
   public static final String DB_FILE = "Flora.db";

   /*
   	 "factory method"for an instance
   	If PlantModel is needed in more than one place,
   	Not necessary to create another one
   */
	private static PlantModel theInstance;

   private SQLiteDataSource db;

   public static PlantModel getInstance() {
	   if (theInstance == null)
	   {
	      theInstance = new PlantModel();
	   }
		return theInstance;
	}

	private PlantModel(){
      db = new SQLiteDataSource();
      db.setUrl("jdbc:sqlite:"+DB_FILE);
	}

	public Connection getConnection() throws SQLException{
	   return db.getConnection();
	}

	public void addNewUser(String username, String password) throws SQLException{

		try(Connection connection = getConnection()){
			if (connection.isValid(0)) {
				System.out.println("Connected to Flora.db");
			}
			String qS = "insert into Users (username, password, date_created) "
					+ "values (?, ?, datetime('now'))";

			PreparedStatement preparedStatement = connection.prepareStatement(qS);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.execute();

		}catch (SQLException e) {
		// if the error message is "out of memory",
		e.printStackTrace();
		System.err.println(e.getMessage());
		}
	}

	public void addNewPlantToDB(Plant plant) {
		try(Connection connection = getConnection()){
			if (connection.isValid(0)) {
				System.out.println("Connected to Flora.db");
			}
			String qS = "insert into Plants (ownerID, common_name, genus, species, "
					+ "size, type, aquired_from, soil_type, water_quantity, sun_quantity, "
					+ "date_ofAquisition, date_ofFirstWatering, notes, "
					+ "water_frequency, cost, InOut, forSale) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(qS);
			preparedStatement.setInt(1, plant.getOwnerID());
			preparedStatement.setString(2, plant.getCommon_Name());
			preparedStatement.setString(3, plant.getGenus());
			preparedStatement.setString(4, plant.getSpecies_Name());
			preparedStatement.setString(5, plant.getSize());
			preparedStatement.setString(6, plant.getType());
			preparedStatement.setString(7, plant.getAquired_from());
			preparedStatement.setString(8, plant.getSoil_type());
			preparedStatement.setDouble(9, plant.getWater_quantity());
			preparedStatement.setDouble(10, plant.getSun_quantity());
			preparedStatement.setLong(11, plant.getLongDate_ofAquisition());
			preparedStatement.setLong(12, plant.getLastWateredLong());
			preparedStatement.setString(13, plant.getPlant_notes());
			preparedStatement.setDouble(14, plant.getWater_frequency());
			preparedStatement.setString(15, plant.getCost());
			preparedStatement.setString(16, plant.getInorOut());
			preparedStatement.setInt(17, plant.getIntForSale());


			preparedStatement.execute();
		}catch (SQLException e) {
		// if the error message is "out of memory",
		System.out.println("Databsse not found");
		System.err.println(e.getMessage());
		}

	}

	public User getUser(String username) {
		User aUser = null;
		try(Connection connection = getConnection()){
			if (connection.isValid(0)) {
				System.out.println("Connected to Flora.db");
			}

			String qS = "select id, username, password, date_created "
					+ "from Users where username = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(qS);
			preparedStatement.setString(1, username);
			ResultSet rset = preparedStatement.executeQuery();
			if(rset.next()) {
				aUser = new User(rset.getInt("id"), rset.getString(2), rset.getString(3),rset.getString(4));
			}
		}catch (SQLException e) {
		// if the error message is "out of memory",
		e.printStackTrace();
		System.err.println(e.getMessage());
		}
		return aUser;

	}


	public ObservableList<Plant> getPlantListFromDB(ObservableList<Plant> plantsList, User currentUser, String inOut) throws SQLException {
		//ObservableList<Plant> plantsList = FXCollections.observableArrayList();
		plantsList.clear();
		System.out.println("cleared");
		try(Connection connection = getConnection()){
			if (connection.isValid(0)) {
				System.out.println("Connected to Flora.db");
			}
			String qS = "SELECT * FROM Plants where ownerID=? and InOut=?";

			PreparedStatement preparedStatement = connection.prepareStatement(qS);
			preparedStatement.setInt(1, currentUser.getId());
			preparedStatement.setString(2, inOut);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				Plant aPlant = new Plant();
				aPlant.setPlantID(rs.getInt(1));
				aPlant.setOwnerID(rs.getInt(2));
				aPlant.setCommon_Name(rs.getString(3));
				aPlant.setGenus(rs.getString(4));
				aPlant.setSpecies_Name(rs.getString(5));
				aPlant.setSize(rs.getString(6));
				aPlant.setPlant_type(rs.getString(7));
				aPlant.setAquired_from(rs.getString(8));
				aPlant.setSoil_type(rs.getString(9));
				aPlant.setIntWater_quantity(rs.getInt(10));
				aPlant.setIntSun_quantity(rs.getInt(11));
				aPlant.setLongDate_ofAquisition(rs.getLong(12));
				aPlant.setLastWateredLong(rs.getLong(13));
				aPlant.setPlant_notes(rs.getString(14));
				aPlant.setIntWater_interval(rs.getInt(15));
				aPlant.setCost(rs.getString(16));
				aPlant.setIntForSale(rs.getInt(17));
				aPlant.setInorOut(rs.getString(18));

				plantsList.add(aPlant);

			}
		}catch (SQLException e) {
			// if the error message is "out of memory",
			e.printStackTrace();
			System.err.println(e.getMessage());
			}
		System.out.println(plantsList.size());
		return plantsList;

	}
	public Plant getAPlantFromDB(User currentUser, int plantID) {
		Plant aPlant = new Plant();
		try(Connection connection = getConnection()){
			if (connection.isValid(0)) {
				System.out.println("Connected to Flora.db");
			}
			String qS = "SELECT * FROM Plants where ownerID=? and plantID=?";

			PreparedStatement preparedStatement = connection.prepareStatement(qS);
			preparedStatement.setInt(1, currentUser.getId());
			preparedStatement.setInt(2, plantID);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				aPlant.setPlantID(rs.getInt(1));
				aPlant.setOwnerID(rs.getInt(2));
				aPlant.setCommon_Name(rs.getString(3));
				aPlant.setGenus(rs.getString(4));
				aPlant.setSpecies_Name(rs.getString(5));
				aPlant.setSize(rs.getString(6));
				aPlant.setPlant_type(rs.getString(7));
				aPlant.setAquired_from(rs.getString(8));
				aPlant.setSoil_type(rs.getString(9));
				aPlant.setIntWater_quantity(rs.getInt(10));
				aPlant.setIntSun_quantity(rs.getInt(11));
				aPlant.setLongDate_ofAquisition(rs.getLong(12));
				aPlant.setLastWateredLong(rs.getLong(13));
				aPlant.setPlant_notes(rs.getString(14));
				aPlant.setIntWater_interval(rs.getInt(15));
				aPlant.setCost(rs.getString(16));
				aPlant.setIntForSale(rs.getInt(17));
				aPlant.setInorOut(rs.getString(18));
			}
		}catch (SQLException e) {
			// if the error message is "out of memory",
			e.printStackTrace();
			System.err.println(e.getMessage());
			}

		return aPlant;

	}

   public void updatePlantInDB(Plant plant) {
      try(Connection connection = getConnection()){
         if (connection.isValid(0)) {
            System.out.println("Connected to Flora.db");
         }
         String qS ="update Plants set "
            +"ownerID = ?,"
            +"common_name = ?,"
            +"genus = ?,"
            +"species = ?,"
            +"size = ?,"
            +"type = ?,"
            +"aquired_from = ?,"
            +"soil_type = ?,"
            +"water_quantity = ?,"
            +"sun_quantity = ?,"
            +"date_ofAquisition = ?,"
            +"date_ofFirstWatering = ?,"
            +"notes = ?,"
            +"water_frequency = ?,"
            +"cost = ?,"
            +"InOut = ?,"
            +"forSale = ?"
            +"where plantID = ?";

         PreparedStatement preparedStatement = connection.prepareStatement(qS);
         preparedStatement.setInt(1, plant.getOwnerID());
         preparedStatement.setString(2, plant.getCommon_Name());
         preparedStatement.setString(3, plant.getGenus());
         preparedStatement.setString(4, plant.getSpecies_Name());
         preparedStatement.setString(5, plant.getSize());
         preparedStatement.setString(6, plant.getType());
         preparedStatement.setString(7, plant.getAquired_from());
         preparedStatement.setString(8, plant.getSoil_type());
         preparedStatement.setDouble(9, plant.getWater_quantity());
         preparedStatement.setDouble(10, plant.getSun_quantity());
         preparedStatement.setLong(11, plant.getLongDate_ofAquisition());
         preparedStatement.setLong(12, plant.getLastWateredLong());
         preparedStatement.setString(13, plant.getPlant_notes());
         preparedStatement.setDouble(14, plant.getWater_frequency());
         preparedStatement.setString(15, plant.getCost());
         preparedStatement.setString(16, plant.getInorOut());
         preparedStatement.setInt(17, plant.getIntForSale());

         preparedStatement.setInt(18, plant.getPlantID());

         preparedStatement.execute();

         System.out.println("Rows Updated: "+ preparedStatement.getUpdateCount());

      }catch (SQLException e) {
         System.out.println("Databsse not found");
         System.err.println(e.getMessage());
      }
   }

}
