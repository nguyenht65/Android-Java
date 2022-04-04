package fu.prm391.sample.test1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnShow;
    private ArrayList<User> users;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRegister = findViewById(R.id.btnRegister);
        btnShow = findViewById(R.id.btnShow);
        users = new ArrayList<>();
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == 200) {
                            Intent data = result.getData();
                            String name = data.getStringExtra("name");
                            Boolean gender = data.getBooleanExtra("gender", true);
                            String email = data.getStringExtra("email");
                            String phone = data.getStringExtra("phone");
                            String address = data.getStringExtra("address");
                            User user = new User(name, gender, email, phone, address);
                            users.add(user);
                            Toast.makeText(getBaseContext(), "Add user successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
                launcher.launch(intent);
            }
        });


        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowRegisteredUserActivity.class);
                intent.putExtra("listUser", (Serializable) users);
                startActivity(intent);
            }
        });
    }

}