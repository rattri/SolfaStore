package solfadev.net.solfastore.adapter;

import android.content.Context;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.api.Interfaces.RegisterService;
import solfadev.net.solfastore.api.ServiceGenerator;
import solfadev.net.solfastore.api.form.RegisterForm;
import solfadev.net.solfastore.model.ServerResponse;

/**
 * Created by Ratri on 11/22/2016.
 */
public class RegisterAdapter {
    Context context;
    BaseActivity activity;
    RegisterForm mRegisterForm;
    ServerResponse registerResponse;
    BaseActivity baseActivity;


    public RegisterAdapter(BaseActivity activity, RegisterForm registerForm){
        context = activity.getBaseContext();
        this.activity = activity;
        mRegisterForm = registerForm;

    }

    public void initData(){
        System.out.println(mRegisterForm.getUsername());
        RegisterService registerService = ServiceGenerator.testCnc(RegisterService.class);
        Call<ServerResponse> registerResponseCall = registerService.getRegisterResponse(mRegisterForm);
        registerResponseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                System.out.println(resp.getResult()+ resp.getMessage());
                //if(resp.getResult().equals(BaseActivity.SUCCESS)){
                if(response.isSuccess()){
//                        //Toast.makeText(, resp.getMessage(), 6);

                    System.out.println("....................sukses");
                    System.out.println(response.raw().toString());
                    System.out.println(new Gson().toJson(response.body()));
                    System.out.println(resp.getResult()+ resp.getMessage());
                    baseActivity.getBaseActivity().startFragment(BaseActivity.FRAGMENT_LOGIN, "Login FRAGMENT");
//
//                    }

                }
                else System.out.println("....................else");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                System.out.println("...................."+t.toString());
            }
        });
    }
}

