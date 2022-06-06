package fr.promeo.xaviersquest.objects.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import fr.promeo.xaviersquest.objects.mapobjects.doors.Door;
import fr.promeo.xaviersquest.screens.GameScreen;

public class CollisionListener implements ContactListener {

    private GameScreen game;

    public CollisionListener(GameScreen game) {
        this.game = game;
    }

    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureA().getBody() == game.getPlayer().getBody()) {
            if (contact.getFixtureB().getBody().getUserData() instanceof Door) {
                Door door = (Door) contact.getFixtureB().getBody().getUserData();
                System.out.println(door.getName());
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
