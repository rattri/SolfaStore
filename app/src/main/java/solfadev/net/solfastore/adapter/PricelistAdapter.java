package solfadev.net.solfastore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.api.Interfaces.PricelistService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.holder.PricelistHolder;
import solfadev.net.solfastore.model.Pricelist;

/**
 * Created by Ratri on 11/22/2016.
 */
public class PricelistAdapter extends ListAdapter<Pricelist, PricelistHolder> {
    Context context;
    BaseActivity activity;

    String title;
    String mId;


    public PricelistAdapter(BaseActivity activity, String id, String kategori){
        context = activity.getBaseContext();
        this.activity = activity;
        mId = id;
        title = kategori;

    }

    @Override
    public PricelistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("on create holder");
        PricelistHolder pricelistHolder = new PricelistHolder(parent);
        return pricelistHolder;
    }

    @Override
    public void onBindViewHolder(PricelistHolder holder, int position) {
        final Pricelist pricelist = get(position);
        holder.textNominal.setText("Voucher " +pricelist.getNama());
        holder.textHarga.setText("Rp "+pricelist.getHarga());
        holder.lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pricelist.cardOnClock(activity, title, mId);
                System.out.println("klik");
            }
        });

    }

    public void initData(){
        PricelistService pricelistService = ServiceGenerator.connect(PricelistService.class);
        Call<List<Pricelist>> pricelistCall = pricelistService.getPricelist(mId);
        pricelistCall.enqueue(new Callback<List<Pricelist>>() {
            @Override
            public void onResponse(Call<List<Pricelist>> call, Response<List<Pricelist>> response) {
                if(response.isSuccess()){
                    addAll(response.body());
                    System.out.println(response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Pricelist>> call, Throwable t) {

            }
        });
    }
}

