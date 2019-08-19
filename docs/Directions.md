# Setup Directions API
To setup Directions API in your application follow these steps
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
  implementation 'com.fgt.maps.sdk:directions:0.1'
}
```
3. Add Internet permission in your `AndroidManifest.xml`
``` xml
<uses-permission android:name="android.permission.INTERNET" />
```
4. Configure API Key
 - Create an account on [FGTMaps LBS Portal](https://api.tplmaps.com/apiportal).
 - Generate Android API Key through [Generate Key](https://api.tplmaps.com/apiportal/#/app/key-generation) option.
 - Copy the key put it into `<meta-data>` tag mentioned below and copy the tag in your project’s `AndroidManifest.xml` under `<application>` tag
``` xml
    <meta-data
      android:name="com.fgtmaps.android.sdk.API_KEY"
      android:value="YOUR_API_KEY_HERE" />
```
5.	Initialize locations array with source and destination locations
``` java
// Initializing/preparing source and destination locations array
ArrayList<Place> locations = new ArrayList<>();
// Source location
Place source = new Place();
source.setName("King Abdullah Bin Abdulaziz Square, Jeddah");
source.setX(39.170069);
source.setY(21.507161);
// Destination Location
Place destination = new Place();
destination.setName("Kudy, Makkah, Saudi Arabia");
destination.setX(39.839409);
destination.setY(21.390267);

locations.add(source);
locations.add(destination);
```
6.	Prepare `RouteConfig` object and pass it to `RouteManager.calculate(RouteConfig)` method with necessary arguments as given below, You will get list of **multiple routes** if exist between source & destination locations in `IMapRoute#onMapRoutingOverview(ArrayList<Place> endPoints, ArrayList<Route> routes)` callback.
``` java
RouteConfig config = new RouteConfig.Builder(false, locations)
        .build();

new RouteManager().calculate(this, config, new IMapRoute() {
    @Override
    public void onMapRoutingOverview(ArrayList<Place> endPoints, ArrayList<Route>
            routes) {
         // TODO Use data from ArrayList<Route> routes
}
});
```
## Response
![Elaboration](/images/assets/routing-response.jpg?raw=true)
