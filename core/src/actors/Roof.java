package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

import box2d.RoofUserData;
import box2d.UserData;
import util.Constants;

public class Roof extends GameActor {
    protected final TextureRegion textureRegion;

    public Roof(Body body) {
        super(body);
        textureRegion = atlas.findRegion(Constants.ROOF_TEXTURE);
        //textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.ROOF_TEXTURE)));
    }

    @Override
    public UserData getUserData() {
        return (RoofUserData) userData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(textureRegion, screenRectangle.x, screenRectangle.y, screenRectangle.width,screenRectangle.height);

    }
}
