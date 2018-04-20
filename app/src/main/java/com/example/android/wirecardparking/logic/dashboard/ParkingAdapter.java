package com.example.android.wirecardparking.logic.dashboard;

/**
 * Created by PepovPC on 7/16/2017.
 * Adapter, ktory zobrazi jednotlive views
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.wirecardparking.R;
import com.example.android.wirecardparking.rest.model.getallplaces.Place;

import java.util.ArrayList;


public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingAdapterViewHolder> {
    private ArrayList<Place> parkingData;
    private Context context;
    private final ParkingAdapterOnClickHandler mClickHandler;

    /**
     * Rozhranie, ktore urcuje, co sa vykona po kliknuti na konkretny view
     */
    public interface ParkingAdapterOnClickHandler {
        void onClick(Place place);
    }

    public ParkingAdapter(Context context, ParkingAdapterOnClickHandler clickHandler) {
        this.context = context;         // for Picasso
        mClickHandler = clickHandler;   // for Clicking
    }

    /**
     * Cache of the children views for a forecast list item.
     * Tato mala trieda obsahuje v sebe vsetko co ma obsahovat kazdy jednotlivy view
     * Vytvori a inicializuje ich
     * Tiez implementuje rozhranie, ktore umozni click (tak ako v main), ale tu sa nastavuju data (pozicia, movie)
     */
    public class ParkingAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView title;
        public final TextView spot;

       public ParkingAdapterViewHolder(View view) {
            super(view);
           title = view.findViewById(R.id.title);
           spot = view.findViewById(R.id.spot);
           view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Place place = parkingData.get(adapterPosition);
            mClickHandler.onClick(place);
        }
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     * @return A new ParkingAdapterViewHolder that holds the View for each list item
     *
     */
    @Override
    public ParkingAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.parking_place_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new ParkingAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param parkingAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     *
     * Vykresluje/nastavuje/vizualizuje data
     *
     */
    @Override
    public void onBindViewHolder(ParkingAdapterViewHolder parkingAdapterViewHolder, int position) {

//        String visibleID = parkingData.get(position).get();
//        parkingAdapterViewHolder.spot.setText(visibleID);

        String visibleID = parkingData.get(position).getDisplayID();
        parkingAdapterViewHolder.spot.setText(Html.fromHtml("Spot number: " + "<i>" + visibleID + "</i> "));


    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == parkingData) return 0;
        return parkingData.size();
    }

    /**
     * This method is used to set the movies on a Adapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new ForecastAdapter to display it.
     *
     * @param data The new weather data to be displayed.
     */
    public void setParkingData(ArrayList<Place> data) {
        parkingData = data;
        notifyDataSetChanged();
    }

}