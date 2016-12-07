package solfadev.net.solfastore.fragment;

/**
 * Created by Ratri on 11/22/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import solfadev.net.solfastore.BaseActivity;
import solfadev.net.solfastore.R;
import solfadev.net.solfastore.adapter.RegisterAdapter;
import solfadev.net.solfastore.api.form.RegisterForm;

public class RegisterFragment extends BaseFragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_email,et_password,et_name;
    private TextView tv_login;
    private ProgressBar progress;
    RegisterAdapter registerAdapter;
    RegisterForm registerForm;
    String message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment,container,false);
        baseActivity = (BaseActivity) getActivity();
        btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);
        tv_login = (TextView)view.findViewById(R.id.tv_login);
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);
        setTitle("Register");
        progress = (ProgressBar)view.findViewById(R.id.progress);
        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        baseActivity.setBaseFragment(this);/*WAJIB ADA*/
        return  view;
    }

    public static RegisterFragment newInstance(String message) {
        RegisterFragment fragment = new RegisterFragment();
        fragment.message = message;
        return fragment;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_login:
                getBaseActivity().startFragment(BaseActivity.FRAGMENT_LOGIN, "LOGIN");
                break;

            case R.id.btn_register:

                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(name,email,password);

                } else {

                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void registerProcess(String name, String email,String password){
        RegisterForm registerForm = new RegisterForm();
        registerForm.setUsername(name);
        registerForm.setEmail(email);
        registerForm.setPassword(password);
        registerAdapter = new RegisterAdapter(getBaseActivity(), registerForm) ;
        registerAdapter.initData();
    }

}
