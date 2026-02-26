package edu.ntnu.idi.bidata.cardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardGameApp extends Application {

  @Override
  public void start(Stage stage) {

    // Lager en tekst
    Label label = new Label("Hello, JavaFX!");

    // VBox legger elementer under hverandre
    VBox root = new VBox();
    root.getChildren().add(label);

    // Scene = innholdet i vinduet
    Scene scene = new Scene(root, 400, 200);

    stage.setTitle("Card Game");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}