package com.snake.java.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import javax.xml.soap.Text;

public class GameOverScreen implements Screen {
    // Screen Variables
    private MainMenuScreen mainmenu;
    private SnakeGame game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    // Game Variables
    private Texture gameover;
    private Sound gameoverSound;
    private boolean playGameoverSound;


    public GameOverScreen(final SnakeGame game, MainMenuScreen mainmenu){
        this.game = game;
        // Implement pause to main menu
        this.mainmenu = mainmenu;
        // Create Camera
        camera = new OrthographicCamera(mainmenu.width, mainmenu.height);
        camera.position.set(mainmenu.width / 2, mainmenu.height / 2, 0);
        // Initialize Graphics
        shapeRenderer = new ShapeRenderer();
        gameover = new Texture(Gdx.files.internal("game_over.png"));
        gameoverSound = Gdx.audio.newSound(Gdx.files.internal("game_over_sound.wav"));
        playGameoverSound = true;
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(gameover, (mainmenu.width / 2) - (325 / 2), (mainmenu.height / 2) - (75 / 2));
        game.batch.end();

        if(playGameoverSound){
            gameoverSound.play();
            playGameoverSound = false;
        }

    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
    }
}
