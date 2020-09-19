package com.tencent.example.location;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

public class DemoIntervalActivity extends Activity implements OnClickListener,
        TencentLocationListener {
    /**
     * 周期列表, 这些周期仅用于演示, 除此之外您还可以按自己需要设置其他值.
     */
    private static final long[] INTERVALS = new long[]{2 * 1000, 3 * 1000,
            5 * 1000, 10 * 1000};
    private static final int DEFAULT = 2;

    private static final String NOTIFICATION_CHANNEL_NAME = "locationdemoBackgroundLocation";
    private static final int LOC_NOTIFICATIONID = 100;

    private int mIndex = DEFAULT;
    private long mInterval = INTERVALS[DEFAULT];

    private TencentLocationManager mLocationManager;
    private TextView mLocationStatus;
    /**
     * 使用一个标志, 防止 requestLocationUpdates 和 removeUpdates 被多次重复调用.
     * (注,多次调用不会产生任何问题)
     */
    private boolean mStarted;
    private boolean isCreateChannel;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_template);
        mLocationStatus = (TextView) findViewById(R.id.status);

        Button settings = ((Button) findViewById(R.id.settings));
        settings.setText("周期");
        settings.setVisibility(View.VISIBLE);

        mLocationManager = TencentLocationManager.getInstance(this);
        // 设置坐标系为 gcj-02, 缺省坐标为 gcj-02, 所以通常不必进行如下调用
        mLocationManager
                .setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
        mLocationManager.triggerCodeGuarder(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出 activity 前一定要停止定位!
        stopLocation(null);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        mIndex = which;
        mInterval = INTERVALS[which];
        dialog.dismiss();
    }

    // ====== view listener

    // 响应点击"停止"
    public void stopLocation(View view) {
        if (mStarted) {
            mStarted = false;
            mLocationManager.removeUpdates(this);
            mLocationManager.disableForegroundLocation(true);
            updateLocationStatus("停止定位");
        }
    }

    // 响应点击"开始"
    public void startLocation(View view) {
        if (!mStarted) {
            mStarted = true;
            // 创建定位请求
            TencentLocationRequest request = TencentLocationRequest
                    .create()
                    .setRequestLevel(
                            TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA)
                    .setInterval(mInterval); // 设置定位周期, 建议值为 1s-20s

            // 开始定位
            mLocationManager.enableForegroundLocation(LOC_NOTIFICATIONID, buildNotification());
            mLocationManager.requestLocationUpdates(request, this, getMainLooper());

            updateLocationStatus("开始定位: " + request + ", 坐标系="
                    + DemoUtils.toString(mLocationManager.getCoordinateType()));
        }
    }

    public void clearStatus(View view) {
        mLocationStatus.setText(null);
    }

    public void settings(View view) {
        Builder builder = new Builder(this).setSingleChoiceItems(
                new String[]{"2s", "3s", "5s", "10s"}, mIndex, this);
        builder.show();
    }

    // ====== view listener

    // ====== location callback

    @Override
    public void onLocationChanged(TencentLocation location, int error,
                                  String reason) {
        String msg = null;
        if (error == TencentLocation.ERROR_OK) {
            // 定位成功
            StringBuilder sb = new StringBuilder();
            sb.append("(纬度=").append(location.getLatitude()).append(",经度=")
                    .append(location.getLongitude()).append(",精度=")
                    .append(location.getAccuracy()).append("), 来源=")
                    .append(location.getProvider()).append(", 城市=")
                    .append(location.getCity()).append(", citycode=")
                    .append(location.getCityCode());
            msg = sb.toString();
        } else {
            // 定位失败
            msg = "定位失败: " + reason;
        }
        updateLocationStatus(msg);
    }

    @Override
    public void onStatusUpdate(String name, int status, String desc) {
        // ignore
    }

    // ====== location callback

    private void updateLocationStatus(String message) {
        mLocationStatus.append(message);
        mLocationStatus.append("\n---\n");
    }

    private Notification buildNotification() {
        Notification.Builder builder = null;
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            //Android O上对Notification进行了修改，如果设置的targetSDKVersion>=26建议使用此种方式创建通知栏
            if (notificationManager == null) {
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }
            String channelId = getPackageName();
            if (!isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(channelId,
                        NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.enableLights(true);//是否在桌面icon右上角展示小圆点
                notificationChannel.setLightColor(Color.BLUE); //小圆点颜色
                notificationChannel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                notificationManager.createNotificationChannel(notificationChannel);
                isCreateChannel = true;
            }
            builder = new Notification.Builder(getApplicationContext(), channelId);
        } else {
            builder = new Notification.Builder(getApplicationContext());
        }
        builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("LocationDemo")
                .setContentText("正在后台运行")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setWhen(System.currentTimeMillis());

        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            notification = builder.getNotification();
        }
        return notification;
    }

}
