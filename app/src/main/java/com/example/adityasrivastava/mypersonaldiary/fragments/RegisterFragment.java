package com.example.adityasrivastava.mypersonaldiary.fragments;

import android.content.Context;
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

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.dbHelper.SQLiteHelper;
import com.example.adityasrivastava.mypersonaldiary.models.UserCredentials;
import com.example.adityasrivastava.mypersonaldiary.utils.preferences.SharedPreferenceStorage;

import java.util.Objects;

/**
 * The type Register fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

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
    @BindView(R.id.edit_user_name_register)
    EditText etUserName;

    /**
     * The Et password.
     */
    @BindView(R.id.edit_password_register)
    EditText etPassword;

    /**
     * The Et confirm password.
     */
    @BindView(R.id.edit_confirm_password_register)
    EditText etConfirmPassword;

    /**
     * The Btn register.
     */
    @BindView(R.id.button_register)
    Button btnRegister;

    /**
     * The Progress bar.
     */
    @BindView(R.id.progress_bar_register)
    ProgressBar progressBar;

    /**
     * Instantiates a new Register fragment.
     */
    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPreferenceStorage = new SharedPreferenceStorage(getContext());
        sharedPreferenceStorage.initializePreferences();

        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        showProgressBar();
        btnRegister.setOnClickListener(this);
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
            case R.id.button_register:
                if(validate()){
                    if(SQLiteHelper.getInstance(getContext()).isUserNameUnique(
                            etUserName.getText().toString())){
                        showProgressBar();
                        if(!sharedPreferenceStorage.getRegisterPreference()) {
                            sharedPreferenceStorage.setRegisterPreference(true);
                            sharedPreferenceStorage.setIsLoggedInPreference(false);
                            UserCredentials user = new UserCredentials();
                            user.userId = user._id;
                            user.userName = etUserName.getText().toString();
                            user.userPassword = etPassword.getText().toString();
                            SQLiteHelper.getInstance(getContext()).insert(user);
                        }
                        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                                .beginTransaction().replace(android.R.id.content, new LoginFragment())
                                .commit();
                    }else{
                        etUserName.setError(getContext().getResources()
                                .getString(R.string.user_name_already_exists));
                    }
                }
                break;

            default:
                break;
        }
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

        if(TextUtils.isEmpty(etConfirmPassword.getText().toString())){
            etConfirmPassword.setError(getContext().getResources().getString(R.string.please_enter_password));
            return false;
        }else{
            if(etConfirmPassword.getText().toString().length()<3){
                etConfirmPassword.setError(getContext().getResources().getString(R.string.minimum_three_charaters_required));
                return false;
            }

            if(!etConfirmPassword.getText().toString().equals(etPassword.getText().toString())){
                etConfirmPassword.setError(getContext().getResources().getString(R.string.pasword_confirm_password_do_not_match));
                return false;
            }
        }

        return true;
    }

    /**
     * Show progress bar.
     */
    public void showProgressBar(){
        new CountDownTimer(1000, 100){

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