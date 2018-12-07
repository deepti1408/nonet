NoNet
=======

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.keiferstone/nonet/badge.svg)](http://search.maven.org/#search|ga|1|g:"com.keiferstone"%20AND%20a:"nonet")
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.keiferstone/nonet/badge.svg)](http://www.javadoc.io/doc/com.keiferstone/nonet)
![Build Status](https://build.keiferstone.com/buildStatus/icon?job=NoNet%20Snapshot)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-NoNet-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5219)

NoNet is an Android library for monitoring network connectivity.

Sample Usage
-----

Monitor network connectivity:
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    NoNet.monitorConnection(this) {
        Log.d("NoNet", if (it) "Connected" else "Not connected")
    }
}
```

Check network connectivity:
```kotlin
NoNet.isConnected {
    Log.d("NoNet", if (it) "Connected" else "Not connected")
}
```

Provide a custom configuration for checking network connectivity:
```kotlin
val config = config {
    url = "https://google.com"
    timeout = 1
    connectedPollInterval = 10
    disconnectedPollInterval = 2
}
NoNet.isConnected(config) {
    Log.d("NoNet", if (it) "Connected" else "Not connected")
}
```

Download
--------

Download [the latest AAR][1] or grab via Gradle:
```groovy
implementation 'com.keiferstone:nonet:3.0.0-alpha08'
```
or Maven:
```xml
<dependency>
  <groupId>com.keiferstone</groupId>
  <artifactId>nonet</artifactId>
  <version>3.0.0-alpha08</version>
</dependency>
```


License
--------

    Copyright 2018 Keifer Stone

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: http://search.maven.org/#search|gav|1|g:"com.keiferstone"%20AND%20a:"nonet"