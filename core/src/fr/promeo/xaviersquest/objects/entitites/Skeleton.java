package fr.promeo.xaviersquest.objects.entitites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import fr.promeo.xaviersquest.helpers.AnimationHelper;
import fr.promeo.xaviersquest.utils.Constants;

public class Skeleton extends GameEntity {

    private Animation idleAnimation;
    private Animation currentAnimation;
    private float stateTime;

    public Skeleton(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
        this._acc = 1f;
        this._dcc = 0.5f;

        idleAnimation = AnimationHelper.generateAnimation("./skins/player/idle-sprite.png", 4, 64, 64, 1f / 3f);
        this.currentAnimation = idleAnimation;
    }

    @Override
    public void update() {
        x = body.getPosition().x * Constants.PPM;
        y = body.getPosition().y * Constants.PPM;

        if (velX > 0) velX -= _dcc;
        else if (velX < 0) velX += _dcc;

        if (velY > 0) velY -= _dcc;
        else if (velY < 0) velY += _dcc;

        velX = clamp(velX, 3, -3);
        velY = clamp(velY, 3, -3);

        body.setLinearVelocity(velX * speed, velY * speed);
    }

    @Override
    public void render(SpriteBatch spriteBatch, float delta) {
        stateTime += delta;
        TextureRegion animationFrame = (TextureRegion) currentAnimation.getKeyFrame(stateTime, true);
        spriteBatch.draw(animationFrame, x - 128, y - 128, 256, 256);
    }

    private float clamp(float value, float max, float min) {
        if (value >= max) return max;
        return Math.max(value, min);
    }
}
