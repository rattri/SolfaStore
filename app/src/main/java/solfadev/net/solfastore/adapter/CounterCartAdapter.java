package solfadev.net.solfastore.adapter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.api.Interfaces.CartService;
import solfadev.net.solfastore.api.Interfaces.CounterCartService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.api.form.CartForm;
import solfadev.net.solfastore.api.form.LoginForm;
import solfadev.net.solfastore.api.form.RegisterForm;
import solfadev.net.solfastore.model.LoginResponse;
import solfadev.net.solfastore.model.ServerResponse;

import static solfadev.net.solfastore.BaseActivity.getBaseActivity;

/**
 * Created by Ratri on 11/28/2016.
 */

public class CounterCartAdapter {

    Context context;
    BaseActivity activity;
    RegisterForm mRegisterForm;
    LoginForm mLoginForm;
    LoginResponse loginResponse;
    BaseActivity baseActivity;
    private SharedPreferences pref;
    String ID;

    public class Counter {
        String NumberCart;

        public String getNumberCart() {
            return NumberCart;
        }

        public void setNumberCart(String numberCart) {
            NumberCart = numberCart;
        }
    }

    public void initData(){
        SharedPreferences pref;
        pref = getBaseActivity().getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
        CounterCartService counterCartService = ServiceGenerator.testCnc(CounterCartService.class);
        Call<Counter> CounterCall = counterCartService.getCounter(pref.getString(baseActivity.UNIQUE_ID,""));
        CounterCall.enqueue(new Callback<Counter>() {
            @Override
            public void onResponse(Call<Counter> call, Response<Counter> response) {
//                ServerResponse resp = response.body();
                //System.out.println(resp.getResult()+ resp.getMessage());
                //if(resp.getResult().equals(BaseActivity.SUCCESS)){
                if(response.isSuccess()){
//                        //Toast.makeText(, resp.getMessage(), 6);

                    System.out.println("....................sukses");
                    System.out.println(response.raw().toString());
                    System.out.println(new Gson().toJson(response.body()));
                    System.out.println(response.body().getNumberCart());
//                    BaseActivity.CounterCart=response.body().getNumberCart();

                    //System.out.println(resp.getResult()+ resp.getMessage());


                }
                else {
                    System.out.println("....................else");
                }
            }

            @Override
            public void onFailure(Call<Counter> call, Throwable t) {
                System.out.println("...................."+t.toString());
            }
        });
    }
}
