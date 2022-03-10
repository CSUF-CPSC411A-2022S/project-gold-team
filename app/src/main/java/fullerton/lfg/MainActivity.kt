package fullerton.lfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import fullerton.lfg.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * use a binding object to simplify access to the visual design elements.
         */
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * navController refers to our navigation fragment. The setupActionBarWithnavController
         * method connects our navController to the ActionBar that maintains the "back stack" which
         * is the succession of fragments that were opened. Pressing the Up button will display the
         * fragment that opened the current fragment, thereby back tracking its way to the first
         * fragment.
         */
        val navController = this.findNavController(R.id.nav_host)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host)
        return navController.navigateUp()
    }
}

