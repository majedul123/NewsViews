package com.example.newsviews.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.newsviews.Activity.Login;
import com.example.newsviews.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExitFragment extends Fragment {

    GoogleApiClient mGoogleApiClient;

    public static final String PREFS_NAME = "LoginPrefs";
    @BindView(R.id.signout)
    Button bt_signout;

    @OnClick(R.id.signout)
    void singining_out() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                        Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                    }
                });

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_exit, container, false);

        ButterKnife.bind(this, root);



        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("logged");
        editor.commit();

        return root;
    }

    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();


//        bt_signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                        new ResultCallback<Status>() {
//                            @Override
//                            public void onResult(Status status) {
//                                // ...
//                                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
//            }
//        });


    }
}