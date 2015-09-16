package by.epam.hockeyschool;

import by.epam.hockeyschool.database.dao.PersonDAOImpl;
import by.epam.hockeyschool.entity.Person;
import org.junit.Assert;
import org.junit.Test;

public class PersonDAOTest {

    private Person getTestPerson() {
        Person person = new Person();
        person.setFirstName("testFirstName");
        person.setLastName("testLastName");
        return person;
    }

    @Test
    public void testGet() throws Exception {
        Person testPerson = getTestPerson();
        PersonDAOImpl personDAO = new PersonDAOImpl();
        personDAO.addPerson(testPerson);
        int id = personDAO.getNewestId();
        testPerson.setId(id);
        Person person = personDAO.getPerson(id);
        Assert.assertEquals("Compare persons", testPerson, person);
        personDAO.deletePerson(id);
    }
}
