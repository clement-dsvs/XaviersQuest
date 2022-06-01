package fr.promeo.xaviersquest.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu extends ApplicationAdapter {

    private SpriteBatch sb;
    private BitmapFont tileFont;
    private BitmapFont font;
    private final String title = "Xavier's Quest";
    private int currentItem;
    private Camera camera;
    private String[] menuItems;
    public static GlyphLayout glyphLayout = new GlyphLayout();

    public void init() {

        sb = new SpriteBatch();
        tileFont = new BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"));
        tileFont.setColor(Color.WHITE);

        menuItems = new String[] {
            "Jouer",
            "Paramètre",
            "Crédit",
            "Quitter"
        };

    }

    public void update(float dt) {

        handleInput();

    }

    public void draw() {

        camera = new OrthographicCamera(50,50);
        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        tileFont.draw(sb, title, glyphLayout.width / 2 , glyphLayout.height / 2);

        sb.end();
    }

    public void handleInput() {}

    public void dispose() {}

}
