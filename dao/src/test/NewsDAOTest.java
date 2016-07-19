import com.revotech.nsegen.dao.NewsDAO;
import com.revotech.nsegen.entities.News;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * Created by Revotech on 08.07.16.
 */
public class NewsDAOTest {

    private static final Logger log = Logger.getLogger(NewsDAOTest.class);

    private NewsDAO newsDAO;
    private News testNews;
    private News expectedNews;
    private List<News> newses;

    @Before
    public void setUp() throws Exception {
        newsDAO = NewsDAO.getInstance();
        testNews = new News();
        testNews.setTitle("Тест добавления");
        testNews.setAuthor("fgapon");
        testNews.setContent("ололол");
        testNews.setImgUrl("test url");
        testNews.setDate(LocalDate.of(1997, 4 ,16));
    }

    @After
    public void tearDown() throws Exception {
        newsDAO.deleteEntity(testNews.getId());
    }

    @Test
    public void addEntity() throws Exception {
        NewsDAO newsDAO = NewsDAO.getInstance();
        Integer id = newsDAO.addEntity(testNews);
        testNews.setId(id);
        expectedNews = newsDAO.getNewsById(id);
        Assert.assertEquals("News don't add: id == null", (testNews.getId() != null), true);
        Assert.assertEquals("Add news failed: title mismatch", testNews.getTitle(), expectedNews.getTitle());
        Assert.assertEquals("Add news failed: content mismatch", testNews.getContent(), expectedNews.getContent());
        Assert.assertEquals("Add news failed: date mismatch", testNews.getDate(), expectedNews.getDate());
        Assert.assertEquals("Add news failed: author mismatch", testNews.getAuthor(), expectedNews.getAuthor());
        Assert.assertEquals("Add news failed: img url mismatch", testNews.getImgUrl(), expectedNews.getImgUrl());
    }

    @Test
    public void getNewsByID() throws Exception{
        NewsDAO newsDAO = NewsDAO.getInstance();
        Integer id = newsDAO.addEntity(testNews);
        testNews.setId(id);
        expectedNews = newsDAO.getNewsById(id);
        Assert.assertEquals("get news by id news failed: id mismatch", testNews.getId(), expectedNews.getId());
        Assert.assertEquals("get news by id news failed: title mismatch", testNews.getTitle(), expectedNews.getTitle().trim());
        Assert.assertEquals("get news by id news failed: content mismatch", testNews.getContent(), expectedNews.getContent());
        Assert.assertEquals("get news by id news failed: date mismatch", testNews.getDate(), expectedNews.getDate());
        Assert.assertEquals("get news by id news failed: author mismatch", testNews.getAuthor(), expectedNews.getAuthor());
        Assert.assertEquals("get news by id news failed: img url mismatch", testNews.getImgUrl(), expectedNews.getImgUrl());
    }


    /*@Test
    public void getEntities() throws Exception {

    }

    @Test
    public void deleteEntity() throws Exception {

    }*/

    @Test
    public void updateEntity() throws Exception {
        NewsDAO newsDAO = NewsDAO.getInstance();
        Integer id = newsDAO.addEntity(testNews);
        testNews.setId(id);
        testNews.setTitle("Change");
        testNews.setContent("ololo");
        testNews.setContent("rapapa");
        newsDAO.updateEntity(testNews);
        expectedNews = newsDAO.getNewsById(id);
        Assert.assertEquals("update news failed: id mismatch", testNews.getId(), expectedNews.getId());
        Assert.assertEquals("update news failed: title mismatch", testNews.getTitle(), expectedNews.getTitle().trim());
        Assert.assertEquals("update news failed: content mismatch", testNews.getContent(), expectedNews.getContent());
        Assert.assertEquals("update news failed: date mismatch", testNews.getDate(), expectedNews.getDate());
        Assert.assertEquals("update news failed: author mismatch", testNews.getAuthor(), expectedNews.getAuthor());
        Assert.assertEquals("update news failed: img url mismatch", testNews.getImgUrl(), expectedNews.getImgUrl());

    }

}