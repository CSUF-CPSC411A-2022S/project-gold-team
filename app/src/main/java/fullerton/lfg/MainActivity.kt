package fullerton.lfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


class CreateAccount{
    // Creates a user account for the app.
    // requires the user to input an email and password
    // to create the account.
}

class CreateLookingForGroup{
    // User creates a looking for group event post.
    // Information about the event is provided in this class.
}

class JoinLookingForGroup{
    // User joins the looking for group event.
}
