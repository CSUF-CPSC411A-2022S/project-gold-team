package fullerton.lfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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