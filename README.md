# CompositionAdapter
A composition Recycler-View adapter kotlin implementation inspired from the post [here](http://hannesdorfmann.com/android/adapter-delegates)

The library reduces the boiler plate required to write a `recyclerview` adapter with multiple `viewTypes`.

## Download from repository
This library can be downloaded from repository by adding the following into Android studio gradle file.
```
allProjects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
dependencies {
  compile "com.github.ksughosh:PermissionsManager:${latest_version}"
}
```
The `latest_version` can be found under [releases](https://github.com/ksughosh/CompositionAdapter/releases)

## Concept and working
The view types are handled as individual views and views are encapsulated within a delegate class that implements all the recycler view methods.
This would make each `View` encapsulated as a delegate with the recyclerview adapter. This is also a data driven approach so view modelling can be handled directly with data.

Every model class should be of type `ListItem` the example represents:
```kotlin
data class ModelForViewOne(isFeatured: Boolean, isTriggered: Boolean, title: String): ListItem

data class ModelForViewTwo(showTextView: Boolean, hideButton: Boolean, titleText: String): ListItem
```

Every on of these data classes must have an adjoining view class to which it is bound:
```kotlin
class MyLinearLayoutRecyclerViewItem(context: Context?) : BaseLinearHolder<ModelForViewOne>(context){
  override onDataReady(data: ModelForViewOne){
    // manipulate view from data
  }
}
```

or

```kotlin
class MyConstraintRecyclerViewItem(context: Context?): BaseConstraintHolder<ModelForViewTwo>(context) {
  override onDataReady(data: ModelForViewTwo){
    // manipulate view from data
  }
}
```

There are multiple available `BaseHolder` classes that would facilitate the construction of the composition adapter.Individual `ViewDelegate`s are created with two parameters. First parameter is a `lambda` that returns a view and the second parameter is also a `lambda` that returns the condition to evaluate for the `viewType`. Finally, add the individual view delegates to the adapter to he list as shown:

```kotlin
class ExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // p0: view, p1: condition for the view to be shown on recyclerview
        // show MyLinearLayoutRecyclerViewItem item when the data in the list provided is of type ModelForViewOne
        val delegateOne = ViewDelegate({ MyLinearLayoutRecyclerViewItem(it?.context) }, { it is ModelForViewOne }) 
        
        // second delegate
        val delegateTwo = ViewDelegate({ MyConstraintRecyclerViewItem(it?.context) }, { it is ModelForViewTwo }) 
        
        // delegate with condition 
        val delegateThree = ViewDelegate( { NewRecyclerViewItem1(it?.context), { (it as? ModelForViewOne).isTriggered == false } )
        
        val delegateFour = ViewDelegate( { NewRecyclerViewItem2(it?.context), { (it as? ModelForViewOne).isTriggered == true } )
        
        // get the items 
        val listOfItems : MutableList<ListItem> = getListItems()
        recyclerListView?.adapter = DataAdapter(listOfItems)
          .addDelegate(delegateOne, delegateTwo, delegateThree, delegateFour)
          
        recyclerListView?.layoutManager = LinearLayoutManager(this)
    }
    
    private fun getListItems(): MutableList<ListItem> {
      val list = mutableListOf<ListItem>()
      list.add(ModelForViewOne(true, false, "title"))
      list.add(ModelForViewTwo(false, true, "title2"))
      list.add(ModelForViewOne(true, false, "title"))
      list.add(ModelForViewOne(true, false, "title"))
      list.add(ModelForViewTwo(false, false, "title"))
      list.add(ModelForViewTwo(false, false, "title")) // and so on...
      return list
    }
}
```
In total we have 4 different recyclerview items with individual view types and all of the view handling would be done through the data in their corresponding view classes.

# Licence
Copyright [2018] [Sughosh Krishna Kumar]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
