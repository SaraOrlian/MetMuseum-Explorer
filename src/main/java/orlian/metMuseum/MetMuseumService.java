package orlian.metMuseum;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.ArrayList;

public interface MetMuseumService {

    //List of Departments https://collectionapi.metmuseum.org/public/collection/v1/departments
    @GET("/public/collection/v1/departments")
    Call<DepartmentsObject> getDepartments();

    //List of Objects within a Department https://collectionapi.metmuseum.org/public/collection/v1/objects?departmentIds=18
    @GET("/public/collection/v1/objects?")
    Call<DepartmentSingleObject> getDepartmentSingle(@Query("departmentIds") String departmentId);

    //Metadata about one Object https://collectionapi.metmuseum.org/public/collection/v1/objects/501607
    @GET("/public/collection/v1/objects/{articleNum}")
    Call<ArticleObject> getArticle(@Path("articleNum") String articleNum);

}
