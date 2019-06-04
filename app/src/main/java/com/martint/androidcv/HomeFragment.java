package com.martint.androidcv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;


/**
 * This shows the 'Home' section of my CV.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Make links in fragments TextView clickable
        TextView textView = view.findViewById(R.id.homeTextView);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Set up FAB speed dial
        SpeedDialView speedDialView = view.findViewById(R.id.speedDial);
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.email, R.drawable.ic_email_24)
                        .setLabel(R.string.speed_dial_email)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_linkedIn, R.drawable.ic_linkedin_24)
                        .setLabel(R.string.speed_dial_linkedin)
                        .create()
        );
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_gitHub, R.drawable.ic_github_24)
                        .setLabel(R.string.speed_dial_github)
                        .create()
        );

        return view;
    }
}
