package com.feigdev.fragmentex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebFragment extends FragmentActivity {
    int mStackLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);


        if (savedInstanceState == null) {
            // Do first time initialization -- add initial fragment.
            Fragment newFragment = Web.newInstance(mStackLevel);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.web_login, newFragment).commit();
        } else {
            mStackLevel = savedInstanceState.getInt("level");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", mStackLevel);
    }


    void addFragmentToStack() {
        mStackLevel++;

        // Instantiate a new fragment.
        Fragment newFragment = Web.newInstance(mStackLevel);

        // Add the fragment to the activity, pushing this transaction
        // on to the back stack.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.web_login, newFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    public static class Web extends Fragment{
    	int mNum;

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }
        
        public static Web newInstance(int index) {
        	Web f = new Web();

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putInt("index", index);
            f.setArguments(args);

            return f;
        }
    	
        /** Called when the activity is first created. */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (container == null){
            	return null;
            }
            
            View web = inflater.inflate(R.layout.web, container, false);
            ((WebView)(web.findViewById(R.id.web_login))).getSettings().setJavaScriptEnabled(true);
            ((WebView)(web.findViewById(R.id.web_login))).setWebViewClient(new HelloWebViewClient());
            ((WebView)(web.findViewById(R.id.web_login))).loadUrl("http://www.google.com");
            return web;
        }
        
        private class HelloWebViewClient extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }
        
        
        


    }

	
}