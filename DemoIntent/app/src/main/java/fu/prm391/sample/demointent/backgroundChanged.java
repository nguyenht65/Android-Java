package fu.prm391.sample.demointent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class backgroundChanged extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout constraintLayout;
    private Button btnBack;
    private TextView textView;
    private SeekBar seekBar;

    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_changed);
        Intent intent = getIntent();
        int backGroundColor = intent.getIntExtra("backColor", Color.WHITE);
        constraintLayout = findViewById(R.id.resultLayout);
        constraintLayout.setBackgroundColor(backGroundColor);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(backgroundChanged.this, MainActivity.class);
                startActivity(intent1);
            }
        });

//        Bundle bundle = intent.getExtras();
//        textView = findViewById(R.id.textView2);
//        textView.setText(bundle.getString("hello") + bundle.getString("bye"));

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);
    }

    public void ok(View view) {
        seekBar = findViewById(R.id.seekBar);
        int value = seekBar.getProgress();
        Intent intent = new Intent();
        intent.putExtra("textSize", value);
        setResult(200, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        EditText etPhone = findViewById(R.id.editTextPhone);
        String phone = etPhone.getText().toString();
        if(checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            startActivity(intent);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 101);
        }

    }
}