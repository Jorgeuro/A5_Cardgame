package edu.ntnu.idi.bidata.cardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HandOfCardsTest {
  private HandOfCards hand;

  @BeforeEach
  void setUp() {
    hand = new HandOfCards();
  }

  @Test
  void testEmptyHandInitialization() {
    assertEquals(0, hand.getSize());
    assertTrue(hand.getCards().isEmpty());
  }

  @Test
  void testAddCard() {
    PlayingCard card = new PlayingCard('H', 5);
    hand.addCard(card);
    assertEquals(1, hand.getSize());
    assertTrue(hand.getCards().contains(card));
  }

  @Test
  void testAddMultipleCards() {
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('S', 10));
    hand.addCard(new PlayingCard('D', 1));
    assertEquals(3, hand.getSize());
  }

  @Test
  void testGetSumOfFaces() {
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('S', 10));
    hand.addCard(new PlayingCard('D', 3));
    assertEquals(18, hand.getSumOfFaces());
  }

  @Test
  void testGetSumOfFacesEmptyHand() {
    assertEquals(0, hand.getSumOfFaces());
  }

  @Test
  void testGetSumOfFacesWithAces() {
    hand.addCard(new PlayingCard('H', 1));
    hand.addCard(new PlayingCard('S', 1));
    hand.addCard(new PlayingCard('D', 5));
    assertEquals(7, hand.getSumOfFaces());
  }

  @Test
  void testGetHearts() {
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('S', 10));
    hand.addCard(new PlayingCard('H', 9));
    hand.addCard(new PlayingCard('D', 3));
    hand.addCard(new PlayingCard('H', 1));

    List<PlayingCard> hearts = hand.getHearts();
    assertEquals(3, hearts.size());
    assertTrue(hearts.stream().allMatch(card -> card.getSuit() == 'H'));
  }

  @Test
  void testGetHeartsNoHearts() {
    hand.addCard(new PlayingCard('S', 5));
    hand.addCard(new PlayingCard('D', 10));
    hand.addCard(new PlayingCard('C', 3));

    List<PlayingCard> hearts = hand.getHearts();
    assertTrue(hearts.isEmpty());
  }

  @Test
  void testGetHeartsAsString() {
    hand.addCard(new PlayingCard('H', 12));
    hand.addCard(new PlayingCard('S', 10));
    hand.addCard(new PlayingCard('H', 9));
    hand.addCard(new PlayingCard('H', 1));

    String heartsString = hand.getHeartsAsString();
    assertTrue(heartsString.contains("H12"));
    assertTrue(heartsString.contains("H9"));
    assertTrue(heartsString.contains("H1"));
    assertFalse(heartsString.contains("S10"));
  }

  @Test
  void testGetHeartsAsStringNoHearts() {
    hand.addCard(new PlayingCard('S', 5));
    hand.addCard(new PlayingCard('D', 10));
    assertEquals("No Hearts", hand.getHeartsAsString());
  }

  @Test
  void testHasQueenOfSpadesTrue() {
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('S', 12));
    hand.addCard(new PlayingCard('D', 3));
    assertTrue(hand.hasQueenOfSpades());
  }

  @Test
  void testHasQueenOfSpadesFalse() {
    hand.addCard(new PlayingCard('H', 12));
    hand.addCard(new PlayingCard('S', 10));
    hand.addCard(new PlayingCard('D', 3));
    assertFalse(hand.hasQueenOfSpades());
  }

  @Test
  void testHasQueenOfSpadesEmptyHand() {
    assertFalse(hand.hasQueenOfSpades());
  }

  @Test
  void testIsFlushTrue() {
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('H', 10));
    hand.addCard(new PlayingCard('H', 3));
    hand.addCard(new PlayingCard('H', 1));
    hand.addCard(new PlayingCard('H', 13));
    assertTrue(hand.isFlush());
  }

  @Test
  void testIsFlushFalse() {
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('H', 10));
    hand.addCard(new PlayingCard('H', 3));
    hand.addCard(new PlayingCard('S', 1));
    hand.addCard(new PlayingCard('H', 13));
    assertFalse(hand.isFlush());
  }

  @Test
  void testIsFlushNotEnoughCards() {
    hand.addCard(new PlayingCard('H', 5));
    hand.addCard(new PlayingCard('H', 10));
    hand.addCard(new PlayingCard('H', 3));
    hand.addCard(new PlayingCard('H', 1));
    assertFalse(hand.isFlush());
  }

  @Test
  void testIsFlushMoreThanFiveCards() {
    hand.addCard(new PlayingCard('S', 5));
    hand.addCard(new PlayingCard('S', 10));
    hand.addCard(new PlayingCard('S', 3));
    hand.addCard(new PlayingCard('S', 1));
    hand.addCard(new PlayingCard('S', 13));
    hand.addCard(new PlayingCard('S', 7));
    hand.addCard(new PlayingCard('H', 2));
    assertTrue(hand.isFlush());
  }

  @Test
  void testGetCardsAsString() {
    hand.addCard(new PlayingCard('H', 4));
    hand.addCard(new PlayingCard('H', 12));
    hand.addCard(new PlayingCard('C', 3));
    hand.addCard(new PlayingCard('D', 11));
    hand.addCard(new PlayingCard('S', 1));

    String cardsString = hand.getCardsAsString();
    assertTrue(cardsString.contains("H4"));
    assertTrue(cardsString.contains("H12"));
    assertTrue(cardsString.contains("C3"));
    assertTrue(cardsString.contains("D11"));
    assertTrue(cardsString.contains("S1"));
  }

  @Test
  void testGetCardsReturnsCopy() {
    hand.addCard(new PlayingCard('H', 5));
    List<PlayingCard> cards = hand.getCards();
    assertThrows(UnsupportedOperationException.class, () -> cards.clear());
  }
}
