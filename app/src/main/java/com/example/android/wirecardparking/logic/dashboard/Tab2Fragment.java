package com.example.android.wirecardparking.logic.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.activities.DashboardActivity;
import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.SetAvailableDaysRequest;
import com.example.android.wirecardparking.rest.model.getallplaces.ParkingHouses;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;
import static com.example.android.wirecardparking.logic.LoginFragment.TOKEN;

@FragmentWithArgs
public class Tab2Fragment extends BaseFragment implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.tw_title)
    TextView tw_title;

    @BindView(R.id.tw_spot)
    TextView tw_spot;

    @BindView(R.id.tw_owner)
    TextView tw_owner;

    @BindView(R.id.tw_contact)
    TextView tw_contact;

    @BindView(R.id.textView3)
    TextView tw_textview;

    @BindView(R.id.view1)
    View view1;

    @BindView(R.id.view2)
    View view2;

    private String value;
    private String[] holidays;
    private List<String> arrayHelper = new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {

        tw_contact.setPaintFlags(tw_contact.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tw_contact.setOnClickListener(v -> {
            //ask for permissions
            String phone = tw_contact.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        });

        button.setOnClickListener(v -> {
            showDatePicker();
        });


        SharedPreferences settings = getContext().getSharedPreferences(TOKEN, MODE_PRIVATE);
        // Reading from SharedPreferences
        value = "Bearer " + settings.getString("token", "");

        ApiClient.getApiService().getAllPlaces(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,
                        this::handleError );


    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_tab2;
    }


    private void handleError(Throwable throwable) throws IOException {
//        HttpException err = (HttpException) throwable;
//        String errBody = err.response().errorBody().string();
        Toast.makeText(getActivity(), "Error occurred", Toast.LENGTH_SHORT).show();
        System.out.println(throwable.getMessage());
    }


    public void handleResponse(ParkingHouses response){

        ArrayList<ParkingHouses.ParkingPlaces> fullParkingHouses = new ArrayList<>(response.getHouses());

        if(DashboardActivity.getHasParkingSpot()) {
            String owner_number = "";
            String owner_name = "";
            String parkingID = "";
            String houseName = "";
            for(int i = 0; i < fullParkingHouses.size(); i++){
                for(int j = 0; j < fullParkingHouses.get(i).getPlaces().size(); j++){
                    if(DashboardActivity.getPlaceID().equals(fullParkingHouses.get(i).getPlaces().get(j).getId())) {
                        houseName = fullParkingHouses.get(0).getHouseDisplayName();
                        parkingID = fullParkingHouses.get(i).getPlaces().get(j).getDisplayID();
                        owner_number = fullParkingHouses.get(i).getPlaces().get(j).getOwner().getUserNumber();
                        owner_name = fullParkingHouses.get(i).getPlaces().get(j).getOwner().getFirstName()
                                + " " + fullParkingHouses.get(i).getPlaces().get(j).getOwner().getLasNname();
                    }
                }
            }

            String boldHelperString = "Spot owner: " + "<b>" + owner_name + "</b> ";
            tw_owner.setText(Html.fromHtml(boldHelperString));
            tw_contact.setText(owner_number);
            boldHelperString = "Spot number: " + "<b>" + parkingID + "</b> ";
            tw_spot.setText(Html.fromHtml(boldHelperString));
            tw_title.setText(houseName);
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            tw_textview.setVisibility(View.VISIBLE);
            button.setEnabled(true);
        }else{
            tw_title.setText("You do not have any spot now");
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            tw_textview.setVisibility(View.INVISIBLE);
            button.setEnabled(false);
        }

    }



    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), "DatePickerDialog");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

       Date date = null;

        if(holidays == null || holidays.length == 0)
            return;

        for (String holiday : holidays) {

            try {
                date = sdf.parse(holiday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            calendar = dateToCalendar(date);
            System.out.println(calendar.getTime());

            List<Calendar> dates = new ArrayList<>();
            dates.add(calendar);
            Calendar[] disabledDays1 = dates.toArray(new Calendar[dates.size()]);
            dpd.setDisabledDays(disabledDays1);
        }

    }

    private Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        monthOfYear = monthOfYear + 1;
        String date = year + "-" + monthOfYear + "-" + +dayOfMonth;
        arrayHelper.add(date);
        holidays =  arrayHelper.toArray(new String[arrayHelper.size()]);

        SetAvailableDaysRequest setAvailableDaysRequest = new SetAvailableDaysRequest();
        setAvailableDaysRequest.setParkingPlaceIdentifier("fd58b607-dc14-43f5-b33c-ce77430e64a5");  // parkovisko 2
        setAvailableDaysRequest.setDays(arrayHelper);


        ApiClient.getApiService().setAvailableDays(value, setAvailableDaysRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {},
                        this::handleError
                );

    }



}

