/**
 * @providesModule MyAndroidToast
 * @flow
 */
'use strict';

var NativeMyAndroidToast = require('NativeModules').MyAndroidToast;

/**
 * High-level docs for the MyAndroidToast iOS API can be written here.
 */

var MyAndroidToast = {
  test: function() {
    NativeMyAndroidToast.test();
  }
};

module.exports = MyAndroidToast;
