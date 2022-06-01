package fr.promeo.xaviersquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.promeo.xaviersquest.screens.GameScreen;

public class MyGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

}
