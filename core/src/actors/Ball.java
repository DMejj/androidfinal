package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import box2d.BallUserData;
import box2d.UserData;
import util.Constants;

public class Ball extends GameActor {


    int speedX;
    int speedY;
    protected final TextureRegion textureRegion;

    private Sound hit;

    private Circle screenCircle;

    public Ball(Body body) {
        super(body);
        speedX = Constants.BALL_SPEED;
        speedY = Constants.BALL_SPEED;
        screenCircle = new Circle();
        textureRegion = atlas.findRegion(Constants.BALL_IMAGE_PATH);
        //textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BALL_IMAGE_PATH)));
        hit = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_EFFECT_HIT));
    }

    public void move(){
        this.body.applyLinearImpulse(new Vector2(speedX, speedY),new Vector2(0,0),true);
    }

    //cambiar direccion x
    public void changeX(){
        this.body.setLinearVelocity(this.body.getLinearVelocity().x*-1,this.body.getLinearVelocity().y);

        //this.body.applyLinearImpulse(new Vector2(speedX*-2, 0),new Vector2(0,0),true);
    }

    //cambiar direccion y
    public void changeY(){
        this.body.setLinearVelocity(this.body.getLinearVelocity().x,this.body.getLinearVelocity().y*-1);
        //this.body.applyLinearImpulse(new Vector2(0, speedY*-2),new Vector2(0,0),true);
    }

    //si golpeamos la pelota mientras el usuario se mueve, le pasa una parte de su fuerza en el eje x
    public void addForce(Vector2 v) {
        this.body.applyLinearImpulse(v, new Vector2(0, 0), true);
        hit.setVolume(0,0.2f);
        hit.play();
    }

    //mismo metodo que el de gameactor, pero con un circulo
    private void updateCircle() {
        screenCircle.x = transformToScreen(body.getPosition().x - userData.getWidth() / 2);
        screenCircle.y = transformToScreen(body.getPosition().y - userData.getHeight() / 2);
        screenCircle.radius = transformToScreen(userData.getWidth());
    }

    public void act(float delta){
        super.act(delta);
        updateCircle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, screenCircle.x, screenCircle.y, screenCircle.radius, screenCircle.radius);
    }

    @Override
    public UserData getUserData() {
        return (BallUserData) userData;
    }

    @Override
    public void dispose(){
        atlas.dispose();
        hit.dispose();
    }
}
