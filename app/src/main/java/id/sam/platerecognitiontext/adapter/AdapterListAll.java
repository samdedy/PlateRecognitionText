package id.sam.platerecognitiontext.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import id.sam.platerecognitiontext.AppDatabase;
import id.sam.platerecognitiontext.ListAllActivity;
import id.sam.platerecognitiontext.MainActivity;
import id.sam.platerecognitiontext.R;
import id.sam.platerecognitiontext.model.listall.ListAllModel;
import id.sam.platerecognitiontext.model.listall.Product;
import id.sam.platerecognitiontext.model.room.PlatesModel;

public class AdapterListAll extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> items;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Product obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListAll(Context context, List<Product> items) {
        this.items = items;
        ctx = context;
        mOnItemClickListener = (ListAllActivity)context;
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

            Product product = items.get(position);
            view.txtName.setText(product.getName());
            view.txtJenisKendaraan.setText(product.getJenisKendaraan());
            view.txtWarna.setText(product.getWarna());
            view.txtStatus.setText(product.getStatus());
            view.txtNoPlat.setText(product.getNoPlat());
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