package util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import box2d.BallUserData;
import box2d.GroundUserData;
import box2d.LeftWallUserData;
import box2d.PlayerUserData;
import box2d.RightWallUserData;
import box2d.RoofUserData;
import util.Constants;
//clase para crear todos los cuerpos de los actores
//a los cuerpos se les ejecutara las fisicas
public class WorldUtils {

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        //posicion inicial del cuerpo
        bodyDef.position.set(new Vector2(Constants.GROUND_X, util.Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(util.Constants.GROUND_WIDTH / 2, util.Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, util.Constants.GROUND_DENSITY);
        //definimos que tipo de cuerpo será
        body.setUserData(new GroundUserData(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
        shape.dispose();
        return body;
    }


    public static Body createWallLeft(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.WALL_LEFT_X, Constants.WALL_LEFT_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.WALL_LEFT_WIDTH / 2, Constants.WALL_LEFT_HEIGHT / 2);
        body.createFixture(shape, Constants.WALL_LEFT_DENSITY);
        body.setUserData(new LeftWallUserData(Constants.WALL_LEFT_WIDTH, Constants.WALL_LEFT_HEIGHT));
        shape.dispose();

        return body;
    }

    public static Body createWallRight(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.WALL_RIGHT_X, Constants.WALL_RIGHT_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.WALL_RIGHT_WIDTH / 2, Constants.WALL_RIGHT_HEIGHT / 2);
        body.createFixture(shape, Constants.WALL_RIGHT_DENSITY);
        body.setUserData(new RightWallUserData(Constants.WALL_RIGHT_WIDTH, Constants.WALL_LEFT_HEIGHT));
        shape.dispose();

        return body;
    }

    public static Body createRoof(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.ROOF_X, Constants.ROOF_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.ROOF_WIDTH / 2, Constants.ROOF_HEIGHT / 2);
        body.createFixture(shape, Constants.ROOF_DENSITY);
        body.setUserData(new RoofUserData(Constants.ROOF_WIDTH, Constants.ROOF_HEIGHT));
        shape.dispose();

        return body;

    }


    public static Body createPlayer(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(util.Constants.PLAYER_X, util.Constants.PLAYER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(util.Constants.PLAYER_WIDTH / 2, util.Constants.PLAYER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.PLAYER_DENSITY);
        body.resetMassData();
        body.setUserData(new PlayerUserData(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createBall(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.BALL_X, Constants.BALL_Y));
        CircleShape shape = new CircleShape();
        shape.setRadius(Constants.BALL_RADIUS / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Constants.BALL_DENSITY);
        body.resetMassData();
        body.setUserData(new BallUserData(Constants.BALL_RADIUS, Constants.BALL_RADIUS));
        shape.dispose();
        return body;
    }


}
