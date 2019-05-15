package actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

import box2d.UserData;
import util.Constants;

public abstract class GameActor extends Actor {

    TextureAtlas atlas;
    protected Body body;
    protected Rectangle screenRectangle;
    protected UserData userData;


    public GameActor(Body body) {
        this.body = body;
        screenRectangle = new Rectangle();
        atlas = new TextureAtlas("fitxeratlas.atlas");
        this.userData = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();

    //cambiamos el valor de las coordenadas en pixeles para dibujar
    protected float transformToScreen(float n) {
        return Constants.WORLD_TO_SCREEN * n;
    }

    //actualizar la posicion del rectangulo. El rectangulo se usa para almacenar las posiciones de los actores a dibujar
    private void updateRectangle() {
        screenRectangle.x = transformToScreen(body.getPosition().x - userData.getWidth() / 2);
        screenRectangle.y = transformToScreen(body.getPosition().y - userData.getHeight() / 2);
        screenRectangle.width = transformToScreen(userData.getWidth());
        screenRectangle.height = transformToScreen(userData.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateRectangle();
    }

    public void dispose(){
        atlas.dispose();
    }

}
