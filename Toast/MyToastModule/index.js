
import { NativeModules } from  'react-native';

// 由 react native 套件中取出已經註冊的 Native module
// 這裡的名稱就是我們寫在 getName() 中回傳的名稱
// 注意：`RN` 開頭的字串會被替換掉，所以實際使用的 module 名稱會是 `MyToastModule`
const { RNMyToastModule } = NativeModules;

export default RNMyToastModule;