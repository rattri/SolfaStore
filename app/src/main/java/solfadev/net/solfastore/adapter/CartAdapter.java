package solfadev.net.solfastore.adapter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.api.Interfaces.CartService;
import solfadev.net.solfastore.api.Interfaces.LoginService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.api.form.CartForm;
import solfadev.net.solfastore.api.form.LoginForm;
import solfadev.net.solfastore.api.form.RegisterForm;
import solfadev.net.solfastore.model.LoginResponse;
import solfadev.net.solfastore.model.ServerResponse;

public class CartAdapter{
    Context context;
    BaseActivity activity;
    RegisterForm mRegisterForm;
    LoginForm mLoginForm;
    LoginResponse loginResponse;
    BaseActivity baseActivity;
    private SharedPreferences pref;

    CartForm mCartForm;


    public CartAdapter(BaseActivity activity, CartForm cartForm){
        context = activity.getBaseContext();
        this.activity = activity;
        mCartForm = cartForm;
    }

    public void initData(){
        CartService cartService = ServiceGenerator.testCnc(CartService.class);
//        Call<ServerResponse> serverResponseCall = cartService.getServerResponse(mCartForm);
        Call<ServerResponse> serverResponseCall = cartService.getServerResponse(mCartForm);
        serverResponseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                ServerResponse resp = response.body();
                //System.out.println(resp.getResult()+ resp.getMessage());
                //if(resp.getResult().equals(BaseActivity.SUCCESS)){
                if(response.isSuccess()){
//                        //Toast.makeText(, resp.getMessage(), 6);

                    System.out.println("....................sukses");
                    System.out.println(response.raw().toString());
                    System.out.println(new Gson().toJson(response.body()));
                    //System.out.println(resp.getResult()+ resp.getMessage());


                }
                else {
                    System.out.println("....................else");
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                System.out.println("...................."+t.toString());
            }
        });
    }
}