package com.appinventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.appinventory.Helper.SQLiteHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class AddBarangActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper mSQLiteHelper;
    EditText mSafetyStock, mOptimumStock, mCurrentStock, mPrice, mNote, mTags;
    ImageView mImageView;
    Button mBtnAdd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("New Record");

        mSafetyStock = findViewById(R.id.safetyStockField);
        mOptimumStock = findViewById(R.id.optimumStockField);
        mCurrentStock = findViewById(R.id.qtyField);
        mPrice = findViewById(R.id.priceField);
        mNote =  findViewById(R.id.notesField);
        mTags = findViewById(R.id.tagsField);
        mBtnAdd = findViewById(R.id.btnAdd);

//        String kamera = "Kamera";
//        Integer stockSafety = Integer.valueOf(mSafetyStock.getText().toString());

        mImageView = findViewById(R.id.imageView);



        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read external storage permission to select image from gallery
                //runtime permission for devices android 6.0 and above
                ActivityCompat.requestPermissions(
                        AddBarangActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.mSQLiteHelper.insertData(
                            "Kamera",
                            Integer.valueOf(mSafetyStock.getText().toString().trim()),
                            Integer.valueOf(mOptimumStock.getText().toString().trim()),
                            Integer.valueOf(mCurrentStock.getText().toString().trim()),
                            mPrice.getText().toString().trim(),
                            mNote.getText().toString().trim(),
                            mTags.getText().toString().trim(),
                            imageViewToByte(mImageView)
                    );
                    Toast.makeText(AddBarangActivity.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    //reset views
                    mSafetyStock.setText("");
                    mOptimumStock.setText("");
                    mCurrentStock.setText("");
                    mPrice.setText("");
                    mNote.setText("");
                    mTags.setText("");
                    mImageView.setImageResource(R.drawable.addphoto);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON) //enable image guidlines
                    .setAspectRatio(1,1)// image will be square
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result =CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                //set image choosed from gallery to image view
                mImageView.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
