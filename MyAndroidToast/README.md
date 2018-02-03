
# react-native-my-android-toast

## Getting started

`$ npm install react-native-my-android-toast --save`

### Mostly automatic installation

`$ react-native link react-native-my-android-toast`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-my-android-toast` and add `RNMyAndroidToast.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMyAndroidToast.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMyAndroidToastPackage;` to the imports at the top of the file
  - Add `new RNMyAndroidToastPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-my-android-toast'
  	project(':react-native-my-android-toast').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-my-android-toast/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-my-android-toast')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNMyAndroidToast.sln` in `node_modules/react-native-my-android-toast/windows/RNMyAndroidToast.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using My.Android.Toast.RNMyAndroidToast;` to the usings at the top of the file
  - Add `new RNMyAndroidToastPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNMyAndroidToast from 'react-native-my-android-toast';

// TODO: What to do with the module?
RNMyAndroidToast;
```
  