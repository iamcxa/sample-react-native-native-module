using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace My.Android.Toast.RNMyAndroidToast
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNMyAndroidToastModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNMyAndroidToastModule"/>.
        /// </summary>
        internal RNMyAndroidToastModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNMyAndroidToast";
            }
        }
    }
}
