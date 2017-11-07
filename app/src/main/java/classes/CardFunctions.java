package classes;

import com.joshwing.ridethebus.R;

public class CardFunctions {
    // diamonds = 0-12
    // hearts = 13-25
    // clubs = 26-38
    // spades 39-51

    public static int getCardNumber(int card) {
        return card % 13;
    }
    public static int getCardSuit(int card) {
        return card / 13;
    }
    public static boolean isRed(int card) {
        return isDiamond(card) || isHeart(card);
    }
    public static boolean isBlack(int card) {
        return !isRed(card);
    }
    public static boolean isDiamond(int card) {
        return getCardSuit(card) == 0;
    }
    public static boolean isHeart(int card) {
        return getCardSuit(card) == 1;
    }
    public static boolean isClub(int card) {
        return getCardSuit(card) == 2;
    }
    public static boolean isSpade(int card) {
        return getCardSuit(card) == 3;
    }
    public static boolean isHigher(int card1, int card2) {
        return getCardNumber(card1) < getCardNumber(card2);
    }
    public static boolean isLower(int card1, int card2) {
        return getCardNumber(card1) > getCardNumber(card2);
    }
    public static boolean isBetween(int card1, int card2, int card3) {
        return isHigher(Math.min(card1, card2), card3) && isLower(Math.max(card1, card2), card3);
    }
    public static boolean isOutside(int card1, int card2, int card3) {
        return isLower(Math.min(card1, card2), card3) || isHigher(Math.max(card1, card2), card3);
    }
    public static int getImage(int card) {
        switch(card) {
            case 0:
                return R.drawable.diamonds_ace;
            case 1:
                return R.drawable.diamonds_2;
            case 2:
                return R.drawable.diamonds_3;
            case 3:
                return R.drawable.diamonds_4;
            case 4:
                return R.drawable.diamonds_5;
            case 5:
                return R.drawable.diamonds_6;
            case 6:
                return R.drawable.diamonds_7;
            case 7:
                return R.drawable.diamonds_8;
            case 8:
                return R.drawable.diamonds_9;
            case 9:
                return R.drawable.diamonds_10;
            case 10:
                return R.drawable.diamonds_jack;
            case 11:
                return R.drawable.diamonds_queen;
            case 12:
                return R.drawable.diamonds_king;
            case 13:
                return R.drawable.hearts_ace;
            case 14:
                return R.drawable.hearts_2;
            case 15:
                return R.drawable.hearts_3;
            case 16:
                return R.drawable.hearts_4;
            case 17:
                return R.drawable.hearts_5;
            case 18:
                return R.drawable.hearts_6;
            case 19:
                return R.drawable.hearts_7;
            case 20:
                return R.drawable.hearts_8;
            case 21:
                return R.drawable.hearts_9;
            case 22:
                return R.drawable.hearts_10;
            case 23:
                return R.drawable.hearts_jack;
            case 24:
                return R.drawable.hearts_queen;
            case 25:
                return R.drawable.hearts_king;
            case 26:
                return R.drawable.clubs_ace;
            case 27:
                return R.drawable.clubs_2;
            case 28:
                return R.drawable.clubs_3;
            case 29:
                return R.drawable.clubs_4;
            case 30:
                return R.drawable.clubs_5;
            case 31:
                return R.drawable.clubs_6;
            case 32:
                return R.drawable.clubs_7;
            case 33:
                return R.drawable.clubs_8;
            case 34:
                return R.drawable.clubs_9;
            case 35:
                return R.drawable.clubs_10;
            case 36:
                return R.drawable.clubs_jack;
            case 37:
                return R.drawable.clubs_queen;
            case 38:
                return R.drawable.clubs_king;
            case 39:
                return R.drawable.spades_ace;
            case 40:
                return R.drawable.spades_2;
            case 41:
                return R.drawable.spades_3;
            case 42:
                return R.drawable.spades_4;
            case 43:
                return R.drawable.spades_5;
            case 44:
                return R.drawable.spades_6;
            case 45:
                return R.drawable.spades_7;
            case 46:
                return R.drawable.spades_8;
            case 47:
                return R.drawable.spades_9;
            case 48:
                return R.drawable.spades_10;
            case 49:
                return R.drawable.spades_jack;
            case 50:
                return R.drawable.spades_queen;
            case 51:
                return R.drawable.spades_king;
        }
        return R.drawable.back;
    }
}
