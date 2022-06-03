package fr.promeo.xaviersquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.promeo.xaviersquest.screens.MenuScreen;

import java.util.Stack;

public class MyGame extends Game {
	SpriteBatch batch;
	Screen screen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	public void changeScreen(Screen screen) {
		this.setScreen(screen);
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

	public void exitGame(){
		Gdx.app.exit();
	}

}
