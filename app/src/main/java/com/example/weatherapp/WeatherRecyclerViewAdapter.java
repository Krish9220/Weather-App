package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<WeatherRecyclerViewModel>weatherRecyclerViewModelArrayList;

    public WeatherRecyclerViewAdapter(Context context, ArrayList<WeatherRecyclerViewModel> weatherRecyclerViewModelArrayList) {
        this.context = context;
        this.weatherRecyclerViewModelArrayList = weatherRecyclerViewModelArrayList;
    }

    @NonNull
    @Override
    public WeatherRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRecyclerViewAdapter.ViewHolder holder, int position) {

        //last exit
        WeatherRecyclerViewModel model = weatherRecyclerViewModelArrayList.get(position);
        holder.temperatureTV.setText(model.getTemperature() + "°c");
        Picasso.get().load("http:".concat(model.getIcon())).into(holder.conditionIV);
        holder.windTV.setText(model.getWindSpeed() + "Km/h");

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");

        try{
            Date t = input.parse(model.getTime());
            holder.timeTV.setText(output.format(t));

        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherRecyclerViewModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView windTV,temperatureTV,timeTV;
        private ImageView conditionIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            windTV = itemView.findViewById(R.id.idTVWindSpeed);
            temperatureTV = itemView.findViewById(R.id.idTVTemperature2);
            timeTV = itemView.findViewById(R.id.idTVTime2);
            conditionIV = itemView.findViewById(R.id.idIVCondition2);
        }
    }
}
