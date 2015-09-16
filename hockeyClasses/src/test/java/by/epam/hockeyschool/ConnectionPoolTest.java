package by.epam.hockeyschool;

import by.epam.hockeyschool.database.pool.ConnectionPool;
import by.epam.hockeyschool.exception.DAOException;
import by.epam.hockeyschool.exception.TechnicalException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by demonwtf on 20.08.2015.
 */
public class ConnectionPoolTest {

    private static ConnectionPool connectionPool;

    @BeforeClass
    public static void initConnectionPool() {
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    public void getConnectionTest() throws TechnicalException, DAOException {
        for (int i = 0; i < 20; i++) {
            Assert.assertNotNull(connectionPool.getConnection());
        }
    }

    @AfterClass
    public static void cleanUpConnectionPool() throws DAOException {
        connectionPool.cleanUp();
    }
}
