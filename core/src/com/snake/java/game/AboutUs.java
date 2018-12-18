package com.snake.java.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.awt.Rectangle;

public class AboutUs implements Screen {

    final SnakeGame game;

    // Implement pause to main menu
    private MainMenuScreen mainmenu;

    // Game Sound & Graphics

    private OrthographicCamera camera;

    private Texture title;

    private BitmapFont bfont;

    private Texture back;
    private Sprite backButton;
    private Rectangle backHitBox;

    // Implement pause to main menu: constructor requires the MainMenuScreen object as a parameter.
    public AboutUs(final SnakeGame game, MainMenuScreen mainmenu) {
        this.game = game;
        this.mainmenu = mainmenu;
        // Initialize Camera
        camera = new OrthographicCamera(this.mainmenu.width, this.mainmenu.height);
        camera.position.set(this.mainmenu.width / 2, this.mainmenu.height / 2, 0);
        // Initialize Graphics
        title = new Texture(Gdx.files.internal("about_button.png"));

        back = new Texture(Gdx.files.internal("arrowLeft.png"));
        backButton = new Sprite(back);
        backHitBox = new Rectangle(50,50,50,50);

        bfont = new BitmapFont(Gdx.files.internal("bfont.fnt"),Gdx.files.internal("bfont.png"), false);
        bfont.setColor(Color.WHITE);
        bfont.getData().setScale(1.25f,1.25f);
    }
    @Override

    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        // Draw Title
        game.batch.draw(title,50,400,311,47);
        // Draw Text
        bfont.draw(game.batch, "Created by: ", 50, 375);
        bfont.draw(game.batch,"Jonathan Dzialo",50,325);
        bfont.draw(game.batch,"Anthony Vives",50,275);
        bfont.draw(game.batch,"Jimmy Duong",50,225);
        // Draw Back Button
        game.batch.draw(back, backHitBox.x, backHitBox.y, backHitBox.width, backHitBox.height);
        game.batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set((float) Gdx.input.getX(), (float) Gdx.input.getY(), 0.0F);
            camera.unproject(touchPos);
            if (backHitBox.contains(touchPos.x, touchPos.y)){
                game.setScreen(mainmenu);
                dispose();
            }
        }
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
