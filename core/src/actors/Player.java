package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import box2d.PlayerUserData;
import box2d.UserData;
import util.Constants;

public class Player extends GameActor {

    protected final TextureRegion textureRegion;

    public Player(Body body) {
        super(body);
        this.textureRegion = atlas.findRegion(Constants.PLAYER_IMAGE_PATH);
        //textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.PLAYER_IMAGE_PATH)));
    }

    @Override
    public PlayerUserData getUserData() {
        return (PlayerUserData) userData;
    }


    //aplica movimiento lineal segun a que direccion mover
    //los body han dejado de responder a movimiento lineal??

    public void moveRight(){
        //this.body.applyLinearImpulse(getUserData().getRightLinearImpulse(),new Vector2(0,0),true);
        this.body.setLinearVelocity(getUserData().getRightLinearImpulse());

    }

    public void moveLeft(){
        this.body.setLinearVelocity(getUserData().getLeftLinearImpulse());
        //this.body.applyLinearImpulse(getUserData().getLeftLinearImpulse(),new Vector2(0,0),true);
    }

    //quitar el movimiento lineal
    public void rest(){
        this.body.setLinearVelocity(0,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(textureRegion, screenRectangle.x, screenRectangle.y, screenRectangle.width,screenRectangle.height);

    }
}
