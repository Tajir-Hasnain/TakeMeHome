package com.example.tajir.takemehome;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Background extends Service {
    public Background() {
    }

    String status;
    String id;
    Integer count = 0;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Status","Reached RequestCount class");
        id = intent.getStringExtra("id");
        String s = intent.getStringExtra("loopCount");
        count = Integer.valueOf(s);
        if(intent.getStringExtra("status").equals("start")) {
            sendStudentStatus();
            Log.d("Status","Background Service started");
        }
        else if(intent.getStringExtra("status").equals("stop")) {
            status = "0";
            delStudentStatus();
        }

        return START_STICKY;
    }

    public void sendStudentStatus() {
        Log.d("POST Count",count.toString());
        if(count == 0 || count == 120) {
            //JSON POST
            RequestQueue queue = Volley.newRequestQueue(this);

            String url = "http://6105b92d.ngrok.io/addstudentrequest";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("POST method", "Success");

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public byte[] getBody() {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("id", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (json != null) {
                        Log.d("POST method JSON object", json.toString());
                        return json.toString().getBytes();
                    }
                    return null;
                }
            };
            queue.add(jsonObjectRequest);
        }

    }

    public void delStudentStatus() {
        Log.d("DELETE Count",count.toString());
        if(count == 0) {
            //JSON POST
            RequestQueue queue = Volley.newRequestQueue(this);

            String url = "http://6105b92d.ngrok.io/delstudentrequest";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("DELETE method", "Success");
                    onDestroy();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public byte[] getBody() {
                    JSONObject json = new JSONObject();
                    try {
                        json.put("id", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (json != null) {
                        Log.d("DELETE method object", json.toString());
                        return json.toString().getBytes();
                    }
                    return null;
                }
            };
            queue.add(jsonObjectRequest);
        }

    }
}
