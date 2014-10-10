package de.hwr_berlin.lifepartner;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HealthBloodFragment extends Fragment {

    public static HealthBloodFragment newInstance() {
        HealthBloodFragment fragment = new HealthBloodFragment();
        return fragment;
    }

    public HealthBloodFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_blood, container, false);
    }


}
