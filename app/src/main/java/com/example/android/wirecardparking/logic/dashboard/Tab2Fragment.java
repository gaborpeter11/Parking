package com.example.android.wirecardparking.logic.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.wirecardparking.BaseFragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.activities.DashboardActivity;
import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.getallplaces.ParkingHouses;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;
import static com.example.android.wirecardparking.logic.LoginFragment.TOKEN;

@FragmentWithArgs
public class Tab2Fragment extends BaseFragment {

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

    @BindView(R.id.view1)
    View view1;

    @BindView(R.id.view2)
    View view2;


    @Override
    protected void init(Bundle savedInstanceState) {

        tw_contact.setPaintFlags(tw_contact.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tw_contact.setOnClickListener(v -> {
            //ask for permissions
            String phone = tw_contact.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        });



        SharedPreferences settings = getContext().getSharedPreferences(TOKEN, MODE_PRIVATE);
        // Reading from SharedPreferences
        String value = "Bearer " + settings.getString("token", "");

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
            tw_owner.setText("Spot owner: " + owner_name);
            tw_contact.setText(owner_number);
            tw_spot.setText("Spot number: " + parkingID);
            tw_title.setText(houseName);
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
            button.setEnabled(true);
        }else{
            tw_title.setText("You do not have any spot now");
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.INVISIBLE);
            button.setEnabled(false);
        }

    }



}

