package com.students.qb365.ui.auth.register;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.students.qb365.R;
import com.students.qb365.utils.AvenuesParams;
import com.students.qb365.utils.DialogUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class WebViewActivity extends AppCompatActivity {

    Intent mainIntent;
    String encVal;
    String vResponse;
    String rsaURL;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_web_view);
        mainIntent = getIntent();

        rsaURL = mainIntent.getStringExtra(AvenuesParams.RSA_KEY_URL);

        Log.e("RSA_URL", rsaURL);

        get_RSA_key(mainIntent.getStringExtra(AvenuesParams.ACCESS_CODE),
                mainIntent.getStringExtra(AvenuesParams.ORDER_ID));

    }


    private class RenderView extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DialogUtil.INSTANCE.showProgressDialog(WebViewActivity.this);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            if (!vResponse.equals("")
                    && vResponse.indexOf("ERROR") == -1) {
                StringBuffer vEncVal = new StringBuffer();
                vEncVal.append(addToPostParams(AvenuesParams.AMOUNT,
                        mainIntent.getStringExtra(AvenuesParams.AMOUNT)));
                vEncVal.append(addToPostParams(AvenuesParams.CURRENCY,
                        mainIntent.getStringExtra(AvenuesParams.CURRENCY)));
                vEncVal.append(addToPostParams("billing_name", "Vishal Bhanderi"));
                vEncVal.append(addToPostParams("billing_address", "Surat Gujarat"));
                vEncVal.append(addToPostParams("billing_zip", "394101"));
                vEncVal.append(addToPostParams("billing_city", "Surat"));
                vEncVal.append(addToPostParams("billing_state", "Gujarat"));
                vEncVal.append(addToPostParams("billing_country", "India"));
                vEncVal.append(addToPostParams("billing_tel", "8787878787"));
                vEncVal.append(addToPostParams("billing_email", "demo@gmail.com"));
//                vEncVal.append(addToPostParams("delivery_name", "Demo Name"));
//                vEncVal.append(addToPostParams("delivery_address", "Surat Gujarat"));
//                vEncVal.append(addToPostParams("delivery_zip", "394101"));
//                vEncVal.append(addToPostParams("delivery_city", "Surat"));
//                vEncVal.append(addToPostParams("delivery_state", "Gujarat"));
//                vEncVal.append(addToPostParams("delivery_country", "India"));
//                vEncVal.append(addToPostParams("delivery_tel", "8787878787"));
//                vEncVal.append(addToPostParams("delivery_email", "vishal@gmail.com"));
                encVal = encrypt(vEncVal.substring(0, vEncVal.length() - 1), vResponse);  //encrypt amount and currency
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            DialogUtil.INSTANCE.dismissProgressDialog();

            @SuppressWarnings("unused")
            class MyJavaScriptInterface {
                @JavascriptInterface
                public void processHTML(String html) {
                    // process the html source code to get final status of transaction
                    String status = null;
                    if (html.indexOf("Failure") != -1) {
                        status = "Transaction Declined!";
                    } else if (html.indexOf("Success") != -1) {
                        status = "Transaction Successful!";
                    } else if (html.indexOf("Aborted") != -1) {
                        status = "Transaction Cancelled!";
                    } else {
                        status = "Status Not Known!";
                    }
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                    Log.e("CC_STATUS", status);
//                    Intent intent = new Intent(getApplicationContext(), StatusActivity.class);
//                    intent.putExtra("transStatus", status);
//                    startActivity(intent);
                }
            }

            final WebView webview = (WebView) findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
            webview.setWebViewClient(new WebViewClient() {
                @Override

                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(webview, url);
                    DialogUtil.INSTANCE.dismissProgressDialog();
                    if (url.indexOf("/ccavResponseHandler.jsp") != -1) {
                        webview.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                    }
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    DialogUtil.INSTANCE.showProgressDialog(WebViewActivity.this);
                }
            });


            try {
                String postData = AvenuesParams.ACCESS_CODE + "=" +
                        URLEncoder.encode(mainIntent.getStringExtra(AvenuesParams.ACCESS_CODE),
                                "UTF-8") + "&" + AvenuesParams.MERCHANT_ID + "=" +
                        URLEncoder.encode(mainIntent.getStringExtra(AvenuesParams.MERCHANT_ID),
                                "UTF-8") + "&" + AvenuesParams.ORDER_ID + "=" +
                        URLEncoder.encode(mainIntent.getStringExtra(AvenuesParams.ORDER_ID),
                                "UTF-8") + "&" + AvenuesParams.REDIRECT_URL + "=" +
                        URLEncoder.encode(mainIntent.getStringExtra(AvenuesParams.REDIRECT_URL),
                                "UTF-8") + "&" + AvenuesParams.CANCEL_URL + "=" +
                        URLEncoder.encode(mainIntent.getStringExtra(AvenuesParams.CANCEL_URL),
                                "UTF-8") + "&" + AvenuesParams.ENC_VAL + "=" +
                        URLEncoder.encode(encVal, "UTF-8");
                webview.postUrl(AvenuesParams.TRANS_URL, postData.getBytes());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
    }

    public String encrypt(String plainText, String key) {
        try {
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(key, Base64.DEFAULT)));
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")), Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void get_RSA_key(final String ac, final String od) {
        DialogUtil.INSTANCE.showProgressDialog(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                rsaURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(WebViewActivity.this,response,Toast.LENGTH_LONG).show();
                        DialogUtil.INSTANCE.dismissProgressDialog();

                        if (response != null && !response.equals("")) {
                            vResponse = response;     ///save retrived rsa key
                            if (vResponse.contains("!ERROR!")) {
                                Log.e("ERROR_PAYMENT", vResponse);
                                show_alert(vResponse);
                            } else {
                                new RenderView().execute();   // Calling async task to get display content
                            }


                        } else {
                            show_alert("No response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogUtil.INSTANCE.dismissProgressDialog();
                        //Toast.makeText(WebViewActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(AvenuesParams.ACCESS_CODE, ac);
                params.put(AvenuesParams.ORDER_ID, od);
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public static String addToPostParams(String paramKey, String paramValue) {
        if (paramValue != null)
            return paramKey.concat(AvenuesParams.PARAMETER_EQUALS).concat(paramValue)
                    .concat(AvenuesParams.PARAMETER_SEP);
        return "";
    }

    public void show_alert(String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                WebViewActivity.this).create();

        alertDialog.setTitle("Error!!!");
        if (msg.contains("\n"))
            msg = msg.replaceAll("\\\n", "");

        alertDialog.setMessage(msg);


        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });


        alertDialog.show();
    }
}