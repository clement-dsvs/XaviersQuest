package fr.promeo.xaviersquest.helpers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import fr.promeo.xaviersquest.objects.mapobjects.doors.Door;
import fr.promeo.xaviersquest.objects.entitites.Player;
import fr.promeo.xaviersquest.screens.GameScreen;
import fr.promeo.xaviersquest.utils.Constants;


public class TilemapHelper {
    public static OrthogonalTiledMapRenderer setupMap(String fileName, GameScreen game) {
        TiledMap tiledMap = new TmxMapLoader().load(fileName);
        if (game != null) parseMapObjects(tiledMap.getLayers().get("objects").getObjects(), game);
        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    private static void parseMapObjects(MapObjects mapObjects, GameScreen game) {
        for (MapObject mapObject : mapObjects) {
            if (mapObject instanceof PolygonMapObject) {
                if (mapObject.getName() != null && mapObject.getName().startsWith("door-")) {
                    createDoor((PolygonMapObject) mapObject, game);
                } else {
                    createStaticBody((PolygonMapObject) mapObject, game);
                }
            }

            if (mapObject instanceof RectangleMapObject) {
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                System.out.println(rectangle);
                String rectangleName = mapObject.getName();
                if (rectangleName.equals("player")) {
                    Body body = BodyHelperService.createBody(
                            rectangle.getX() + rectangle.getWidth() / 2,
                            rectangle.getY() + rectangle.getHeight() / 2,
                            rectangle.getWidth(),
                            rectangle.getHeight(),
                            false,
                            game.getWorld()
                    );
                    game.setPlayer(new Player(rectangle.getWidth(), rectangle.getHeight(), body));
                }
            }
        }
    }

    private static Body createStaticBody(PolygonMapObject polygonMapObject, GameScreen game) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = game.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);

        body.createFixture(shape, 1000);
        shape.dispose();
        return body;
    }

    private static Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; i++) {
            Vector2 current = new Vector2(vertices[i * 2] / Constants.PPM, vertices[i * 2 + 1] / Constants.PPM);
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    private static void createDoor(PolygonMapObject polygonMapObject, GameScreen game) {
        Body doorBody = createStaticBody(polygonMapObject, game);
        doorBody.setUserData(new Door(polygonMapObject.getName()));
    }

}
