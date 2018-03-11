
package com.reactlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"WeakerAccess", "unused"})
public class RNMyWifiModuleModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private final static String KEY_WIFI_STATUS_CHANGE = "WifiStatusChange";

    private MyWifiManager wifiManager;

    // 監聽 Wifi 狀態改變，即時發送到 JS 端
    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = connMgr != null ? connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI) : null;

            boolean isConnected = wifi != null && wifi.isConnectedOrConnecting();
            WritableMap param = Arguments.createMap();
            if (isConnected) {
                Log.d("Wifi Available ", "YES");
                param.putBoolean("WifiStatus", true);
            } else {
                Log.d("Wifi Available ", "NO");
                param.putBoolean("WifiStatus", false);
            }
            sendEvent(KEY_WIFI_STATUS_CHANGE, param);
        }
    };

    // 在建構子初始化 MyWifiManager
    public RNMyWifiModuleModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.wifiManager = new MyWifiManager(reactContext);

        // 註冊監聽網路狀態
        final IntentFilter filters = new IntentFilter();
        filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filters.addAction("android.net.wifi.STATE_CHANGE");
        reactContext.getApplicationContext().registerReceiver(networkChangeReceiver, filters);
    }

    @Override
    public String getName() {
        return "RNMyWifiModule";
    }


    // 製作一個可以主動發送事件到 JS 端的接口
    private void sendEvent(String eventName,
                           @Nullable WritableMap params) {
        if (reactContext.hasActiveCatalystInstance()) {
            reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        }
    }

    // 回傳 WifiManager 提供的四組狀態常數
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("WIFI_STATE_DISABLED", WifiManager.WIFI_STATE_DISABLED);
        constants.put("WIFI_STATE_DISABLING", WifiManager.WIFI_STATE_DISABLING);
        constants.put("WIFI_STATE_ENABLED", WifiManager.WIFI_STATE_ENABLED);
        constants.put("WIFI_STATE_ENABLING", WifiManager.WIFI_STATE_ENABLING);

        constants.put("EVENT_WIFI_STATUS_CHANGE", KEY_WIFI_STATUS_CHANGE);
        return constants;
    }

    // 製作一個 Promise 函式，使我們可以把 Android 資訊回傳到 JavaScript 端，
    // 並在 JS 端使用 async/await 方式來取得資訊
    // e.g. => const status = await MyWifiManager.getWifiStatus();
    // *Promise 來自 React Native 提供的 com.facebook.react.bridge.Promise 套件
    @ReactMethod
    public void getWifiStatus(Promise promise) {
        try {
            // 回傳狀態
            int statusCode = this.wifiManager.getState(reactContext);
            WritableMap map = Arguments.createMap();
            promise.resolve(statusCode);
        } catch (Exception e) {
            promise.reject(e.getMessage());
        }
    }

    // 取得目前已經連線熱點的 ID，取得 ID 之後可以進行斷線/移除等操作
    @ReactMethod
    public void getCurrentWifiId(Promise promise) {
        try {
            // 回傳狀態
            promise.resolve(this.wifiManager.getNetworkId());
        } catch (Exception e) {
            promise.reject(e.getMessage());
        }
    }

    @ReactMethod
    public void getCurrentWifiBssId(Promise promise) {
        try {
            promise.resolve(this.wifiManager.getBSSID());
        } catch (Exception e) {
            promise.reject(e.getMessage());
        }
    }

    @ReactMethod
    public void getIpAddress(Promise promise) {
        try {
            promise.resolve(this.wifiManager.getIPAddress());
        } catch (Exception e) {
            promise.reject(e.getMessage());
        }
    }


    @ReactMethod
    public void getWifiList(Promise promise) {
        try {
            promise.resolve(this.wifiManager.getWifiList(reactContext));
        } catch (Exception e) {
            promise.reject(e.getMessage());
        }
    }

    @ReactMethod
    public void getExistWifiListString(Promise promise) {
        try {
            this.wifiManager.getWifiList(reactContext);
            String info = this.wifiManager.getWifiListString().toString();
            promise.resolve(info);
        } catch (Exception e) {
            promise.reject(e.getMessage());
        }
    }

    @ReactMethod
    public void getMacAddress(Promise promise) {
        try {
            promise.resolve(this.wifiManager.getMacAddress());
        } catch (Exception e) {
            promise.reject(e.getMessage());
        }
    }

    // 關閉 ＷiFi，這裡使用 event 的方式回傳
    @ReactMethod
    public void turnOffWifi() {
        this.wifiManager.closeWifi(reactContext);
    }

    // 關閉 ＷiFi，這裡使用 event 的方式回傳
    @ReactMethod
    public void turnOnWifi() {
        this.wifiManager.openWifi(reactContext);
    }

}


