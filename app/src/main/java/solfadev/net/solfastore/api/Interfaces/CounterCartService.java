package solfadev.net.solfastore.api.Interfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import solfadev.net.solfastore.adapter.CounterCartAdapter;
import solfadev.net.solfastore.model.ServerResponse;


/**
 * Created by Ratri on 11/28/2016.
 */

public interface CounterCartService {
    @GET("counter_cart.php")
    Call<CounterCartAdapter.Counter> getCounter(@Query("") String id_user );

}
