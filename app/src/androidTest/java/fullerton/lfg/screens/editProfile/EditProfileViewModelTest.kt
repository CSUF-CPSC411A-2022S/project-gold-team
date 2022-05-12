package fullerton.lfg.screens.editProfile

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
class EditProfileViewModelTest : TestCase() {

    private lateinit var viewModel: EditProfileViewModel
    private lateinit var db: ProfileDatabase
    private lateinit var dao: ProfileDao

    private val testEmail = "test@mail.com"
    private val testFN = "Tester"
    private val testLN = "One"
    private val testPW = "password"

    @Before
    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()
        // Init mock db and dao
        db = Room.inMemoryDatabaseBuilder(context, ProfileDatabase::class.java).build()
        dao = db.profileDao()

        viewModel = EditProfileViewModel(dao, Application())
    }

    @Test
    fun testViewModel() = runBlocking {
        val testProfile = Profile()
        testProfile.username = testEmail
        testProfile.firstname = testFN
        testProfile.lastname = testLN
        testProfile.password = testPW
        // insert into db
        dao.addProfile(testProfile)
        // test get profile
        val profile1 = viewModel.getUserProfile(testEmail)
        assertEquals(testProfile.username, profile1.value?.username)
        assertEquals(testProfile.firstname, profile1.value?.firstname)
        assertEquals(testProfile.lastname, profile1.value?.lastname)
        assertEquals(testProfile.password, profile1.value?.password)

        // test update first name
        val newFirstName = "Tested"
        viewModel.updateUserFirstName(testEmail, newFirstName)
        val profile2 = viewModel.getUserProfile(testEmail)
        assertEquals(profile2.value?.firstname, newFirstName)
        // test last name
        val newLastName = "Two"
        viewModel.updateUserLastName(testEmail, newLastName)
        val profile3 = viewModel.getUserProfile(testEmail)
        assertEquals(profile2.value?.lastname, newLastName)
        // test password
        val newPassword = "password2"
        viewModel.updateUserPassword(testEmail, newPassword)
        val profile4 = viewModel.getUserProfile(testEmail)
        assertEquals(profile4.value?.password, newPassword)
    }
}