package fu.prm391.sample.practicalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnSendData{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendData(String data) {
        Fragment2 secondFragment = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        secondFragment.receiveData(data);
    }
}