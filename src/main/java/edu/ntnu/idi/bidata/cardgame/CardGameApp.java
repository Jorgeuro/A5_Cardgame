package edu.ntnu.idi.bidata.cardgame;

import edu.ntnu.idi.bidata.cardgame.model.DeckOfCards;
import edu.ntnu.idi.bidata.cardgame.model.HandOfCards;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardGameApp extends Application {
  private DeckOfCards deck;
  private HandOfCards currentHand;
  
  private Label cardsLabel;
  private Label sumLabel;
  private Label heartsLabel;
  private Label queenLabel;
  private Label flushLabel;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    deck = new DeckOfCards();
    
    VBox root = new VBox(10);
    root.setPadding(new Insets(10));

    Button dealButton = new Button("Deal Hand");
    dealButton.setOnAction(e -> dealNewHand());

    Button checkButton = new Button("Check Hand");
    checkButton.setOnAction(e -> checkHand());

    cardsLabel = new Label("Cards: ");
    sumLabel = new Label("Sum: ");
    heartsLabel = new Label("Hearts: ");
    queenLabel = new Label("Queen of Spades: ");
    flushLabel = new Label("Flush: ");

    root.getChildren().addAll(dealButton, checkButton, cardsLabel, sumLabel, heartsLabel, queenLabel, flushLabel);

    Scene scene = new Scene(root, 400, 250);
    stage.setTitle("Card Game");
    stage.setScene(scene);
    stage.show();
  }

  private void dealNewHand() {
    if (deck.size() < 5) {
      deck = new DeckOfCards();
    }
    
    currentHand = deck.dealHand(5);
    cardsLabel.setText("Cards: " + currentHand.getCardsAsString());
    
    sumLabel.setText("Sum: ");
    heartsLabel.setText("Hearts: ");
    queenLabel.setText("Queen of Spades: ");
    flushLabel.setText("Flush: ");
  }

  private void checkHand() {
    if (currentHand == null) {
      sumLabel.setText("Sum: Deal hand first");
      return;
    }

    sumLabel.setText("Sum: " + currentHand.getSumOfFaces());
    heartsLabel.setText("Hearts: " + currentHand.getHeartsAsString());
    queenLabel.setText("Queen of Spades: " + (currentHand.hasQueenOfSpades() ? "YES" : "NO"));
    flushLabel.setText("Flush: " + (currentHand.isFlush() ? "YES" : "NO"));
  }
}