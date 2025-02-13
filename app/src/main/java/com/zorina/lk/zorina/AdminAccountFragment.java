package com.zorina.lk.zorina;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zorina.lk.zorina.model.AdminSessionManager;


public class AdminAccountFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_admin_account, container, false);

        TextView AdminAccountName=view.findViewById(R.id.adminAccountName);
        TextView AdminAccountEmail=view.findViewById(R.id.adminAccountEmail);
        TextView AdminAccountMobile=view.findViewById(R.id.adminAccountMobile);
        TextView AdminAccountPassword=view.findViewById(R.id.adminAccountPassword);

                AdminSessionManager adminSession= new AdminSessionManager(getContext());
                AdminAccountName.setText(adminSession.getAdmin().getName());
                AdminAccountEmail.setText(adminSession.getAdmin().getEmail());
                AdminAccountMobile.setText(adminSession.getAdmin().getMobile());
//                AdminAccountPassword.setText(adminSession.getAdmin().getPassword());


         return view;

    }
}