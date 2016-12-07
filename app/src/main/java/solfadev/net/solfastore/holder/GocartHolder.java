package solfadev.net.solfastore.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import solfadev.net.solfastore.R;

/**
 * Created by Ratri on 11/30/2016.
 */

public class GocartHolder extends RecyclerView.ViewHolder {
    public TextView nominal, harga, voucher,nomor, jumlah;
    public ImageView gambar;

    public  GocartHolder (View itemView){
        super(itemView);
        nominal = (TextView)itemView.findViewById(R.id.text_produk);
        harga = (TextView) itemView.findViewById(R.id.text_harga);
        jumlah=(TextView) itemView.findViewById(R.id.text_jumlah);
        gambar=(ImageView) itemView.findViewById(R.id.img_cover);
    }

    public GocartHolder(ViewGroup parrent){
        this(LayoutInflater.from(parrent.getContext()).inflate(R.layout.cart_row, parrent,false));
    }
}
