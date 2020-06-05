package orlian.metMuseum;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MetMuseumDepartmentsSingleControllerTest {

    @Test
    public void requestDepartmentSingleData() {
        //given
        MetMuseumService service = mock(MetMuseumService.class);
        String element = "Arms and Armour";
        JList list = mock(JList.class);
        JLabel label = mock(JLabel.class);
        MetMuseumMainFrame frame = mock(MetMuseumMainFrame.class);
        MetMuseumSubFrame frame2 = mock(MetMuseumSubFrame.class);
        HashMap<String, Integer> departmentsInfo = new HashMap<>();
        ArrayList<Integer> objectIDlist = new ArrayList<>();
        departmentsInfo.put(element, 4);
        MetMuseumArticleController articleController = mock(MetMuseumArticleController.class);
        Call<DepartmentsFeed> call = mock(Call.class);
        doReturn(call).when(service).getDepartmentSingle(Integer.toString(departmentsInfo.get(element)));
        MetMuseumDepartmentsSingleController controller = new MetMuseumDepartmentsSingleController(service
                                                                                                    , element
                                                                                                    , list
                                                                                                    , label
                                                                                                    , label
                                                                                                    , label
                                                                                                    , frame
                                                                                                    , frame2
                                                                                                    , articleController
                                                                                                    , objectIDlist
                                                                                                    , label
                                                                                                    , label
                                                                                                    , departmentsInfo);


        //when
        controller.requestDepartmentSingleData();

        //then
        verify(call).enqueue(any());
    }


    @Test
    public void onResponse() {
        //given
        MetMuseumService service = mock(MetMuseumService.class);
        String element = "Arms and Armour";
        JList list = mock(JList.class);
        JLabel label = mock(JLabel.class);
        MetMuseumMainFrame frame = mock(MetMuseumMainFrame.class);
        MetMuseumSubFrame frame2 = mock(MetMuseumSubFrame.class);
        HashMap<String, Integer> departmentsInfo = new HashMap<>();
        ArrayList<Integer> objectIDlist_ = mock(ArrayList.class);
        objectIDlist_.add(21986);
        departmentsInfo.put(element, 4);
        MetMuseumArticleController articleController = mock(MetMuseumArticleController.class);
        Call<DepartmentSingleFeed> call = mock(Call.class);
        MetMuseumDepartmentsSingleController controller = new MetMuseumDepartmentsSingleController(service
                                                                                                    , element
                                                                                                    , list
                                                                                                    , label
                                                                                                    , label
                                                                                                    , label
                                                                                                    , frame
                                                                                                    , frame2
                                                                                                    , articleController
                                                                                                    , objectIDlist_
                                                                                                    , label
                                                                                                    , label
                                                                                                    , departmentsInfo);


        Response<DepartmentSingleFeed> response = mock(Response.class);
        DepartmentSingleFeed feed = new DepartmentSingleFeed();
        feed.objectIDs = objectIDlist_;
        doReturn(frame2).when(response).body();
        doReturn(feed).when(response).body();


        //when
        controller.onResponse(call, response);

        //then
        verify(frame2).setTotal(feed.total);
        verify(objectIDlist_).addAll(feed.objectIDs);
    }


}
