package com.example.lab9;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Buiness> businessList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public SearchAdapter (Context context,List<Buiness> buinessList){
        this.inflater=LayoutInflater.from(context);
        this.businessList=buinessList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.search_line,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.businessIndex.setText(businessList.get(position).getIndex());
        Picasso.get().load(businessList.get(position).getImageurl()).into(holder.businessImage);
        holder.businessName.setText(businessList.get(position).getName());
        holder.businessDistance.setText(businessList.get(position).getDistance());
        holder.businessRate.setText(businessList.get(position).getRate());

    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView businessIndex,businessName,businessRate,businessDistance;
        ImageView businessImage;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            businessIndex=itemView.findViewById(R.id.buinessIndex);
            businessName=itemView.findViewById(R.id.buinessName);
            businessRate=itemView.findViewById(R.id.buinessRate);
            businessDistance=itemView.findViewById(R.id.businessDistance);
            businessImage=itemView.findViewById(R.id.buinessImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getBindingAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}
