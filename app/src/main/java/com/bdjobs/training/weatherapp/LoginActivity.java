package com.bdjobs.training.weatherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText userNameET, passwordET;
    Button loginBTN;
    String userName, password;
    String baseUrl = "http://my.bdjobs.com/apps/mybdjobs/apps_agent_log.asp";

    ProgressDialog pdialog;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String UserNameKey = "usernameKey";
    public static final String PasswordKey = "passwordKey";
    SharedPreferences sharedpreferences;
    public static final String TAG = "LoginActivity";
    public ArrayList<String> sharedarrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializer();
        onClickListeners();
        sharedarrayList = getSharedPreference();
        String user = sharedarrayList.get(0);
        String pass = sharedarrayList.get(1);

        if(user != null && !user.isEmpty()){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

//        if (user.isEmpty() && pass.isEmpty()) {
//          //  doLogin(user, pass);
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//
//        } else  {
//            //doLogin(user, pass);
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        sharedarrayList = getSharedPreference();
//        String user = sharedarrayList.get(0);
//        String pass = sharedarrayList.get(1);
//        if(user !=null && pass != null){
//            doLogin(user, pass);
//
//        }
//        else {
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        finishAffinity();
        //Log.d(TAG, "back pressed");
    }

    private void onClickListeners() {
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = userNameET.getText().toString();
                password = passwordET.getText().toString();

                if (userName == null || userName.isEmpty() || userName.equalsIgnoreCase(null)) {
                    Toast.makeText(LoginActivity.this, "Username can not be empty!", Toast.LENGTH_SHORT).show();
                } else if (password == null || password.isEmpty() || password.equalsIgnoreCase(null)) {
                    Toast.makeText(LoginActivity.this, "Password can not be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    doLogin(userName, password);

                }


            }
        });
//        logoutBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();
//                editor.commit();
//            }
//        });
    }

    private void doSharedPreference(String usernameShared, String passwordShared) {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(UserNameKey, usernameShared);
        editor.putString(PasswordKey, passwordShared);
        editor.commit();
        Toast.makeText(getApplicationContext(), "shared done", Toast.LENGTH_LONG).show();
        getSharedPreference();
    }

    private ArrayList<String> getSharedPreference() {
        ArrayList<String> arrayList = new ArrayList<>();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String getusernameShared = sharedpreferences.getString("usernameKey", "");
        String getuserpasswordShared = sharedpreferences.getString("passwordKey", "");
        arrayList.add(getusernameShared);
        arrayList.add(getuserpasswordShared);
        // Toast.makeText(getApplicationContext(), getusernameShared+getuserpasswordShared, Toast.LENGTH_LONG).show();
        return arrayList;
    }

    private void doLogin(final String userName1, final String password1) {
          pdialog = ProgressDialog.show(this, "Please wait",
                 "Logging in..", false);
        String url2 = baseUrl + "?userName=" + userName1 + "&password=" + password1;
        // username : Evan ; Password: 123456

        JsonObjectRequest loginRequest = new JsonObjectRequest
                (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (pdialog != null) {
                            pdialog.dismiss();
                        }
                        try {
                            String success = response.getString("success");
                            Toast.makeText(LoginActivity.this, "success = " + success, Toast.LENGTH_SHORT).show();
                            int value = Integer.parseInt(success);
                            if (value == 1) {
                                doSharedPreference(userName1, password1);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                               // sharedpref = true;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        if (pdialog != null) {
                            pdialog.dismiss();
                        }

                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                }) {

          /*@Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               Map<String,String> params = new HashMap<String, String>();
               params.put("Content-Type","application/x-www-form-urlencoded");
              return params;
           }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("userName", userName1);
                params.put("password", password1);
                return params;
            }*/

        };

        MySingleton.getInstance(this).addToRequestQueue(loginRequest);


    }


    private void initializer() {
        userNameET = (EditText) findViewById(R.id.userNameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        loginBTN = (Button) findViewById(R.id.loginBTN);
        //  logoutBTN = (Button) findViewById(R.id.logoutBTN);
    }
}
//check for test
//check for second test
