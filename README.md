# GarbageGone
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
<p align="center"><img src="https://firebasestorage.googleapis.com/v0/b/garbagegone-fa7e4.appspot.com/o/ggicon.jpg?alt=media&token=3808947e-8807-4c19-890c-95d82de4c0c7" height="300" width="300"></p> 

An Android based app to help in better management of community waste using GPS based Crowd-Sourcing to report and map uncleared garbage bins and illegal dumping.

Available in Dark and Light themes. Themes are pre-chosen depending on the user system settings. Preview of the app is available below in this document. 
## License
This project is licensed under the Apache License 2.0, a permissive license whose main conditions require preservation of copyright and license notices. Contributors provide an express grant of patent rights. Licensed works, modifications, and larger works may be distributed under different terms and without source code. Trademark use is also strictly prohibited. Any material found which vandalises or threatens any sort of plagiarism will be strictly given a legal action.

## Preview

### Dark theme 
<p align="center"><img src="https://firebasestorage.googleapis.com/v0/b/garbagegone-fa7e4.appspot.com/o/darkSC.jpg?alt=media&token=a1534240-3082-4523-91c9-38f5c29ad360"></p> 

### Light theme
<p align="center"><img src="https://firebasestorage.googleapis.com/v0/b/garbagegone-fa7e4.appspot.com/o/lightSC.jpg?alt=media&token=3b65bb9c-f6a5-4a34-9553-7291326ac748"></p> 

## Android Signed APK build (Debug)
The signed ```.apk``` debug build variant of this app that you can install on your Android device is available here in the link below. \
https://firebasestorage.googleapis.com/v0/b/garbagegone-fa7e4.appspot.com/o/app-debug.apk?alt=media&token=766f5919-7a85-4207-b801-dde7465c340a

Use the following credentials to login for testing purposes: Username:dev1@garbagegone.com, pass: 123123

## Working
On the first screen all the current uncleared reports can be seen and clicking on the 'Go to maps' button you are navigated to the location of the report on Google maps. Using the '+' button a new report can be submitted by taking a photograph and entering a remark.
The second screen appears on swiping to the right. This section is intended for the municipality or sanitation workers who can login with their provided credentials and view, navigate to and remove the reports after finishing the job.

## Components
- Android codebase in Java 
- Firebase Authentication   (authenticate requests)
- Firebase Database         (Realtime database)
- Firebase Storage          (Mass Storage)

## Installation (Android app configuration)
- Go to your Firebase console, setup this project, select Android app, add the package name of this app and download <b>google-services.json</b>.
- Move the <b>google-services.json</b> file you just downloaded into your Android app module root directory.
###### Note 
Developed with Android Studio version 3.6.3.
