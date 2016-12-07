package solfadev.net.solfastore.adapter;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.api.Interfaces.LoginService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.api.form.LoginForm;
import solfadev.net.solfastore.api.form.RegisterForm;
import solfadev.net.solfastore.model.LoginResponse;

/**
 * Created by Ratri on 11/22/2016.
 */
public class LoginAdapter {
    Context context;
    BaseActivity activity;
    RegisterForm mRegisterForm;
    LoginForm mLoginForm;
    LoginResponse loginResponse;
    BaseActivity baseActivity;
    private SharedPreferences pref;


    public LoginAdapter(BaseActivity activity, LoginForm loginForm){
        context = activity.getBaseContext();
        this.activity = activity;
        mLoginForm = loginForm;

    }

    public void initData(){
        LoginService loginService = ServiceGenerator.testCnc(LoginService.class);
        Call<LoginResponse> loginResponseCall = loginService.getLoginResponse(mLoginForm);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse resp = response.body();
                //System.out.println(resp.getResult()+ resp.getMessage());
                //if(resp.getResult().equals(BaseActivity.SUCCESS)){
                if(response.isSuccess()){
//                        //Toast.makeText(, resp.getMessage(), 6);

                    System.out.println("....................sukses");
                    System.out.println(response.raw().toString());
                    System.out.println(new Gson().toJson(response.body()));
                    //System.out.println(resp.getResult()+ resp.getMessage());

                    if(resp.getResult().equals(BaseActivity.SUCCESS)){
                        pref = activity.getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putBoolean(BaseActivity.IS_LOGGED_IN,true);
                        editor.putString(BaseActivity.EMAIL,resp.getEmail());
                        editor.putString(BaseActivity.NAME,resp.getUsername());
                        editor.putString(BaseActivity.UNIQUE_ID,resp.getId());
                        editor.apply();
                        baseActivity.getBaseActivity().startFragment(BaseActivity.FRAGMENT_HOME, "Home FRAGMENT");
                    }

                }
                else {
                    System.out.println("....................else");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("...................."+t.toString());
            }
        });
    }
}