package util;

import com.badlogic.gdx.math.Vector2;

//clase para guardar todas las constantes del juego
public class Constants {

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, 0);

    public static final String TITLE = "Arcanoid Demo";
    public static final int APP_WIDTH = 640;
    public static final int APP_HEIGHT = 480;
    public static final float WORLD_TO_SCREEN = 32;

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 40;
    public static final float GROUND_HEIGHT = 0;
    public static final float GROUND_DENSITY = 0;

    public static final float WALL_LEFT_X = 0;
    public static final float WALL_LEFT_Y = 0;
    public static final float WALL_LEFT_WIDTH = 1f;
    public static final float WALL_LEFT_HEIGHT = 30f;
    public static final float WALL_LEFT_DENSITY = 0f;


    public static final float WALL_RIGHT_X = APP_WIDTH/WORLD_TO_SCREEN;
    public static final float WALL_RIGHT_Y = 0;
    public static final float WALL_RIGHT_WIDTH = 1f;
    public static final float WALL_RIGHT_HEIGHT = 30f;
    public static final float WALL_RIGHT_DENSITY = 0f;

    public static final float ROOF_X = 0;
    public static final float ROOF_Y = 14.75f;
    public static final float ROOF_WIDTH = 40;
    public static final float ROOF_HEIGHT = 0.5f;
    public static final float ROOF_DENSITY = 0f;


    public static final float PLAYER_X = 3;
    public static final float PLAYER_Y = 1;
    public static final float PLAYER_WIDTH = 2f;
    public static final float PLAYER_HEIGHT = 0.5f;
    public static float PLAYER_DENSITY = 0f;

    public static final float BALL_X = 3;
    public static final float BALL_Y = 3;
    public static final float BALL_RADIUS = 0.6f;
    public static float BALL_DENSITY = 0f;
    public static final int BALL_SPEED = 5;


    public static final Vector2 RIGHT_LINEAR_IMPULSE = new Vector2(10, 0);
    public static final Vector2 LEFT_LINEAR_IMPULSE = new Vector2(-10, 0);

    public static final String BACKGROUND_IMAGE_PATH = "background";
    public static final String BALL_IMAGE_PATH = "ball";
    public static final String PLAYER_IMAGE_PATH = "player";
    public static final String MUSIC_PATH = "music.mp3";
    public static final String SOUND_EFFECT_HIT = "soundhit.mp3";

    public static final String LEFT_WALL_TEXTURE = "left_wall";
    public static final String RIGHT_WALL_TEXTURE = "right_wall";
    public static final String ROOF_TEXTURE = "roof";

}
