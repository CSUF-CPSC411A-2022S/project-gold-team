package fullerton.lfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class EventInfo () {

//    Work in Progress
    private var loc: String = "CSUF"
    private var t: String = "12:30 PM"
    private var description: String = "Destiny 2 Raid"

    //Display Location
    fun location(){
        return loc
    }
    // Display time
    fun time(){
        return t
    }
    // Display Other
    fun eventDescription(){
        return description
    }
}