package fr.promeo.xaviersquest.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationHelper {

    public static Animation generateAnimation(String fileName, int nbTuile, int width, int height, float frameTime) {
        Texture img = new Texture(fileName);
        TextureRegion[][] tmpFrames = TextureRegion.split(img, width, height);
        TextureRegion[] animationFrames = new TextureRegion[nbTuile];

        int index = 0;
        for (int i = 0; i < nbTuile; i++) {
            animationFrames[index++] = tmpFrames[0][i];
        }

        return new Animation(frameTime, animationFrames);
    }
}
