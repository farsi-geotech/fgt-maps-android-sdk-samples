# Setup Places/Search API
To setup Places API in your application follow these steps
1. Add the following configuration in project level `build.gradle`
``` groovy
allprojects {
repositories {
   jcenter()
   maven { url "http://api.tplmaps.com:8081/artifactory/example-repo-local/"
   }
}
```
2. Add the following gradle dependency in android application module’s `build.gradle`
``` groovy
dependencies {
  implementation 'com.fgt.maps.sdk:places:0.1'
}
```
3. Add Internet permission in your `AndroidManifest.xml`
``` xml
<uses-permission android:name="android.permission.INTERNET" />
```
4. Configure API Key
   - Create an account on [FGT LBS Portal](http://gatewayzone1.northeurope.cloudapp.azure.com:8081/apiportal/#/portal/home) and sign in.
   - Generate Android API Key through [Generate Key](http://gatewayzone1.northeurope.cloudapp.azure.com:8081/apiportal/#/app/key-generation) option.
   - Copy the key put it into `<meta-data>` tag mentioned below and copy the tag in your project’s `AndroidManifest.xml` under `<application>` tag
``` xml
      <meta-data 
        android:name="com.fgtmaps.android.sdk.API_KEY"
        android:value="YOUR_API_KEY_HERE" />
```
5.	Initialize an instance of SearchManager class by passing your Activity’s reference as
``` java
SearchManager searchManager = new SearchManager(this);
```
In the above code `this` belongs to your Activity.

   - Implement `OnSearchResult` interface and its methods with your activity. There you will get responses against your search queries.
   Interface’s callback will look like this
``` java
@Override
public void onSearchResult(ArrayList<Place> results) { }

@Override
public void onSearchResultNotFound(Params params, long requestTimeInMS) { }

@Override
public void onSearchRequestFailure(Exception e) { }

@Override
public void onSearchRequestCancel(Params params, long requestTimeInMS) { }

@Override
public void onSearchRequestSuspended(String errorMessage, Params params, long requestTimeInMS) { }
```
6.	Register a search listener as
``` java
searchManager.setListener(this);
```
7. The method will take a reference of `OnSearchResult` interface’s instance Call `SearchManager.request (String, Params)` method by setting at least one param named Params.query you will get response in your callback methods defined by your listener. In case of missing some configurations related to the API or exceptions, you will get informed in these callbacks.
``` java
searchManager.request(url, Params.builder().query(“restaurant”).build());
```
> **Note:** Call SearchManager.request (String, Params) for search query after an interval of minimum 3 seconds because of network limitations applied on our servers otherwise all your request will be suspended
