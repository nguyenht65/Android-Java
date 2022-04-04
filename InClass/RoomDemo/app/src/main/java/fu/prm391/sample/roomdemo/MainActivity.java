package fu.prm391.sample.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import fu.prm391.sample.roomdemo.dao.StudentDAO;
import fu.prm391.sample.roomdemo.database.AppDatabase;
import fu.prm391.sample.roomdemo.entity.Student;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    private EditText etName, etAge, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = Room.databaseBuilder(MainActivity.this, AppDatabase.class, "student.db").allowMainThreadQueries().build();
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etAge = findViewById(R.id.etAge);
    }

    public void insert(View view) {
        StudentDAO studentDAO = appDatabase.studentDAO();
        Student student = new Student();
        student.name = etName.getText().toString();
        student.age = Integer.parseInt(etAge.getText().toString());
        student.address = etAddress.getText().toString();
        studentDAO.insert(student);
    }


    public void show(View view) {
        StudentDAO studentDAO = appDatabase.studentDAO();
        List<Student> users = studentDAO.getAllStudent();
        for (Student student : users) {
            Toast.makeText(MainActivity.this, student.name, Toast.LENGTH_SHORT).show();
        }
    }
}