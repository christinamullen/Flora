/**********************************************************************
 * Programmer: Christina Mullen
 * 0861078
 * Advanced Java Programming
 * Final Project: Flora
 * Copyright 12/04/2022
 *************************************************************************/
package application;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;


public class Plant {

private int ownerID;
   private String cost;
   private String common_Name, species_Name, genus, aquired_from, soil_type;
   private double sun_quantity;
   private LocalDate date_ofAquisition;
   private LocalDate lastWatered;
   private LocalDate lastFertilized;
   boolean for_Sale;
   private String size;
   private String plant_type;
   private String InorOut;
   private double water_quantity;
   private double water_interval;
   private String plant_notes;
  // private int imageID;
   private Image plantImage;

   public Plant() {

   }
   private int plantID;
   public Plant(int plantID, int ownerID, String cost, String common_Name, String species_Name, String genus,
		String aquired_from, String soil_type, double sun_quantity, LocalDate date_ofAquisition, LocalDate lastWatered,
		LocalDate lastFertilized, boolean for_Sale, String size, String plant_type, String inorOut,
		double water_quantity, double water_interval, String plant_notes, String nextWaterDOW, // int nextWaterDOM,
		int imageID, Image plantImage) {
	super();
	this.plantID = plantID;
	this.ownerID = ownerID;
	this.cost = cost;
	this.common_Name = common_Name;
	this.species_Name = species_Name;
	this.genus = genus;
	this.aquired_from = aquired_from;
	this.soil_type = soil_type;
	this.sun_quantity = sun_quantity;
	this.date_ofAquisition = date_ofAquisition;
	this.lastWatered = lastWatered;
	this.lastFertilized = lastFertilized;
	this.for_Sale = for_Sale;
	this.size = size;
	this.plant_type = plant_type;
	InorOut = inorOut;
	this.water_quantity = water_quantity;
	this.water_interval = water_interval;
	this.plant_notes = plant_notes;

	//this.imageID = imageID;
	this.plantImage = plantImage;
}
   public Image getPlantImage() {
      return plantImage;
   }
   public void setPlantImage(Image aPlantImage) {
      plantImage = aPlantImage;
   }

   public int getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
   public void setIntForSale(int value) {
	   for_Sale = (value > 0);
   }
   public int getIntForSale() {
		return for_Sale? 1 : 0;
	}

	public int getPlantID() {
		return plantID;
	}

	public void setPlantID(int plantID) {
		this.plantID = plantID;
	}

	public double getWater_frequency() {
		return water_interval;
	}
	public void setWater_interval(double water_interval) {
		this.water_interval = water_interval;
	}

	public void setIntWater_interval(int water_interval) {
		this.water_interval = water_interval;
	}
	public int getIntWater_interval() {
		return (int)water_interval;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCommon_Name() {
		return common_Name;
	}

	public void setCommon_Name(String common_Name) {
		this.common_Name = common_Name;
	}

	public String getSpecies_Name() {
		return species_Name;
	}

	public void setSpecies_Name(String species_Name) {
		this.species_Name = species_Name;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getAquired_from() {
		return aquired_from;
	}

	public void setAquired_from(String aquired_from) {
		this.aquired_from = aquired_from;
	}

	public String getSoil_type() {
		return soil_type;
	}

	public void setSoil_type(String soil_type) {
		this.soil_type = soil_type;
	}

	public double getSun_quantity() {
		return sun_quantity;
	}
	public int getIntSun_quantity() {

		return (int)sun_quantity;
	}
	public void setSun_quantity(double sun_quantity) {
		this.sun_quantity = sun_quantity;
	}
	public void setIntSun_quantity(int sun_quantity) {
		this.sun_quantity = sun_quantity;
	}

	public LocalDate getDate_ofAquisition() {
		return date_ofAquisition;
	}

	public void setDate_ofAquisition(LocalDate localDate) {
		this.date_ofAquisition = localDate;
	}
	public Long getLongDate_ofAquisition() {
		return Date.from(date_ofAquisition.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant()).getTime();

	}

	public void setLongDate_ofAquisition(Long localDate) {
		Date dateToConvert = new Date(localDate);
		this.date_ofAquisition =
		dateToConvert.toInstant()
		.atZone(ZoneId.systemDefault())
			      .toLocalDate();
	}

   public LocalDate getLastFertilized() {
      return lastFertilized;
   }
   public void setLastFertilized(LocalDate when) {
      this.lastFertilized = when;
   }

   public long getLastFertilizedLong() {
      return Date.from(lastFertilized.atStartOfDay()
                       .atZone(ZoneId.systemDefault())
                       .toInstant()).getTime();
   }
   public void setLastFertilized(long lf) {
      this.lastWatered = new Date(lf).toInstant()
         .atZone(ZoneId.systemDefault())
         .toLocalDate();
   }

   public LocalDate getLastWatered() {
		return lastWatered;
	}

   public Long getLastWateredLong() {
      return Date.from(lastWatered.atStartOfDay()
               .atZone(ZoneId.systemDefault())
               .toInstant()).getTime();
   }

	public void setLastWatered(LocalDate when) {
		this.lastWatered = when;
	}

   public void setLastWateredLong(Long lw) {
      this.lastWatered = new Date(lw).toInstant()
                                     .atZone(ZoneId.systemDefault())
                                     .toLocalDate();
   }

   /**
    * This is a generic function to extract a DOW from a date
    *
    * @param ofDate
    * @return
    */
	public String getDayOfWeek(LocalDate ofDate) {
		//nextWaterDOW = ofDate.getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault());
      // return nextWaterDOW;
		return ofDate.getDayOfWeek().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault());
	}

   public LocalDate getNextWateringDate() {
      return lastWatered.plusDays((long) water_interval);
   }
  /*
	public StringProperty nextWaterDOWProperty() {

		return nextWaterDOW;
	}
*/

	public String getNextWaterDOM() {
	   return Integer.toString(getNextWateringDate().getDayOfMonth());
		//return nextWaterDOM;
	}













	public boolean isFor_Sale() {
		return for_Sale;
	}

	public void setFor_Sale(boolean for_Sale) {
		this.for_Sale = for_Sale;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return plant_type;
	}

	public void setPlant_type(String plant_type) {
		this.plant_type = plant_type;
	}

	public String getInorOut() {
		return InorOut;
	}
	public void setInorOut(String inorOut) {
		InorOut = inorOut;
	}
	public double getWater_quantity() {
		return water_quantity;
	}
	public int getIntWater_quantity() {
		return (int)water_quantity;
	}

	public void setWater_quantity(double wq) {
		this.water_quantity = wq;
	}
	public void setIntWater_quantity(int wq) {
		this.water_quantity = wq;
	}

	public String getPlant_notes() {
		return plant_notes;
	}

	public void setPlant_notes(String plant_notes) {
		this.plant_notes = plant_notes;
	}
	public class IndoorFlora extends Plant {
	      public class Prop extends IndoorFlora {
	         Date cut_date;
	         int change_water_interval;
	         Date last_H20changed;

	         public Prop() {

	         }
	      }

	      public IndoorFlora() {

	      }
	   }
	   public class OutdoorFlora extends Plant {
	      public class Clone extends OutdoorFlora {
	         double cutting_length;
	         Date cut_date;

	         public Clone() {

	         }
	      }
	      public class Pup extends OutdoorFlora {
	         int nNodes;
	         Date cut_date;

	         public Pup() {

	         }
	      }

	      String flowerColor;

	      boolean hasFlowered;

	      public OutdoorFlora() {

	      }
	   }
	   public StringProperty getNextWateringProperty() {
		   
		   StringProperty spreturn = new SimpleStringProperty(this, "Next_Watering");
		   String value = getNextWateringDateDisplay();
		   //FRi 16 MArch
		   spreturn.set(value);
		   return spreturn;
		   }
	   
		public String getNextWateringDateDisplay() {
			int dom = getNextWateringDate().getDayOfMonth();
		   String dow = getDayOfWeek(getNextWateringDate());
		   String mo = getNextWateringDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
		   String value = dow + " " + dom + " "+ mo;
			return value;
		}
		public StringProperty getNextWateringDOWProperty() {
			StringProperty spreturn = new SimpleStringProperty(this, "DOW");
			spreturn.set(getDayOfWeek(getNextWateringDate()));
			return spreturn;
		}
		public StringProperty getSpeciesProperty() {
			StringProperty spreturn = new SimpleStringProperty(this, "Species");
			spreturn.set(getSpecies_Name());
			return spreturn;
		}

		public StringProperty getGenusProperty() {
			StringProperty genus = new SimpleStringProperty(this, "Genus");
			genus.set(getGenus());
			return genus;
		}

}

