package fullerton.lfg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * use a binding object to simplify access to the visual design elements.
         */
        setContentView(R.layout.activity_main)

    }
}

