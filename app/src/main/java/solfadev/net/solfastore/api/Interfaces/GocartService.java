package solfadev.net.solfastore.api.Interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import solfadev.net.solfastore.model.Gocart;

/**
 * Created by Ratri on 11/30/2016.
 */

public interface GocartService {
    @GET("gocart.php")
    Call<List<Gocart>> getGocart(@Query("user") String id_user);
}
