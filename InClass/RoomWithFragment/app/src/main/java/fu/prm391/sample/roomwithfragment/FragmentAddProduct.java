package fu.prm391.sample.roomwithfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAddProduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAddProduct extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText productName, productPrice;
    private Spinner spCatalog;
    private Button btnSave;
    private MyDatabase myDatabase;


    public FragmentAddProduct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAddProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAddProduct newInstance(String param1, String param2) {
        FragmentAddProduct fragment = new FragmentAddProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productName = view.findViewById(R.id.etProductName);
        productPrice = view.findViewById(R.id.etProductPrice);
        spCatalog = view.findViewById(R.id.spCatalog);
        btnSave = view.findViewById(R.id.btnSave);

        myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "myDatabase.db")
                .allowMainThreadQueries()
                .build();
        CatalogDAO catalogDAO = myDatabase.createCatalogDAO();
        List<Catalog> catalogs = catalogDAO.getCatalogs();
        ArrayAdapter<Catalog> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, catalogs);
        spCatalog.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDAO productDAO = myDatabase.createProductDAO();
                Catalog catalog = (Catalog) spCatalog.getSelectedItem();
                Product product = new Product(productName.getText().toString(),
                        Double.parseDouble(productPrice.getText().toString()),
                        catalog.getCatalogId());
                productDAO.insert(product);
                Toast.makeText(getContext(), "add success!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}