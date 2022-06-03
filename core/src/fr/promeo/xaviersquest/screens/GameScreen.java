package fr.promeo.xaviersquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import fr.promeo.xaviersquest.objects.entitites.Player;

public class GameScreen implements Screen {

    private static final float PPM = 32.f;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private MyGame game;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    //game objects
    private Player player;

    public GameScreen(MyGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1920, 1080);
        this.world = new World(new Vector2(0, 0), false);
        this.box2DDebugRenderer = new Box2DDebugRenderer();
        this.batch = game.getBatch();
        this.orthogonalTiledMapRenderer = TilemapHelper.setupMap("./maps/mainmap/mainmap.tmx", this);
    }

    @Override
    public void render(float delta) {
        this.update();
        ScreenUtils.clear(0, 0, 0, 1);
        orthogonalTiledMapRenderer.render();
        batch.begin();
        //render objects
        player.render(batch, delta);
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
        batch.dispose();
        world.dispose();
        orthogonalTiledMapRenderer.dispose();
        box2DDebugRenderer.dispose();
    }

    private void update() {
        world.step(1 / 60f, 6, 2);
        player.update();
        this.cameraUpdate();
        this.handleInput();
        batch.setProjectionMatrix(camera.combined);
        orthogonalTiledMapRenderer.setView(camera);
    }

    private void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        this.camera.position.set(position);
        camera.update();
    }

    public World getWorld() {
        return world;
    }

    private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MenuScreen(game));
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }

}