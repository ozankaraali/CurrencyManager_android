package com.ozankaraali.Currency_Manager;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by root on 6/8/17.
 */

public class RetrieveCurrencies extends AsyncTask<String,Void,String> {
    String jsonData = "";
    @Override
    protected String doInBackground(String... params) {

        BufferedReader br = null;
        URL a;
        try {
            a = new URL("http://api.fixer.io/latest");
            try {
                String line;
                URL url = a;
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    br = new BufferedReader(new InputStreamReader(in));
                    while ((line = br.readLine()) != null) {
                        jsonData += line + "\n";
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
    } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}
