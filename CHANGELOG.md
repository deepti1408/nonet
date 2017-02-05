Change Log
==========

Version 1.2.2 *(2017-02-05)*
----------------------------

* Changes to build environment.

Version 1.2.1 *(2017-02-03)*
----------------------------

* Fixed banner being leaked after calling `NoNet.stopMonitoring()`.
* Extended all `Monitor` capabilities to `Check`.

Version 1.2.0 *(2017-02-03)*
----------------------------

* Attempting to create a `Configuration` with invalid values will now throw an 
  `IllegalArgumentException`.
* Added some `Configuration` tests.
* Added support for `BannerView` and methods for creating a banner.

Version 1.1.0 *(2017-02-02)*
----------------------------

* Calling `NoNet.stopMonitoring()` now completes the `Observable` returned from 
  `Monitor.observe()`, if it exists.
* Made RxJava dependency optional. Users must now explicitly declare the RxJava dependency in order
  to use `Monitor.observe()`.


Version 1.0.8 *(2017-02-01)*
----------------------------

 * Changed default endpoint to `http://gstatic.com/generate_204`.
 * Added support for RxJava.


Version 1.0.7 *(2017-01-31)*
----------------------------

 * Lowered `minSdkVersion` from 21 to 16. 
 * Updated OkHttp to 3.6.0.


Version 1.0.6 *(2017-01-31)*
----------------------------

 * Added networking permissions to `AndroidManifest.xml`.


Version 1.0.5 *(2017-01-31)*
----------------------------

Initial release.
