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


    public void requestDepartmentSingleData(String element, MetMuseumMainFrame frame, MetMuseumSubFrame frame2, JLabel name, JLabel title, JLabel imageLabel, ArrayList<Integer> objectIDlist, JLabel outOf, JLabel number) {
        service.getDepartmentSingle(Integer.toString(departmentsInfo.get(element))).enqueue(new Callback<DepartmentSingleObject>() {
            @Override
            public void onResponse(Call<DepartmentSingleObject> call, Response<DepartmentSingleObject> response) {


                int total = response.body().total;
                frame2.setTotal(total);
                System.out.println("Total objects in dept: " + total);
                outOf.setText("Total objects in Department: " + total);
                objectIDlist.addAll(response.body().objectIDs);
                requestArticleNames(name, title, imageLabel, frame, frame2, objectIDlist, number);
            }

            @Override
            public void onFailure(Call<DepartmentSingleObject> call, Throwable t) {

            }
        });

    }


    public void requestArticleNames(JLabel name, JLabel title, JLabel imageLabel, MetMuseumMainFrame frame, MetMuseumSubFrame frame2, ArrayList<Integer> objectIDlist, JLabel number) {
        service.getArticle(String.valueOf(objectIDlist.get(frame2.getCounter()))).enqueue(new Callback<ArticleObject>() {
            @Override
            public void onResponse(Call<ArticleObject> call, Response<ArticleObject> response) {

                title.setText(response.body().period);
                name.setText(response.body().objectName);

                System.out.println(response.body().title);
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

