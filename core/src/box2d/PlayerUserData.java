package box2d;

import com.badlogic.gdx.math.Vector2;

import enums.UserDataType;
import util.Constants;

public class PlayerUserData extends UserData {

    private Vector2 rightLinearImpulse;
    private Vector2 leftLinearImpulse;

    public PlayerUserData(float width, float height) {
        super(width, height);
        rightLinearImpulse = Constants.RIGHT_LINEAR_IMPULSE;
        leftLinearImpulse = Constants.LEFT_LINEAR_IMPULSE;
        userDataType = UserDataType.PLAYER;
    }

    public Vector2 getRightLinearImpulse() {
        return rightLinearImpulse;
    }

    public void setRightLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.rightLinearImpulse = jumpingLinearImpulse;
    }

    public Vector2 getLeftLinearImpulse() {return leftLinearImpulse;}

    public void setLeftLinearImpulse(Vector2 leftLinearImpulse) {
        this.leftLinearImpulse = leftLinearImpulse;
    }
}
