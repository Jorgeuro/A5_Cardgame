package edu.ntnu.idi.bidata.cardgame.model;

public class PlayingCard {

  private final char suit;
  private final int face;

  public PlayingCard(char suit, int face) {
    this.suit = suit;
    this.face = face;
  }

  public char getSuit() {
    return suit;
  }

  public int getFace() {
    return face;
  }

  public String getAsString() {
    return "" + suit + face;
  }
}