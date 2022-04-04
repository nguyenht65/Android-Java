package fu.prm391.sample.he141002_hoangtrungnguyen_contentproviderexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fu.prm391.sample.he141002_hoangtrungnguyen_contentproviderexercise.adapter.ContactListAdapter;
import fu.prm391.sample.he141002_hoangtrungnguyen_contentproviderexercise.model.Contact;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnUpload, btnDownload;
    private ContentResolver resolver;
    private List<Contact> contactListPhone;
    private List<Contact> contactListFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        btnDownload = findViewById(R.id.btnDownload);
        btnUpload = findViewById(R.id.btnUpload);

        resolver = getContentResolver();
        contactListFireBase = new ArrayList<>();

        checkPermission();

        getListContactsFromFirebase();
        uploadContactToFireBase();
        downloadContactFromFireBase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListContactsFromPhone();
    }

    private void checkPermission() {
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }
        if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, 101);
        }
    }

    private void getListContactsFromFirebase() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Contacts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            List<DocumentSnapshot> documentSnapshotList = querySnapshot.getDocuments();
                            for (DocumentSnapshot document : documentSnapshotList) {
                                Contact contact = new Contact();
                                contact.setName(document.get("name").toString());
                                contact.setPhoneNumber(document.get("phoneNumber").toString());
                                contactListFireBase.add(contact);
                            }
                        }
                    }
                });
    }

    private void uploadContactToFireBase() {
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Contact contact : contactListPhone) {
                    if (!isContactExisted(contact)) {
                        addContactToFireBase(contact);
                    }
                }
                Toast.makeText(MainActivity.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downloadContactFromFireBase() {
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Contact contact : contactListFireBase) {
                    ContentValues contentValues = new ContentValues();
                    Uri rawContact = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, contentValues);
                    long contactId = ContentUris.parseId(rawContact);
                    insertContactName(ContactsContract.Data.CONTENT_URI, contactId, contact.getName());
                    insertContactPhoneNumber(ContactsContract.Data.CONTENT_URI, contactId, contact.getPhoneNumber());
                }
                getListContactsFromPhone();
                Toast.makeText(getApplicationContext(), "Download contacts successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean isContactExisted(Contact contact) {
        for (Contact contact1 : contactListFireBase) {
            if (contact1.getName().equals(contact.getName())
                    && contact1.getPhoneNumber().equals(contact.getPhoneNumber())) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint("Range")
    private void getListContactsFromPhone() {
        contactListPhone = new ArrayList<>();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts._ID},
                null, null, null);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor cursor1 = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null);
            String phoneNumber = "";
            while (cursor1.moveToNext()) {
                phoneNumber += cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Contact contact = new Contact();
            contact.setName(name);
            contact.setPhoneNumber(phoneNumber);
            contactListPhone.add(contact);
        }
        recyclerView.setAdapter(new ContactListAdapter(MainActivity.this, contactListPhone));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    private void addContactToFireBase(Contact contact) {
        Map<String, String> map = new HashMap<>();
        map.put("name", contact.getName());
        map.put("phoneNumber", contact.getPhoneNumber());
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Contacts")
                .add(map);
    }

    private void insertContactName(Uri addContactsUri, long rawContactId, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        getContentResolver().insert(addContactsUri, contentValues);
    }

    private void insertContactPhoneNumber(Uri addContactsUri, long rawContactId, String phoneNumber) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        getContentResolver().insert(addContactsUri, contentValues);
    }

}