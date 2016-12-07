package solfadev.net.solfastore.api.Interfaces;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import solfadev.net.solfastore.model.Pricelist;

/**
 * Created by Ratri on 10/10/2016.
 */
public interface PricelistService {
    @GET("pricelist.php")
    Call<List<Pricelist>> getPricelist(@Query("name") String id_voucher);

}
