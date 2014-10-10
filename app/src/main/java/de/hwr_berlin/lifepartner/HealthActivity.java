package de.hwr_berlin.lifepartner;

import java.util.Locale;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HealthActivity extends LifePartnerActivity implements HealthHeartFragment.OnFragmentInteractionListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    ImageView tabMedicine;
    ImageView tabHeart;
    ImageView tabBlood;

    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabMedicine = (ImageView) findViewById(R.id.health_tab_medicine);
        tabHeart = (ImageView) findViewById(R.id.health_tab_heart);
        tabBlood = (ImageView) findViewById(R.id.health_tab_blood);
        header = (TextView) findViewById(R.id.health_header_text);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                switch (i) {
                    case 0:
                        tabMedicine.setBackgroundResource(R.color.app_health);
                        tabHeart.setBackgroundResource(R.color.app_settings);
                        tabBlood.setBackgroundResource(R.color.app_settings);
                        header.setText(getResources().getString(R.string.health_medicine));
                        break;
                    case 1:
                        tabMedicine.setBackgroundResource(R.color.app_settings);
                        tabHeart.setBackgroundResource(R.color.app_health);
                        tabBlood.setBackgroundResource(R.color.app_settings);
                        header.setText(getResources().getString(R.string.health_heart));
                        break;
                    case 2:
                        tabMedicine.setBackgroundResource(R.color.app_settings);
                        tabHeart.setBackgroundResource(R.color.app_settings);
                        tabBlood.setBackgroundResource(R.color.app_health);
                        header.setText(getResources().getString(R.string.health_blood));
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
        tabMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(0);
            }
        });
        tabHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(1);
            }
        });
        tabBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(2);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
            switch (position) {
                case 0:
                    return HealthMedicineFragment.newInstance();
                case 1:
                    return HealthHeartFragment.newInstance();
                case 2:
                    return HealthBloodFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.health_medicine).toUpperCase(l);
                case 1:
                    return getString(R.string.health_heart).toUpperCase(l);
                case 2:
                    return getString(R.string.health_blood).toUpperCase(l);
            }
            return null;
        }
    }


    public static class HealthMedicineFragment extends Fragment {

        public static HealthMedicineFragment newInstance() {
            return new HealthMedicineFragment();
        }

        public HealthMedicineFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_health_medicine, container, false);
            return rootView;
        }
    }

}
