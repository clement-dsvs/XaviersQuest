package fr.promeo.xaviersquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.promeo.xaviersquest.MyGame;
import fr.promeo.xaviersquest.helpers.FreeTypeFontHelper;
import fr.promeo.xaviersquest.helpers.TilemapHelper;
import fr.promeo.xaviersquest.utils.Constants;

public class MenuScreen implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private String title;
    private BitmapFont font;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private MyGame game;

    public MenuScreen(MyGame game) {this.game = game;}

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1280, 720);
        this.title = "Xavier's Quest";
        /*this.font = FreeTypeFontHelper.FontGenerator("./fonts/m5x7.ttf", 32);
        this.font.setColor(1f, 0f, 0f, 1f);
        this.font.getData().setScale(2,2);*/
        this.batch = game.getBatch();

        this.tiledMapRenderer = TilemapHelper.setupMap("./Screens/MenuPrincipal/menuprincipal.tmx", null);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        tiledMapRenderer.render();
        //batch.begin();
        //font.draw(batch, title, 100,100);
        //batch.end();
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
