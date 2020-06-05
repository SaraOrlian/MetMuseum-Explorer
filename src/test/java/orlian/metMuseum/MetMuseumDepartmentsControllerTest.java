package orlian.metMuseum;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class MetMuseumDepartmentsControllerTest {

    @Test
    public void requestDepartmentsData() {
        //given
        MetMuseumService service = mock(MetMuseumService.class);
        String element = "Arms and Armour";
        JList list = mock(JList.class);
        HashMap<String, Integer> departmentsInfo = mock(HashMap.class);
        Call<DepartmentsFeed> call = mock(Call.class);
        doReturn(call).when(service).getDepartments();
        MetMuseumDepartmentsController controller = new MetMuseumDepartmentsController(service, element, list, departmentsInfo);

        //when
        controller.requestDepartmentsData();

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void onResponse() {
        //given
        MetMuseumService service = mock(MetMuseumService.class);
        String element = "Arms and Armour";
        JList list = mock(JList.class);
        HashMap<String, Integer> departmentsInfo = new HashMap<>();
        MetMuseumDepartmentsController controller = new MetMuseumDepartmentsController(service, element, list, departmentsInfo);
        Call<DepartmentsFeed> call = mock(Call.class);
        Response<DepartmentsFeed> response = mock(Response.class);

        DepartmentsFeed feed = new DepartmentsFeed();
        DepartmentsFeed.DeptObject deptObject = new DepartmentsFeed.DeptObject();
        deptObject.displayName = "DeptName";
        feed.departments.add(0, deptObject);

        doReturn(feed).when(response).body();

        //when
        controller.onResponse(call, response);

        //then
        verify(list).setListData(departmentsInfo.keySet().toArray());
    }

}