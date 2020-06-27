package com.example.avvmarket.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.avvmarket.MainActivity;
import com.example.avvmarket.R;
import com.example.avvmarket.data.UserDetails;
import com.example.avvmarket.ui.home.HomeFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationsFragment extends Fragment {

    private static final String LOG_TAG = NotificationsFragment.class.getSimpleName();

    private TextView TVfund;
    private Button BTNsignout;
    private TextView TVusername;

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static ChildEventListener mChildEventListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        TVfund = view.findViewById(R.id.TVfund);
        TVusername = view.findViewById(R.id.TVusername);
        BTNsignout = view.findViewById(R.id.BTNsignout);

        TVusername.setText(MainActivity.Username);
        TVfund.setText("Loading Your Funds");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        Log.e(LOG_TAG, MainActivity.uid);
        mDatabaseReference = mFirebaseDatabase.getReference().child("users");

        BTNsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.mDatabaseReference.removeEventListener(HomeFragment.mChildEventListener);
                HomeFragment.mChildEventListener = null;
                AuthUI.getInstance().signOut(getActivity());
            }
        });

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                    if(userDetails.getEmail().equals(MainActivity.email)) {
                        Log.e(LOG_TAG, userDetails.getEmail() + userDetails.getUsername());
                        MainActivity.funds = userDetails.getFunds();
                        TVfund.setText(String.valueOf(MainActivity.funds));
                    }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);
                    if(userDetails.getEmail().equals(MainActivity.email)) {
                        Log.e(LOG_TAG, userDetails.getEmail() + userDetails.getUsername());
                        MainActivity.funds = userDetails.getFunds();
                        TVfund.setText(String.valueOf(MainActivity.funds));
                    }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(LOG_TAG, "Problem retreiving data from database: " + databaseError.getCode());
            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);

        return view;
    }
}