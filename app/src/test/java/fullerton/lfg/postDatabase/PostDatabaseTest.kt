package fullerton.lfg.postDatabase
//
//import android.content.Context
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import junit.framework.TestCase
//import kotlinx.coroutines.runBlocking
//import org.junit.*
//import org.junit.runner.RunWith
//
//class PostDatabaseTest : TestCase() {
//
//    private lateinit var db: PostDatabase
//    private lateinit var dao: PostDao
//
//    @Before
//    public override fun setUp() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        // Init post db and dao
//        db = Room.inMemoryDatabaseBuilder(context,
//            PostDatabase::class.java).build()
//        dao = db.postDao
//    }
//
//    @After
//    fun closeDb() {
//        db.close()
//    }
//
//
//}