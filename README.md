# Android MVVM Architecture: Sample App

This repository contains a detailed sample app that implements MVVM architecture using DaggerHilt, Room, Flow, Coroutines, Retrofit. It fetchs information from a free api https://swapi.dev/api/

Planet List           |  Planet detail
:-------------------------:|:-------------------------:
![Screenshot_20240304_081352](https://github.com/sirKings/android-mvvm-test/assets/19403727/56215364-b657-45be-a04d-79e56b36a425)| ![Screenshot_20240304_081412](https://github.com/sirKings/android-mvvm-test/assets/19403727/bbd70631-f206-40f2-8fb3-eddad50f8a8a)

 
<br>
<br>

# About The Author
You can connect with me here:
* [Nwankwo Kingsley](https://www.linkedin.com/in/kingsley-nwankwo-111b5271)

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **domain**: Dependency providing classes.
3. **presentation**: View classes along with their corresponding ViewModel and state.
4. **utils**: Utility classes.
5. **Main**: Entry package

#### Classes have been designed in such a way that it could be inherited and maximize the code reuse.

### Library reference resources:
2. DaggerHilt: https://developer.android.com/training/dependency-injection/hilt-android
7. Room: https://developer.android.com/topic/libraries/architecture/room.html!
### MVVM Architecture used is described by the diagram below
<img src="https://raw.githubusercontent.com/sirKings/android-mvvm/main/mvvm.png">

#### Unit Testing and Instrumentation tests were written using junit, mockito and HiltTestRunner



### License
```
   Copyright (C) 2024 Nwankwo Kingsley John

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
