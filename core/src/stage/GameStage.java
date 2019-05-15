package stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;


import actors.Background;
import actors.Ball;
import actors.Ground;
import actors.Player;
import actors.Roof;
import actors.Wall_Left;
import actors.Wall_Right;
import util.BodyUtils;
import util.Constants;
import util.WorldUtils;

public class GameStage extends Stage implements ContactListener {


    private World world;
    private Ground ground;
    private Player player;
    private Ball ball;
    private Roof roof;
    private Wall_Left wall_left;
    private Wall_Right wall_right;
    private Background background;


    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;

    //con el renderer se testearon las fisicas antes de aplicar cualquier textura a los actores
    private Box2DDebugRenderer renderer;


    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;


    //llamamos a todos los metodos para crear los actores y seteamos el viewport
    public GameStage() {
        super(new StretchViewport(Constants.APP_WIDTH, Constants.APP_HEIGHT));
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
        renderer = new Box2DDebugRenderer();
    }

    //metodo para crear a todos los actores y añadirlos al stage
    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpGround();
        setUpPlayer();
        setUpWalls();
        setUpBall();
    }

    //crear fondo
    private void setUpBackground(){
        background = new Background();
        addActor(background);
    }

    //crear suelo
    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    //crear pelota
    private void setUpBall() {
        ball = new Ball(WorldUtils.createBall(world));
        addActor(ball);
        ball.move();
    }

    //crear paredes
    private void setUpWalls() {
        wall_left = new Wall_Left(WorldUtils.createWallLeft(world));
        addActor(wall_left);
        wall_right = new Wall_Right(WorldUtils.createWallRight(world));
        addActor(wall_right);
        roof = new Roof(WorldUtils.createRoof(world));
        addActor(roof);
    }


    //crear jugador
    private void setUpPlayer(){
        player = new Player(WorldUtils.createPlayer(world));
        addActor(player);
    }

    //crear camara
    private void setupCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        //camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        //camera.update();
    }

    //definimos las zonas izquierda y derecha de la pantalla
    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenRightSide = new com.badlogic.gdx.math.Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);

        Gdx.input.setInputProcessor(this);
    }

    //hace avanzar al mundo
    @Override
    public void act(float delta) {
        super.act(delta);

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    //metodo que se usaba para testear las fisicas del juego antes de tener texturas los actores
    //@Override
    //public void draw() {
        //super.draw();
      //  renderer.render(world, camera.combined);
    //}

    //dependiendo de que lado toquemos la pantalla, movera a un lado o a otro
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {

        // traduce pixeles a coordenadas del mundo
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            player.moveRight();
        }else if (leftSideTouched(touchPoint.x,touchPoint.y)){
            player.moveLeft();
        }

        return super.touchDown(x, y, pointer, button);
    }

    //cuando dejemos de tocar, el jugador dejara de moverse
    @Override
    public boolean touchUp(int x, int y, int pointer, int button){

        translateScreenToWorldCoordinates(x, y);
        player.rest();

        return super.touchDown(x, y, pointer, button);
    }

    //metodos para saber que lado de la pantalla se ha tocado
    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }
    private boolean leftSideTouched(float x, float y){ return screenLeftSide.contains(x,y);}

    //metodo para saber que parte de la pantalla hemos tocado
    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    //control de colisiones
    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        //control de la pelota
        if ((BodyUtils.bodyIsBall(a) && BodyUtils.bodyIsRightWall(b)) ||
                (BodyUtils.bodyIsRightWall(a) && BodyUtils.bodyIsBall(b)) ||
                (BodyUtils.bodyIsBall(a) && BodyUtils.bodyIsLeftWall(b)) ||
                (BodyUtils.bodyIsLeftWall(a) && BodyUtils.bodyIsBall(b))) {

            ball.changeX();
        }
        if ((BodyUtils.bodyIsBall(a) && BodyUtils.bodyIsRoof(b)) ||
                (BodyUtils.bodyIsRoof(a) && BodyUtils.bodyIsBall(b))) {
            ball.changeY();
        }

        //toca el suelo
        if ((BodyUtils.bodyIsBall(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsBall(b))){
            setUpWorld();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    //para impedir que la pelota y el jugador se ejerzan fisicas entre ellos al chocarse
    //definimos nosotros el cambio de direcciones o fuerzas e impedimos la colision
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        Fixture fixA;
        Fixture fixB;
        Body bodyA;
        Body bodyB;

        fixA = contact.getFixtureA();
        fixB = contact.getFixtureB();

        bodyA = fixA.getBody();
        bodyB = fixB.getBody();


        if ((BodyUtils.bodyIsBall(bodyA) && BodyUtils.bodyIsPlayer(bodyB))){
            contact.setEnabled(false);
            if(bodyA.getLinearVelocity().y < 0){
                ball.changeY();
                ball.addForce(new Vector2(bodyA.getLinearVelocity().x/3,0));
            }
        }else if (BodyUtils.bodyIsPlayer(bodyA) && BodyUtils.bodyIsBall(bodyB)) {
            contact.setEnabled(false);
            if(bodyB.getLinearVelocity().y < 0) {
                ball.changeY();
                ball.addForce(new Vector2(bodyA.getLinearVelocity().x / 3, 0));
            }
        }

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void dispose(){
        background.dispose();
        ball.dispose();
        wall_left.dispose();
        wall_right.dispose();
        roof.dispose();
        player.dispose();
        ground.dispose();
    }
}
