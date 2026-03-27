package edu.ntnu.idi.bidata.cardgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckOfCards {
  private final List<PlayingCard> cards;
  private final char[] suit = {'S', 'H', 'D', 'C'};
  private final Random random;

  public DeckOfCards() {
    cards = new ArrayList<>();
    random = new Random();

    for (char s : suit) {
      for (int face = 1; face <= 13; face++) {
        cards.add(new PlayingCard(s, face));
      }
    }
  }

  public int size() {
    return cards.size();
  }

  public List<PlayingCard> getCards() {
    return List.copyOf(cards);
  }

  public HandOfCards dealHand(int n) {
    if (n < 1 || n > 52) {
      throw new IllegalArgumentException("n must be between 1 and 52");
    }

    if (n > cards.size()) {
      throw new IllegalStateException("Not enough cards in deck");
    }

    HandOfCards hand = new HandOfCards();
    
    for (int i = 0; i < n; i++) {
      int randomIndex = random.nextInt(cards.size());
      PlayingCard card = cards.remove(randomIndex);
      hand.addCard(card);
    }

    return hand;
  }
}
