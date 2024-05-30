package com.example.parkingapp.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.parkingapp.R;
import com.example.parkingapp.model.ParkingSpot;

import java.util.List;

public class ParkingSpotAdapter extends RecyclerView.Adapter<ParkingSpotAdapter.ViewHolder> {

    private List<ParkingSpot> parkingSpots;

    public ParkingSpotAdapter(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parking_spot, parent, false);
        return new ViewHolder(view);
    }

   // @androidx.annotation.NonNull
    //@Override
    //public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
      //  return null;
    //}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParkingSpot parkingSpot = parkingSpots.get(position);
        holder.bind(parkingSpot);
    }

    @Override
    public int getItemCount() {
        return parkingSpots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView spotStatusTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spotStatusTextView = itemView.findViewById(R.id.textViewSpots);
        }

        public void bind(ParkingSpot parkingSpot) {
            spotStatusTextView.setText(parkingSpot.getSpotStatus());
        }
    }
}

