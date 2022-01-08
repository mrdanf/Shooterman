package entity.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import entity.AnimatingEntity;
import entity.object.ground.Item;
import entity.object.obstacle.Box;
import entity.object.ground.Ammunition;
import entity.object.ground.HealthOrb;
import entity.object.weapon.Shotgun;
import entity.object.weapon.WeaponType;
import hud.Status;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Pistol;
import entity.object.weapon.Weapon;
import entity.projectile.Projectile;

import java.util.ArrayList;

public class Player extends AnimatingEntity {
    private PlayerMovement move = new PlayerMovement();
    private int health;
    private boolean alive;
    private float movement = 3f;
    private int playerNumber;
    private ArrayList<Integer> playerInput = new ArrayList();
    private Status status;
    private int viewDirection = 0;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Player> players;
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<DestructibleBox> destructibleBoxes = new ArrayList<>();
    private ArrayList<Weapon> weapons;
    private ArrayList<Item> items;
    private ArrayList<Texture> textures;

    private Weapon pistol = new Pistol();
    private Weapon weapon2;
    private Weapon activeWeapon;
    private boolean hasWeapon2 = false;


    /**
     *Initialisiert alle notwendigen Attribute für den Spieler
     */
    public Player(int health, int playerNumber, float startX, float startY, Texture texture, ArrayList<Texture> textures) {
        super(startX, startY, 35, 35, texture, 6, 1);
        this.health = health;
        this.alive = true;
        this.playerNumber = playerNumber;
        this.status = new Status(playerNumber);
        setX(startX);
        setY(startY);
        input();
        this.activeWeapon = this.pistol;
        this.textures = textures;
    }

    /**
     * Weist  dem Player die richtigen tasten zu um das spiel spielen zu können
     * 0.add Nach oben bewegen
     * 1.add Nach links bewegen
     * 2.add Nach unten bewegen
     * 3.add Nach rechts bewegen
     * 4.add Schießen
     * 5.add Aufheben
     * 6.add Waffe wechseln
     *
     * @Author Marcel Sander
     */
    public void input() {
        if (playerNumber == 1) {
            //Nach oben gehen
            playerInput.add(Input.Keys.W);
            //Nach links gehen
            playerInput.add(Input.Keys.A);
            //Nach unten gehen
            playerInput.add(Input.Keys.S);
            //Nach rechts gehen
            playerInput.add(Input.Keys.D);
            //Schießen
            playerInput.add(Input.Keys.CONTROL_LEFT);
            //Aufheben
            playerInput.add(Input.Keys.E);
            //Waffenslot wechseln
            playerInput.add(Input.Keys.Q);
        } else if (playerNumber == 2) {
            playerInput.add(Input.Keys.I);
            playerInput.add(Input.Keys.J);
            playerInput.add(Input.Keys.K);
            playerInput.add(Input.Keys.L);
            playerInput.add(Input.Keys.CONTROL_RIGHT);
            playerInput.add(Input.Keys.O);
            playerInput.add(Input.Keys.U);
        }
    }

    /**
     *Erzeugt die Hitbox für die Spieler
     */
    @Override
    public void updateHitbox() {
        hitbox.setPosition(x + 5, y + 20);
    }

    /**
     *Fragt ab ob der Spieler noch Leben hat und was der Nutzer gerade macht und ruft dann die richte metode auf
     */
    @Override
    public void update() {
        status.update(health, activeWeapon);
        activeWeapon.update();
        if (alive) {
            move.move(this, players, boxes, destructibleBoxes, weapons, items);
        } else {
            setSprite(textures.get(WeaponType.DEAD)); // 4 ist tot Sprite
            sprite.setScale(0.7f);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     *Erzeugt ein Projektil welches in der aktuellen Blickrichtung des Spielers fliegt
     */
    public void shoot() {
        if (activeWeapon.shoot()) {
            if(activeWeapon instanceof Shotgun){
                String spriteName = activeWeapon.getProjectileName();
                System.out.println("cool");
                for (int i = 0; i < 3; i++) {
                    Projectile projectile = new Projectile(viewDirection, sprite.getRotation(), getX(), getY(), this,
                        new Texture(spriteName),i);
                    projectiles.add(projectile);
                }



            }
            else{
            String spriteName = activeWeapon.getProjectileName();
            Projectile projectile = new Projectile(viewDirection, sprite.getRotation(), getX(), getY(), this,
                    new Texture(spriteName),0);
            projectiles.add(projectile);
        }}
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public float getMovement() {
        return movement;
    }

    public ArrayList<Integer> getPlayerInput() {
        return playerInput;
    }

    public Status getStatus() {
        return status;
    }

    public int getViewDirection() {
        return viewDirection;
    }

    public void setViewDirection(int viewDirection) {
        this.viewDirection = viewDirection;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        this.boxes = boxes;
    }

    public void setDestructibleBoxes(ArrayList<DestructibleBox> destructibleBoxes) {
        this.destructibleBoxes = destructibleBoxes;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    /**Zieht dem Spieler leben in höhe der stärke der Waffe ab, mit der der Schuss abgefeuert wurde
     *
     * @param power Gibt die Stärke der Waffe an mit der gegnerische Spieler geschossen hat
     */
    public void receiveDamage(int power) {
        this.health -= power;

        if (health <= 0) {
            this.kill();
        }
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public boolean hasWeapon2() {
        return hasWeapon2;
    }

    public void kill() {
        this.alive = false;
    }

    /**
     * Wechselt die aktuelle Waffe mit der anderen ausgerüsteten Waffe
     */
    public void switchWeapon() {
        if (weapon2 != null) {
            if (activeWeapon == pistol) {
                activeWeapon = weapon2;
            } else {
                activeWeapon = pistol;
            }

            float rotation = sprite.getRotation();
            setSprite(textures.get(activeWeapon.getWeaponType()), 6, 1);
            sprite.setRotation(rotation);
        }
    }

    /**Hebt eine Waffe auf, die im aufhebe Bereich des Spielers liegt
     * und rüstet diese aus
     *
     * @param weapon Waffe die im Bereich des Spielers liegt
     */
    public void pickUpWeapon(Weapon weapon) {
        this.weapon2 = weapon;
        this.activeWeapon = weapon;
        this.hasWeapon2 = true;
        float rotation = sprite.getRotation();
        setSprite(textures.get(activeWeapon.getWeaponType()), 6, 1);
        sprite.setRotation(rotation);
    }

    /**
     * Fügt der primären Waffe Munition hinzu
     * @param ammunitionAmount Die Anzahl an Munition, die hinzugefügt wird
     */
    public void giveAmmo(int ammunitionAmount) {
        this.weapon2.giveAmmo(ammunitionAmount);
    }
}

