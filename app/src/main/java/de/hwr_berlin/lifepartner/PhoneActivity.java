package de.hwr_berlin.lifepartner;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class PhoneActivity extends LifePartnerActivity
        implements ActionBar.TabListener, PhoneContactsFragment.OnFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * sections. We use a {@link FragmentPagerAdapter} derivative, which will keep every loaded
     * fragment in memory. If this becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    TextView header;
    ImageView tabPhone;
    ImageView tabContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_phone);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        header = (TextView) findViewById(R.id.phone_header_text);
        tabPhone = (ImageView) findViewById(R.id.phone_tab_phone);
        tabContacts = (ImageView) findViewById(R.id.phone_tab_contacts);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                switch (i) {
                    case 0:
                        tabPhone.setBackgroundResource(R.color.app_phone);
                        tabContacts.setBackgroundResource(R.color.app_settings);
                        header.setText(getResources().getString(R.string.app_phone));
                        break;
                    case 1:
                        tabContacts.setBackgroundResource(R.color.app_phone);
                        tabPhone.setBackgroundResource(R.color.app_settings);
                        header.setText(getResources().getString(R.string.app_phone_contacts));
                        break;
                }
            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        setupTabs();
    }

    private void setupTabs() {
        tabPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });

        tabContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
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
        ((TextView) findViewById(R.id.phone_number)).setText("0160133742");
    }

    @Override
    public void onFragmentInteraction(String id) {
        // ...
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PhoneFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PhoneFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static PhoneFragment newInstance(int sectionNumber) {
            PhoneFragment fragment = new PhoneFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_phone, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the
     * sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PhoneFragment.newInstance(position + 1);
                case 1:
                    return PhoneContactsFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.app_phone).toUpperCase(l);
                case 1:
                    return getString(R.string.app_phone_contacts).toUpperCase(l);
            }
            return null;
        }
    }
}
