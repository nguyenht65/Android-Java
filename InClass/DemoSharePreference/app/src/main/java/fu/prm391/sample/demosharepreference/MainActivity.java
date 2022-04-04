package fu.prm391.sample.demosharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);

        String[] colors = new String[]{"Red", "Green", "Blue"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, colors);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        spinner.getRootView().setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        spinner.getRootView().setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        spinner.getRootView().setBackgroundColor(Color.BLUE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("color", MODE_PRIVATE);
        int position = pref.getInt("position", 0);
        spinner.setSelection(position);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences pref = getSharedPreferences("color", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("position", spinner.getSelectedItemPosition());
        editor.commit();
    }
}