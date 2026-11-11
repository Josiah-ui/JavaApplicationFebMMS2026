// Exercise 7.21 (Fisher-Yates Shuffling Algorithm)
//
// Fisher-Yates: walk the array from the last position down to position 1. At each position i choose a random
// index j with 0 <= j <= i and swap the elements at i and j. Every one of the n! orderings is then equally likely.
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Exercise7_21 {
    public static void main(String[] args) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle();   // Fisher-Yates

        for (int i = 1; i <= 52; i++) {
            System.out.printf("%-19s", myDeckOfCards.dealCard());
            if (i % 4 == 0)
                System.out.println();
        }

        // Experiment: shuffle a three-element array 600,000 times with each algorithm and count the orderings.
        // A fair shuffle produces each of the 6 orderings about 100,000 times.
        final int TRIALS = 600_000;
        Random random = new Random();
        Map<String, Integer> original = new TreeMap<>();
        Map<String, Integer> fisherYates = new TreeMap<>();

        for (int t = 0; t < TRIALS; t++) {
            int[] a = {1, 2, 3};
            // the algorithm of Fig. 7.12: swap every element with a randomly chosen element
            for (int first = 0; first < a.length; first++) {
                int second = random.nextInt(a.length);
                int temp = a[first];
                a[first] = a[second];
                a[second] = temp;
            }
            original.merge("" + a[0] + a[1] + a[2], 1, Integer::sum);

            int[] b = {1, 2, 3};
            for (int i = b.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int temp = b[i];
                b[i] = b[j];
                b[j] = temp;
            }
            fisherYates.merge("" + b[0] + b[1] + b[2], 1, Integer::sum);
        }

        System.out.printf("%nOrderings of {1, 2, 3} after %,d shuffles%n", TRIALS);
        System.out.printf("%-10s%-22s%-22s%n", "Ordering", "Fig. 7.12 shuffle", "Fisher-Yates shuffle");
        for (String ordering : original.keySet())
            System.out.printf("%-10s%-22d%-22d%n", ordering, original.get(ordering), fisherYates.get(ordering));
    }

    static class Card {
        private final String face;
        private final String suit;

        Card(String cardFace, String cardSuit) {
            face = cardFace;
            suit = cardSuit;
        }

        @Override
        public String toString() {
            return face + " of " + suit;
        }
    }

    static class DeckOfCards {
        private static final SecureRandom randomNumbers = new SecureRandom();
        private static final int NUMBER_OF_CARDS = 52;

        private Card[] deck = new Card[NUMBER_OF_CARDS];
        private int currentCard;

        DeckOfCards() {
            String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven",
                              "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
            String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

            for (int count = 0; count < deck.length; count++)
                deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }

        // Fisher-Yates shuffle
        void shuffle() {
            currentCard = 0;
            for (int i = deck.length - 1; i > 0; i--) {
                int j = randomNumbers.nextInt(i + 1);   // 0 <= j <= i
                Card temp = deck[i];
                deck[i] = deck[j];
                deck[j] = temp;
            }
        }

        Card dealCard() {
            if (currentCard < deck.length)
                return deck[currentCard++];
            return null;
        }
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * Ten of Spades      Ten of Clubs       Queen of Hearts    Deuce of Clubs     
 * Jack of Clubs      Seven of Hearts    Four of Clubs      Six of Clubs       
 * Ten of Diamonds    Five of Spades     Eight of Hearts    King of Hearts     
 * Nine of Spades     Eight of Clubs     Nine of Diamonds   Seven of Clubs     
 * Ten of Hearts      Nine of Clubs      Ace of Diamonds    Five of Clubs      
 * Eight of Spades    Six of Spades      Four of Hearts     Eight of Diamonds  
 * Deuce of Hearts    Ace of Spades      Six of Hearts      King of Spades     
 * Five of Diamonds   Jack of Diamonds   Four of Spades     Nine of Hearts     
 * Ace of Hearts      Deuce of Spades    Deuce of Diamonds  Six of Diamonds    
 * Ace of Clubs       Three of Diamonds  Seven of Spades    Three of Spades    
 * Three of Hearts    King of Clubs      Four of Diamonds   Queen of Diamonds  
 * Queen of Spades    King of Diamonds   Queen of Clubs     Three of Clubs     
 * Seven of Diamonds  Jack of Hearts     Five of Hearts     Jack of Spades     
 *
 * Orderings of {1, 2, 3} after 600,000 shuffles
 * Ordering  Fig. 7.12 shuffle     Fisher-Yates shuffle  
 * 123       89204                 99621                 
 * 132       110884                100200                
 * 213       111116                100005                
 * 231       111070                100073                
 * 312       89181                 100274                
 * 321       88545                 99827                 
 */
