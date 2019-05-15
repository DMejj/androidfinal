package box2d;

import enums.UserDataType;

public class LeftWallUserData extends UserData {

    public LeftWallUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.LEFT_WALL;
    }
}
