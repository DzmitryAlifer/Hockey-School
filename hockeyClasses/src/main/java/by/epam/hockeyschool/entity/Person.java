package by.epam.hockeyschool.entity;

/**
 * Created by Dmitry Olifer on 18.06.2015.
 *
 * The {@code Person} class is an entity-class which includes all
 * the necessary getters and setters for its fields and also overrides
 * {@code equals()}, {@code hashCode()} and {@code toString()}.
 */
public class Person {

    private int id;
    private String firstName;
    private String lastName;

    /**
     * Default constructor of the {@code Person} class.
     */
    public Person() {}

    /**
     * Constructor with parameters, which is used by its child classes.
     * @param   firstName is the first name of a person.
     * @param   lastName is the last name of a person.
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets person's {@code id}.
     * @return  a positive integer of player's {@code id}.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets person's {@code id}.
     * @param   id is a positive integer of player's {@code id}.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets person's first name.
     * @return  a string value of player's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets person's first name.
     * @param   firstName is a string value of player's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets person's last name.
     * @return  a string value of player's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets person's last name.
     * @param   lastName is a string value of player's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Determines the rules of comparison of some other object {@code o}
     * with this one. The rules depends on the fields of {@code Person} class.
     * @param   o gives a link to an object with which it is necessary
     *          to compare this one.
     * @return  {@code true} if the object {@param o} satisfies the rules
     *          of comparison with this one, otherwise returns {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        if (id != person.id) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        return true;
    }

    /**
     * Gives a hash code value for the object according to the rules of
     * hash code generation.
     * @return  an integer value that corresponds an object.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    /**
     * Gives a textual representation of the object according to
     * definite rules presented in the method.
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
