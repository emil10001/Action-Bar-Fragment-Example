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

public  class WebFragment extends Fragment{
	int mNum;

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }
    
    public static WebFragment newInstance(int index) {
    	WebFragment f = new WebFragment();

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

	
