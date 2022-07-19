// Zulema Perez
// CSPC 411
package fullerton.lfg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import fullerton.lfg.databinding.MapFragmentBinding
import fullerton.lfg.geocoding.GeocodingViewModel



/**
 * Main application interface.
 *
 */
class MapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var binding: MapFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.map_fragment,
            container,false)

        val model = GeocodingViewModel()
        binding.geoCodingViewModel = model
        binding.lifecycleOwner = this

        return binding.root
    }

}
