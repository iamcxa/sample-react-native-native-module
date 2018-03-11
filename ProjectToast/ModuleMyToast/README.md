
# react-native-module-my-toast

## Getting started

`$ npm install react-native-module-my-toast --save`

### Mostly automatic installation

`$ react-native link react-native-module-my-toast`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNModuleMyToastPackage;` to the imports at the top of the file
  - Add `new RNModuleMyToastPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-module-my-toast'
  	project(':react-native-module-my-toast').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-module-my-toast/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-module-my-toast')
  	```


## Usage
```javascript
import RNModuleMyToast from 'react-native-module-my-toast';

// TODO: What to do with the module?
RNModuleMyToast;
```
  