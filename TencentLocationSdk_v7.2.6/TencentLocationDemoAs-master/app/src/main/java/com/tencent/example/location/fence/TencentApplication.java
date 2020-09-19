package com.tencent.example.location.fence;

import android.app.Application;

import com.tencent.map.geolocation.TencentGeofence;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.mapsdk.raster.model.Marker;

import java.util.ArrayList;

/**
 * 继承 Application, 用于在全局范围内保存 TencentGeofence.
 *
 * <p>
 * 注意, TencentGeofenceManager 不会将将当前的 TencentGeofence 持久化保存. 应用程序需要根据实际需求,
 * 使用数据库, 文件, SharedPreference 或 网络等方式自行保存 TencentGeofence.
 */
public class TencentApplication extends Application {

    /**
     * 全局定义，用于定位请求
     */
    private TencentLocationManager mLocationManager;

    /**
     * 用于底图上显示已添加的 TencentGeofence
     */
    private static ArrayList<Marker> sFenceItems = new ArrayList<Marker>();

    /**
     * 用于在 ListView 中显示已添加的 TencentGeofence
     */
    private static ArrayList<TencentGeofence> sFences = new ArrayList<TencentGeofence>();

    /**
     * 记录已触发的 TencentGeofence 事件
     */
    private static ArrayList<String> sEvents = new ArrayList<String>();

    @Override
    public void onCreate() {
        super.onCreate();
        //如果进程是多进程，考虑将TencentLocationManager初始化动作放在应用初始化时，全局使用。
        if (isMainProc()) {
            mLocationManager = TencentLocationManager.getInstance(this);
            // 设置坐标系为 gcj-02, 缺省坐标为 gcj-02, 所以通常不必进行如下调用
            mLocationManager.setCoordinateType(TencentLocationManager.COORDINATE_TYPE_GCJ02);
        }
    }

    private boolean isMainProc() {
        String packageName = getPackageName();
        //需要对比的不是manifest里的packageName，而是gralde里面的applicationId
        return packageName.equals("com.tencent.example.location");
    }

    public TencentLocationManager getLocationManager() {
        return mLocationManager;
    }

    public static ArrayList<Marker> getFenceItems() {
        return sFenceItems;
    }

    public static ArrayList<TencentGeofence> getFence() {
        return sFences;
    }

    /**
     * 返回最新添加的围栏
     */
    public static TencentGeofence getLastFence() {
        if (sFences.isEmpty()) {
            return null;
        }
        return sFences.get(sFences.size() - 1);
    }

    public static ArrayList<String> getEvents() {
        return sEvents;
    }
}
