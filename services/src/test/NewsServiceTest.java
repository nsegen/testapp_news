import com.revotech.nsegen.entities.News;
import com.revotech.nsegen.services.INewsService;
import com.revotech.nsegen.services.NewsService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by Revotech on 13.07.16.
 */
public class NewsServiceTest {

    private static final Logger log = Logger.getLogger(NewsService.class);
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private News testNews;

    private void setReq(){
        req = mock(HttpServletRequest.class);
        when(req.getParameter("content")).thenReturn(testNews.getContent());
        when(req.getParameter("title")).thenReturn(testNews.getTitle());
        when(req.getParameter("date")).thenReturn("16/04/97");
        when(req.getParameter("author")).thenReturn(testNews.getAuthor());
        when(req.getParameter("image")).thenReturn(testNews.getImgUrl());

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