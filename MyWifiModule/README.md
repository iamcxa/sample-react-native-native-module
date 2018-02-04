
# react-native-my-wifi-module

## Getting started

`$ npm install react-native-my-wifi-module --save`

### Mostly automatic installation

`$ react-native link react-native-my-wifi-module`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMyWifiModulePackage;` to the imports at the top of the file
  - Add `new RNMyWifiModulePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-my-wifi-module'
  	project(':react-native-my-wifi-module').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-my-wifi-module/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-my-wifi-module')
  	```


## Usage
```javascript
import RNMyWifiModule from 'react-native-my-wifi-module';

// TODO: What to do with the module?
RNMyWifiModule;
```
  