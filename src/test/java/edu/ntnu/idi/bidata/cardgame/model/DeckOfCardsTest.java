package edu.ntnu.idi.bidata.cardgame.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckOfCardsTest {
  private DeckOfCards deck;

  @BeforeEach
  void setUp() {
    deck = new DeckOfCards();
  }

  @Test
  void testDeckInitializationSize() {
    assertEquals(52, deck.size());
  }

  @Test
  void testDeckContainsAllCards() {
    var cards = deck.getCards();
    assertEquals(52, cards.size());

    char[] suits = {'S', 'H', 'D', 'C'};
    for (char suit : suits) {
      for (int face = 1; face <= 13; face++) {
        char finalSuit = suit;
        int finalFace = face;
        boolean found = cards.stream()
            .anyMatch(card -> card.getSuit() == finalSuit && card.getFace() == finalFace);
        assertTrue(found, "Deck should contain " + suit + face);
      }
    }
  }

  @Test
  void testDealHandValidSize() {
    HandOfCards hand = deck.dealHand(5);
    assertEquals(5, hand.getSize());
    assertEquals(47, deck.size());
  }

  @Test
  void testDealHandReducesDeckSize() {
    assertEquals(52, deck.size());
    deck.dealHand(10);
    assertEquals(42, deck.size());
    deck.dealHand(5);
    assertEquals(37, deck.size());
  }

  @Test
  void testDealHandInvalidSizeTooSmall() {
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(0));
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(-5));
  }

  @Test
  void testDealHandInvalidSizeTooLarge() {
    assertThrows(IllegalArgumentException.class, () -> deck.dealHand(53));
  }

  @Test
  void testDealHandNotEnoughCards() {
    deck.dealHand(50);
    assertEquals(2, deck.size());
    assertThrows(IllegalStateException.class, () -> deck.dealHand(5));
  }

  @Test
  void testDealHandRandomness() {
    DeckOfCards deck1 = new DeckOfCards();
    DeckOfCards deck2 = new DeckOfCards();

    HandOfCards hand1 = deck1.dealHand(5);
    HandOfCards hand2 = deck2.dealHand(5);

    boolean different = false;
    var cards1 = hand1.getCards();
    var cards2 = hand2.getCards();

    for (int i = 0; i < 5; i++) {
      if (cards1.get(i).getSuit() != cards2.get(i).getSuit() ||
          cards1.get(i).getFace() != cards2.get(i).getFace()) {
        different = true;
        break;
      }
    }

    assertTrue(different, "Two random hands should likely be different");
  }

  @Test
  void testGetCardsReturnsCopy() {
    var cards = deck.getCards();
    assertEquals(52, cards.size());
    assertThrows(UnsupportedOperationException.class, () -> cards.clear());
  }
}
