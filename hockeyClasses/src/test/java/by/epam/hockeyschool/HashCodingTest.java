package by.epam.hockeyschool;

import by.epam.hockeyschool.validator.HashCoder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by demonwtf on 20.08.2015.
 */
public class HashCodingTest {

    private HashCoder hashCoder;

    @Before
    public void init() {
        hashCoder = new HashCoder();
    }

    @After
    public void clear() {
        hashCoder = null;
    }

    @Test
    public void doHashTest() {
        String line = hashCoder.createHashedValue("BlackSabbath");
        Assert.assertNotNull(line);
    }
}
