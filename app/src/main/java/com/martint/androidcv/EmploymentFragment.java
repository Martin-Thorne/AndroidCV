package com.martint.androidcv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * This shows the 'Employment' section of my CV.
 */
public class EmploymentFragment extends Fragment implements FragmentSettings {

    TextView textView;

    public EmploymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employment, container, false);

        //Sets the text size
        textView = (TextView) view.findViewById(R.id.employmentTextView);
        textView.setTextSize(Settings.getTextSize());

        return view;
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
