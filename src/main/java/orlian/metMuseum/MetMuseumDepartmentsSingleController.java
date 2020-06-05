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

public class MetMuseumDepartmentsSingleController implements Callback<DepartmentSingleFeed> {

    private MetMuseumService service;
    private HashMap<String, Integer> departmentsInfo;
    String element;
    JList list;
    JLabel name;
    JLabel title;
    JLabel imageLabel;
    MetMuseumMainFrame frame;
    MetMuseumSubFrame frame2;
    MetMuseumArticleController controller;
    ArrayList<Integer> objectIDlist;
    JLabel number;
    JLabel outOf;

    public MetMuseumDepartmentsSingleController(MetMuseumService service
                                                , String element
                                                , JList list
                                                , JLabel name
                                                , JLabel title
                                                , JLabel imageLabel
                                                , MetMuseumMainFrame frame
                                                , MetMuseumSubFrame frame2
                                                , MetMuseumArticleController controller
                                                , ArrayList<Integer> objectIDlist
                                                , JLabel number
                                                , JLabel outOf
                                                , HashMap<String, Integer> departmentsInfo) {
        this.service = service;
        this.element = element;
        this.list = list;
        this.name = name;
        this.title = title;
        this.imageLabel = imageLabel;
        this.frame = frame;
        this.frame2 = frame2;
        this.controller = controller;
        this.objectIDlist = objectIDlist;
        this.number = number;
        this.outOf = outOf;
        this.departmentsInfo = departmentsInfo;
    }


    public void requestDepartmentSingleData() {
        service.getDepartmentSingle(Integer.toString(departmentsInfo.get(element))).enqueue(this); }

    @Override
    public void onResponse(Call<DepartmentSingleFeed> call, Response<DepartmentSingleFeed> response) {

        int total = response.body().total;
        frame2.setTotal(total);
        System.out.println("Total objects in dept: " + total);
        outOf.setText("Total objects in Department: " + total);
        objectIDlist.addAll(response.body().objectIDs);
        controller.requestArticleNames();
    }

    @Override
    public void onFailure(Call<DepartmentSingleFeed> call, Throwable t) {

    }


    }






