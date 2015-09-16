package by.epam.hockeyschool.entity;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The {@code Coach} class is an entity-class which includes all
 * the necessary getters and setters for its fields and also overrides
 * {@code equals()}, {@code hashCode()} and {@code toString()}.
 */
public class Coach extends Person {

    private int defenceInc;
    private int attackInc;
    private String loginHash;
    private String passwordHash;
    //private boolean isOwner;

    /**
     * Default constructor of the {@code Coach} class.
     */
    public Coach() {}

    /**
     * Constructor with parameters, which receives some values from
     * inherited {@code Person} class via {@code super()} method.
     * @param   firstName is the first name of a coach.
     * @param   lastName is the last name of a coach.
     * @param   defenceInc shows the ability of a coach to train
     *          player's {@code defence} skill.
     * @param   attackInc shows the ability of a coach to train
     *          player's {@code attack} skill.
     * @param   loginHash is a hashed value of coach's login.
     * @param   passwordHash is a hashed value of coach's password.
     */
    public Coach(String firstName, String lastName, int defenceInc, int attackInc, String loginHash, String passwordHash) {
        super(firstName, lastName);
        this.defenceInc = defenceInc;
        this.attackInc = attackInc;
        this.loginHash = loginHash;
        this.passwordHash = passwordHash;
    }

    /*public boolean isOwner() {
        return isOwner;
    }*/

    /**
     * Gets coach's {@code loginHash} - hashed value of a login.
     * @return  hashed login value.
     */
    public String getLogin() {
        return loginHash;
    }

    /**
     * Sets hashed login value of a coach.
     * @param   loginHash is a hashed login value.
     */
    public void setLogin(String loginHash) {
        this.loginHash = loginHash;
    }

    /**
     * Gets coach's {@code passwordHash} - hashed value of a password.
     * @return  hashed password value.
     */
    public String getPassword() {
        return passwordHash;
    }

    /**
     * Sets hashed password value of a coach.
     * @param   passwordHash is a hashed password value.
     */
    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets coach's {@code defenceInc} - defence teaching value.
     * @return  defence teaching skill value.
     */
    public int getDefenceInc() {
        return defenceInc;
    }

    /**
     * Sets coach's {@code defenceInc} - defence teaching value.
     * @param   defenceInc is a defence teaching skill value.
     */
    public void setDefenceInc(int defenceInc) {
        this.defenceInc = defenceInc;
    }

    /**
     * Gets coach's {@code defenceInc} - attack teaching value.
     * @return  attack teaching skill value.
     */
    public int getAttackInc() {
        return attackInc;
    }

    /**
     * Sets coach's {@code attackInc} - attack teaching value.
     * @param   attackInc is a attack teaching skill value.
     */
    public void setAttackInc(int attackInc) {
        this.attackInc = attackInc;
    }

    /**
     * Determines the rules of comparison of some other object {@code o}
     * with this one. The rules depends on the fields of {@code Coach} class.
     * @param   o gives a link to an object with which it is necessary
     *          to compare this one.
     * @return  {@code true} if the object {@param o} satisfies the rules
     *          of comparison with this one, otherwise returns {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Coach)) return false;
        if (!super.equals(o)) return false;
        Coach coach = (Coach) o;
        if (attackInc != coach.attackInc) return false;
        if (defenceInc != coach.defenceInc) return false;
        if (loginHash != null ? !loginHash.equals(coach.loginHash) : coach.loginHash != null) return false;
        if (passwordHash != null ? !passwordHash.equals(coach.passwordHash) : coach.passwordHash != null) return false;
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
        result = 31 * result + defenceInc;
        result = 31 * result + attackInc;
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
        final StringBuilder sb = new StringBuilder("Coach{");
        sb.append(super.toString());
        sb.append("defenceInc=").append(defenceInc);
        sb.append(", attackInc=").append(attackInc);
        sb.append('}');
        return sb.toString();
    }
}
