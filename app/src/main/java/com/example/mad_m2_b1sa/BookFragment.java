package com.example.mad_m2_b1sa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookFragment extends Fragment {

    Button date, time, confirm;
    ListView listView;
    String setDate, setTime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_book, container, false);

        date = root.findViewById(R.id.date);
        time = root.findViewById(R.id.time);
        confirm = root.findViewById(R.id.confirm);
        listView = root.findViewById(R.id.listView);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Travel date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build();

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        setDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date(selection));
                        date.setText(setDate);
                    }
                });
                materialDatePicker.show(getFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                        .setTitleText("Travel time")
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
                        .build();

                materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setTime = MessageFormat.format("{0}:{1}",
                                String.format(Locale.getDefault(), "%02d", materialTimePicker.getHour()),
                                String.format(Locale.getDefault(), "%02d", materialTimePicker.getMinute()));
                        time.setText(setTime);

                    }
                });
                materialTimePicker.show(getFragmentManager(), "MATERIAL_TIME_PICKER");
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setTime == null || setDate == null){
                    Toast.makeText(getContext(),"Select booking date & time!",Toast.LENGTH_SHORT).show();
                } else{
                    String[] list_item = new String[]{"hauler Mini", "Nissan Leaf", "UBKL-1916", "Arriving at: 63, Bankers Street", "Drop off: Hill Crest, 13th Avenue", "Pickup Date & time: "+setDate+" @ "+setTime, "Payment via hauler. Pay"};

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.z_list_item, list_item);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String clicked = adapterView.getItemAtPosition(i).toString();
                            Toast.makeText(getContext(),"Clicked "+clicked,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return root;
    }
}