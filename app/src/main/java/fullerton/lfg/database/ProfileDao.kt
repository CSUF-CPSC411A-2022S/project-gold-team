package fullerton.lfg.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {
    // Add an intersection entity to a table in the database.
    // We use suspend to run the function asynchronously (coroutine).
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProfile(profile: Profile)

    // Update an intersection entity to a table in the database. Often uses the primary key
    // We use suspend to run the function asynchronously (coroutine).
    @Update
    suspend fun update(profile: Profile)

    @Query("UPDATE profile_table SET first_name=:firstName WHERE user_name=:username")
    suspend fun updateFirstName(username: String, firstName: String)

    @Query("UPDATE profile_table SET last_name=:lastName WHERE user_name=:username")
    suspend fun updateLastName(username: String, lastName: String)

    @Query("UPDATE profile_table SET password=:password WHERE user_name=:username")
    suspend fun updatePassword(username: String, password: String)

    @Query("SELECT * FROM profile_table WHERE user_name = :username")
    fun getProfile(username: String): LiveData<Profile>

    // Custom query for retrieving all Intersection entities from a table in the database.
    // Data is stored to a List LiveData. We don't use suspend because LiveData objects
    // are already designed to work asynchronously.
    @Query("SELECT * FROM profile_table ORDER BY profile_Id DESC")
    fun getAllProfiles(): LiveData<List<Profile>>

    // Custom query for deleting all entities on a table in the database
    // We use suspend to run the function asynchronously (coroutine).
    @Query("DELETE FROM profile_table")
    suspend fun clear()
}