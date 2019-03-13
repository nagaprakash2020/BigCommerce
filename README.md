This Project is build using MVVM architecture and is categorized into following layers

1. UI layer
2. Business layer
3. Repository layer



<b>UI Layer</b>

  All Activities,fragments,adapters or any classes that have an android UI dependency fall under this layer.
UI layer is responsible for rendering UI on to the screen. UI layer will listen to data changes from corresponding ViewModel and update the UI.

In our example 
LandingActivity
ParentActivity
SearchFragment
EventDetailFragment
ResultsAdapter    comes under UI layer.

<b>Business Layer</b>

  Handles all the businesses logic for the application. Businesses layer will request the repository layer for any required data.
These view models will not have any android UI dependency hence any Unit Test cases written on these classes can run without an android device/emulator. It acts as link between repository layer and UI layer. It is responsible for getting data from repository layer and providing data required for UI.

In our example
ResultsViewModel comes under Businesses Layer.

<b>Repository Layer</b><br>
  Responsible for all data related queries. Repository layer will fetch data from either the Room Database or from the API call.


Advantage of this layered architecture is that each module can be tested independently using the Mocking frameworks.


Overview of external Libraries used.

1. Espresso for UI testing
2. Mockito for mocking interfaces during testing.
3. Hamcrest for assertions during testing.
4. Room database to save favorite events.
5. Dagger2 for dependency injection.
6. okhttp and Retorfit for networking.
7. Glide to display images.
8. Stetho for debugging data in databases and shared preferences.
9. MockWebServer from Square to mock responses from web server.


This project utilizes following Android Architecture Components
  <br><i>Live Data</i> helps updating observers about data changes. LiveData is also used inside RoomDatabase to update UI when data inside database changes.
  <br><i>ViewModel</i> helps storing data in classes which aren’t destroyed when Activity destroyed.
  <br><i>Room</i> helps us focusing on writing functionality without worrying about the boiler plate code to create databases and tables. It is a Object mapping library which provides compile time checks to see if SQL statements to return LiveData observables.
  <br><i>DataBinding</i> helps us injecting data directly to XML files. 


