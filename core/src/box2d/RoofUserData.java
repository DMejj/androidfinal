package box2d;

import enums.UserDataType;

public class RoofUserData extends UserData {

    public RoofUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.ROOF;
    }
}
