package com.bananasik.recipecollection;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MyFragmentManager {
    FragmentManager fragmentManager;

    public MyFragmentManager(FragmentManager fm){
        fragmentManager = fm;
    }

    public void addFragment(int containerViewId, Fragment fragment, String tag, boolean toBackStack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerViewId, fragment, tag);
        if(toBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment, String tag, boolean toBackStack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment, tag);
        if(toBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void removeFragment(Fragment fragment, boolean toBackStack){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        if(toBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
