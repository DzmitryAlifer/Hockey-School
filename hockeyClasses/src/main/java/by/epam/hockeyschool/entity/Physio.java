package by.epam.hockeyschool.entity;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The {@code Physio} class is an entity-class which includes all
 * the necessary getters and setters for its fields and also overrides
 * {@code equals()}, {@code hashCode()} and {@code toString()}.
 */
public class Physio extends Person {

    private int speedInc;
    private int strengthInc;
    private String loginHash;
    private String passwordHash;

    /**
     * Default constructor of the {@code Physio} class.
     */
    public Physio() {}

    /**
     * Constructor with parameters, which receives some values from
     * inherited {@code Person} class via {@code super()} method.
     * @param   firstName is the first name of a physiotherapist.
     * @param   lastName is the last name of a physiotherapist.
     * @param   speedInc  shows the ability of a physiotherapist to
     *          train player's {@code speed} skill.
     * @param   strengthInc shows the ability of a physiotherapist to
     *          train player's {@code strength} skill.
     */
    public Physio(String firstName, String lastName, int speedInc, int strengthInc, String loginHash, String passwordHash) {
        super(firstName, lastName);
        this.speedInc = speedInc;
        this.strengthInc = strengthInc;
        this.loginHash = loginHash;
        this.passwordHash = passwordHash;
    }

    /**
     * Gets physiotherapist's {@code speedInc} - speed teaching value.
     * @return  speed teaching skill value.
     */
    public int getSpeedInc() {
        return speedInc;
    }

    /**
     * Sets physiotherapist's {@code speedInc} - speed teaching value.
     * @param   speedInc is a speed teaching skill value.
     */
    public void setSpeedInc(int speedInc) {
        this.speedInc = speedInc;
    }

    /**
     * Gets physiotherapist's {@code strengthInc} - strength teaching value.
     * @return  strength teaching skill value.
     */
    public int getStrengthInc() {
        return strengthInc;
    }

    /**
     * Sets physiotherapist's {@code strengthInc} - strength teaching value.
     * @param   strengthInc is a strength teaching skill value.
     */
    public void setStrengthInc(int strengthInc) {
        this.strengthInc = strengthInc;
    }

    public String getLoginHash() {
        return loginHash;
    }

    public void setLoginHash(String loginHash) {
        this.loginHash = loginHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Determines the rules of comparison of some other object {@code o}
     * with this one. The rules depends on the fields of {@code Physio} class.
     * @param   o gives a link to an object with which it is necessary
     *          to compare this one.
     * @return  {@code true} if the object {@param o} satisfies the rules
     *          of comparison with this one, otherwise returns {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Physio)) return false;
        if (!super.equals(o)) return false;
        Physio physio = (Physio) o;
        if (speedInc != physio.speedInc) return false;
        if (strengthInc != physio.strengthInc) return false;
        if (loginHash != null ? !loginHash.equals(physio.loginHash) : physio.loginHash != null) return false;
        if (passwordHash != null ? !passwordHash.equals(physio.passwordHash) : physio.passwordHash != null)
            return false;

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
        result = 31 * result + speedInc;
        result = 31 * result + strengthInc;
        result = 31 * result + (loginHash != null ? loginHash.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        return result;
    }

    /**
     * Gives a textual representation of the object according to
     * definite rules presented in the method.
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Physio{");
        sb.append(super.toString());
        sb.append("speedInc=").append(speedInc);
        sb.append(", strengthInc=").append(strengthInc);
        sb.append('}');
        return sb.toString();
    }
}
