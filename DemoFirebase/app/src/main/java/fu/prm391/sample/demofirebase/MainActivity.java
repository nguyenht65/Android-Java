package fu.prm391.sample.demofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etDob, etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etDob = findViewById(R.id.etDob);
        etAddress = findViewById(R.id.etAddress);

    }

    public void save(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("name", etName.getText().toString());
        map.put("dob", etDob.getText().toString());
        map.put("address", etAddress.getText().toString());
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        // add data
        firestore.collection("students")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Add success!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Add fail!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void read(View view) {
        // read
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firestore.collection("students").document();

        firestore.collection("students")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //cach1
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.i("Test", document.getData().get("name").toString());
//                                etName.setText(document.getData().get("name").toString());
//                            }

                            //cach2
                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> list = querySnapshot.getDocuments();
                            for (DocumentSnapshot document : list) {
                                Log.i("Test2", document.get("name").toString());
                            }
                            for(int i = 0; i < list.size(); i++) {
                                etName.setText(list.get(0).get("name").toString());
                            }
//
                        } else {
                            Log.i("Test", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}