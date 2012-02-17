package com.feigdev.fragmentex;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

public class FragmentExampleActivity extends FragmentActivity implements ActionBar.OnNavigationListener{
	Point imgSize;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(this, R.array.locations, R.layout.abs__simple_spinner_item);
        list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSupportActionBar().setListNavigationCallbacks(list, this);
        
    }
    
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		switch ((int)itemId){
		case 0:
			getSupportFragmentManager()
			.beginTransaction()
			.replace(android.R.id.content, GridFragment.newInstance(itemPosition))
			.commit();
			break;
		case 1:
			getSupportFragmentManager()
			.beginTransaction()
			.replace(android.R.id.content, WebFragment.newInstance(itemPosition))
			.commit();
			break;
		}
		return true;
	}
    
}