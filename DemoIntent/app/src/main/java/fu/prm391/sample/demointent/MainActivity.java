package fu.prm391.sample.demointent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText editTextURL;
    private Button btnGo;
    private Button btnGoResult;

    private Button btnChangeColor;
    private RadioButton btnRed;
    private RadioButton btnGreen;
    private RadioButton btnBlue;
    private RadioButton btnYellow;

    ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextURL = findViewById(R.id.editTextURL);
        btnGo = findViewById(R.id.btnGo);
        btnGoResult = findViewById(R.id.btnGoResult);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextURL.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        btnGoResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editTextURL.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("name", text);
                startActivity(intent);
            }
        });

        btnChangeColor = findViewById(R.id.btnChangeColor);
        btnRed = findViewById(R.id.radioButtonRed);
        btnGreen = findViewById(R.id.radioButtonGreen);
        btnYellow = findViewById(R.id.radioButtonYellow);
        btnBlue = findViewById(R.id.radioButtonBlue);
        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, backgroundChanged.class);
                int color;
                if (btnRed.isChecked()) {
                    color = Color.RED;
                } else if (btnGreen.isChecked()) {
                    color = Color.GREEN;
                } else if (btnYellow.isChecked()) {
                    color = Color.YELLOW;
                } else if (btnBlue.isChecked()) {
                    color = Color.BLUE;
                } else {
                    color = Color.WHITE;
                }
                intent.putExtra("backColor", color);
                //put bundle
//                Bundle bundle = new Bundle();
//                bundle.putString("hello", "Hello World");
//                bundle.putString("bye", "Good bye");
//                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == 200) {
                            Intent data = result.getData();
                            EditText editText = findViewById(R.id.editText);
                            editText.setTextSize(data.getIntExtra("textSize", 1));
                        }
                    }
                }
        );
    }

    public void setting(View view) {
        Intent intent = new Intent(MainActivity.this, backgroundChanged.class);
//        startActivityForResult(intent, 100);
        launcher.launch(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 100 && resultCode == 200) {
//            int value = data.getIntExtra("textSize", 1);
//            EditText editText = findViewById(R.id.editText);
//            editText.setTextSize(value);
//        }
//    }
}