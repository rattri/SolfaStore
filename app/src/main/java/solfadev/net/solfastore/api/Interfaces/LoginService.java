package solfadev.net.solfastore.api.Interfaces;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import solfadev.net.solfastore.api.form.LoginForm;
import solfadev.net.solfastore.model.LoginResponse;

/**
 * Created by Ratri on 11/22/2016.
 */

public interface LoginService {
    @POST("login.php")
    Call<LoginResponse> getLoginResponse(@Body LoginForm loginForm);
}