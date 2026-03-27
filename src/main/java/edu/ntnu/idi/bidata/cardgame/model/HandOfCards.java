package edu.ntnu.idi.bidata.cardgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HandOfCards {
  private final List<PlayingCard> cards;

  public HandOfCards() {
    this.cards = new ArrayList<>();
  }

  public void addCard(PlayingCard card) {
    cards.add(card);
  }

  public List<PlayingCard> getCards() {
    return List.copyOf(cards);
  }

  public int getSize() {
    return cards.size();
  }

  public int getSumOfFaces() {
    return cards.stream()
        .mapToInt(PlayingCard::getFace)
        .sum();
  }

  public List<PlayingCard> getHearts() {
    return cards.stream()
        .filter(card -> card.getSuit() == 'H')
        .collect(Collectors.toList());
  }

  public String getHeartsAsString() {
    List<PlayingCard> hearts = getHearts();
    if (hearts.isEmpty()) {
      return "No Hearts";
    }
    return hearts.stream()
        .map(PlayingCard::getAsString)
        .collect(Collectors.joining(" "));
  }

  public boolean hasQueenOfSpades() {
    return cards.stream()
        .anyMatch(card -> card.getSuit() == 'S' && card.getFace() == 12);
  }

  public boolean isFlush() {
    if (cards.size() < 5) {
      return false;
    }

    Map<Character, Long> suitCounts = cards.stream()
        .collect(Collectors.groupingBy(PlayingCard::getSuit, Collectors.counting()));

    return suitCounts.values().stream()
        .anyMatch(count -> count >= 5);
  }

  public String getCardsAsString() {
    return cards.stream()
        .map(PlayingCard::getAsString)
        .collect(Collectors.joining(" "));
  }
}
