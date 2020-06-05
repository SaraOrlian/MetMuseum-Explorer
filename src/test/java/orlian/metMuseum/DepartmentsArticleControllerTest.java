package orlian.metMuseum;

import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import javax.swing.*;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DepartmentsArticleControllerTest {

    @Test
    public void requestArticleNames() {
        //given
        MetMuseumService service = mock(MetMuseumService.class);
        JLabel label = mock(JLabel.class);
        MetMuseumSubFrame frame2 = mock(MetMuseumSubFrame.class);
        ArrayList<Integer> objectIDlist = new ArrayList<>();
        Call<ArticleFeed> call = mock(Call.class);
        objectIDlist.add(22055);
        doReturn(call).when(service).getArticle(String.valueOf(objectIDlist.get(0)));
        MetMuseumArticleController controller = new MetMuseumArticleController(service
                                                                                , label
                                                                                , label
                                                                                , label
                                                                                , frame2
                                                                                , objectIDlist
                                                                                , label);

        //when
        controller.requestArticleNames();

        //then
        verify(call).enqueue(any());
    }

    @Test
    public void onResponse() {
        //given
        MetMuseumService service = mock(MetMuseumService.class);
        JLabel label = mock(JLabel.class);
        MetMuseumSubFrame frame2 = mock(MetMuseumSubFrame.class);
        ArrayList<Integer> objectIDlist = new ArrayList<>();
        Call<ArticleFeed> call = mock(Call.class);
        MetMuseumArticleController controller = new MetMuseumArticleController(service
                                                                                , label
                                                                                , label
                                                                                , label
                                                                                , frame2
                                                                                , objectIDlist
                                                                                , label);

        Response<ArticleFeed> response = mock(Response.class);
        ArticleFeed feed = new ArticleFeed();
        feed.period = "title";
        feed.objectName = "name";
        feed.primaryImage = "";

        doReturn(feed).when(response).body();

        //when
        controller.onResponse(call, response);

        //then
        verify(label).setText(feed.period);
        verify(label).setText(feed.objectName);
        verify(label).setText("Image Unavailable");
        verify(label).setIcon(null);
    }
}
