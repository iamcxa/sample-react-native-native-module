/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  Button,
  Alert,
  View
} from 'react-native';

import RNModuleMyWifiManager from 'react-native-module-my-wifi-manager';

type Props = {};
export default class App extends Component<Props> {

  componentDidMount() {
    RNModuleMyWifiManager.on(RNModuleMyWifiManager.EVENT_WIFI_STATUS_CHANGE, (data) => {
      console.log('EVENT_WIFI_STATUS_CHANGE data=>', data);
      RNModuleMyWifiManager.showMessage(`EVENT_WIFI_STATUS_CHANGE=>${data.status.toString()}`);
    });
  }

  showMessage = () => {
    console.log('RNModuleMyWifiManager=>', RNModuleMyWifiManager);
    RNModuleMyWifiManager.showMessage('WifiMessage is bridged!');
  }

  getWifiStatus = async () => {
    const status = await RNModuleMyWifiManager.getWifiStatus();
    Alert.alert('getWifiStatus', `getWifiStatus=>${status}`);
  }

  getIpAddress = async () => {
    const result = await RNModuleMyWifiManager.getIpAddress();
    Alert.alert('getIpAddress', `getIpAddress=>${result}`);
  }

  getCurrentWifiId = async () => {
    const result = await RNModuleMyWifiManager.getCurrentWifiId();
    Alert.alert('getCurrentWifiId', `getCurrentWifiId=>${result}`);
  }

  getCurrentWifiBssId = async () => {
    const result = await RNModuleMyWifiManager.getCurrentWifiBssId();
    Alert.alert('getCurrentWifiBssId', `getCurrentWifiBssId=>${result}`);
  }

  getWifiList = async () => {
    const result = await RNModuleMyWifiManager.getExistWifiListString();
    console.log('result=>', result);
    Alert.alert('getWifiList', `getWifiList=>${result}`);
  }

  turnOffWifi = async () => {
    await RNModuleMyWifiManager.turnOffWifi();
  }

  turnOnWifi = async () => {
    await RNModuleMyWifiManager.turnOnWifi();
  }

  render() {
    return (
      <View style={styles.container}>

        <Button
        title="Show Message"
        onPress={this.showMessage}/>

        <Button
        title='Get Wifi Status!'
        onPress={this.getWifiStatus}/>

        <Button
        title='Get Wifi IP Address!'
        onPress={this.getIpAddress}/>
        
        <Button
        title='Get Cruuent Wifi ID'
        onPress={this.getCurrentWifiId}/>

        <Button
        title='Get Cruuent Wifi BSS ID'
        onPress={this.getCurrentWifiBssId}/>

        <Button
        title='Get available Wifi list'
        onPress={this.getWifiList}/>

        <Button
        title='Trun OFF Wifi'
        onPress={this.turnOffWifi}/>

        <Button
        title='Trun ON Wifi'
        onPress={this.turnOnWifi}/>

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
});
