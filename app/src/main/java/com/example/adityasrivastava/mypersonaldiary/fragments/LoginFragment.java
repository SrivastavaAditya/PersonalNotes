package com.example.adityasrivastava.mypersonaldiary.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.activities.HomeActivity;
import com.example.adityasrivastava.mypersonaldiary.utils.preferences.SharedPreferenceStorage;

import java.util.Objects;

/**
 * The type Login fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    /**
     * The Shared preference storage.
     */
    SharedPreferenceStorage sharedPreferenceStorage;
    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The Et user name.
     */
    @BindView(R.id.edit_user_name)
    EditText etUserName;

    /**
     * The Et password.
     */
    @BindView(R.id.edit_password)
    EditText etPassword;

    /**
     * The Btn login.
     */
    @BindView(R.id.button_login)
    Button btnLogin;

    /**
     * The Tv forgot password.
     */
    @BindView(R.id.text_forgot_password)
    TextView tvForgotPassword;

    /**
     * The Progress bar.
     */
    @BindView(R.id.progress_bar_login)
    ProgressBar progressBar;
    @BindView(R.id.text_change_password)
    TextView tvChangePassword;
    @BindView(R.id.text_register)
    TextView tvRegister;


    /**
     * Instantiates a new Login fragment.
     */
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferenceStorage = new SharedPreferenceStorage(getContext());
        sharedPreferenceStorage.initializePreferences();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        showProgressBar();
        btnLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_login:
                if(validate()){
                    if(validateCredentials()){
                        if(!sharedPreferenceStorage.getLoginPreference()){
                            showProgressBar();
                            sharedPreferenceStorage.setIsLoggedInPreference(true);
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                            Objects.requireNonNull(getActivity()).finish();
                        }
                    }
                }
                break;

            case R.id.text_forgot_password:
                break;

            case R.id.text_register:
                break;

            case R.id.text_change_password:
                break;

            default:
                break;
        }
    }

    private boolean validateCredentials() {
        return true;
    }

    private boolean validate() {
        if(TextUtils.isEmpty(etUserName.getText().toString())){
            etUserName.setError(getContext().getResources().getString(R.string.please_enter_username));
            return false;
        }

        if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError(getContext().getResources().getString(R.string.please_enter_password));
            return false;
        }else{
            if(etPassword.getText().toString().length()<3){
                etPassword.setError(getContext().getResources().getString(R.string.minimum_three_charaters_required));
                return false;
            }
        }
        return true;
    }

    public void showProgressBar(){
        new CountDownTimer(5000, 100){

            @Override
            public void onTick(long l) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
            }
        };
    }
}