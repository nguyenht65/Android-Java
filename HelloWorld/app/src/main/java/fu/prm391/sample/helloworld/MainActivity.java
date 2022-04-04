package fu.prm391.sample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiple;
    private Button btnDivide;
    private EditText firstText;
    private EditText secondText;
    private EditText result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiple = findViewById(R.id.btnMultiple);
        btnDivide = findViewById(R.id.btnDivide);
        firstText = findViewById(R.id.firstNumber);
        secondText = findViewById(R.id.secondNumber);
        result = findViewById(R.id.result);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int firstNumber = Integer.parseInt(firstText.getText().toString());
                    int secondNumber = Integer.parseInt(secondText.getText().toString());
                    result.setText(String.valueOf(firstNumber + secondNumber));
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int firstNumber = Integer.parseInt(firstText.getText().toString());
                    int secondNumber = Integer.parseInt(secondText.getText().toString());
                    result.setText(String.valueOf(firstNumber - secondNumber));
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        });
        btnMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int firstNumber = Integer.parseInt(firstText.getText().toString());
                    int secondNumber = Integer.parseInt(secondText.getText().toString());
                    result.setText(String.valueOf(firstNumber * secondNumber));
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int firstNumber = Integer.parseInt(firstText.getText().toString());
                    int secondNumber = Integer.parseInt(secondText.getText().toString());
                    result.setText(String.valueOf(firstNumber / secondNumber));
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}