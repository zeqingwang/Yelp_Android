package com.example.lab9;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<Reserve> reserveList;

    private ReserveAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ReserveAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public ReserveAdapter (Context context,List<Reserve> reserveList){
        this.inflater=LayoutInflater.from(context);
        this.reserveList=reserveList;

    }

    @NonNull
    @Override
    public ReserveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.reserve_line,parent,false);
        return new ReserveAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReserveAdapter.ViewHolder holder, int position) {
        holder.rpageindex.setText(reserveList.get(position).getIndex());
        holder.rpagename.setText(reserveList.get(position).getName());
        holder.rpagedate.setText(reserveList.get(position).getDate());
        holder.rpagetime.setText(reserveList.get(position).getTime());
        holder.rpageemail.setText(reserveList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return reserveList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView rpageindex,rpagename,rpagedate,rpagetime,rpageemail;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rpageindex=itemView.findViewById(R.id.rpageindex);
            rpagename=itemView.findViewById(R.id.rpagename);
            rpagedate=itemView.findViewById(R.id.rpagedate);
            rpagetime=itemView.findViewById(R.id.rpagetime);
            rpageemail=itemView.findViewById(R.id.rpageemail);

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
