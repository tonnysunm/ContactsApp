# Contacts mobile app

This demo shows how to develop mobile apps in different platforms (iOS, Android, Web). It's not about cross-platform. it's about taking fully advantage of best part of Swift, Kotlin and JS.

### iOS

<p float="left">
 <img src="/Design/ios/home.png" width="250">
 <img src="/Design/ios/search.png" width="250">
 <img src="/Design/ios/detail.png" width="250">
</p>

- Protocol-oriented Programming
- Proper Extension
- CoreData
- JS bridge between web and native(Going on)
- Reactive in Combine framework(TODO)

### Android with Jetpack (on going)

<img src="/Design/android/final-architecture.png" width="400">

- MVVM
- Paging Library
- LiveData 
- Data-binding
- Room
- Coroutine
- Compose

### React SAP(TODO)

- Next.js
- Redux & Redux-saga
- JS bridge between web and native.

### What do you need to build a modern table in iOS and Android?
The following graphic lists the basic MVC knowledge of table or list UI in iOS and Android, which is supported by platform's framework. For iOS, there is no any big change in both architect and api since iOS 2.0 (12 yrs). 

However, Android brings a huge change, and the change is constantly going with next releases. The 🆕 features comes in a new version of the support library called androiX (Android 9.0, API level 28, 2018), in other kotlinx library (kotlinx-coroutines-android 2018), or in new version of Android Studio (DataBinding needs Android Studio 3.4 2019).

| | iOS | Android |
---- | ---- | ---- |
Controller | UITableViewController | Activiy<br> AppCompatActivity  🆕<br> ViewModel 🆕 or<br>AndroidViewModel 🆕
UI components | UITableView<br>UITableViewCell | Fragment 🆕<br>ListView / RecyclerView 🆕<br>RecyclerView.ViewHolder 🆕<br>Lifecycle and LifecycleOwner 🆕
Layout | .xib or .storyboard (iOS 5)<br>AutoLayout (iOS 6) | .xml<br>ConstraintLayout 🆕
View access | viewWithTag or<br>IBOutlet | findViewById or<br>synthetic 🆕<br>View Binding 🆕
Delegate<br>DataSource | UITableViewDelegate<br>UITableViewDataSource | PagedListAdapter 🆕<br>Paging library (DataSource.Factory,<br>PagedList.BoundaryCallback) 🆕
Bind data to UI | | Data Binding 🆕<br>LiveData 🆕
Model | CoreData (iOS 3) | Room 🆕
Concurrency | GCD (iOS 8) | Coroutine 🆕

Which one seem more challenging to you?

### How to sync data or keep consistency between remote datasouce and local database? (on going)

* Two important points
	1. uniqueId
	2. same order in api and sqlite fetch

### Data-driven UI. (on going)
A mechanism of updateing list view after data being inserted, updated or deleted.

| | iOS, UITableView | Android, RecyclerView |
| ---- | ---- | ---- |
Component(s) | NSFetchedResultsControllerDelegate | Paging Library<br>LiveData<br>PagedListAdapter

* Android

 <img src="/Design/android/paging_1.gif">
 
 <img src="/Design/android/paging_2.gif">
 
 <img src="/Design/android/paging_3.gif">

### A summary of this app about mobile development. (on going)

- [Good practices in iOS development](/swift.md)
- [Good practices in Android development](/kotlin.md)
- [What I've learned from Kotlin as iOS developer](/kotlin_for_ios.md)
- [Comparison between Swift and Kotlin](/swift_vs_kotlin.md)
