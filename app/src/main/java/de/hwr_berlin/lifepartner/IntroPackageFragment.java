package de.hwr_berlin.lifepartner;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IntroPackageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntroPackageFragment extends Fragment {

    public static IntroPackageFragment newInstance(String param1, String param2) {
        return new IntroPackageFragment();
    }

    public IntroPackageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro_package, container, false);
    }
}
