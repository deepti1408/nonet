NoNet
=======

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.keiferstone/nonet/badge.svg)](http://search.maven.org/#search|ga|1|g:"com.keiferstone"%20AND%20a:"nonet")
[![Javadoc](https://javadoc-emblem.rhcloud.com/doc/com.keiferstone/nonet/badge.svg)](http://www.javadoc.io/doc/com.keiferstone/nonet)
![Build Status](https://build.keiferstone.com/buildStatus/icon?job=NoNet%20Snapshot)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-NoNet-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5219)

NoNet is an Android library for monitoring network connectivity.

![](https://keiferstone.com/nonet/nonet-banner.png)


Sample Usage
-----

Monitor for network connectivity events and show a snackbar when disconnected:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ...
    NoNet.monitor(this)
            .poll()
            .snackbar()
            .start();
}
```

Set the global configuration:
```java
NoNet.configure()
        .endpoint("https://google.com")
        .timeout(5)
        .connectedPollFrequency(60)
        .disconnectedPollFrequency(1);
```

Make a one-off network connectivity check with a custom configuration, showing a toast if 
disconnected, and provide a callback to be invoked on result:
```java
NoNet.check(this)
        .configure(configuration)
        .toast()
        .callback(new Monitor.Callback() {
            @Override
            public void onConnectionChanged(int connectionStatus) {
                // TODO
            }
        })
        .start();
```


RxJava Support
--------------

Create an Observable from a Monitor that emits connectivity events:
```java
NoNet.monitor(this)
        .banner()
        .start()
        .observe()
        .subscribe(connectionStatus -> {...});
```

**Note: Calling `Monitor.observe()` without declaring the io.reactivex.rxjava2:rxjava dependency in
your `build.gradle` will cause errors at compile-time.**


ProGuard
--------

If you're using ProGuard, you may need to add these lines to your `proguard-rules.pro`:
```
-dontwarn io.reactivex.**
-dontwarn okio.**
```


Download
--------

Download [the latest AAR][1] or grab via Gradle:
```groovy
compile 'com.keiferstone:nonet:2.0.3'
```
or Maven:
```xml
<dependency>
  <groupId>com.keiferstone</groupId>
  <artifactId>nonet</artifactId>
  <version>2.0.3</version>
</dependency>
```


License
--------

    Copyright 2017 Keifer Stone

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