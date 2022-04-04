package fu.prm391.sample.demoarrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //view
    Spinner spinner;

    //model
    private List<Employee> employeeList;

    //adapter
    private ArrayAdapter<Employee> employeeArrayAdapter;

    //custom adapter
    private EmployeeAdapter employeeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Nguyen Van A", 10));
        employeeList.add(new Employee(2, "Nguyen Van D", 20));
        employeeList.add(new Employee(3, "Bui Van B", 15));
        employeeList.add(new Employee(4, "Tran Van E", 30));
        employeeList.add(new Employee(5, "Hoang Van F", 5));

//        employeeArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, employeeList);

        employeeAdapter = new EmployeeAdapter(employeeList, this);
        spinner.setAdapter(employeeAdapter);

    }
}