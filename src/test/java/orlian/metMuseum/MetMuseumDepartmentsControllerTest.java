package orlian.metMuseum;

import org.junit.Test;
import retrofit2.Call;

import javax.swing.*;

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

}