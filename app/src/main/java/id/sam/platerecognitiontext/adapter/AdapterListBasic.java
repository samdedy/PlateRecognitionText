package id.sam.platerecognitiontext.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import java.util.List;

import id.sam.platerecognitiontext.AppDatabase;
import id.sam.platerecognitiontext.MainActivity;
import id.sam.platerecognitiontext.R;
import id.sam.platerecognitiontext.model.room.PlatesModel;

public class AdapterListBasic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AppDatabase mDb;
    private List<PlatesModel> items;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, PlatesModel obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasic(Context context, List<PlatesModel> items) {
        this.items = items;
        ctx = context;
        mDb = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "plate_db").allowMainThreadQueries().build();
        mOnItemClickListener = (MainActivity)context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtJenisKendaraan, txtWarna, txtStatus, txtNoPlat;
        public CardView parentLayout;

        public OriginalViewHolder(View v) {
            super(v);
            txtName = v.findViewById(R.id.txtName);
            txtJenisKendaraan = v.findViewById(R.id.txtJenisKendaraan);
            txtWarna = v.findViewById(R.id.txtWarna);
            txtStatus = v.findViewById(R.id.txtStatus);
            txtNoPlat = v.findViewById(R.id.txtNoPlate);
            parentLayout = v.findViewById(R.id.layout_utama);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_plate_item, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            PlatesModel plateModel = items.get(position);
            view.txtName.setText(plateModel.getName());
            view.txtJenisKendaraan.setText(plateModel.getJenisKendaraan());
            view.txtWarna.setText(plateModel.getWarna());
            view.txtStatus.setText(plateModel.getStatus());
            view.txtNoPlat.setText(plateModel.getNoPlat());
            view.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}