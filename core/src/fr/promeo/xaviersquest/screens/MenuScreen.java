package fr.promeo.xaviersquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.promeo.xaviersquest.MyGame;
import fr.promeo.xaviersquest.helpers.FreeTypeFontHelper;
import fr.promeo.xaviersquest.helpers.TilemapHelper;
import fr.promeo.xaviersquest.utils.Constants;
import jdk.internal.org.jline.terminal.MouseEvent;

public class MenuScreen implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture title;
    private BitmapFont font;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private MyGame game;
    private Texture background;
    private Button playButton;
    private Stage stage;

    public MenuScreen(MyGame game) {this.game = game;}

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 598, 336);

        this.font = FreeTypeFontHelper.FontGenerator("./fonts/m5x7.ttf", 32);
        this.font.setColor(1f, 0.65f, 0.01f, 1f);
        this.font.getData().setScale(2,2);
        this.batch = game.getBatch();

        this.tiledMapRenderer = TilemapHelper.setupMap("./Screens/MenuPrincipal/menuprincipal.tmx", null);

        this.background = new Texture("./Screens/MenuPrincipal/background2.jpg");
        this.title = new Texture("./Screens/MenuPrincipal/title.png");

        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.fontColor = Color.BLUE;
        buttonStyle.downFontColor = Color.RED;
        buttonStyle.font = this.font;
        playButton = new TextButton("play button", buttonStyle);
        playButton.setSize(300, 50);
        playButton.setPosition(95,500);

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Button Pressed");
                game.updateScreen(new GameScreen(game));
            }
        });

        stage.addActor(playButton);
    }

    @Override
    public void render(float delta) {
        this.update();
        ScreenUtils.clear(0,0,0,1);
        batch.begin();
            batch.draw(background, 0, 0);
            batch.draw(title, 180, 160, 400, 120);
        batch.end();
        tiledMapRenderer.render();
        stage.draw();
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

    private void update() {
        this.cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera);
    }

    private void cameraUpdate() {
        Vector3 position = camera.position;
        this.camera.position.set(position);
        camera.update();
    }
}
