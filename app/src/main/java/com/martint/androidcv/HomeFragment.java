package com.martint.androidcv;


import android.content.Intent;
import android.net.Uri;
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
public class HomeFragment extends Fragment implements FragmentSettings {

    TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Make links in fragments TextView clickable
        textView = view.findViewById(R.id.homeTextView);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        //Sets the text size
        textView.setTextSize(Settings.getTextSize());

        // Set up FAB speed dial
        SpeedDialView speedDialView = view.findViewById(R.id.speedDial);
        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_email, R.drawable.ic_email_24)
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

        //Set up FAB speed dial links
        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem speedDialActionItem) {
                switch (speedDialActionItem.getId()) {
                    case R.id.fab_email:
                        composeEmail();
                        return false; // true to keep the Speed Dial open
                    case R.id.fab_linkedIn:
                        openWebPage("https://www.linkedin.com/in/martinjthorne/");
                        return false;
                    case R.id.fab_gitHub:
                        openWebPage("https://github.com/Martin-Thorne");
                        return false;
                    default:
                        return false;
                }
            }
        });
        return view;
    }

    /**
     * Intent to send email
     */
    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:martinjthorne@gmail.com"));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Intent to open web page
     *
     * @param url
     */
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Used when user wishes to changes text size
     *
     * @param textSelection    Indicates if user wants to increase/decrease text size
     * @param fragmentSelected
     */
    @Override
    public void setTextSize(int textSelection, int fragmentSelected) {
        //If fragment was the first in collection it sets the text size
        if (fragmentSelected == 0) {
            Settings.setTextSize(textSelection);
        }
        textView.setTextSize(Settings.getTextSize());
    }
}
