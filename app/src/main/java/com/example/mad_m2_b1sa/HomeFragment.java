package com.example.mad_m2_b1sa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {
    Button book, reserve;
    WebView webView;

    ImageView img1, img2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        book = root.findViewById(R.id.home_bookbtn);
        reserve = root.findViewById(R.id.home_reservebtn);
        webView = root.findViewById(R.id.home_webview);
        img1 = root.findViewById(R.id.home_image1);
        img2 = root.findViewById(R.id.home_image2);

        img1.setImageResource(R.drawable.cabimg);
        img2.setImageResource(R.drawable.cabimg);

        webView.loadUrl("https://www.uber.com/us/en/drive/driver-app/phone-support/");
        WebSettings webSettings = webView.getSettings();
        webSettings.getJavaScriptEnabled();
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                final String url = String.valueOf(request.getUrl());
                if(url.startsWith("tel:")){
                    Intent telephone = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(telephone);
                    return true;
                } else if(url.startsWith("mailto:")){
                    Intent mail = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                    startActivity(mail);
                    return true;
                } else {
                    view.loadUrl(url);
                }
                return false;
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookFragment bookFragment = new BookFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.FrameLayout,bookFragment).addToBackStack(null).commit();

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setSelectedItemId(R.id.nav_book);

            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookFragment bookFragment = new BookFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.FrameLayout,bookFragment).addToBackStack(null).commit();

                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setSelectedItemId(R.id.nav_book);

            }
        });


        return root;
    }
}