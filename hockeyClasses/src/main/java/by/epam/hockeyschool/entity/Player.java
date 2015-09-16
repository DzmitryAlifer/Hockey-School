package by.epam.hockeyschool.entity;

import java.io.Serializable;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The {@code Player} class is an entity-class which includes all
 * the necessary getters and setters for its fields and also overrides
 * {@code equals()}, {@code hashCode()} and {@code toString()}.
 */
public class Player extends Person /*implements Serializable */{

    private int defence;
    private int attack;
    private int strength;
    private int speed;
    private String location;
    private PlayerQuestionnaire quest;
    private int price;

    /**
     * Default constructor of the {@code Player} class.
     */
    public Player() {}

    /**
     * Constructor with parameters, which receives some values from
     * inherited {@code Person} class via {@code super()} method.
     * @param   firstName is the first name of a player.
     * @param   lastName is the last name of a player.
     * @param   defence shows player's defencive skills.
     * @param   attack shows player's forward skills.
     * @param   speed shows player's speed.
     * @param   strength shows player's strength.
     */
    public Player(String firstName, String lastName, int defence, int attack, int speed, int strength) {
        super(firstName, lastName);
        this.defence = defence;
        this.attack = attack;
        this.strength = strength;
        this.speed = speed;
        this.location = "school";
    }

    /**
     * Gets player's {@code defence} value.
     * @return  a positive integer value of player's defence.
     */
    public int getDefence() {
        return defence;
    }

    /**
     * Sets player's {@code defence} value.
     * @param   defence is a positive integer value of player's defence.
     */
    public void setDefence(int defence) {
        this.defence = defence;
    }

    /**
     * Gets player's {@code attack} value.
     * @return  a positive integer value of player's attack.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Sets player's {@code attack} value.
     * @param    attack is a positive integer value of player's attack.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Gets player's {@code speed} value.
     * @return  a positive integer value of player's speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets player's {@code speed} value.
     * @param    speed is a positive integer value of player's speed.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets player's {@code strength} value.
     * @return  a positive integer value of player's strength.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Sets player's {@code strength} value.
     * @param    strength is a positive integer value of player's strength.
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Gets player's {@code location} value.
     * @return  a text value of player's location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets player's {@code location} value. Can takes only two values:
     * 'school' - when a player is being created, 'team' - when a player
     * is being promoted.
     * @param    location is a text value of player's location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public PlayerQuestionnaire getQuest() {
        return quest;
    }

    public void setQuest(PlayerQuestionnaire quest) {
        this.quest = quest;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Determines the rules of comparison of some other object {@code o}
     * with this one. The rules depends on the fields of {@code Player} class.
     * @param   o gives a link to an object with which it is necessary
     *          to compare this one.
     * @return  {@code true} if the object {@param o} satisfies the rules
     *          of comparison with this one, otherwise returns {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Player)) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        if (attack != player.attack) return false;
        if (defence != player.defence) return false;
        if (speed != player.speed) return false;
        if (strength != player.strength) return false;
        if (location != null ? !location.equals(player.location) : player.location != null) return false;
        return true;
    }

    /**
     * Gives a hash code value for the object according to the rules of
     * hash code generation.
     * @return  an integer value that corresponds an object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + defence;
        result = 31 * result + attack;
        result = 31 * result + strength;
        result = 31 * result + speed;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    /**
     * Gives a textual representation of the object according to
     * definite rules presented in the method.
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append(super.toString());
        sb.append("defence=").append(defence);
        sb.append(", attack=").append(attack);
        sb.append(", strength=").append(strength);
        sb.append(", speed=").append(speed);
        sb.append(", location='").append(location).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

