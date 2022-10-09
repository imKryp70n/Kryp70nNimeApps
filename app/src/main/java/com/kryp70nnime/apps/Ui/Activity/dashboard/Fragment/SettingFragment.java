package com.kryp70nnime.apps.Ui.Activity.dashboard.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import android.view.View;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.derohimat.sweetalertdialog.SweetAlertDialog;
import com.kryp70nnime.apps.Ui.Activity.About.AboutMeActivity;
import com.kryp70nnime.apps.Ui.Activity.splash.Splash;
import com.kryp70nnime.apps.R;

public class SettingFragment extends PreferenceFragmentCompat {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Auth0 account = new Auth0("HdF1WaupDYNymQ4ZGsDURCzmL0TgBZf1", "dev-kh7kgb0v.us.auth0.com");
        //Auth0 account = new Auth0(getContext());
        final FragmentManager fragmentManager = getFragmentManager();
        final SettingFragment settingFragment = new SettingFragment();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SwitchPreferenceCompat switchPreferenceCompat;
        switchPreferenceCompat = findPreference("KEY_NightMode");
        switchPreferenceCompat.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.equals(true)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putInt("NightMode", 1);
                    editor.apply();
                    Intent intent = new Intent(getActivity(), Splash.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putInt("NightMode", 0);
                    editor.apply();
                    Intent intent = new Intent(getActivity(), Splash.class);
                    startActivity(intent);
                    getActivity().finish();
                }
                return true;
            }
        });

        Preference preference = findPreference("KEY_Profile");
        preference.setOnPreferenceClickListener(preference1 -> {
            Intent intent = new Intent(getActivity(), AboutMeActivity.class);
            startActivity(intent);
            return true;
        });


        Preference loginUser = findPreference("KEY_Login");
        loginUser.setOnPreferenceClickListener(preference1 -> {
            Callback<Credentials, AuthenticationException> callback = new Callback<Credentials, AuthenticationException>() {
                @Override
                public void onFailure(@NonNull AuthenticationException exception) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("ERROR");
                    sweetAlertDialog.setContentText(exception.getDescription());
                    sweetAlertDialog.setConfirmText("OK");
                    sweetAlertDialog.setConfirmClickListener(sweetAlertDialog1 -> {
                        sweetAlertDialog1.dismissWithAnimation();
                    });
                    sweetAlertDialog.show();
                    //failed with an exception
                }

                @Override
                public void onSuccess(@Nullable Credentials credentials) {
                    //success
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("Login Success");
                    sweetAlertDialog.setContentText(credentials.getUser().getNickname());
                    sweetAlertDialog.setConfirmText("OK");
                    sweetAlertDialog.setConfirmClickListener(sweetAlertDialog1 -> {
                        sweetAlertDialog1.dismissWithAnimation();
/*                        Intent intent = new Intent(getActivity(), Splash.class);
                        startActivity(intent);*/
                    });
                    sweetAlertDialog.show();

                }
            };

            WebAuthProvider.login(account)
                    .withScheme("https")
                    .withScope("openid profile email")
                    .start(getActivity(), callback);

            return true;
        });

        Preference logoutUser = findPreference("KEY_Logout");
        logoutUser.setOnPreferenceClickListener(preference1 -> {
            Callback<Void, AuthenticationException> callback = new Callback<Void, AuthenticationException>() {

                @Override
                public void onSuccess(Void unused) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("Logout Success");
                    sweetAlertDialog.setConfirmText("OK");
                    sweetAlertDialog.setConfirmClickListener(sweetAlertDialog1 -> {
                        sweetAlertDialog1.dismissWithAnimation();
                        Intent intent = new Intent(getActivity(), Splash.class);
                        startActivity(intent);
                    });
                    sweetAlertDialog.show();
                }

                @Override
                public void onFailure(@NonNull AuthenticationException e) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("ERROR");
                    sweetAlertDialog.setContentText(e.getDescription());
                    sweetAlertDialog.setConfirmText("OK");
                    sweetAlertDialog.setConfirmClickListener(sweetAlertDialog1 -> {
                        sweetAlertDialog1.dismissWithAnimation();
                    });
                    sweetAlertDialog.show();
                }
            };

            WebAuthProvider.logout(account)
                    .withScheme("https")
                    .start(getActivity(),callback);
            return true;
        });


/*
            Callback<Void, AuthenticationException> logoutCallback = new Callback<Void, AuthenticationException>() {
                @Override
                public void onFailure(@NonNull AuthenticationException e) {

                }

                @Override
                public void onSuccess(@Nullable Void payload) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("Login Success");
                    sweetAlertDialog.setConfirmText("OK");
                    sweetAlertDialog.setConfirmClickListener(sweetAlertDialog1 -> {
                        sweetAlertDialog1.dismissWithAnimation();
                    });
                    sweetAlertDialog.show();
                }
            };

            WebAuthProvider.logout(account)
                    .start(getContext(), logoutCallback);

*/


    }


}