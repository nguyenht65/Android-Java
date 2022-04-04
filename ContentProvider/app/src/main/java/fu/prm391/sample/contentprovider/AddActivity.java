package fu.prm391.sample.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
@SuppressLint("Range")
public class AddActivity extends AppCompatActivity {

    private ContentResolver resolver;
    private EditText etName, etPhone;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, 101);
        }

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);

        resolver = getContentResolver();

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
//                String phone = etPhone.getText().toString();
                ContentValues contentValues = new ContentValues();

                contentValues.put(ContactsContract.Contacts._ID, 10000);
                contentValues.put(ContactsContract.Contacts.DISPLAY_NAME, name);

                resolver.insert(ContactsContract.Contacts.CONTENT_URI, contentValues);

//                Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
//                        new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID},
//                        "name LIKE ?", new String[]{"%" + name + "%"}, null);
//                if (cursor != null) {
//                    long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
//                    Cursor c = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                            new String[]{String.valueOf(id)}, null);
//                    ContentValues phoneNumber = new ContentValues();
//                    phoneNumber.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
//                    resolver.insert(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, phoneNumber);
//                }

            }
        });
    }
}