package fr.promeo.xaviersquest.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import fr.promeo.xaviersquest.MyGame;
import fr.promeo.xaviersquest.helpers.TilemapHelper;
import sun.font.TrueTypeFont;

public class GameScreen implements Screen {

    private static final float PPM = 32.f;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private MyGame game;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TilemapHelper tilemapHelper;

    //game objects

    public GameScreen(MyGame game){
        this.game = game;
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1920, 1080);
        this.world = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.batch = game.getBatch();
        this.tilemapHelper = new TilemapHelper(this);
        this.orthogonalTiledMapRenderer = tilemapHelper.setupMap("./maps/mainmap/mainmap.tmx");
    }

    @Override
    public void render(float delta) {
        this.update();
        ScreenUtils.clear(0, 0, 0, 1);
        orthogonalTiledMapRenderer.render();
        batch.begin();
        //render objects
        batch.end();
        box2DDebugRenderer.render(world, camera.combined.scl(PPM));
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
        world.step(1 / 60f, 6, 2);
        this.cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);

    }

    private void cameraUpdate() {
        Vector3 position = camera.position;
        this.camera.position.set(position);
        camera.update();
    }

    public World getWorld() {
        return world;
    }

}