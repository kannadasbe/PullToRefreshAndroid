package com.example.kannadasang.pulltorefresh;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    List<Employee> employees=new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_employee);
        setTitle("Employee List");
        setEmployeeList();
        recyclerView=(RecyclerView) findViewById(R.id.empRecycler);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        EmployeeAdapter empAdapter= new EmployeeAdapter(employees);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(empAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        fetchContent();
                                    }
                                }
        );

    }
    private void setEmployeeList()
    {
        employees.add(new Employee("Employee1","Tech Lead","Male","2001"));
        employees.add(new Employee("Employee2","Designer","Female","2002"));
        employees.add(new Employee("Employee3","Tester","Male","2005"));
        employees.add(new Employee("Employee4","Developer","Female","2009"));
        employees.add(new Employee("Employee5","Team Lead","Male","2011"));

    }

    @Override
    public void onRefresh() {
        fetchContent();
    }

    private void fetchContent() {
        Toast.makeText(this,"Data Refreshed",Toast.LENGTH_SHORT).show();
        // Here your actual code will come to update recycler view.
        swipeRefreshLayout.setRefreshing(false);
    }

}
