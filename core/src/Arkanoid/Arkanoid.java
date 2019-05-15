package Arkanoid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import screens.GameScreen;
import util.Constants;

public class Arkanoid extends Game {
    private Screen screen;
    private Music music;

    @Override
    public void create() {
        music = Gdx.audio.newMusic(Gdx.files.internal(Constants.MUSIC_PATH));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        screen = new GameScreen();
        setScreen(screen);
    }


    @Override
    public void dispose(){
        music.dispose();
        screen.dispose();
    }

}
