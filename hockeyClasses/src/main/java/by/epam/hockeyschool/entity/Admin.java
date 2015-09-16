package by.epam.hockeyschool.entity;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The {@code Admin} class is an entity-class which includes all
 * the necessary getters and setters for its fields and also overrides
 * {@code equals()}, {@code hashCode()} and {@code toString()}.
 */
public class Admin extends Person {

    private String loginHash;
    private String passwordHash;

    /**
     * Default constructor of the {@code Admin} class.
     */
    public Admin () {}

    /**
     * Constructor with parameters, which receives some values from
     * inherited {@code Person} class via {@code super()} method.
     * @param   firstName is the first name of an administrator.
     * @param   lastName is the last name of an administrator.
     * @param   loginHash is a hashed value of administrator's login.
     * @param   passwordHash is a hashed value of administrator's password.
     */
    public Admin(String firstName, String lastName, String loginHash, String passwordHash) {
        super(firstName, lastName);
        this.loginHash = loginHash;
        this.passwordHash = passwordHash;
    }

    /**
     * Gets administrator's {@code loginHash} - hashed value of a login.
     * @return  hashed login value.
     */
    public String getLogin() {
        return loginHash;
    }

    /**
     * Sets hashed login value of an administrator.
     * @param   loginHash is a hashed login value.
     */
    public void setLogin(String loginHash) {
        this.loginHash = loginHash;
    }

    /**
     * Gets administrator's {@code passwordHash} - hashed value of a password.
     * @return  hashed password value.
     */
    public String getPassword() {
        return passwordHash;
    }

    /**
     * Sets hashed password value of an administrator.
     * @param   passwordHash is a hashed password value.
     */
    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Determines the rules of comparison of some other object {@code o}
     * with this one. The rules depends on the fields of {@code Admin} class.
     * @param   o gives a link to an object with which it is necessary
     *          to compare this one.
     * @return  {@code true} if the object {@param o} satisfies the rules
     *          of comparison with this one, otherwise returns {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Admin)) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        if (loginHash != null ? !loginHash.equals(admin.loginHash) : admin.loginHash != null) return false;
        if (passwordHash != null ? !passwordHash.equals(admin.passwordHash) : admin.passwordHash != null) return false;
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
        final StringBuilder sb = new StringBuilder("Admin{");
        sb.append(super.toString());
        sb.append(", loginHash='");
        sb.append(loginHash);
        sb.append("'}");
        return sb.toString();
    }
}
