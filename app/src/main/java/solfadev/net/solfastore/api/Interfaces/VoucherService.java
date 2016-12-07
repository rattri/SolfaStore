package solfadev.net.solfastore.api.Interfaces;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import solfadev.net.solfastore.model.Voucher;

/**
 * Created by Ratri on 10/3/2016.
 */
public interface VoucherService {
    @GET("voucherlist.php")
    Call<List<Voucher>> getVoucher(@Query("name") String a);
}
