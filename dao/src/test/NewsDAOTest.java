import com.revotech.nsegen.dao.NewsDAO;
import com.revotech.nsegen.entities.News;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        testNews = new News("test news", "fgapon", "test content", "test url", new Date("16/04/1997"));
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
        newses = newsDAO.getEntities();
        expectedNews = newses.get(newses.size()-1);
        log.info("++++++++++++++++++++++++++++++++" + expectedNews.getId());
        Assert.assertEquals("Add news failed: title mismatch", testNews.getTitle(), expectedNews.getTitle().trim());
        Assert.assertEquals("Add news failed: content mismatch", testNews.getContent(), expectedNews.getContent());
        Assert.assertEquals("Add news failed: date mismatch", testNews.getDate(), expectedNews.getDate());
        Assert.assertEquals("Add news failed: author mismatch", testNews.getAuthor(), expectedNews.getAuthor());
        Assert.assertEquals("Add news failed: img url mismatch", testNews.getImgUrl(), expectedNews.getImgUrl());
    }

    @Test
    public void getEntities() throws Exception {

    }

    @Test
    public void deleteEntity() throws Exception {

    }

    @Test
    public void updateEntity() throws Exception {

    }

}