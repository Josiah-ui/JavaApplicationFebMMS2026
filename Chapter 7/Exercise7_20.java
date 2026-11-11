// Exercise 7.20 (Project: Card Shuffling and Dealing) - Card and DeckOfCards using the Face and Suit enum types
// Needs Face.java and Suit.java (Face and Suit are public types in their own files, as the exercise requires).
import java.security.SecureRandom;

public class Exercise7_20 {
    public static void main(String[] args) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle();

        // print all 52 cards in the order in which they are dealt, four per row
        for (int i = 1; i <= 52; i++) {
            System.out.printf("%-19s", myDeckOfCards.dealCard());
            if (i % 4 == 0)
                System.out.println();
        }
    }

    // Card: each Card has a Face and a Suit instance variable, initialized by the constructor
    static class Card {
        private final Face face;
        private final Suit suit;

        Card(Face cardFace, Suit cardSuit) {
            face = cardFace;
            suit = cardSuit;
        }

        public Face getFace() {
            return face;
        }

        public Suit getSuit() {
            return suit;
        }

        // the name of an enum constant is displayed when the constant is converted to a String
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
            Face[] faces = Face.values();    // array of Faces: Ace, Deuce, ..., King
            Suit[] suits = Suit.values();    // array of Suits: Hearts, Diamonds, Clubs, Spades

            for (int count = 0; count < deck.length; count++)
                deck[count] = new Card(faces[count % 13], suits[count / 13]);
        }

        void shuffle() {
            currentCard = 0;
            for (int first = 0; first < deck.length; first++) {
                int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
                Card temp = deck[first];
                deck[first] = deck[second];
                deck[second] = temp;
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
 * Six of Diamonds    Four of Spades     Four of Diamonds   Ten of Clubs       
 * Ten of Spades      Eight of Hearts    Deuce of Diamonds  Queen of Diamonds  
 * Eight of Spades    Three of Hearts    Seven of Diamonds  Six of Spades      
 * Three of Spades    Five of Hearts     Three of Diamonds  Jack of Hearts     
 * Seven of Clubs     Nine of Diamonds   Queen of Hearts    Eight of Diamonds  
 * Five of Diamonds   Ace of Clubs       Nine of Hearts     King of Spades     
 * Four of Hearts     Jack of Diamonds   Queen of Clubs     Nine of Spades     
 * Queen of Spades    Six of Hearts      Deuce of Hearts    Nine of Clubs      
 * Jack of Spades     Seven of Spades    Ten of Hearts      Four of Clubs      
 * Ten of Diamonds    Ace of Hearts      Jack of Clubs      King of Hearts     
 * Seven of Hearts    Ace of Spades      Ace of Diamonds    King of Diamonds   
 * Deuce of Clubs     Five of Spades     Five of Clubs      Deuce of Spades    
 * Eight of Clubs     King of Clubs      Three of Clubs     Six of Clubs       
 */
