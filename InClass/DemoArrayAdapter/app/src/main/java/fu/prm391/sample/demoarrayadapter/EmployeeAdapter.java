package fu.prm391.sample.demoarrayadapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    private List<Employee> employeeList;
    private Activity activity;

    public EmployeeAdapter(List<Employee> employeeList, Activity activity) {
        this.employeeList = employeeList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int i) {
        return employeeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txtId;
        TextView txtName;
        TextView txtAge;

        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.employee_layout,null);
            txtId = view.findViewById(R.id.txtId);
            txtName = view.findViewById(R.id.txtName);
            txtAge = view.findViewById(R.id.txtAge);

            view.setTag(R.id.txtId, txtId);
            view.setTag(R.id.txtName, txtName);
            view.setTag(R.id.txtAge, txtAge);
        } else {
            txtId = (TextView) view.getTag(R.id.txtId);
            txtName = (TextView) view.getTag(R.id.txtName);
            txtAge = (TextView) view.getTag(R.id.txtAge);
        }

        Employee employee = employeeList.get(i);
        txtId.setText(String.valueOf(employee.getId()));
        txtName.setText(employee.getName());
        txtAge.setText(String.valueOf(employee.getAge()));

        return view;
    }
}
