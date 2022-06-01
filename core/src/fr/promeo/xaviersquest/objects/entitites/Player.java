package fr.promeo.xaviersquest.objects.entitites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import fr.promeo.xaviersquest.utils.Constants;

public class Player extends GameEntity {
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
        this._acc = 1f;
        this._dcc = 0.5f;
    }

    @Override
    public void update() {
        x = body.getPosition().x * Constants.PPM;
        y = body.getPosition().y * Constants.PPM;

        checkUserInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    }

    private void checkUserInput() {
        //mouvement horizontal
        if (Gdx.input.isKeyPressed(Input.Keys.D)) velX += _acc;
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) velX -= _acc;
        else if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (velX > 0) velX -= _dcc;
            else if (velX < 0) velX += _dcc;
        }

        //mouvement vertical
        if (Gdx.input.isKeyPressed(Input.Keys.W)) velY += _acc;
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) velY -= _acc;
        else if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (velY > 0) velY -= _dcc;
            else if (velY < 0) velY += _dcc;
        }

        velX = clamp(velX, 3, -3);
        velY = clamp(velY, 3, -3);

        body.setLinearVelocity(velX * speed, velY * speed);
    }

    private float clamp(float value, float max, float min) {
        if (value >= max) return max;
        return Math.max(value, min);
    }
}
