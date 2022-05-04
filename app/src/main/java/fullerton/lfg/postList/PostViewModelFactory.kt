package fullerton.lfg.postList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fullerton.lfg.PostDatabase.PostDao

/**
 * Generates an IntersectionViewModel tied to the database.
 */
class PostViewModelFactory(
    private val dataSource: PostDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the IntersectionViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) { // ViewModel class
            return PostViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}