package orlian.metMuseum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MetMuseumDepartmentsSingleController implements Callback<DepartmentSingleFeed> {

    private MetMuseumService service;
    private HashMap<String, Integer> departmentsInfo;
    private String element;
    private MetMuseumSubFrame frame2;
    private MetMuseumArticleController controller;
    private ArrayList<Integer> objectIDlist;
    private JLabel outOf;

    public MetMuseumDepartmentsSingleController(MetMuseumService service
                                                , String element
                                                , MetMuseumSubFrame frame2
                                                , MetMuseumArticleController controller
                                                , ArrayList<Integer> objectIDlist
                                                , JLabel outOf
                                                , HashMap<String, Integer> departmentsInfo) {
        this.service = service;
        this.element = element;
        this.frame2 = frame2;
        this.controller = controller;
        this.objectIDlist = objectIDlist;
        this.outOf = outOf;
        this.departmentsInfo = departmentsInfo;
    }


    public void requestDepartmentSingleData() {
        service.getDepartmentSingle(Integer.toString(departmentsInfo.get(element))).enqueue(this); }

    @Override
    public void onResponse(Call<DepartmentSingleFeed> call, Response<DepartmentSingleFeed> response) {

        if (response.body() != null) {
            frame2.setTotal(response.body().total);
        }
        System.out.println("Total objects in dept: " + frame2.getTotal());
        outOf.setText("Total objects in Department: " + frame2.getTotal());
        if (response.body() != null) {
            objectIDlist.addAll(response.body().objectIDs);
        }
        controller.requestArticleNames();
    }

    @Override
    public void onFailure(Call<DepartmentSingleFeed> call, Throwable t) {

    }


    }






