package com.example.cristobalhp.toledoguiado.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cristobalhp.toledoguiado.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoWeb extends Fragment {

    ProgressDialog mProgress;
    WebView webview;
    public FragmentoWeb() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmento_web, container,
                false);

        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        webview = (WebView) rootView.findViewById(R.id.webview1);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        mProgress = ProgressDialog.show(getActivity(), "Loading",
                "Please wait for a moment...");

        webview.loadUrl(url);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mProgress.isShowing()) {
                    mProgress.dismiss();
                }
            }
        });

        return rootView;
    }

}
