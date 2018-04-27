package com.reactlibrary;


import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kent on 2018/2/1.
 */


@SuppressWarnings("unused")
public class MyWifiManager {
    private WifiManager mWifiManager;
    private WifiInfo mWifiInfo;

    private List<ScanResult> mWifiList;
    private List<WifiConfiguration> mWifiConfiguration;

    private WifiLock mWifiLock;


    public MyWifiManager(Context context) {
        mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        mWifiInfo = mWifiManager != null ? mWifiManager.getConnectionInfo() : null;
    }

    // 開啟 WIFI
    public void openWifi(Context context) {
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        } else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
            Toast.makeText(context, "Wifi 正在開啟中...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Wifi 已經開啟", Toast.LENGTH_SHORT).show();
        }
    }

    // 關閉 WIFI
    public void closeWifi(Context context) {
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        } else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
            Toast.makeText(context, "Wifi 已經處於關閉狀態", Toast.LENGTH_SHORT).show();
        } else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING) {
            Toast.makeText(context, "Wifi 關閉中，請稍候...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "請嘗試再次關閉 Wifi", Toast.LENGTH_SHORT).show();
        }
    }

    // 檢查並取得目前 WIFI 狀態
    public int getState(Context context) {
        if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLING) {
            Toast.makeText(context, "Wifi 關閉中...", Toast.LENGTH_SHORT).show();
        } else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
            Toast.makeText(context, "Wifi 已經關閉", Toast.LENGTH_SHORT).show();
        } else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
            Toast.makeText(context, "Wifi 正在開啟...", Toast.LENGTH_SHORT).show();
        } else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
            Toast.makeText(context, "Wifi已經開啟", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "無法取得 WIFI 狀態", Toast.LENGTH_SHORT).show();
        }
        return mWifiManager.getWifiState();
    }

    // 啟用 WifiLock，鎖定狀態
    public void acquireWifiLock() {
        mWifiLock.acquire();
    }

    // 解開 WifiLock，使狀態可以改變
    public void releaseWifiLock() {
        // 判斷是否已經鎖定
        if (mWifiLock.isHeld()) {
            mWifiLock.acquire();
        }
    }

    // 建立一個 WifiLock
    public void createWifiLock() {
        mWifiLock = mWifiManager.createWifiLock("Test");
    }

    // 取得目前的網路設定陣列
    public List<WifiConfiguration> getConfiguration() {
        return mWifiConfiguration;
    }

    // 指定連線到某個網路設定
    public void connectConfiguration(int index) {
        // 如果給予的索引不在陣列範圍內
        if (index > mWifiConfiguration.size()) {
            return;
        }
        // 連線到指定的設定檔
        mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId,
                true);
    }

    // 掃描周遭無線網路
    public List<ScanResult> getWifiList(Context context) {
        mWifiManager.startScan();
        // 儲存掃描結果
        List<ScanResult> results = mWifiManager.getScanResults();

        // 取得網路設定
        mWifiConfiguration = mWifiManager.getConfiguredNetworks();

        if (results == null) {
            if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
                Toast.makeText(context, "附近沒有可用的無線網路", Toast.LENGTH_SHORT).show();
            } else if (mWifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {
                Toast.makeText(context, "Ｗifi 正在開啟，請稍後重試掃瞄", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "WiFi 未開啟", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d("MyWifiManager", "getWifiList result=>" + TextUtils.join(", ", results));
            mWifiList = new LinkedList<>();
            mWifiList.clear();
            for (ScanResult result : results) {
                if (result.SSID == null || result.SSID.length() == 0 || result.capabilities.contains("[IBSS]")) {
                    continue;
                }
                boolean found = false;
                for (ScanResult item : mWifiList) {
                    if (item.SSID.equals(result.SSID) && item.capabilities.equals(result.capabilities)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    mWifiList.add(result);
                }
            }
        }
        return mWifiList;
    }

    // 把取得的 Wifi 掃描結果轉成字串輸出
    public StringBuilder getWifiListString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mWifiList.size(); i++) {
            stringBuilder.append("Index_").append(Integer.valueOf(i + 1).toString()).append(":");
            //            BSSID、SSID、capabilities、frequency、level
            stringBuilder.append((mWifiList.get(i)).toString());
            stringBuilder.append("/n");
        }
        return stringBuilder;
    }

    // 取得本機 MAC 地址
    public String getMacAddress() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
    }

    // 取得目前連線熱點的 BSSID
    public String getBSSID() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
    }

    // 取得 IP 地址
    public String getIPAddress() {
        if (mWifiInfo == null) {
            return null;
        }
        String ipAddressString = "";
        try {
            int ipAddress = mWifiInfo.getIpAddress();
            byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFIIP", "Unable to get host address.");
            ipAddressString = null;
        }
        return ipAddressString;
    }

    // 取得目前連線熱點的 ID
    public int getNetworkId() {
        return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
    }

    // 取得 WifiInfo 物件的所有資訊
    public String getWifiInfo() {
        return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
    }

    // 手動增加一組網路設定
    public void addNetwork(WifiConfiguration wcg) {
        int wcgID = mWifiManager.addNetwork(wcg);
        boolean b = mWifiManager.enableNetwork(wcgID, true);
        System.out.println("a--" + wcgID);
        System.out.println("b--" + b);
    }

    // 手動切斷某個 id 的網路連線
    public void disconnectWifi(int netId) {
        mWifiManager.disableNetwork(netId);
        mWifiManager.disconnect();
    }

    // 移除某個 id 的網路設定檔案
    public void removeWifi(int netId) {
        disconnectWifi(netId);
        mWifiManager.removeNetwork(netId);
    }
}