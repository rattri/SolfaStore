package solfadev.net.solfastore.api.Interfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import solfadev.net.solfastore.api.form.CartForm;
import solfadev.net.solfastore.model.ServerResponse;

/**
 * Created by Ratri on 11/22/2016.
 */

public interface CartService {
    @POST("cart.php")
//    Call<ServerResponse> getServerResponse(@Body CartForm cartForm);
    Call<ServerResponse> getServerResponse(@Body CartForm cartForm);
}