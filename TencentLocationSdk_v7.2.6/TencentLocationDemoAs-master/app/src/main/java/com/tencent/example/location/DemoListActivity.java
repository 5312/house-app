package com.tencent.example.location;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

public class DemoListActivity extends ListActivity implements OnClickListener {
    private static final String DEMOS_JSON_FILE = "demos.json";

    private static final String TAG = "DemoListActivity";

    private static final String LABEL = "label";

    private static final String NAME = "name";

    private List<DemoEntry> mDemos = new ArrayList<DemoEntry>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载 demo 列表
        loadDemos(DEMOS_JSON_FILE);
        getListView().setAdapter(
                new ArrayAdapter<DemoEntry>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, mDemos));
        String key = DemoUtils.getKey(this);

        // 检查 key 的结构
        if (TextUtils.isEmpty(key)
                || !Pattern.matches("\\w{5}(-\\w{5}){5}", key)) {
            new AlertDialog.Builder(this).setTitle("错误的key")
                    .setMessage("运行前请在manifest中设置正确的key")
                    .setPositiveButton("确定", this).show();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            requirePermission();
        }
    }

    @AfterPermissionGranted(1)
    private void requirePermission() {
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        String[] permissionsForQ = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION, //target为Q时，动态请求后台定位权限
                Manifest.permission.READ_PHONE_STATE
        };
        if (Build.VERSION.SDK_INT >= 29 ? EasyPermissions.hasPermissions(this, permissionsForQ) :
                EasyPermissions.hasPermissions(this, permissions)) {
            Toast.makeText(this, "权限OK", Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "需要权限",
                    1, Build.VERSION.SDK_INT >= 29 ? permissionsForQ : permissions);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        finish();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // 启动目标 demo
        try {
            startActivity(mDemos.get(position).getIntent(this));
        } catch (Exception e) {
            Log.e(TAG, "无法启动 " + mDemos.get(position).name);
        }
    }

    private void loadDemos(String jsonFile) {
        byte[] data = new byte[1024 * 4];
        String content = null;
        try {
            InputStream in = getAssets().open(jsonFile);
            int len = in.read(data);
            in.close();

            content = new String(data, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (content != null) {
            try {
                JSONArray demoArray = new JSONArray(content);
                int size = demoArray.length();
                for (int i = 0; i < size; i++) {
                    JSONObject demoObj = demoArray.getJSONObject(i);
                    mDemos.add(new DemoEntry(demoObj.optString(NAME), demoObj
                            .optString(LABEL)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (DemoEntry e : mDemos) {
            System.out.println(e.getIntent(this));
        }
    }

    static class DemoEntry {
        final String name;
        final String label;

        public DemoEntry(String name, String label) {
            super();
            this.name = name;
            this.label = label;
        }

        Intent getIntent(Context context) {
            try {
                String className = name;
                System.out.println(className);
                return new Intent(context, Class.forName(className));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String toString() {
            return label;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
