package com.example.avvmarket.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.avvmarket.MainActivity;
import com.example.avvmarket.R;
import com.example.avvmarket.data.DatabaseContract.StocksEntry;
import com.firebase.ui.auth.AuthUI;

public class NotificationsFragment extends Fragment {

    private TextView TVfund;
    private Button BTNsignout;
    private TextView TVusername;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        TVfund = view.findViewById(R.id.TVfund);
        TVusername = view.findViewById(R.id.TVusername);
        BTNsignout = view.findViewById(R.id.BTNsignout);

        TVfund.setText(String.valueOf(StocksEntry.FUND));
        TVusername.setText(MainActivity.Username);

        BTNsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(getActivity());
            }
        });

        return view;
    }
}