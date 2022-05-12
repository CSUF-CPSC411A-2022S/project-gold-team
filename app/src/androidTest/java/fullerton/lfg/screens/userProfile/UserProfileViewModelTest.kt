package fullerton.lfg.screens.userProfile

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao
import fullerton.lfg.database.ProfileDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserProfileViewModelTest : TestCase() {

    private lateinit var viewModel: UserProfileViewModel
    private lateinit var db: ProfileDatabase
    private lateinit var dao: ProfileDao

    @Before
    public override fun setUp() {
        super.setUp()
        // get context
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Init mock db and dao
        db = Room.inMemoryDatabaseBuilder(context, ProfileDatabase::class.java).build()
        dao = db.profileDao()

        viewModel = UserProfileViewModel(dao, Application())
    }

    @Test
    fun testGetProfile() = runBlocking {
        // init test profile
        // I would've added another constructor to profile
        // but I cannot since Zulema insists her code is left alone
        var testProfile = Profile()
        testProfile.username = "test@mail.com"
        testProfile.firstname = "Tester"
        testProfile.lastname = "One"
        testProfile.password = "password"

        // insert test profile
        dao.addProfile(testProfile)
        // retrieve and compare
        val result = viewModel.getUserProfile(testProfile.username)
        assertEquals(result.value?.firstname, testProfile.firstname)
        assertEquals(result.value?.lastname, testProfile.lastname)
        assertEquals(result.value?.password, testProfile.password)
    }
}