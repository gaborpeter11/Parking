package com.example.android.wirecardparking.utils;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    private final static String MOVIEDB_BASE_URL = "http://10.0.3.2:8090/parking/rest-api/v1/register/user/";


    //private final static String PARAM_QUERY = "api_key";
    private final static String PARAM_KEY = "c88f3eabe09958ae472c9cd7e20b38aa";

    /**
     * Builds the URL.
     * Vysklada URL adresu
     */
    public static URL buildUrl(String searchQuery) {

        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendEncodedPath(searchQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println(url.toString());
        return url;
    }


    public static String putOnServer2() throws IOException {


        URL url = null;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(MOVIEDB_BASE_URL);
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }

        BufferedReader br;
        StringBuilder sb = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);

            String type = "application/json";
            String accept = "application/json";
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", accept);

            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            // create function for this
            JSONObject parameters = new JSONObject();
            parameters.put("salutation", "MR");
            parameters.put("first-name", "Pavol");
            parameters.put("last-name", "MATE");
            parameters.put("mobile-number", "+44875464469");
            parameters.put("email", "pavol.mate@wirecard.com");
            parameters.put("user-name", "44875464467i7407v1.14.2");
            parameters.put("password", "aaaa1111");
            parameters.put("security-question", "BEST_CHILDHOOD_FRIEND_NAME");
            parameters.put("security-answer", "Willi");
            parameters.put("terms-of-use-accepted", "true");

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(parameters.toString());
            writer.flush();
            writer.close();
            os.close();

            if(httpURLConnection.getResponseCode() == 200)
                br = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
            else
                br = new BufferedReader(new InputStreamReader((httpURLConnection.getErrorStream())));

            sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return sb.toString();

    }


    /**
     * This method returns the entire result from the HTTP response.
     * Vrati response zo stranky (cely JSON)
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {

        String PUUT = "http://10.0.3.2:8090/parking/rest-api/v1/user/+44875464469/profile";
        URL url1 = new URL(PUUT);

        HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();

        String type = "application/json";
        String accept = "application/json";
        urlConnection.setRequestProperty("Content-Type", type);
        urlConnection.setRequestProperty("Accept", accept);

        try {
            urlConnection.setConnectTimeout(5000);  // if there are some connection problems wait 5 seconds

            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }




}