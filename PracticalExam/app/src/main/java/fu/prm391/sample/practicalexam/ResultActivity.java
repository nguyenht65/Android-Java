package fu.prm391.sample.practicalexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        myDatabase = Room.databaseBuilder(ResultActivity.this, MyDatabase.class, "myHistory.db")
                .allowMainThreadQueries()
                .build();
        HistoryDAO historyDAO = myDatabase.createHistoryDAO();
        List<History> histories = historyDAO.getHistories();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new HistoryListAdapter(ResultActivity.this, histories));
        LinearLayoutManager layoutManager = new LinearLayoutManager(ResultActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}