package com.subash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.subash.api.EmployeeAPI;
import com.subash.api.R;
import com.subash.model.Employee;
import com.subash.url.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvData = findViewById(R.id.tvText);
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployee();
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()){
                    tvData.setText("Code :"+response.code());
                    return;
                }
                List<Employee> employeeList = response.body();
                for (Employee employee : employeeList){
                    String content = "";
                    content += "ID : "+ employee.getid()+"\n";
                    content += "Employee Name : "+employee.getEmployee_name()+"\n";
                    content += "Employee Salary : "+ employee.getEmployee_salary()+"\n";
                    content += "Employee Age : "+employee.getEmployee_age()+"\n";
                    content += "Profile Image : "+employee.getProfile_image()+"\n";
                    content += "*************************************************** : "+employee.getProfile_image()+"\n";

                    tvData.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                tvData.setText("Error " + t.getMessage());
            }
        });
    }

//        Retrofit retrofit= new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
//
//        Call<List<Employee>> listCall= employeeAPI.getAllEmployee();
//
//        ListCall.enqueue(new Callback<List><Employee>>(){
//
//        }
//        public void onResponse
//
//    }
}

