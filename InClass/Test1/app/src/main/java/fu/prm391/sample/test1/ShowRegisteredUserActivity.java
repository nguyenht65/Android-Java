package fu.prm391.sample.test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowRegisteredUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_registered_user);
        recyclerView = findViewById(R.id.recyclerView);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        users = new ArrayList<>();
        Intent intent = getIntent();
        users = (ArrayList<User>) intent.getSerializableExtra("listUser");
        LinearLayoutManager layoutManager = new LinearLayoutManager(ShowRegisteredUserActivity.this, LinearLayoutManager.VERTICAL, false);
        UserListAdapter userListAdapter = new UserListAdapter(ShowRegisteredUserActivity.this, users);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userListAdapter);
    }
}