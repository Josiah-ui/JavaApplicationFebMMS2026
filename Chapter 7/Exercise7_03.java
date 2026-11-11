// Exercise 7.3 (Project: Card Shuffling and Dealing) - the dealer draws replacement cards
import java.security.SecureRandom;
import java.util.Scanner;

public class Exercise7_03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DeckOfCards myDeckOfCards = new DeckOfCards();
        char again;

        do {
            myDeckOfCards.shuffle();
            Card[] player = myDeckOfCards.dealHand();
            Card[] dealer = myDeckOfCards.dealHand();   // dealt face down: not displayed
            Card[] dealerOriginal = dealer.clone();

            System.out.println("\nYour hand:");
            printHand(player);
            System.out.printf("  -> %s%n", DeckOfCards.handName(player));

            System.out.println("\nThe dealer's five cards are dealt face down.");

            // the dealer evaluates the hand and draws one, two or three replacement cards
            int[] discards = chooseDiscards(dealer, false);
            replaceCards(myDeckOfCards, dealer, discards);
            if (discards.length == 0)
                System.out.println("The dealer is happy with the hand and draws no cards.");
            else
                System.out.printf("The dealer draws %d card%s.%n", discards.length, discards.length == 1 ? "" : "s");

            // reveal and re-evaluate the dealer's hand
            System.out.println("\nThe dealer's original hand was: " + DeckOfCards.handName(dealerOriginal));
            printHand(dealerOriginal);
            System.out.println("\nThe dealer's hand after drawing: " + DeckOfCards.handName(dealer));
            printHand(dealer);

            System.out.println("\n" + resultText(player, dealer, "You", "The dealer"));

            System.out.print("\nPlay again (y or n)? ");
            again = input.next().toLowerCase().charAt(0);
        } while (again == 'y');
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

    // ======================================================================
    // The dealer's drawing strategy
    // ======================================================================

    // Decides which cards of a hand to replace (never more than three); returns their indexes.
    //   four of a kind  -> replace the odd card           two pairs  -> replace the odd card
    //   three of a kind -> replace the two other cards    one pair   -> replace the three other cards
    //   straight, flush, full house, straight flush -> stand pat (no cards replaced)
    //   nothing         -> keep the two highest cards and replace three
    // The refined strategy also draws one card to a four-card flush or a four-card open-ended straight.
    static int[] chooseDiscards(Card[] hand, boolean refined) {
        int rank = DeckOfCards.rank(hand);
        int[] counts = DeckOfCards.faceCounts(hand);
        boolean[] discard = new boolean[hand.length];

        if (rank == 7 || rank == 3 || rank == 2 || rank == 1) {
            int keepCount = (rank == 7) ? 4 : (rank == 3) ? 3 : 2;
            for (int i = 0; i < hand.length; i++)
                discard[i] = counts[DeckOfCards.faceValue(hand[i])] < keepCount;
        } else if (rank == 0) {
            if (!(refined && markDrawingCard(hand, discard)))
                markAllButTwoHighest(hand, discard);
        }
        // ranks 4, 5, 6 and 8: stand pat

        int number = 0;
        for (boolean d : discard)
            if (d)
                number++;
        int[] indexes = new int[number];
        int n = 0;
        for (int i = 0; i < discard.length; i++)
            if (discard[i])
                indexes[n++] = i;
        return indexes;
    }

    // four to a flush or four to an open-ended straight: mark the one card to throw away
    static boolean markDrawingCard(Card[] hand, boolean[] discard) {
        for (String suit : DeckOfCards.SUITS) {
            int inSuit = 0;
            for (Card card : hand)
                if (card.getSuit().equals(suit))
                    inSuit++;
            if (inSuit == 4) {
                for (int i = 0; i < hand.length; i++)
                    discard[i] = !hand[i].getSuit().equals(suit);
                return true;
            }
        }

        boolean[] present = new boolean[15];
        for (Card card : hand)
            present[DeckOfCards.faceValue(card)] = true;
        for (int low = 2; low <= 10; low++) {
            if (present[low] && present[low + 1] && present[low + 2] && present[low + 3]) {
                for (int i = 0; i < hand.length; i++) {
                    int value = DeckOfCards.faceValue(hand[i]);
                    discard[i] = value < low || value > low + 3;
                }
                return true;
            }
        }
        return false;
    }

    static void markAllButTwoHighest(Card[] hand, boolean[] discard) {
        Integer[] order = {0, 1, 2, 3, 4};
        java.util.Arrays.sort(order, (x, y) -> DeckOfCards.pokerRank(hand[y]) - DeckOfCards.pokerRank(hand[x]));
        for (int k = 2; k < order.length; k++)
            discard[order[k]] = true;
    }

    // replaces the chosen cards with new cards from the deck
    static void replaceCards(DeckOfCards deck, Card[] hand, int[] indexes) {
        for (int index : indexes)
            hand[index] = deck.dealCard();
    }

    // text describing who won
    static String resultText(Card[] first, Card[] second, String firstName, String secondName) {
        int comparison = DeckOfCards.compareHands(first, second);
        int rank1 = DeckOfCards.rank(first);
        int rank2 = DeckOfCards.rank(second);

        if (comparison == 0)
            return "It's a tie.";
        String winner = comparison > 0 ? firstName : secondName;
        if (rank1 != rank2)
            return winner + " wins: " + DeckOfCards.handName(comparison > 0 ? first : second)
                    + " beats " + DeckOfCards.handName(comparison > 0 ? second : first) + ".";
        return "Both hands contain " + DeckOfCards.handName(first) + ", but " + winner.substring(0, 1).toLowerCase() + winner.substring(1) + " has the higher cards and wins.";
    }
}

/*
 * SAMPLE RUN (actual output produced by running this program;
 * programs that use random numbers give different results each time)
 * ----------------------------------------------------------------
 *
 * Your hand:
 *   1) Deuce of Diamonds
 *   2) Six of Spades
 *   3) Queen of Diamonds
 *   4) Nine of Diamonds
 *   5) Ten of Clubs
 *   -> high card
 *
 * The dealer's five cards are dealt face down.
 * The dealer draws 3 cards.
 *
 * The dealer's original hand was: a pair
 *   1) Jack of Diamonds
 *   2) Four of Spades
 *   3) Three of Hearts
 *   4) Ace of Clubs
 *   5) Four of Clubs
 *
 * The dealer's hand after drawing: a pair
 *   1) Deuce of Hearts
 *   2) Four of Spades
 *   3) Three of Clubs
 *   4) Eight of Spades
 *   5) Four of Clubs
 *
 * The dealer wins: a pair beats high card.
 *
 * Play again (y or n)? y
 *
 * Your hand:
 *   1) Seven of Hearts
 *   2) Three of Diamonds
 *   3) Eight of Spades
 *   4) Four of Spades
 *   5) Ten of Hearts
 *   -> high card
 *
 * The dealer's five cards are dealt face down.
 * The dealer draws 3 cards.
 *
 * The dealer's original hand was: high card
 *   1) King of Spades
 *   2) Ace of Diamonds
 *   3) Deuce of Hearts
 *   4) Jack of Clubs
 *   5) Queen of Clubs
 *
 * The dealer's hand after drawing: a pair
 *   1) King of Spades
 *   2) Ace of Diamonds
 *   3) Nine of Clubs
 *   4) Queen of Diamonds
 *   5) Ace of Clubs
 *
 * The dealer wins: a pair beats high card.
 *
 * Play again (y or n)? n
 */
