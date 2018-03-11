import { NativeModules, DeviceEventEmitter } from 'react-native';

const { RNModuleMyWifiManager } = NativeModules;

// 如果 export 的 native module package 有多個 module，
// 可以在這裡指定哪個 function 呼叫哪個 module method
RNModuleMyWifiManager.getWifiStatus = RNModuleMyWifiManager.getWifiStatus;

// 定義事件 key-map
const eventsMap = {
  WifiStatusChange: 'WifiStatusChange',
};

// 定義 Event Listener function，可以在 RN 專案內使用
RNModuleMyWifiManager.on = (event, callback) => {
  const nativeEvent = eventsMap[event];
  if (!nativeEvent) {
    throw new Error('@index.js: Invalid event');
  }
  DeviceEventEmitter.removeAllListeners(nativeEvent);
  return DeviceEventEmitter.addListener(nativeEvent, callback);
}

export default RNModuleMyWifiManager;
