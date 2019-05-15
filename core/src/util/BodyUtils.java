package util;

import com.badlogic.gdx.physics.box2d.Body;

import box2d.UserData;
import enums.UserDataType;

//clase para comprobar que tipo de actor tiene el cuerpo
//usado para controlar las colisiones
public class BodyUtils {


    public static boolean bodyIsPlayer(Body body) {
        UserData userData = (UserData) body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.PLAYER;
    }

    public static boolean bodyIsRoof(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ROOF;
    }

    public static boolean bodyIsLeftWall(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.LEFT_WALL;
    }

    public static boolean bodyIsRightWall(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RIGHT_WALL;

    }

    public static boolean bodyIsBall(Body body){
        UserData userData = (UserData) body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.BALL;
    }

    public static boolean bodyIsGround(Body body){
        UserData userData = (UserData) body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
}
