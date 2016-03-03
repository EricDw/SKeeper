package com.publicmethod.owner.skeeper.constants;

/**
 * Created by Owner on 2016-02-17.
 */
public class Keys {
//    int keys
    public static final int KEY_DEFAULT_PLAYER_SCORE = 0;
    public static final String KEY_DEFAULT_PLAYER_NAME = "Player ";
    public static final String CALCULATOR_PACKAGE_NAME = "com.android.calculator2";
    public static final String CALCULATOR_CLASS_NAME = "com.android.calculator2.Calculator";

    //    String keys
    public static final int KEY_DEFAULT_PLAYERS_AMOUNT = 2;
    public static final String KEY_PLAYERS_AMOUNT = "key_number_of_players";
    public static final String KEY_PLAYER_NAME = "key_player_name";
    public static final String KEY_PLAYER_SCORE = "key_player_score";

    private static final String PREFS_FILE = "com.publicmethod.owner.skeeper.preferences";

    public static String getPrefsFile() {
        return PREFS_FILE;
    }
}
