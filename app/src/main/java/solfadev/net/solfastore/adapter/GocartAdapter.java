package solfadev.net.solfastore.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.api.Interfaces.GocartService;
import solfadev.net.solfastore.api.Interfaces.PricelistService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.holder.GocartHolder;
import solfadev.net.solfastore.model.Gocart;
import solfadev.net.solfastore.model.Pricelist;

/**
 * Created by Ratri on 11/30/2016.
 */

public class GocartAdapter extends ListAdapter<Gocart, GocartHolder> {
    Context context;
    BaseActivity activity;
    String id_user;

    public GocartAdapter (BaseActivity activity, String user){
        context = activity.getBaseContext();
        id_user=user;
        this.activity = activity;

    }

    @Override
    public GocartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GocartHolder gocartHolder = new GocartHolder(parent);
        return  gocartHolder;
    }

    @Override
    public void onBindViewHolder(GocartHolder holder, int position) {
        final Gocart gocart = get(position);
        holder.nominal.setText(gocart.getVoucher() +" "+gocart.getNominal()+" "+ gocart.getNomor());
        holder.harga.setText(gocart.getHarga());
        holder.jumlah.setText(gocart.getJumlah());
        Glide.with(context).load(BaseActivity.BASE_URL+"produk/"+gocart.getGambar()+".png").override(100, 100).into(holder.gambar);

    }

    public void initData(){
        GocartService gocartService = ServiceGenerator.connect(GocartService.class);
        Call<List<Gocart>> gocartCall = gocartService.getGocart(id_user);
        gocartCall.enqueue(new Callback<List<Gocart>>() {
            @Override
            public void onResponse(Call<List<Gocart>> call, Response<List<Gocart>> response) {
                if(response.isSuccess()){
                    addAll(response.body());
                    System.out.println(response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Gocart>> call, Throwable t) {

            }
        });
    }
}
