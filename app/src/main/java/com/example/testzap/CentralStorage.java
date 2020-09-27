package com.example.testzap;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CentralStorage {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Context myContext;

    public CentralStorage(Context myContext) {
        this.myContext = myContext;
        preferences= PreferenceManager.getDefaultSharedPreferences(myContext);
        editor=preferences.edit();
    }
    public void setData(String Key, String Value)
    {
        editor.putString(Key, Value);
        editor.commit();
    }
    public String getData(String Key)
    {
        return preferences.getString(Key,"");
    }
    public void clearData()
    {
        editor.clear();
        editor.commit();
    }
    public void removeData(String Key)
    {
        editor.remove(Key);
        editor.commit();
    }
}
