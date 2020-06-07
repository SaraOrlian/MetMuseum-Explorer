package orlian.metMuseum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MetMuseumArticleController implements Callback<ArticleFeed> {

    private MetMuseumService service;
    private JLabel name;
    private JLabel title;
    private JLabel imageLabel;
    private MetMuseumSubFrame frame2;
    private ArrayList<Integer> objectIDlist;
    private JLabel number;

    public MetMuseumArticleController(MetMuseumService service
            , JLabel name
            , JLabel title
            , JLabel imageLabel
            , MetMuseumSubFrame frame2
            , ArrayList<Integer> objectIDlist
            , JLabel number) {
        this.service = service;
        this.name = name;
        this.title = title;
        this.imageLabel = imageLabel;
        this.frame2 = frame2;
        this.objectIDlist = objectIDlist;
        this.number = number;
    }

    public void requestArticleNames() {
        service.getArticle(String.valueOf(objectIDlist.get(frame2.getCounter()))).enqueue(this); }

    @Override
    public void onResponse(Call<ArticleFeed> call, Response<ArticleFeed> response) {

        title.setText(response.body().period);
        name.setText(response.body().objectName);

        System.out.println(response.body().period);
        System.out.println(response.body().objectName);
        System.out.println(response.body().primaryImage);

        if(frame2.getCounter() == 0) {
            number.setText("First Object ID in this department: " + response.body().objectID);
        }else {
            number.setText("Object ID: " + response.body().objectID);
        }

        if (response.body().primaryImage.equals("")) {
            imageLabel.setIcon(null);
            imageLabel.setText("Image Unavailable");
        } else {
            URL url = null;
            try {

                url = new URL(response.body().primaryImage);

            } catch(MalformedURLException e){
                e.printStackTrace();
            }
            Image image = null;
            try {
                image = ImageIO.read(url);
            } catch (IOException e) {
                imageLabel.setIcon(null);
                imageLabel.setText("Error during retrieval of Image");
                e.printStackTrace();
            }
            imageLabel.setIcon(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        }

    }

    @Override
    public void onFailure(Call<ArticleFeed> call, Throwable t) {
        title.setText("");
        name.setText("Error occurred when retrieving article data");
        imageLabel.setIcon(null);
        imageLabel.setText("");
    }


}
