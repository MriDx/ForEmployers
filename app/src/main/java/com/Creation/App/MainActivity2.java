package com.Creation.App;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.moos.navigation.BottomBarLayout;
import com.moos.navigation.BottomTabView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        BottomBarLayout bottomBarLayout = findViewById(R.id.navigation_bar_vertical);

        BottomTabView tab_Annual = new BottomTabView(getApplicationContext());
        tab_Annual.setTabIcon(R.drawable.first_logo);
        tab_Annual.setTabTitle("Annual");

        BottomTabView tab_Employee = new BottomTabView(getApplicationContext());

        tab_Employee.setTabTitle("Emp");
        tab_Employee.setTabIcon(R.drawable.icon);


        BottomTabView tab_Monthly = new BottomTabView(getApplicationContext());

        tab_Monthly.setTabTitle("Monthly");
        tab_Monthly.setTabIcon(R.drawable.first_logo);
        BottomTabView tab_Profile = new BottomTabView(getApplicationContext());

        tab_Profile.setTabTitle("Profile");
        tab_Profile.setTabIcon(R.drawable.icon_pro);


        replaceFragment(new com.Creation.App.fragment.Annual());

        bottomBarLayout
                .addTab(tab_Annual)
                .addTab(tab_Monthly)
                .addTab(tab_Employee)
                .addTab(tab_Profile)

                .create(new BottomBarLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(BottomTabView tab) {

                        switch (tab.getTabPosition()) {
                            case 0:
                                replaceFragment(new com.Creation.App.fragment.Annual());
                                break;

                            case 1:
                                replaceFragment(new com.Creation.App.fragment.Monthly());
                                break;

                            case 2:
                                replaceFragment(new com.Creation.App.fragment.Employees());
                                break;
                            case 3:
                                replaceFragment(new com.Creation.App.fragment.Profile());
                                break;

                        }
                    }

                    @Override
                    public void onTabUnselected(BottomTabView tab) {

                    }

                    @Override
                    public void onTabReselected(BottomTabView tab) {

                    }
                });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}