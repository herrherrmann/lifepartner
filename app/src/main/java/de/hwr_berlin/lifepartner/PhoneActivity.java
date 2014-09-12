package de.hwr_berlin.lifepartner;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hwr_berlin.rp5000.R;


public class PhoneActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.phone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void finish(View view) {
        finish();
    }

    public void removeNumber(View view) {
        TextView phoneNumber = (TextView) findViewById(R.id.phone_number);
        int length = phoneNumber.getText().length();
        if (length > 0) {
            phoneNumber.setText(phoneNumber.getText().subSequence(0, length - 1));
        }
    }

    public void addNumber(View view) {
        TextView phoneNumber = (TextView) findViewById(R.id.phone_number);
        if (phoneNumber.getText().length() <= 18) {
            switch (view.getId()) {
                case R.id.btn_phone_0:
                    phoneNumber.append("0");
                    break;
                case R.id.btn_phone_1:
                    phoneNumber.append("1");
                    break;
                case R.id.btn_phone_2:
                    phoneNumber.append("2");
                    break;
                case R.id.btn_phone_3:
                    phoneNumber.append("3");
                    break;
                case R.id.btn_phone_4:
                    phoneNumber.append("4");
                    break;
                case R.id.btn_phone_5:
                    phoneNumber.append("5");
                    break;
                case R.id.btn_phone_6:
                    phoneNumber.append("6");
                    break;
                case R.id.btn_phone_7:
                    phoneNumber.append("7");
                    break;
                case R.id.btn_phone_8:
                    phoneNumber.append("8");
                    break;
                case R.id.btn_phone_9:
                    phoneNumber.append("9");
                    break;
                case R.id.btn_phone_asterisk:
                    phoneNumber.append("*");
                    break;
                case R.id.btn_phone_hash:
                    phoneNumber.append("#");
                    break;
            }
        }
    }

    /**
     * Just a dummy.
     *
     * @param view
     */
    public void redial(View view) {
        TextView phoneNumber = (TextView) findViewById(R.id.phone_number);
        phoneNumber.setText("0160133742");
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PhoneFragment (defined as a static inner class below).
            return PhoneFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PhoneFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PhoneFragment newInstance(int sectionNumber) {
            PhoneFragment fragment = new PhoneFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PhoneFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_phone, container, false);
            return rootView;
        }
    }


}