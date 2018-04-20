package com.example.android.wirecardparking.logic.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.activities.DashboardActivity;
import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.AssignParkingPlace;
import com.example.android.wirecardparking.rest.model.getallplaces.ParkingHouses;
import com.example.android.wirecardparking.rest.model.getallplaces.Place;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;
import static com.example.android.wirecardparking.logic.LoginFragment.TOKEN;

public class Tab1Fragment extends BaseFragment implements ParkingAdapter.ParkingAdapterOnClickHandler,
                                             DatePickerDialog.OnDateSetListener{


    @BindView(R.id.rv_spots)
    RecyclerView recycler;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar loader;

    private String value;
    private String phone;
    private ParkingAdapter pAdapter;
    private String[] holidays;  // = {"2018-04-26"}
    private List<String> arrayHelper = new ArrayList<>();
    private String parkingPlaceID;
    ArrayList<String> freeParkingDays;

    @Override
    protected void init(Bundle savedInstanceState) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        pAdapter = new ParkingAdapter(getContext(), this);
        recycler.setAdapter(pAdapter);

        SharedPreferences settings = getContext().getSharedPreferences(TOKEN, MODE_PRIVATE);
        // Reading from SharedPreferences
        value = "Bearer " + settings.getString("token", "");
        phone = settings.getString("number", "");

        // add loading maybe
        ApiClient.getApiService().getAllPlaces(value)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse,
                            this::handleError );

    }

    private void handleError(Throwable throwable) throws IOException {
        System.out.println(throwable.getMessage());
    }


    public void handleResponse(ParkingHouses response){

        ArrayList<ParkingHouses.ParkingPlaces> fullParkingHouses = new ArrayList<>(response.getHouses());

        Set<Place> freeParkingSpots = new LinkedHashSet<>();

        for(int i = 0; i < fullParkingHouses.size(); i++){
            for(int j = 0; j < fullParkingHouses.get(i).getPlaces().size(); j++){
                for(int k = 0; k < fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().size(); k++){
                    if(fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().get(k).getEachDayUser()== null) {
                        freeParkingSpots.add(fullParkingHouses.get(i).getPlaces().get(j));
                        //fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().get(k).getDay();
                    }
                }
            }
        }

        pAdapter.setParkingData(new ArrayList<>(freeParkingSpots));

    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_tab1;
    }


    @Override
    public void onClick(Place place) {

        parkingPlaceID = place.getId();

        ApiClient.getApiService().getAllPlaces(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view -> handleResponse2(view, parkingPlaceID),
                        this::handleError );

    }

    private void handleResponse2(ParkingHouses parkingHouses, String id) {
        ArrayList<ParkingHouses.ParkingPlaces> fullParkingHouses = new ArrayList<>(parkingHouses.getHouses());

        freeParkingDays = new ArrayList<>();

        for(int i = 0; i < fullParkingHouses.size(); i++){
            for(int j = 0; j < fullParkingHouses.get(i).getPlaces().size(); j++){
                for(int k = 0; k < fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().size(); k++){
                    if(fullParkingHouses.get(i).getPlaces().get(j).getId().equals(id)){
                        if(fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().get(k).getEachDayUser()== null) {
                            freeParkingDays.add(fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().get(k).getDay());
                        }
                    }
                }
            }
        }
        holidays =  freeParkingDays.toArray(new String[freeParkingDays.size()]);
        showDatePicker();

    }


    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );


        Calendar thenMaximum = Calendar.getInstance();
        int getDaysOfMonth = Calendar.DAY_OF_MONTH;
        thenMaximum.set(Calendar.DAY_OF_MONTH, +getDaysOfMonth);// you can pass your custom date
        dpd.setMinDate(thenMaximum);

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
            dpd.setSelectableDays(disabledDays1);

        }

    }

    private Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        AssignParkingPlace assignParkingPlace = new AssignParkingPlace();
        assignParkingPlace.setParkingPlaceIdentifier(parkingPlaceID);
        assignParkingPlace.setDays(freeParkingDays);
        assignParkingPlace.setLoginAlias(phone);
        assignParkingPlace.setLoginAliasType("msisdn");


        ApiClient.getApiService().assignParkingPlace(value, assignParkingPlace, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> ((DashboardActivity) getContext()).refreshUI(),
                        this::handleError
                );

    }



}

