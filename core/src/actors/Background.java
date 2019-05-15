package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import util.Constants;

public class Background extends Actor {

    TextureAtlas atlas;
    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed = 50;

    //cargamos la textura del atlas.
    //se crearan dos fondos que se irán desplazando de arriba a abajo. Cuando el segundo tenga la posicion del primero
    //se reinician las posiciones
    public Background() {
        atlas = new TextureAtlas("fitxeratlas.atlas");
        textureRegion = atlas.findRegion(Constants.BACKGROUND_IMAGE_PATH);
        //textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
        textureRegionBounds1 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, Constants.APP_HEIGHT, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

    //controlamos el movimiento de los fondos y reinicia si llega
    @Override
    public void act(float delta) {
        if (downBoundsReached(delta)) {
            resetBounds();
        } else {
            updateYBounds(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, 0, textureRegionBounds1.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        batch.draw(textureRegion, 0, textureRegionBounds2.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
    }

    //devuelve si el segundo fondo ha llegado a abajo
    private boolean downBoundsReached(float delta) {
        return (textureRegionBounds2.y - (delta * speed)) <= 0;
    }

    //desplazamiento
    private void updateYBounds(float delta) {
        textureRegionBounds1.y += delta * speed;
        textureRegionBounds2.y += delta * speed;
    }

    //reinicio
    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, Constants.APP_HEIGHT, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

    public void dispose(){
        atlas.dispose();
    }
}
