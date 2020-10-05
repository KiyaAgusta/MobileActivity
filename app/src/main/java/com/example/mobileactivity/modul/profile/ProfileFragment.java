package com.example.mobileactivity.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mobileactivity.R;
import com.example.mobileactivity.base.BaseFragment;
import com.example.mobileactivity.modul.login.LoginActivity;

public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.Presenter> implements ProfileContract.View {
    TextView tvEmail;
    TextView tvPassword;
    Button btnLogout;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        mPresenter = new ProfilePresenter(this);
        mPresenter.start();

        tvEmail = fragmentView.findViewById(R.id.tv_email);
        tvPassword = fragmentView.findViewById(R.id.tv_password);

        setTextView();

        btnLogout = fragmentView.findViewById(R.id.bt_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLogoutClick();
            }
        });

        setTitle("My Profile View");

        return fragmentView;
    }

    public void setTextView() {
        Intent intent = getActivity().getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        tvEmail.setText(email);
        tvPassword.setText(password);
    }

    public void setBtLogoutClick(){
        mPresenter.performLogout();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
