package com.example.avvmarket.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.avvmarket.R;
import com.example.avvmarket.data.DatabaseContract.StocksEntry;

public class NotificationsFragment extends Fragment {

    private TextView TVfund;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        TVfund = view.findViewById(R.id.TVfund);

        TVfund.setText(String.valueOf(StocksEntry.FUND));

        return view;
    }
}