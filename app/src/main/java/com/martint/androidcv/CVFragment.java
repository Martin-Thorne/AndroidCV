package com.martint.androidcv;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CVFragment extends Fragment {

    private TextView textView;
    private int page;

    /**
     * Used to create an instance of CVFragment
     *
     * @param i states which page of the CV the fragment should use
     * @return
     */
    public static CVFragment newInstance(int i) {
        CVFragment myFragment = new CVFragment();

        Bundle args = new Bundle();
        args.putInt("pageInt", i);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Gets the required page number
        final Bundle args = getArguments();
        page = args.getInt("pageInt", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        // Inflate the layout for this fragment. Home page has FAB speed dial so has separate layout.
        if (page == 0) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            textView = view.findViewById(R.id.homeTextView);
            setFAB(view);

        } else {
            view = inflater.inflate(R.layout.fragment_cv, container, false);
            textView = view.findViewById(R.id.CVTextView);
        }

        // Make links in fragments TextView clickable
        textView.setText(setCVText());
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        //Sets the text size
        textView.setTextSize(getResources().getInteger(Settings.getTextSize()));

        return view;
    }

    /**
     * Set up FAB speed dial
     *
     * @param view
     */
    private void setFAB(View view) {
        SpeedDialView speedDialView = view.findViewById(R.id.speedDial);
        speedDialView.setVisibility(View.VISIBLE);
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
    }

    /**
     * Intent to send email
     */
    private void composeEmail() {
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
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * @return relevant text for each section
     */
    private Spanned setCVText() {
        Spanned text = null;
        switch (page) {
            case 0:
                text = Html.fromHtml(getString(R.string.main_text));
                break;
            case 1:
                text = getCompleteText();
                break;
            case 2:
                text = Html.fromHtml(getString(R.string.about_me_text));
                break;
            case 3:
                text = Html.fromHtml(getString(R.string.education_text));
                break;
            case 4:
                text = Html.fromHtml(getString(R.string.qualification_text));
                break;
            case 5:
                text = Html.fromHtml(getString(R.string.computing_project_text));
                break;
            case 6:
                text = Html.fromHtml(getString(R.string.employment_text));
                break;
            case 7:
                text = Html.fromHtml(getString(R.string.android_apps_text));
                break;
            case 8:
                text = Html.fromHtml(getString(R.string.interests_text));
                break;
        }
        return text;
    }

    /**
     * Uses a string array to create 'Complete CV'.
     *
     * @return complete CV
     */
    private Spanned getCompleteText() {
        // Used to add tags to text to improve layout
        SpannableStringBuilder complete = new SpannableStringBuilder();
        String[] array = getResources().getStringArray(R.array.complete);
        for (int i = 0; i < array.length; i++) {
            // Separates sections header and main text
            if ((i % 2) == 0) {
                // Add tags to header to help separate sections
                complete.append(Html.fromHtml("<b>" + array[i] + "</b>" + "<br>" + "<br>"));
            } else {
                complete.append(Html.fromHtml(array[i] + "<br>"));
            }
        }
        return complete;
    }

    /**
     * Used when user wishes to changes text size
     *
     * @param textSelection    Indicates if user wants to increase/decrease text size
     * @param fragmentSelected
     */
    public int setTextSize(int textSelection, int fragmentSelected) {
        //If fragment was the first in collection it sets the text size
        if (fragmentSelected == 0) {
            Settings.setTextSize(textSelection);
        }
        textView.setTextSize(getResources().getInteger(Settings.getTextSize()));
        return Settings.getTextSize();
    }
}
