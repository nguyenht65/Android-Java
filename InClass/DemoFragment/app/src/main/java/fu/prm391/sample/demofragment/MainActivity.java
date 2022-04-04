package fu.prm391.sample.demofragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Button btnAdd, btnReplace, btnRemove;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout2);
        btnAdd = findViewById(R.id.btnAdd);
        btnReplace = findViewById(R.id.btnReplace);
        btnRemove = findViewById(R.id.btnRemove);

        manager = getSupportFragmentManager();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                fragmentA = new FragmentA();
                transaction.add(R.id.frameLayout2, fragmentA);
                transaction.commit();
            }
        });

        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                fragmentB = new FragmentB();
                transaction.replace(R.id.frameLayout2, fragmentB);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                transaction.remove(fragmentB);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}