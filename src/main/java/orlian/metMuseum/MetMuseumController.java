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
import java.util.HashMap;

public class MetMuseumController {

    private MetMuseumService service;
    private HashMap<String, Integer> departmentsInfo = new HashMap<String, Integer>();
    private String id;

    public MetMuseumController(MetMuseumService service) {
        this.service = service;
    }

    public void requestDepartmentsData(JList list) {
        service.getDepartments().enqueue(new Callback<DepartmentsObject>() {
            @Override
            public void onResponse(Call<DepartmentsObject> call, Response<DepartmentsObject> response) {


                ArrayList<DepartmentsObject.DeptObject> Do = response.body().departments;
                for (int ix = 0; ix < Do.size(); ix++)
                {
                    departmentsInfo.put(Do.get(ix).displayName, Do.get(ix).departmentId);
                }
                list.setListData(departmentsInfo.keySet().toArray());

            }

            @Override
            public void onFailure(Call<DepartmentsObject> call, Throwable t) {

            }
        });
    }


    public void requestDepartmentSingleData(String element, MetMuseumMainFrame frame, MetMuseumSubFrame frame2, JLabel name, JLabel title, JLabel imageLabel) {
        int elementID = departmentsInfo.get(element);
        id = Integer.toString(elementID);
        System.out.println(id);
        service.getDepartmentSingle(id).enqueue(new Callback<DepartmentSingleObject>() {
            @Override
            public void onResponse(Call<DepartmentSingleObject> call, Response<DepartmentSingleObject> response) {

                frame2.setTotal(response.body().total);
                int fo = response.body().objectIDs.get(0);
                frame.setFirstObject(Integer.toString(fo));
                requestArticleNames(name, title, imageLabel, frame);
            }

            @Override
            public void onFailure(Call<DepartmentSingleObject> call, Throwable t) {

            }
        });

    }


    public void requestArticleNames(JLabel name, JLabel title, JLabel imageLabel, MetMuseumMainFrame frame) {
        service.getArticle(frame.getFirstObject()).enqueue(new Callback<ArticleObject>() {
            @Override
            public void onResponse(Call<ArticleObject> call, Response<ArticleObject> response) {

                title.setText(response.body().period);
                System.out.println(response.body().title);
                name.setText(response.body().objectName);
                System.out.println(response.body().objectName);
                System.out.println(response.body().primaryImage);
                if (response.body().primaryImage.equals("")) {
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
                    e.printStackTrace();
                }
                imageLabel.setIcon(new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            }


            }

            @Override
            public void onFailure(Call<ArticleObject> call, Throwable t) {
            }
        });

    }


    }

