package fu.prm391.sample.practicalexam;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    private EditText etNumber;
    private Spinner spinnerFrom;
    private Spinner spinnerTo;
    private Button btnConvert;
    private List<Unit> unitList;
    private ArrayAdapter<Unit> arrayAdapter;
    private BigDecimal number;
    private double fromValue, toValue;
    private BigDecimal result;
    private OnSendData onSendData;
    private MyDatabase myDatabase;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onSendData = (OnSendData) getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etNumber = view.findViewById(R.id.etNumber);
        spinnerFrom = view.findViewById(R.id.spinnerFrom);
        spinnerTo = view.findViewById(R.id.spinnerTo);
        btnConvert = view.findViewById(R.id.btnConvert);

        setupSpinner();

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNumber.getText().toString().trim().length() != 0) {
                    number = BigDecimal.valueOf(Double.parseDouble(etNumber.getText().toString().trim()));
                    result = (new BigDecimal(fromValue)).divide(new BigDecimal(toValue), 15, RoundingMode.HALF_UP).multiply(number);
                    String stringResult = result.stripTrailingZeros().toPlainString();
                    onSendData.sendData(stringResult);

                    myDatabase = Room.databaseBuilder(getContext(), MyDatabase.class, "myHistory.db")
                            .allowMainThreadQueries()
                            .build();
                    HistoryDAO catalogDAO = myDatabase.createHistoryDAO();
                    Date currentTime = Calendar.getInstance().getTime();
                    catalogDAO.insert(new History(currentTime.toString(),
                            Double.parseDouble(etNumber.getText().toString().trim()),
                            String.valueOf(fromValue),
                            String.valueOf(toValue),
                            Double.parseDouble(stringResult)));
                }
            }
        });

    }

    private void setupSpinner() {
        unitList = new ArrayList<>();
        unitList.add(new Unit("Kilometer", 1000000));
        unitList.add(new Unit("Meter", 1000));
        unitList.add(new Unit("Centimeter", 10));
        unitList.add(new Unit("Millimeter", 1));
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, unitList);
        spinnerFrom.setAdapter(arrayAdapter);
        spinnerTo.setAdapter(arrayAdapter);
        spinnerFrom.setSelection(0);
        spinnerTo.setSelection(1);

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fromValue = unitList.get(i).getValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                toValue = unitList.get(i).getValue();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}