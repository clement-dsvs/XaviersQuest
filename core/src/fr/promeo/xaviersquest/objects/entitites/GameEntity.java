package fr.promeo.xaviersquest.objects.entitites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameEntity {

    protected float x, y, velX, velY, speed, _acc, _dcc;
    protected float width, height;
    protected Body body;

    public GameEntity(float width, float height, Body body) {
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.width = width;
        this.height = height;
        this.velX = 0;
        this.velY = 0;
        this.body = body;
    }

    public abstract void update();

    public abstract void render(SpriteBatch spriteBatch, float delta);

    public Body getBody() {
        return body;
    }

}