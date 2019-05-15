package box2d;

import actors.Ball;
import enums.UserDataType;
import util.Constants;

public class BallUserData extends UserData {

    public BallUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.BALL;
    }
}
