package com.example.regis.medsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    TextView titleName, aboutMe, Art, Count;
    CircleImageView circleImageView;
    Button edit;
    SessionManage sessionManage;
    HashMap<String, String> userData;
    Toolbar toolbar;
    Bitmap bitmap;
    RequestQueue requestQueue;
    private final int code = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManage = new SessionManage(getApplicationContext());
        sessionManage.checkLogin();
        userData = sessionManage.getUserDetail();
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar) findViewById(R.id.toolbarProfile);
        circleImageView = (CircleImageView) findViewById(R.id.circleImage);
        circleImageView.setOnClickListener(this);
        checkProfilePic();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            toolbar.setTitle("Account");

        }
        aboutMe = (TextView) findViewById(R.id.aboutMe);
        titleName = (TextView) findViewById(R.id.title_name);
        Art = (TextView) findViewById(R.id.art);
        Count = (TextView) findViewById(R.id.count);
        titleName.setText(userData.get("username"));

        edit = (Button) findViewById(R.id.editor);

        edit.setOnClickListener(this);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DancingScript-Regular.ttf");
        Art.setTypeface(typeface);
        Count.setTypeface(typeface);
        Count.setText(sessionManage.getUserDetail().get("noOfArticle"));
        aboutMe.setTypeface(typeface);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.editor:
                startActivity(new Intent(this, ArticleDemo.class));
                break;
            case R.id.circleImage:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Upload Image");
                builder.setMessage("Do you want to upload a new pic? ");
                builder.setPositiveButton("upload", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, code);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == code && resultCode == RESULT_OK && data != null) {
            Uri myUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), myUri);
                // circleImageView.setImageBitmap(bitmap);
                Glide.with(this).load(myUri).asBitmap().into(circleImageView);
                uploadImageToServer();
                storeImage();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void storeImage() {
        boolean result;
        String path = Environment.getExternalStorageDirectory().getPath();
        File directory = new File(path + "/profile/");
        if (!directory.exists()) {
            result = directory.mkdir();
            Log.v("Result ", "Result =" + result + "path =" + path + "directory " + directory.getAbsolutePath());
        } else {


            File file = new File(directory, sessionManage.getUserDetail().get("phoneNo") + ".jpg");


            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void checkProfilePic() {
        String path = Environment.getExternalStorageDirectory().getPath();
        File directory = new File(path + "/profile/");
        File file = new File(directory, sessionManage.getUserDetail().get("phoneNo") + ".jpg");

        if (file.exists()) {
            Log.v("FILeExist", "" + file.exists());
            InputStream in;
            try {
                in = new FileInputStream(file);
                Bitmap myBit = BitmapFactory.decodeStream(in);
                circleImageView.setImageBitmap(myBit);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void uploadImageToServer() {
        requestQueue = VolleySingle.getInstance().getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://rex7.890m.com/upload.php", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Log.v("message", "" + response);
                if (response.equals("successful")) {
                    Toast.makeText(getApplicationContext(), "changed dp", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Sorry due to some error" + response, Toast.LENGTH_LONG).show();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Message" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> myData = new HashMap<>();
                myData.put("Image", imageTOString(bitmap));
                myData.put("phoneNo", sessionManage.getUserDetail().get("phoneNo"));


                return myData;

            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

    public String imageTOString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_logout:

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Logout?");
                alertDialog.setMessage("Do you want to logout ?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManage.Logout();

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog1 = alertDialog.create();

                alertDialog1.show();


                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
