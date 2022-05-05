package fullerton.lfg.postList

import android.app.Application
import androidx.lifecycle.*
import fullerton.lfg.postDatabase.Post
import fullerton.lfg.postDatabase.PostDao
import kotlinx.coroutines.launch

/**
 * IntersectionViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class PostViewModel(
    val database: PostDao, // Data access object for the Intersection entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.
    var name = MutableLiveData("")
    var postId = MutableLiveData("")
    var title = MutableLiveData("")
    var body = MutableLiveData("")
    var location = MutableLiveData("")


    // Retrieves all Intersection objects from the database
    // Represented as a LiveData<List<Intersection>>
    val postList = database.getAllPosts()

    /**
     * Inserts the Intersection object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Intersection object using data stored in the EditText views
            var post = Post()
            post.name = name.value.toString()
            post.title = title.value.toString()
            post.body = body.value.toString()
            post.location = location.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(post)
        }
    }

    /**
     * Deletes all Intersection entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}