package edu.ntnu.idi.bidata.cardgame.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayingCardTest {

  @Test
  void testConstructorAndGetters() {
    PlayingCard card = new PlayingCard('H', 5);
    assertEquals('H', card.getSuit());
    assertEquals(5, card.getFace());
  }

  @Test
  void testGetAsString() {
    PlayingCard card1 = new PlayingCard('S', 1);
    assertEquals("S1", card1.getAsString());

    PlayingCard card2 = new PlayingCard('D', 13);
    assertEquals("D13", card2.getAsString());

    PlayingCard card3 = new PlayingCard('C', 7);
    assertEquals("C7", card3.getAsString());
  }

  @Test
  void testAllSuits() {
    PlayingCard spade = new PlayingCard('S', 10);
    PlayingCard heart = new PlayingCard('H', 10);
    PlayingCard diamond = new PlayingCard('D', 10);
    PlayingCard club = new PlayingCard('C', 10);

    assertEquals('S', spade.getSuit());
    assertEquals('H', heart.getSuit());
    assertEquals('D', diamond.getSuit());
    assertEquals('C', club.getSuit());
  }

  @Test
  void testAllFaces() {
    for (int face = 1; face <= 13; face++) {
      PlayingCard card = new PlayingCard('H', face);
      assertEquals(face, card.getFace());
    }
  }
}
