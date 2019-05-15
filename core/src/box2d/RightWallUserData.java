package box2d;

import enums.UserDataType;

public class RightWallUserData extends UserData {

    public RightWallUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.RIGHT_WALL;
    }
}
