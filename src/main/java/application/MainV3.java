/**********************************************************************
 * Programmer: Christina Mullen
 * 0861078
 * Advanced Java Programming
 * Final Project: Flora
 * Copyright 12/04/2022
 *************************************************************************/
package application;

import java.util.Locale;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainV3 extends Application {
   
   public MainV3() {
      // no-arg constructor
   }
   
   @Override public void start(Stage primaryStage) {
      try {
         primaryStage.setTitle("Final Project by Christina Mullen");
         
         PlantController controller = new PlantController(primaryStage);
         Locale.setDefault(Locale.US);
         

         controller.showA();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public static void main(String[] args) {
      launch(args);
   }
}
