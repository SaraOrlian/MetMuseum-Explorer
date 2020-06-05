package orlian.metMuseum;

import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MetMuseumServiceTest {

    @Test
    public void getDepartments() throws IOException {
        //given
        MetMuseumService service = new MetMuseumServiceFactory().getInstance();

        //when
        DepartmentsFeed departments = service.getDepartments().execute().body();
        Response<DepartmentsFeed> response = service.getDepartments().execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        List<DepartmentsFeed.DeptObject> deptsObjects = departments.departments;
        assertFalse(deptsObjects.isEmpty());
        assertNotNull(deptsObjects.size());
        assertNotNull(deptsObjects.get(0).displayName);
        assertNotNull(deptsObjects.get(0).departmentId);

    }



    @Test
    public void getDepartmentSingle() throws IOException {
        //given
        MetMuseumService service = new MetMuseumServiceFactory().getInstance();

        //when
        DepartmentSingleFeed departmentSingle = service.getDepartmentSingle("1").execute().body();
        Response<DepartmentSingleFeed> response = service.getDepartmentSingle("1").execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        DepartmentSingleFeed deptSingleObject = departmentSingle;
        assertNotNull(deptSingleObject.total);
        assertNotNull(deptSingleObject.objectIDs.get(0));
        System.out.println(departmentSingle.objectIDs.size());

    }


    @Test
    public void getArticle() throws IOException {
        //given
        MetMuseumService service = new MetMuseumServiceFactory().getInstance();

        //when
        ArticleFeed articleObject = service.getArticle("501607").execute().body();
        Response<ArticleFeed> response = service.getArticle("501607").execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        ArticleFeed article = articleObject;
        assertNotNull(article.objectID);
        assertNotNull(article.objectName);
        assertNotNull(article.culture);
        assertNotNull(article.department);
        assertNotNull(article.period);
        assertNotNull(article.primaryImage);
        assertNotNull(article.title);

    }



}