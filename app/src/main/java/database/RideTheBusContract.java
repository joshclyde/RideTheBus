package database;

import android.provider.BaseColumns;

public class RideTheBusContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private RideTheBusContract() {}

    public static class GameTable implements BaseColumns {
        public static final String TABLE_NAME = "GAME";
        public static final String COLUMN_PLAYER_ID = "player_id"; //
        public static final String COLUMN_CARD_ID = "card_id";
    }

    public static class CardTable implements BaseColumns {
        public static final String TABLE_NAME = "CARD";
        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_GAME_ID = "game_id";
        public static final String COLUMN_DIAMOND_ORDER ="diamond_order";
        public static final String COLUMN_PLAYER_ID = "player_id";
        public static final String COLUMN_PLAYER_ORDER = "player_order";
    }

    public static class PlayerStateTable implements BaseColumns {
        public static final String TABLE_NAME = "PLAYER_STATE";
        public static final String COLUMN_GAME_ID = "game_id";
        public static final String COLUMN_PLAYER_ID = "player_id";
        public static final String COLUMN_TURN = "turn";
        public static final String COLUMN_DRINKS_TAKEN = "drinks_taken";
        public static final String COLUMN_MAX_DRINKS = "max_drinks";
    }

    public static class PlayerDetailsTable implements BaseColumns {
        public static final String TABLE_NAME = "PLAYER_DETAILS";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PIC = "pic";
        public static final String COLUMN_MAX_DRINKS = "max_drinks";
    }
}
