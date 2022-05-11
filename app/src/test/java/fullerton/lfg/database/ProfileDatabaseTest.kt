package fullerton.lfg.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

class ProfileDatabaseTest : TestCase() {
    // get reference to databases
    private lateinit var db: ProfileDatabase
    private lateinit var dao: ProfileDao

    // default values for test profiles
    // username and email are the same
    private val testUserName= "tester@mail.com"
    private val testFirstName = "Tester"
    private val testLastName = "One"
    private val testPassword = "testpw1"

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

    // helper function to quickly generate test profiles
    private fun createTestProfile(
        userName: String = testUserName,
        firstName: String = testFirstName,
        lastName: String = testLastName,
        password: String = testPassword
    ) : Profile {
        val testProfile = Profile()
        testProfile.firstname = firstName
        testProfile.lastname = lastName
        testProfile.username = userName
        testProfile.password = password
        return testProfile
    }

    // After making the mock db, we create tests
    @Test
    fun testAddProfile() = runBlocking {
        // create default test profile
        val profile = createTestProfile()
        dao.addProfile(profile)
        val result = dao.getProfile(testUserName)
        assertEquals(result.value?.username, profile.username)
        assertEquals(result.value?.firstname, profile.firstname)
        assertEquals(result.value?.lastname, profile.lastname)
        assertEquals(result.value?.password, profile.password)
    }

    @Test
    fun testUpdateProfile() = runBlocking {
        val profile = createTestProfile()
        dao.addProfile(profile)
        // after we insert the profile, we will update its values and compare
        val newFirstName = "New"
        val newLastName = "Tester"
        val newPassword = "newpass"
        dao.updateFirstName(testUserName, newFirstName)
        val newProfile1 = dao.getProfile(testUserName)
        dao.updateLastName(testUserName, newLastName)
        val newProfile2 = dao.getProfile(testUserName)
        dao.updatePassword(testUserName, newPassword)
        val newProfile3 = dao.getProfile(testUserName)
        assertEquals(newProfile1.value?.firstname, newFirstName)
        assertEquals(newProfile2.value?.lastname, newLastName)
        assertEquals(newProfile3.value?.password, newPassword)
    }

    @Test
    fun testGetAllProfiles() = runBlocking {
        var profileList = mutableListOf<Profile>()
        for (i in 1..5) {
            var newProfile = createTestProfile()
            newProfile.username = "test" + i.toString() + "@mail.com"
            newProfile.firstname = "New"
            newProfile.lastname = "Tester" + i.toString()
            newProfile.password = "newpass" + i.toString()
            profileList.add(newProfile)
            dao.addProfile(newProfile)
        }
        // assert we can find all the profiles we inserted
        val allProfiles = dao.getAllProfiles()
        for (profile in profileList) {
            assert(allProfiles.value?.contains(profile)!!)
        }
        // assert we cannot find a profile we did not insert
        val pf6 = createTestProfile(userName = "test6@mail.com")
        assert(!allProfiles.value?.contains(pf6)!!)
    }
}