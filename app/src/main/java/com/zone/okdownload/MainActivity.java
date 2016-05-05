package com.zone.okdownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import download.zone.okhttp.callback.DownloadCallback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String UrlPath="http://101.39.62.207:8089/Test/log";
    Map<String, Object> map = new HashMap<String, Object>();
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_http_test);
        ButterKnife.bind(this);
    }
    @Override
    public void onClick(View v) {
        okdown(v);
    }
    private void okdown(View v) {
        String urlPath = "http://down.360safe.com/360/inst.exe";
        switch (v.getId()) {

            case R.id.bt_downLoader:
                download.zone.okhttp.DownLoader.getInstance(this).startTask(urlPath, FileUtils.getFile(""), new DownloadCallback() {

                    @Override
                    public void onStart() {
                        System.out.println("------------------------onStart!!!------------------------");
                    }

                    @Override
                    public void onProgress(int progress, boolean isDone, long networkSpeed) {
                        System.out.println("页面进度:" + progress + " \t 网速：" + networkSpeed + "k/s");
                        progressBar.setProgress(progress);
                        if (isDone) {
                            System.out.println("------------------------COMPLETE------------------------");
                        }
                    }

                    @Override
                    public void onStop() {
                        System.out.println("------------------------onStop!!!------------------------");
                    }

                    @Override
                    public void onDelete() {
                        System.out.println("------------------------onDelete!!!------------------------");
                    }

                    @Override
                    public void onError(Response response) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onfinished() {

                    }
                });
                break;
            case R.id.bt_downLoaderpause:
                download.zone.okhttp.DownLoader.getInstance(this).stopTask(urlPath);//;urlPath, FileUtils_SD.getFile(""),
                break;
            case R.id.bt_downLoaderDelete:
                download.zone.okhttp.DownLoader.getInstance(this).deleteTask(urlPath);//;urlPath, FileUtils_SD.getFile(""),
                break;
        }
    }
}
