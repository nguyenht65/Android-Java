package fu.prm391.sample.recyclerviewexercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        students = new ArrayList<>();
        students.add(new Student("ID1","Nguyen Van A", "VietNam", 3.7));
        students.add(new Student("ID2","Nguyen Van B", "VietNam", 3.5));
        students.add(new Student("ID3","Nguyen Van C", "VietNam", 2.7));
        students.add(new Student("ID4","Nguyen Van D", "VietNam", 1.7));
        students.add(new Student("ID5","Nguyen Van E", "VietNam", 3.1));
        students.add(new Student("ID6","Nguyen Van A", "VietNam", 3.2));
        students.add(new Student("ID7","Nguyen Van A", "VietNam", 2.5));
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

        StudentListAdapter studentListAdapter = new StudentListAdapter(MainActivity.this, students);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentListAdapter);
    }
}