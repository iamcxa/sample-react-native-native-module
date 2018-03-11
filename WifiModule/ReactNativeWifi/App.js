/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Alert,
  Button,
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';

// 引入 RNMyWifiModule
import RNMyWifiModule from 'react-native-my-wifi-module';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

export default class App extends Component<{}> {

  componentDidMount() {
    RNMyWifiModule.on('WifiStatusChange', (data) => {
      console.log("event received", JSON.stringify(data));
      Alert.alert('WifiStatusChange', data.status);
      // if (AppState.currentState === 'active') {
        // this.setState({
        //   sender: data.app,
        //   text: data.text
        // });
      // }
    });
  }

  getWifiStatus = async () => {
    console.log('RNMyWifiModule=>', RNMyWifiModule);
    const status = await RNMyWifiModule.getWifiStatus();
    Alert.alert('getWifiStatus', `getWifiStatus=>${status}`);
  }

  getIpAddress = async () => {
    console.log('RNMyWifiModule=>', RNMyWifiModule);
    const result = await RNMyWifiModule.getIpAddress();
    Alert.alert('getIpAddress', `getIpAddress=>${result}`);
  }

  getCurrentWifiId = async () => {
    console.log('RNMyWifiModule=>', RNMyWifiModule);
    const result = await RNMyWifiModule.getCurrentWifiId();
    Alert.alert('getCurrentWifiId', `getCurrentWifiId=>${result}`);
  }

  getCurrentWifiBssId = async () => {
    console.log('RNMyWifiModule=>', RNMyWifiModule);
    const result = await RNMyWifiModule.getCurrentWifiBssId();
    Alert.alert('getCurrentWifiBssId', `getCurrentWifiBssId=>${result}`);
  }

  getWifiList = async () => {
    const result = await RNMyWifiModule.getExistWifiListString();
    Alert.alert('getWifiList', `getWifiList=>${result}`);
  }

  render() {
    return (
      <View style={styles.container}>
        <Button title='Get Wifi Status!' onPress={this.getWifiStatus}/>
        <Button title='Get Wifi IP Address!' onPress={this.getIpAddress}/>
        <Button title='Get Cruuent Wifi ID' onPress={this.getCurrentWifiId}/>
        <Button title='Get Cruuent Wifi BSS ID' onPress={this.getCurrentWifiBssId}/>
        <Button title='Get available Wifi list' onPress={this.getWifiList}/>
        <Button title='Trun OFF Wifi' onPress={RNMyWifiModule.turnOffWifi}/>
        <Button title='Trun ON Wifi' onPress={RNMyWifiModule.turnOnWifi}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
