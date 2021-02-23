# PWA vs the others: A comparative study on the UI energy-efficiency of progressive web apps

## Repository content

 - apks: Compiled and signed apks for testing on device
 - apps: Source code of the apps under test and instrumentation
    - instrumentation: UIAutomator Android app 
    - contactapp-native: Android native implementation of app
    - contactapp-react-native: React native implementation of app
    - contactapp-stencil: Stencil/Ionic/Capacitor implementation of app
    - contactapp-flutter: Flutter implementation of app
 - data: raw data measured with batterystats
 - figures: generated figures for paper
 - helper: python helper functions, e.g. for using `adb` or `batterystats`
 - Analysis.ipynb: Notebook with statistical analysis and generation of figures
 - prepare.py: Setup script for preparing a connected device 
 - start_battery_test.py: Script to start the complete battery test on a connected device
 - start_single_battery_test.py Script to start a single app battery test on a connected device

## Setup

 - The project is using Python 3
 - Inside the project folder `python -m venv venv` or `virtualenv venv` creates a virtual environment
 - `./venv/Scripts/activate` activates the virtual environment (Windows 10)
 - Install all project dependencies with `pip install -r requirements.txt` within an active virtual environment

## Battery tests

### Connect adb via Wi-Fi

 - connect mobile device via USB cable to computer
 - get IP-address form mobile device: `adb shell ip address` and search for `wlan0`
 - enable port 7777 for tcp/ip connection mode `adb tcpip 7777` on mobile device
 - connect to mobile device via Wi-Fi: `adb connect "IP-ADDRESS":7777`
 - unplug USB cable from mobile device
 
### Prepare device for testing

#### Apps

The latest release of the apps under test and the instrumentations are compiled and signed in the folder `apk`. To install all apps inside the `apk` folder run the `prepare.py` script. This requires an adb connected device.

```bash
> python prepare.py
```

In order to test the apps, test entries must be created inside the app.

#### PWA

The PWA is deployed [here](https://contactapp.stefanhuber.at) and must be installed with Chrome and Firefox on the respective device.

## Start battery test

### Run all tests

This requires that the device is connected via Wi-Fi and the `IP-ADDRESS` is known. See `Connect adb via Wi-Fi` above for connecting to device.

```bash
> python start_battery_test.py -i IP-ADDRESS
```

## Results

Inside an active virtual environment the notebook can be run with `jupyter notebook`. Inside Jupyter open the `Analysis.ipynb` notebook.

### Energy consumption (Joules) results of different UI interaction scenarios

![Boxplot](figures/results.png)

### Heatmap of the calculated Cliff’s delta effect sizes

![Heatmap](figures/heatmap.png)