import com.revotech.nsegen.dao.UserDAO;
import com.revotech.nsegen.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Revotech on 12.07.16.
 */
public class UserDAOTest {

    private User testUser;
    private Integer id;
    private User expectedUser;
    private UserDAO userDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = UserDAO.getInstance();
        testUser = new User();
        testUser.setFirstName("Ivan");
        testUser.setLastName("Иванов");
        testUser.setNickname("IvanI");
        testUser.setPassword("qwerty");
    }

    @After
    public void tearDown() throws Exception {
        userDAO.deleteEntity(id);
    }

    @Test
    public void getUserIdByNickname() throws Exception {
        id = userDAO.addEntity(testUser);
        Integer expectedId = userDAO.getUserIdByNickname(testUser.getNickname());

        Assert.assertEquals("add news failed: id mismatch", id, expectedId);

    }

    @Test
    public void addEntity() throws Exception {
        id = userDAO.addEntity(testUser);
        expectedUser = userDAO.getUserById(id);
        testUser.setId(id);
        Assert.assertEquals("add news failed: id mismatch", testUser.getId(), expectedUser.getId());
        Assert.assertEquals("add news failed: first name mismatch", testUser.getFirstName(), expectedUser.getFirstName());
        Assert.assertEquals("add news failed: last name mismatch", testUser.getLastName(), expectedUser.getLastName());
        Assert.assertEquals("add news failed: password mismatch", testUser.getPassword(), expectedUser.getPassword());
        Assert.assertEquals("add news failed: nickname mismatch", testUser.getNickname(), expectedUser.getNickname());
    }

    /*@Test
    public void getEntities() throws Exception {

    }

    @Test
    public void deleteEntity() throws Exception {

    }*/

    @Test
    public void updateEntity() throws Exception {
        id = userDAO.addEntity(testUser);
        testUser.setId(id);
        testUser.setFirstName("Change");
        testUser.setLastName("ololo");
        testUser.setPassword("rapapa");
        userDAO.updateEntity(testUser);
        expectedUser = userDAO.getUserById(id);
        Assert.assertEquals("add news failed: id mismatch", testUser.getId(), expectedUser.getId());
        Assert.assertEquals("add news failed: first name mismatch", testUser.getFirstName(), expectedUser.getFirstName());
        Assert.assertEquals("add news failed: last name mismatch", testUser.getLastName(), expectedUser.getLastName());
        Assert.assertEquals("add news failed: password mismatch", testUser.getPassword(), expectedUser.getPassword());
        Assert.assertEquals("add news failed: nickname mismatch", testUser.getNickname(), expectedUser.getNickname());
    }

}