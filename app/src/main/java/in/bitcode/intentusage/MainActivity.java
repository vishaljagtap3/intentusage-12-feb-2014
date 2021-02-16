package in.bitcode.intentusage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private EditText mEdtPath;
    private Button mBtnShowImage, btnShowImageGal, btnVideo, btnAudio, btnWeb, btnCall, btnPickImage, btnShare;
    private Button mBtnBroadcast, mBtnStickyBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtPath = findViewById(R.id.edtPath);
        mBtnShowImage = findViewById(R.id.btnShowImage);

        mBtnStickyBroadcast = findViewById(R.id.btnStickyBroadcast);
        mBtnStickyBroadcast.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("in.bitcode.some.EVENT");
                        sendStickyBroadcast(intent);
                    }
                }
        );

        mBtnShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setAction("in.bitcode.image.SHOW");
                intent.setDataAndType(
                        Uri.parse(mEdtPath.getText().toString()), "image/jpeg"
                );
                //intent.putExtra("path", mEdtPath.getText().toString());
                startActivity(intent);

            }
        });

        btnShowImageGal = findViewById(R.id.btnShowImageInGal);
        btnShowImageGal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.parse(mEdtPath.getText().toString()), "image/jpeg"
                );

                startActivity(intent);

            }
        });

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setDataAndType(
                        Uri.parse(mEdtPath.getText().toString()), "image/jpeg"
                );
                startActivity(intent);
            }
        });


        btnVideo = findViewById(R.id.btnVideo);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.parse(mEdtPath.getText().toString()), "video/mp4"
                );
                startActivity(intent);
            }
        });

        btnAudio = findViewById(R.id.btnAudio);
        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.parse(mEdtPath.getText().toString()), "audio/mp3"
                );
                startActivity(intent);
            }
        });


        btnWeb = findViewById(R.id.btnWeb);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(
                        Uri.parse(mEdtPath.getText().toString())
                );
                startActivity(intent);
            }
        });

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(
                        Uri.parse(mEdtPath.getText().toString())
                );
                startActivity(intent);
            }
        });

        btnPickImage = findViewById(R.id.btnPickImage);
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        mBtnBroadcast = findViewById(R.id.btnBroadcast);
        mBtnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("in.bitcode.media.download.COMPLETE");
                //Intent intent = new Intent(Intent.ACTION_BATTERY_LOW); //security exception
                intent.putExtra("path", mEdtPath.getText().toString());
                sendBroadcast(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mEdtPath.setText(
              data.getData().toString()
        );
        Log.e("tag", data.getData().toString());
        ((ImageView)findViewById(R.id.img)).setImageURI(data.getData());
    }
}