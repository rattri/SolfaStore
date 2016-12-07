package solfadev.net.solfastore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.api.Interfaces.VoucherService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.holder.VoucherHolder;
import solfadev.net.solfastore.model.Voucher;

/**
 * Created by Ratri on 10/3/2016.
 */
public class VoucherAdapter extends ListAdapter<Voucher, VoucherHolder> {
    Context context;
    BaseActivity activity;
    String nvoucher;


    public VoucherAdapter(BaseActivity activity, String ivoucher){
        context = activity.getBaseContext();
        nvoucher=ivoucher;
        this.activity = activity;
    }

    @Override
    public VoucherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("on create holder");
        VoucherHolder voucherHolder = new VoucherHolder(parent);
        return voucherHolder;
    }

    @Override
    public void onBindViewHolder(VoucherHolder holder, int position) {
        final Voucher voucher = get(position);
        holder.textNama.setText(voucher.getNama());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voucher.cardOnClock(activity);
            }
        });
        Glide.with(context).load(BaseActivity.BASE_URL+"produk/"+voucher.getGambar()+".png").override(100, 100).into(holder.thumbnail);
    }

    public void initData(){
        VoucherService voucherService = ServiceGenerator.connect(VoucherService.class);
        Call<List<Voucher>> voucherCall = voucherService.getVoucher(nvoucher);
        voucherCall.enqueue(new Callback<List<Voucher>>() {
            @Override
            public void onResponse(Call<List<Voucher>> call, Response<List<Voucher>> response) {
                if(response.isSuccess()){
                    addAll(response.body());
                    System.out.println(response.raw().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Voucher>> call, Throwable t) {

            }
        });
    }
}
