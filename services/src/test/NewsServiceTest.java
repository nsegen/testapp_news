import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.services.INewsService;
import com.revotech.nsegen.services.NewsService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by Revotech on 13.07.16.
 */
public class NewsServiceTest {

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private News testNews;

    private void setReq(){
        req = mock(HttpServletRequest.class);
        when(req.getParameter())
    }

    @Before
    public void beforeDo(){
        resp = mock(HttpServletResponse.class);
        testNews.setId(1);
        testNews.setTitle("test news title");
        testNews.setContent("test news content");
        testNews.setDate(new Date("16/04/97"));
        testNews.setAuthor("nsegen");
        testNews.setImgUrl("image test url");
    }

    @Test
    public void getListOfNews() throws Exception {
        INewsService newsService = NewsService.getInstance();

    }

    @Test
    public void editNews() throws Exception {

    }

    @Test
    public void deleteNews() throws Exception {

    }

    @Test
    public void addNews() throws Exception {

    }

}