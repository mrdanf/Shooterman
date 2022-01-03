package com.shooterman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.ground.Ammunition;
import entity.object.ground.HealthOrb;
import entity.object.weapon.Weapon;
import entity.player.Player;
import hud.Status;
import hud.menu.AbstractWindow;
import hud.menu.button.AbstractButton;
import hud.menu.MenuWindow;
import entity.projectile.Projectile;
import hud.menu.button.HelpWindow;


public class Shooterman extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture map;
    private OrthographicCamera camera;
    private Sprite mapSprite;
    private Game game;

    AbstractWindow activeWindow;
    boolean paused = false;

    @Override
    public void create() {
        this.activeWindow = new MenuWindow();
        this.game = new Game();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1, h / w);
        batch = new SpriteBatch();
        map = new Texture("Spielfeld.png");
        mapSprite = new Sprite(TextureRegion.split(map, map.getWidth(), map.getHeight())[0][0]);
        camera.position.x = mapSprite.getX() + mapSprite.getOriginX();
        camera.position.y = mapSprite.getY() + mapSprite.getOriginY();
        camera.zoom = 1000f; // Je größer der Zoom, desto weiterweg ist die Kamera
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused = !paused;
        }
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(map, 0, 0);
        if (!paused) {
            game.update();
            batchGame();
        } else {
            updateMenu();
            batchMenu();
        }
        batch.end();

    }

    private void batchGame() {
        for (Player player : game.getPlayers()) {
            Sprite sprite = player.getSprite();
            sprite.setX(player.getX());
            sprite.setY(player.getY());
            sprite.draw(batch);

            Status status = player.getStatus();
            status.getPlayerLabel().draw(batch, 1);
            batch.draw(status.getHealthBar()[0], status.getXPostion(), status.getYPosition() - 35);
            batch.draw(status.getHealthBar()[1], status.getXPostion(), status.getYPosition() - 35);
            status.getAmmoLabel().draw(batch, 1);
        }

        for (Player player : game.getPlayers()) {
            for (Projectile projectile : player.getProjectiles()) {
                Sprite sprite = projectile.getSprite();
                sprite.setX(projectile.getX());
                sprite.setY(projectile.getY());
                sprite.draw(batch);
            }
        }
        for (Box box : game.getBoxes()) {
            Sprite sprite = box.getSprite();
            sprite.setX(box.getX());
            sprite.setY(box.getY());
            sprite.draw(batch);
        }
        for (DestructibleBox destructibleBox : game.getDestructibleBoxes()) {
            Sprite sprite = destructibleBox.getSprite();
            sprite.setX(destructibleBox.getX());
            sprite.setY(destructibleBox.getY());
            sprite.draw(batch);
        }

        for (Weapon weapon : game.getWeapons()) {
            Sprite sprite = weapon.getSprite();
            sprite.setX(weapon.getX());
            sprite.setY(weapon.getY());
            sprite.draw(batch);
        }
        for (Ammunition ammunition : game.getAmmunitions()) {
            Sprite sprite = ammunition.getSprite();
            sprite.setX(ammunition.getX());
            sprite.setY(ammunition.getY());
            sprite.draw(batch);
        }
        for (HealthOrb healthOrb : game.getHealthOrbs()) {
            Sprite sprite = healthOrb.getSprite();
            sprite.setX(healthOrb.getX());
            sprite.setY(healthOrb.getY());
            sprite.draw(batch);
        }

        game.player1Position.setX(game.player1.getHitboxX());
        game.player1Position.setY(game.player1.getHitboxY());
        game.player1Position.draw(batch);
        game.player2Position.setX(game.player2.getHitboxX());
        game.player2Position.setY(game.player2.getHitboxY());
        game.player2Position.draw(batch);
    }

    private void batchMenu() {
        batch.draw(activeWindow.getBackground(), 0, 0);
        batch.draw(activeWindow.getWindow(), activeWindow.getxOffset(), activeWindow.getyOffset());
        for (AbstractButton button : activeWindow.getButtons()) {
            batch.draw(button.getBackground(), button.getxPosition(), button.getyPosition());
            button.getNameLabel().draw(batch, 1);
        }
        if (activeWindow instanceof HelpWindow) {
            ((HelpWindow) activeWindow).getTextLabel().draw(batch, 1);
        }
    }


    private void updateMenu() {
        String button;
        if ((button = activeWindow.update()) != null) {
            System.out.println(button + " pressed!");
            if (button.equals("Hilfe")) {
                activeWindow = new HelpWindow();
            }
            if (button.equals("x")) {
                if (activeWindow instanceof MenuWindow) {
                    paused = false;
                } else {
                    activeWindow = new MenuWindow();
                }
            }
            if (button.equals("Neues Spiel")) {
                this.game = new Game();
                paused = false;
            }
            if (button.equals("Beenden")) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }
}
