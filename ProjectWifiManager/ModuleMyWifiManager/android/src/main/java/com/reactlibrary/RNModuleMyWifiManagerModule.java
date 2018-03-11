
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNModuleMyWifiManagerModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNModuleMyWifiManagerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNModuleMyWifiManager";
  }
}