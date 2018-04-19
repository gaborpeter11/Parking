package com.example.android.wirecardparking.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.android.wirecardparking.BaseActivity;
import com.example.android.wirecardparking.logic.dashboard.Tab1Fragment;
import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.logic.dashboard.Tab2Fragment;
import com.example.android.wirecardparking.logic.dashboard.Tab3Fragment;
import com.example.android.wirecardparking.rest.ApiClient;
import com.example.android.wirecardparking.rest.model.getallplaces.ParkingHouses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.android.wirecardparking.logic.LoginFragment.TOKEN;


public class DashboardActivity extends BaseActivity {


    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private static boolean hasParkingSpot = false;
    String number;
    private static String parkingPlaceID;

    @Override
    public void init(Bundle savedInstanceState) {

        setupViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);

        SharedPreferences settings = getSharedPreferences(TOKEN, MODE_PRIVATE);
        // Reading from SharedPreferences
        String value = "Bearer " + settings.getString("token", "");
        number = settings.getString("number", "");

        ApiClient.getApiService().getAllPlaces(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,
                        this::handleError );


    }


    @Override
    public int getResLayoutId() {
        return R.layout.activity_dashboard;
    }



    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Free spot");   //getResources().getString(R.string.favorite_restarants)
        adapter.addFragment(new Tab2Fragment(), "My spot");
        adapter.addFragment(new Tab3Fragment(), "Settings");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                    getSupportFragmentManager().popBackStackImmediate();
//                } else {
//                    //nerob nic
//                }
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    private void handleError(Throwable throwable) throws IOException {
//        HttpException err = (HttpException) throwable;
//        String errBody = err.response().errorBody().string();
        System.out.println(throwable.getMessage());
    }


    public void handleResponse(ParkingHouses response){

        ArrayList<ParkingHouses.ParkingPlaces> fullParkingHouses = new ArrayList<>(response.getHouses());


        for(int i = 0; i < fullParkingHouses.size(); i++){
            for(int j = 0; j < fullParkingHouses.get(i).getPlaces().size(); j++){
                if (fullParkingHouses.get(i).getPlaces().get(j).getUsers().getUserNumber().equals(number)){
                    viewpager.setCurrentItem(1);
                    setHasParkingSpot(true);
                    setPlaceID(fullParkingHouses.get(i).getPlaces().get(j).getId());
                    return;
                }
                for(int k = 0; k < fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().size(); k++){
                    if(fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().get(k).getEachDayUser()!= null) {
                        if(fullParkingHouses.get(i).getPlaces().get(j).getAvailableSpots().get(k).getEachDayUser().getUserNumber().equals(number)) {
                            viewpager.setCurrentItem(1);
                            setHasParkingSpot(true);
                            setPlaceID(fullParkingHouses.get(i).getPlaces().get(j).getId());
                            return;
                        }
                    }
                }
            }
        }

    }


    public static boolean getHasParkingSpot(){
        return hasParkingSpot;
    }

    public static void setHasParkingSpot(Boolean bool){
        hasParkingSpot = bool;
    }

    public static String getPlaceID(){
        return parkingPlaceID;
    }

    public static void setPlaceID(String id){
        parkingPlaceID = id;
    }

}

