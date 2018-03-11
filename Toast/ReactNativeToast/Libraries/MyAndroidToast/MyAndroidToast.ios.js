/**
 * @providesModule MyToastModule
 * @flow
 */
'use strict';

var NativeMyToastModule = require('NativeModules').MyToastModule;

/**
 * High-level docs for the MyToastModule iOS API can be written here.
 */

var MyToastModule = {
  test: function() {
    NativeMyToastModule.test();
  }
};

module.exports = MyToastModule;
