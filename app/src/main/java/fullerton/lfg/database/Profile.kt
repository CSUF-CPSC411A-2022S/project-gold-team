package fullerton.lfg.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class Profile(

    // Defines the table's primary key. Primary keys are unique values that can be autogenerated.
    // They are used to differentiate one entity from another.
    @PrimaryKey(autoGenerate = true)
    var profileId: Long = 0L,

    // Non-rprimary key column. Data type is specified in the property.
    @ColumnInfo(name = "first_name")
    var firstname: String = "",

    // Non-rprimary key column. Data type is specified in the property.
    @ColumnInfo(name = "last_name")
    var lastname: String = "",

    // Non-rprimary key column. Data type is specified in the property.
    @ColumnInfo(name = "user_name")
    var username: String = "",

    // Non-rprimary key column. Data type is specified in the property.
    @ColumnInfo(name = "password")
    var password: String = ""
)