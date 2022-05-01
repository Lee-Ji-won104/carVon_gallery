package com.sth.opengldemo.Acitivty;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.sth.opengldemo.R;

import java.util.regex.Pattern;

public class SelectVideoFileActivity extends Activity {
    private static final String TAG = "TestActivity";
    private String filePath="~(～￣▽￣)～";

    private Button btn_pick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__video_file);

        // storage permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(this, "외부 저장소 사용을 위해 읽기/쓰기 필요", Toast.LENGTH_SHORT).show();
                }

                requestPermissions(new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
            }
        }

        btn_pick=(Button)findViewById(R.id.pick_video_btn);
        btn_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelectVideoFileActivity.this, FilePickerActivity.class);
                intent.putExtra(FilePickerActivity.ARG_FILTER, Pattern.compile("(.*\\.mp4$)||(.*\\.avi$)||(.*\\.wmv$)||(.*\\.jpg$)"));
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            Intent intent=new Intent(this,MainActivity.class);
            intent.putExtra("filePath",filePath);
            startActivity(intent);
        }
    }

}
