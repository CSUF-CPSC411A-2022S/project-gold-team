package fullerton.lfg.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.*
import org.junit.runner.RunWith

class ProfileDatabaseTest : TestCase() {
    // get reference to databases
    private lateinit var db: ProfileDatabase
    private lateinit var dao: ProfileDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // init room db and dao
        db = Room.inMemoryDatabaseBuilder(context, ProfileDatabase::class.java).build()
        dao = db.profileDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    // After making the mock db, we create tests
    @Test
    fun testAddProfile() = runBlocking {
        val profile = Profile()
        profile.username = "tester1@mail.com"
        profile.firstname = "Tester "
        profile.lastname = "One"
        profile.password = "testpw1"
        dao.addProfile(profile)
        val result = dao.getProfile("tester1@mail.com")
        assertEquals(result.value?.username, profile.username)
        assertEquals(result.value?.firstname, profile.firstname)
        assertEquals(result.value?.lastname, profile.lastname)
        assertEquals(result.value?.password, profile.password)
    }
}