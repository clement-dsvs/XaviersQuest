package fr.promeo.xaviersquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.promeo.xaviersquest.screens.GameScreen;
import fr.promeo.xaviersquest.screens.MenuScreen;

public class MyGame extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

}
