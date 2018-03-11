
package com.reactlibrary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;
public class RNMyToastModulePackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {

        // 回傳 RNMyToastModuleModule 這個 native module，可以有多個 module
      return Arrays.<NativeModule>asList(new RNMyToastModuleModule(reactContext));
    }

    // Deprecated from RN 0.47
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        // 舊版的建構方式
      return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        // react-native-create-library 目前不支援自動產生 UI 類 native module
      return Collections.emptyList();
    }
}