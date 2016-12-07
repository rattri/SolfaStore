package solfadev.net.solfastore.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.MainActivity;
import solfadev.net.solfastore.R;
import solfadev.net.solfastore.adapter.LoginAdapter;
import solfadev.net.solfastore.api.form.LoginForm;

/**
 * Created by Ratri on 11/22/2016.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;
    private TextView tv_register,tv_reset_password;
    private ProgressBar progress;
    private SharedPreferences pref;
    TextView textHome;
    String message;
    Button btnHome, btnOrder, btnPrice;
    LoginAdapter loginAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment,container,false);
        baseActivity = (BaseActivity) getActivity();

        baseActivity.setBaseFragment(this);/*WAJIB ADA*/
        initViews(view);
        setTitle("Login");
        return view;
    }

    public static LoginFragment newInstance(String message) {
        LoginFragment fragment = new LoginFragment();
        fragment.message = message;
        return fragment;
    }


    private void initViews(View view){

//        pref = getActivity().getPreferences(0);
        pref = getActivity().getSharedPreferences(BaseActivity.LOGIN_OPERATION, Context.MODE_PRIVATE);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
//        tv_reset_password = (TextView)view.findViewById(R.id.tv_reset_password);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
//        tv_reset_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

//
            case R.id.tv_register:
                goToRegister();
                break;
            case R.id.btn_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email,password);

                } else {

                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;
//            case R.id.tv_reset_password:
//                goToResetPassword();
//                break;
        }
    }
    private void loginProcess(String email,String password){
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(email);
        loginForm.setPassword(password);
        loginAdapter = new LoginAdapter(getBaseActivity(), loginForm) ;
        loginAdapter.initData();
    }


    private void goToRegister(){

        getBaseActivity().startFragment(BaseActivity.FRAGMENT_REGISTER, "REGISTER");
    }

    private void goToMain (){
        Intent i = new Intent(getActivity(),  MainActivity.class);
        startActivity(i);
    }
}
