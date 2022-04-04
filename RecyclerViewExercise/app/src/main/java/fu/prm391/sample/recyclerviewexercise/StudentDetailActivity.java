package fu.prm391.sample.recyclerviewexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StudentDetailActivity extends AppCompatActivity {

    private TextView txtRollNumber;
    private TextView txtName;
    private TextView txtAddress;
    private TextView txtGpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        txtRollNumber = findViewById(R.id.txtRollNumber);
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtGpa = findViewById(R.id.txtGpa);
        Intent intent = getIntent();
        txtRollNumber.setText(intent.getStringExtra("rollNumber"));
        txtName.setText(intent.getStringExtra("name"));
        txtAddress.setText(intent.getStringExtra("address"));
        double gpa = intent.getDoubleExtra("gpa", 0.0);
        txtGpa.setText(String.valueOf(gpa));
    }
}