package com.example.ecommerce;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {
   userInterface UserInterface = new userInterface();
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(UserInterface.createContent());
        stage.setTitle("Ecommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}