package orlian.metMuseum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MetMuseumDepartmentsController implements Callback<DepartmentsFeed> {

    private MetMuseumService service;
    private HashMap<String, Integer> departmentsInfo;
    String element;
    JList list;

    public MetMuseumDepartmentsController(MetMuseumService service
                                , String element
                                , JList list
                                , HashMap<String, Integer> departmentsInfo) {
        this.service = service;
        this.element = element;
        this.list = list;
        this.departmentsInfo = departmentsInfo;
    }

    public void requestDepartmentsData() {
        service.getDepartments().enqueue(this);
    }

    @Override
    public void onResponse(Call<DepartmentsFeed> call, Response<DepartmentsFeed> response) {

                ArrayList<DepartmentsFeed.DeptObject> Do = response.body().departments;
                for (int ix = 0; ix < Do.size(); ix++)
                {
                    departmentsInfo.put(Do.get(ix).displayName, Do.get(ix).departmentId);
                }
                list.setListData(departmentsInfo.keySet().toArray());

            }

    @Override
    public void onFailure(Call<DepartmentsFeed> call, Throwable t) {

    }

}



