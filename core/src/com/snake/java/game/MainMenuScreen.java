package com.snake.java.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.awt.Rectangle;

import objects.State;

public class MainMenuScreen implements Screen{

    final SnakeGame game;
    // Transition Screens
    private GameScreen gamescreen;
    private AboutUs aboutUsScreen;
    private InstructionScreen instructionScreen;
    // Graphics
    private OrthographicCamera camera;

    private Texture title;

    private Texture start;
    private Sprite startButton;
    private Rectangle startHitBox;

    private Texture instruction;
    private Sprite instructionButton;
    private Rectangle instructionHitBox;

    private Texture about;
    private Sprite aboutButton;
    private Rectangle aboutHitBox;
    // Window Variables
    public static int width = Gdx.graphics.getWidth();
    public static int height = Gdx.graphics.getHeight();

    public MainMenuScreen(final SnakeGame game) {
        this.game = game;
        // Initialize Camera
        camera = new OrthographicCamera(width, height);
        camera.position.set(width / 2, height / 2, 0);
        // Initialize Graphics
        title = new Texture(Gdx.files.internal("snake.png"));

        start = new Texture(Gdx.files.internal("start_button.png"));
        startButton = new Sprite(start);
        startHitBox = new Rectangle((width / 2) - (191 / 2), 250,191,47);

        instruction = new Texture(Gdx.files.internal("instructions_button.png"));
        instructionButton = new Sprite(instruction);
        instructionHitBox =  new Rectangle((width / 2) - (471 / 2), 175, 471, 47);

        about = new Texture(Gdx.files.internal("about_button.png"));
        aboutButton = new Sprite(about);
        aboutHitBox =  new Rectangle((width / 2) - (311 / 2), 100,287,47);
    }
    public void detectClick(){
        // Check Coordinates of a Mouse Click
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set((float)Gdx.input.getX(),(float)Gdx.input.getY(),0.0F);
            camera.unproject(touchPos);
            if(startHitBox.contains(touchPos.x,touchPos.y)){
                gamescreen = new GameScreen(game, this);
                gamescreen.setGameState(State.RUN);
                game.setScreen(gamescreen);
                dispose();
            }
            else if(aboutHitBox.contains(touchPos.x,touchPos.y)){
                aboutUsScreen = new AboutUs(game,this);
                game.setScreen(aboutUsScreen);
                dispose();
            }
            else if(instructionHitBox.contains(touchPos.x,touchPos.y)){
                instructionScreen = new InstructionScreen(game,this);
                game.setScreen(instructionScreen);
                dispose();
            }
        }
    }
    @Override
    public void render(float delta) {
        // Set Background & Camera
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        // Spritebatch
        game.batch.begin();
        // Draw Title
        game.batch.draw(title,(width / 2) - (191 / 2),400,191,47);
        // Draw Start Button
        game.batch.draw(startButton, startHitBox.x, startHitBox.y, startHitBox.width, startHitBox.height);
        // Draw Instruction Button
        game.batch.draw(instructionButton, instructionHitBox.x, instructionHitBox.y, instructionHitBox.width, instructionHitBox.height);
        // Draw About Button
        game.batch.draw(aboutButton, aboutHitBox.x, aboutHitBox.y, aboutHitBox.width, aboutHitBox.height);
        game.batch.end();
        //Check Click Event
        detectClick();
    }
    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void show() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void dispose() {
    }
}