// Exercise 7.1 (Card Shuffling and Dealing) - a five-card poker hand and the hand-testing methods
import java.security.SecureRandom;

public class Exercise7_01 {
    public static void main(String[] args) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle();
        Card[] hand = myDeckOfCards.dealHand();

        System.out.println("A five-card poker hand dealt from a shuffled deck:");
        printHand(hand);
        report(hand);

        // self-test: hands made by hand so that every method can be seen working
        System.out.println("\n---- Self-test with chosen hands ----");
        test("Pair", new Card[] {c("King", "Hearts"), c("King", "Spades"), c("Three", "Clubs"), c("Seven", "Diamonds"), c("Nine", "Spades")});
        test("Two pairs", new Card[] {c("Four", "Hearts"), c("Four", "Spades"), c("Nine", "Clubs"), c("Nine", "Diamonds"), c("Queen", "Spades")});
        test("Three of a kind", new Card[] {c("Jack", "Hearts"), c("Jack", "Spades"), c("Jack", "Clubs"), c("Deuce", "Diamonds"), c("Eight", "Spades")});
        test("Four of a kind", new Card[] {c("Ace", "Hearts"), c("Ace", "Spades"), c("Ace", "Clubs"), c("Ace", "Diamonds"), c("Five", "Spades")});
        test("Flush", new Card[] {c("Deuce", "Hearts"), c("Six", "Hearts"), c("Nine", "Hearts"), c("Jack", "Hearts"), c("King", "Hearts")});
        test("Straight", new Card[] {c("Five", "Hearts"), c("Six", "Spades"), c("Seven", "Clubs"), c("Eight", "Diamonds"), c("Nine", "Spades")});
        test("Straight (ace low)", new Card[] {c("Ace", "Hearts"), c("Deuce", "Spades"), c("Three", "Clubs"), c("Four", "Diamonds"), c("Five", "Spades")});
        test("Full house", new Card[] {c("Three", "Hearts"), c("Three", "Spades"), c("Three", "Clubs"), c("Queen", "Diamonds"), c("Queen", "Spades")});
        test("Royal flush", new Card[] {c("Ten", "Clubs"), c("Jack", "Clubs"), c("Queen", "Clubs"), c("King", "Clubs"), c("Ace", "Clubs")});
    }

    static void test(String title, Card[] hand) {
        System.out.printf("%n%s:%n", title);
        printHand(hand);
        report(hand);
    }

    // shows the result of each of the seven methods
    static void report(Card[] hand) {
        System.out.printf("  a) pair:            %s%n", yesNo(DeckOfCards.containsPair(hand)));
        System.out.printf("  b) two pairs:       %s%n", yesNo(DeckOfCards.containsTwoPairs(hand)));
        System.out.printf("  c) three of a kind: %s%n", yesNo(DeckOfCards.containsThreeOfAKind(hand)));
        System.out.printf("  d) four of a kind:  %s%n", yesNo(DeckOfCards.containsFourOfAKind(hand)));
        System.out.printf("  e) flush:           %s%n", yesNo(DeckOfCards.containsFlush(hand)));
        System.out.printf("  f) straight:        %s%n", yesNo(DeckOfCards.containsStraight(hand)));
        System.out.printf("  g) full house:      %s%n", yesNo(DeckOfCards.containsFullHouse(hand)));
        System.out.printf("  Best hand: %s%n", DeckOfCards.handName(hand));
    }

    static String yesNo(boolean value) {
        return value ? "yes" : "no";
    }

    // ======================================================================
    // Card: Fig. 7.11 with the getFace and getSuit methods added (hint)
    // ======================================================================
    static class Card {
        private final String face;   // face of card ("Ace", "Deuce", ...)
        private final String suit;   // suit of card ("Hearts", "Diamonds", ...)

        Card(String cardFace, String cardSuit) {
            face = cardFace;
            suit = cardSuit;
        }

        public String getFace() {
            return face;
        }

        public String getSuit() {
            return suit;
        }

        @Override
        public String toString() {
            return face + " of " + suit;
        }
    }

    // ======================================================================
    // DeckOfCards: Fig. 7.12 with poker-hand methods added
    // ======================================================================
    static class DeckOfCards {
        static final String[] FACES = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven",
                                       "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
        static final String[] HAND_NAMES = {"high card", "a pair", "two pairs", "three of a kind",
                                            "a straight", "a flush", "a full house", "four of a kind",
                                            "a straight flush"};

        private static final SecureRandom randomNumbers = new SecureRandom();
        private static final int NUMBER_OF_CARDS = 52;

        private Card[] deck = new Card[NUMBER_OF_CARDS];
        private int currentCard;   // index of the next Card to be dealt

        DeckOfCards() {
            for (int count = 0; count < deck.length; count++)
                deck[count] = new Card(FACES[count % 13], SUITS[count / 13]);
        }

        // shuffle the deck (the algorithm of Fig. 7.12)
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

        // deals a five-card poker hand
        Card[] dealHand() {
            Card[] hand = new Card[5];
            for (int i = 0; i < hand.length; i++)
                hand[i] = dealCard();
            return hand;
        }

        // ---------------------------------------------------------------
        // hand evaluation
        // ---------------------------------------------------------------

        // Ace = 1, Deuce = 2, ..., Jack = 11, Queen = 12, King = 13
        static int faceValue(Card card) {
            for (int i = 0; i < FACES.length; i++)
                if (FACES[i].equals(card.getFace()))
                    return i + 1;
            return -1;
        }

        // counts[v] = number of cards in the hand whose face value is v (v = 1..13)
        static int[] faceCounts(Card[] hand) {
            int[] counts = new int[14];
            for (Card card : hand)
                counts[faceValue(card)]++;
            return counts;
        }

        // how many different faces occur exactly 'times' times in the hand
        private static int facesOccurring(Card[] hand, int times) {
            int[] counts = faceCounts(hand);
            int number = 0;
            for (int v = 1; v <= 13; v++)
                if (counts[v] == times)
                    number++;
            return number;
        }

        // a) at least two cards of the same face
        static boolean containsPair(Card[] hand) {
            for (int count : faceCounts(hand))
                if (count >= 2)
                    return true;
            return false;
        }

        // b) two different faces that each occur exactly twice
        static boolean containsTwoPairs(Card[] hand) {
            return facesOccurring(hand, 2) == 2;
        }

        // c) at least three cards of the same face
        static boolean containsThreeOfAKind(Card[] hand) {
            for (int count : faceCounts(hand))
                if (count >= 3)
                    return true;
            return false;
        }

        // d) four cards of the same face
        static boolean containsFourOfAKind(Card[] hand) {
            for (int count : faceCounts(hand))
                if (count == 4)
                    return true;
            return false;
        }

        // e) all five cards of the same suit
        static boolean containsFlush(Card[] hand) {
            for (int i = 1; i < hand.length; i++)
                if (!hand[i].getSuit().equals(hand[0].getSuit()))
                    return false;
            return true;
        }

        // f) five consecutive face values; the Ace may be low (A-2-3-4-5) or high (10-J-Q-K-A)
        static boolean containsStraight(Card[] hand) {
            int[] counts = faceCounts(hand);
            if (facesOccurring(hand, 1) != 5)
                return false;                         // five different faces are needed

            int lowest = 1;
            while (counts[lowest] == 0)
                lowest++;
            int highest = 13;
            while (counts[highest] == 0)
                highest--;

            if (highest - lowest == 4)
                return true;                          // e.g. 5-6-7-8-9 or A-2-3-4-5

            // ace high: 10-J-Q-K-A
            return counts[1] == 1 && counts[10] == 1 && counts[11] == 1 && counts[12] == 1 && counts[13] == 1;
        }

        // g) three cards of one face and two cards of another face
        static boolean containsFullHouse(Card[] hand) {
            return facesOccurring(hand, 3) == 1 && facesOccurring(hand, 2) == 1;
        }

        // rank of the best hand: 0 = high card ... 8 = straight flush (index into HAND_NAMES)
        static int rank(Card[] hand) {
            boolean flush = containsFlush(hand);
            boolean straight = containsStraight(hand);

            if (flush && straight)
                return 8;
            if (containsFourOfAKind(hand))
                return 7;
            if (containsFullHouse(hand))
                return 6;
            if (flush)
                return 5;
            if (straight)
                return 4;
            if (containsThreeOfAKind(hand))
                return 3;
            if (containsTwoPairs(hand))
                return 2;
            if (containsPair(hand))
                return 1;
            return 0;
        }

        static String handName(Card[] hand) {
            int rank = rank(hand);
            if (rank == 8 && faceCounts(hand)[1] == 1 && faceCounts(hand)[13] == 1)
                return "a royal flush";
            return HAND_NAMES[rank];
        }

        // Ace counts as 14 when ordering cards by strength
        static int pokerRank(Card card) {
            int value = faceValue(card);
            return value == 1 ? 14 : value;
        }

        // faces ordered by importance (groups of equal faces first, then higher faces); used to break ties
        static int[] tieBreakers(Card[] hand) {
            int[] counts = new int[15];
            for (Card card : hand)
                counts[pokerRank(card)]++;

            // the wheel (A-2-3-4-5) is a five-high straight
            if (containsStraight(hand) && counts[14] == 1 && counts[2] == 1)
                return new int[] {5, 4, 3, 2, 1};

            int[] order = new int[5];
            int n = 0;
            for (int group = 4; group >= 1; group--)
                for (int r = 14; r >= 2; r--)
                    if (counts[r] == group)
                        order[n++] = r;
            return java.util.Arrays.copyOf(order, n);
        }

        // positive if hand a is better than hand b, negative if worse, 0 if they tie
        static int compareHands(Card[] a, Card[] b) {
            int difference = rank(a) - rank(b);
            if (difference != 0)
                return difference;

            int[] x = tieBreakers(a);
            int[] y = tieBreakers(b);
            for (int i = 0; i < x.length && i < y.length; i++)
                if (x[i] != y[i])
                    return x[i] - y[i];
            return 0;
        }
    }

    static Card c(String face, String suit) {
        return new Card(face, suit);
    }

    static void printHand(Card[] hand) {
        for (int i = 0; i < hand.length; i++)
            System.out.printf("  %d) %s%n", i + 1, hand[i]);
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 * A five-card poker hand dealt from a shuffled deck:
 *   1) Four of Hearts
 *   2) Ten of Clubs
 *   3) Deuce of Spades
 *   4) Seven of Hearts
 *   5) Ten of Diamonds
 *   a) pair:            yes
 *   b) two pairs:       no
 *   c) three of a kind: no
 *   d) four of a kind:  no
 *   e) flush:           no
 *   f) straight:        no
 *   g) full house:      no
 *   Best hand: a pair
 *
 * ---- Self-test with chosen hands ----
 *
 * Pair:
 *   1) King of Hearts
 *   2) King of Spades
 *   3) Three of Clubs
 *   4) Seven of Diamonds
 *   5) Nine of Spades
 *   a) pair:            yes
 *   b) two pairs:       no
 *   c) three of a kind: no
 *   d) four of a kind:  no
 *   e) flush:           no
 *   f) straight:        no
 *   g) full house:      no
 *   Best hand: a pair
 *
 * Two pairs:
 *   1) Four of Hearts
 *   2) Four of Spades
 *   3) Nine of Clubs
 *   4) Nine of Diamonds
 *   5) Queen of Spades
 *   a) pair:            yes
 *   b) two pairs:       yes
 *   c) three of a kind: no
 *   d) four of a kind:  no
 *   e) flush:           no
 *   f) straight:        no
 *   g) full house:      no
 *   Best hand: two pairs
 *
 * Three of a kind:
 *   1) Jack of Hearts
 *   2) Jack of Spades
 *   3) Jack of Clubs
 *   4) Deuce of Diamonds
 *   5) Eight of Spades
 *   a) pair:            yes
 *   b) two pairs:       no
 *   c) three of a kind: yes
 *   d) four of a kind:  no
 *   e) flush:           no
 *   f) straight:        no
 *   g) full house:      no
 *   Best hand: three of a kind
 *
 * Four of a kind:
 *   1) Ace of Hearts
 *   2) Ace of Spades
 *   3) Ace of Clubs
 *   4) Ace of Diamonds
 *   5) Five of Spades
 *   a) pair:            yes
 *   b) two pairs:       no
 *   c) three of a kind: yes
 *   d) four of a kind:  yes
 *   e) flush:           no
 *   f) straight:        no
 *   g) full house:      no
 *   Best hand: four of a kind
 *
 * Flush:
 *   1) Deuce of Hearts
 *   2) Six of Hearts
 *   3) Nine of Hearts
 *   4) Jack of Hearts
 *   5) King of Hearts
 *   a) pair:            no
 *   b) two pairs:       no
 *   c) three of a kind: no
 *   d) four of a kind:  no
 *   e) flush:           yes
 *   f) straight:        no
 *   g) full house:      no
 *   Best hand: a flush
 *
 * Straight:
 *   1) Five of Hearts
 *   2) Six of Spades
 *   3) Seven of Clubs
 *   4) Eight of Diamonds
 *   5) Nine of Spades
 *   a) pair:            no
 *   b) two pairs:       no
 *   c) three of a kind: no
 *   d) four of a kind:  no
 *   e) flush:           no
 *   f) straight:        yes
 *   g) full house:      no
 *   Best hand: a straight
 *
 * Straight (ace low):
 *   1) Ace of Hearts
 *   2) Deuce of Spades
 *   3) Three of Clubs
 *   4) Four of Diamonds
 *   5) Five of Spades
 *   a) pair:            no
 *   b) two pairs:       no
 *   c) three of a kind: no
 *   d) four of a kind:  no
 *   e) flush:           no
 *   f) straight:        yes
 *   g) full house:      no
 *   Best hand: a straight
 *
 * Full house:
 *   1) Three of Hearts
 *   2) Three of Spades
 *   3) Three of Clubs
 *   4) Queen of Diamonds
 *   5) Queen of Spades
 *   a) pair:            yes
 *   b) two pairs:       no
 *   c) three of a kind: yes
 *   d) four of a kind:  no
 *   e) flush:           no
 *   f) straight:        no
 *   g) full house:      yes
 *   Best hand: a full house
 *
 * Royal flush:
 *   1) Ten of Clubs
 *   2) Jack of Clubs
 *   3) Queen of Clubs
 *   4) King of Clubs
 *   5) Ace of Clubs
 *   a) pair:            no
 *   b) two pairs:       no
 *   c) three of a kind: no
 *   d) four of a kind:  no
 *   e) flush:           yes
 *   f) straight:        yes
 *   g) full house:      no
 *   Best hand: a royal flush
 */
