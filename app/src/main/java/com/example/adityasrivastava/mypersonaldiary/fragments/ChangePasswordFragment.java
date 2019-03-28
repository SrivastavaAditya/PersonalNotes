package com.example.adityasrivastava.mypersonaldiary.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adityasrivastava.mypersonaldiary.R;
import com.example.adityasrivastava.mypersonaldiary.dbHelper.SQLiteHelper;
import com.example.adityasrivastava.mypersonaldiary.events.LogoutEvent;
import com.example.adityasrivastava.mypersonaldiary.utils.Utility;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

/**
 * The type Change password fragment.
 */
public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The Et user name.
     */
    @BindView(R.id.edit_cp_username)
    EditText etUserName;

    /**
     * The Et old password.
     */
    @BindView(R.id.edit_cp_old_password)
    EditText etOldPassword;

    /**
     * The Et new password.
     */
    @BindView(R.id.edit_cp_new_password)
    EditText etNewPassword;

    /**
     * The Et confirm password.
     */
    @BindView(R.id.edit_cp_confirm_password)
    EditText etConfirmPassword;

    /**
     * The Btn change password.
     */
    @BindView(R.id.button_change_password)
    Button btnChangePassword;

    /**
     * Instantiates a new Change password fragment.
     */
    public ChangePasswordFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_change_password, container,
                false);
        unbinder = ButterKnife.bind(this, rootView);
        btnChangePassword.setOnClickListener(this);
        return rootView;
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
        switch (view.getId()){
            case R.id.button_change_password:
                if(validate()){
                    if(SQLiteHelper.getInstance(getContext())
                            .checkUserCredentials(etUserName.getText().toString(),
                                    etOldPassword.getText().toString())){
                        int changed = SQLiteHelper.getInstance(getContext())
                                .changeCredentials(SQLiteHelper.getInstance(getContext())
                                        .getUserId(etUserName.getText().toString()),
                                        etUserName.getText().toString(),
                                        etNewPassword.getText().toString());
                        if(changed>0){
                            EventBus.getDefault().post(new LogoutEvent());
                            Utility.showMessage(getContext(), getResources()
                                    .getString(R.string.password_changed_successfully));
                            Objects.requireNonNull(getActivity()).onBackPressed();
                        }else{
                            Utility.showMessage(getContext(), getResources()
                                    .getString(R.string.could_not_change_password));
                            Objects.requireNonNull(getActivity()).onBackPressed();
                        }
                    }
                }
                break;

            default:
                break;
        }
    }

    private boolean validate() {
        if(TextUtils.isEmpty(etUserName.getText().toString())){
            etUserName.setError(getResources().getString(R.string.please_enter_username));
            return false;
        }

        if(TextUtils.isEmpty(etOldPassword.getText().toString())){
            etOldPassword.setError(getResources().getString(R.string.please_enter_password));
            return false;
        }

        if(TextUtils.isEmpty(etNewPassword.getText().toString())){
            etNewPassword.setError(getResources().getString(R.string.please_enter_password));
            return false;
        }

        if(TextUtils.isEmpty(etConfirmPassword.getText().toString())){
            etConfirmPassword.setError(getResources().getString(R.string.please_enter_password));
            return false;
        }
        return true;
    }
}