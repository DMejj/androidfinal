package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;

import box2d.RightWallUserData;
import box2d.UserData;
import util.Constants;

public class Wall_Right extends GameActor {

    protected final TextureRegion textureRegion;

    public Wall_Right(Body body) {
        super(body);
        textureRegion = atlas.findRegion(Constants.RIGHT_WALL_TEXTURE);
        //textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.RIGHT_WALL_TEXTURE)));

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(textureRegion, screenRectangle.x, screenRectangle.y, screenRectangle.width,screenRectangle.height);

    }


    @Override
    public UserData getUserData() {
        return (RightWallUserData) userData;
    }
}
