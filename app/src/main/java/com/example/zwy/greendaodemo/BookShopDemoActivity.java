package com.example.zwy.greendaodemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class BookShopDemoActivity extends AppCompatActivity {


   private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_demo);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.fragment_content);
        if(fragment==null){
            fragment = BookShopFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.fragment_content,fragment).commit();
        }
        new BookShopPresenter(this,(BookShopFragment)fragment);

    }


}
