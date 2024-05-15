package com.example.parkingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkingapp.R;
import com.example.parkingapp.model.ParkingArea;
import java.util.List;

public class ParkingAreaAdapter extends RecyclerView.Adapter<ParkingAreaAdapter.ParkingAreaViewHolder> {

    private final List<ParkingArea> parkingAreaList;

    public ParkingAreaAdapter(List<ParkingArea> parkingAreaList) {
        this.parkingAreaList = parkingAreaList;
    }

    @NonNull
    @Override
    public ParkingAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parking_area, parent, false);
        return new ParkingAreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingAreaViewHolder holder, int position) {
        ParkingArea parkingArea = parkingAreaList.get(position);
        holder.textViewName.setText(parkingArea.getAreaName());
        holder.textViewSpots.setText(String.valueOf(parkingArea.getTotalSpots()));
    }

    @Override
    public int getItemCount() {
        return parkingAreaList.size();
    }

    static class ParkingAreaViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewSpots;

        public ParkingAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewSpots = itemView.findViewById(R.id.textViewSpots);
        }
    }
}

