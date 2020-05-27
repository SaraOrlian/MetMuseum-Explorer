package orlian.metMuseum;

import org.junit.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MetMuseumServiceTest {

    @Test
    public void getDepartments() throws IOException {
        //given
        MetMuseumService service = new MetMuseumServiceFactory().getInstance();

        //when
        DepartmentsObject departments = service.getDepartments().execute().body();
        Response<DepartmentsObject> response = service.getDepartments().execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        List<DepartmentsObject.DeptObject> deptsObjects = departments.departments;
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
        DepartmentSingleObject departmentSingle = service.getDepartmentSingle("1").execute().body();
        Response<DepartmentSingleObject> response = service.getDepartmentSingle("1").execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        DepartmentSingleObject deptSingleObject = departmentSingle;
        assertNotNull(deptSingleObject.total);
        assertNotNull(deptSingleObject.objectIDs.get(0));
        System.out.println(departmentSingle.objectIDs.size());

    }


    @Test
    public void getArticle() throws IOException {
        //given
        MetMuseumService service = new MetMuseumServiceFactory().getInstance();

        //when
        ArticleObject articleObject = service.getArticle("501607").execute().body();
        Response<ArticleObject> response = service.getArticle("501607").execute();

        //then
        assertTrue(response.toString(), response.isSuccessful());
        ArticleObject article = articleObject;
        assertNotNull(article.objectID);
        assertNotNull(article.objectName);
        assertNotNull(article.culture);
        assertNotNull(article.department);
        assertNotNull(article.period);
        assertNotNull(article.primaryImage);
        assertNotNull(article.title);

    }



}