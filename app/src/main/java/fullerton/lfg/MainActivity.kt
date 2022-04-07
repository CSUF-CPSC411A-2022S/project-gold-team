package fullerton.lfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * use a binding object to simplify access to the visual design elements.
         */

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        //for optional bar on top
        //setupActionBarWithNavController(navController)
    }
}
class UserInfo(var name: String, var age: Int, var address: String) {

    private var _name = name
    private var _age = age
    private var _address = address
    private var interests: List<String> = listOf()

    fun getUserName(): String {
        return _name
    }

    fun getUserAge(): Int {
        return _age
    }

    fun getUserAddress() : String {
        return _address
    }

    fun getUserInterests(): List<String> {
        return interests
    }

}

