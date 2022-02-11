

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.deliveryapp.DeliveryApp
import com.example.deliveryapp.R
import com.example.deliveryapp.RemainderRepo
import com.example.deliveryapp.Reminder
import com.example.deliveryapp.utils.Extensions.showReminderInMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt


class AddRemainder : Fragment(), OnMapReadyCallback {

     private lateinit var map: GoogleMap
     //Remainder data object that will be initialized Gradually
     private var reminder = Reminder(latLng = null, radius = null, message = null)
     private  lateinit var marker : ImageView
     private  lateinit var instructionTitle : TextView
     private  lateinit var instructionSubtitle : TextView
     private  lateinit var radiusBar : SeekBar
     private  lateinit var message : EditText
     private  lateinit var radiusDescription : TextView
     private  lateinit var next : Button
    private lateinit var repo: RemainderRepo


    companion object {

        private const val EXTRA_LAT_LNG = "EXTRA_LAT_LNG"
        private const val EXTRA_ZOOM = "EXTRA_ZOOM"

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repo = (activity?.applicationContext as DeliveryApp).getRepo()
        //val latLng: LatLng?= requireArguments().getParcelable(EXTRA_LAT_LNG)
        //val zoom: Float?= requireArguments().getFloat(EXTRA_ZOOM)
       // Log.d("LATMAIN",latLng.toString())
        //Log.d("ZOOMMAN",zoom.toString())
    }



    private fun centerCamera() {
        Toast.makeText(requireContext(),"Centering the Camera in Add Reminder",Toast.LENGTH_SHORT).show()
       // val latLng = requireArguments().getParcelable<LatLng>(EXTRA_LAT_LNG) as LatLng
       // val bay = LatLng(-4.0035, 39.6682)
       // val zoom = requireArguments().getFloat(EXTRA_ZOOM)
       // Toast.makeText(requireContext(),latLng.toString() + zoom.toString(),Toast.LENGTH_SHORT).show()
       // map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom))

    }




    private val radiusBarChangeListener = object : SeekBar.OnSeekBarChangeListener {

         override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }


        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }


        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            updateRadiusWithProgress(progress)
            showReminderUpdate()
        }

    }




    private fun getRadius(progress: Int) = 100 + (2 * progress.toDouble() + 1) * 100







    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

         val  view = inflater.inflate(R.layout.fragment_add_remainder, container, false)

          //Views
          marker = view.findViewById(R.id.marker)
          instructionTitle =view.findViewById(R.id.instructionTitle)
          instructionSubtitle =view.findViewById(R.id.instructionSubtitle)
          radiusBar =view.findViewById(R.id.radiusBar)
          message = view.findViewById(R.id.message)
          radiusDescription = view.findViewById(R.id.radiusDescription)
          next = view.findViewById(R.id.next)

          instructionTitle.visibility =View.GONE
          instructionSubtitle.visibility =View.GONE
          radiusBar.visibility = View.GONE
          message.visibility = View.GONE
          radiusDescription.visibility = View.GONE

          return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
         map = googleMap
        showConfigureLocationStep()
        centerCamera()

    }



    private fun showConfigureLocationStep() {
           marker.visibility = View.VISIBLE
           instructionTitle.visibility = View.VISIBLE
           instructionSubtitle.visibility = View.VISIBLE
           radiusBar.visibility = View.GONE
           radiusDescription.visibility = View.GONE
           message.visibility = View.GONE
           instructionTitle.text = getString(R.string.instruction_where_description)

           next.setOnClickListener {
                reminder.latLng = map.cameraPosition.target
                showConfigureRadiusStep()

           }

           showReminderUpdate()

    }


    private fun showConfigureRadiusStep() {
        marker.visibility = View.GONE
        instructionTitle.visibility = View.VISIBLE
        instructionSubtitle.visibility = View.GONE
        radiusBar.visibility = View.VISIBLE
        radiusDescription.visibility = View.VISIBLE
        message.visibility = View.GONE
        instructionTitle.text = getString(R.string.instruction_radius_description)
        next.setOnClickListener {
            showConfigureMessageStep()
        }

        radiusBar.setOnSeekBarChangeListener(radiusBarChangeListener)
        updateRadiusWithProgress(radiusBar.progress)
        map.animateCamera(CameraUpdateFactory.zoomTo(15f))
        showReminderUpdate()
    }




    private fun updateRadiusWithProgress(progress: Int) {
        val radius = getRadius(progress)
        //Set the radius in the remainder object
        reminder.radius = radius
        radiusDescription.text = getString(R.string.radius_description,radius.roundToInt().toString())


    }


    private fun showConfigureMessageStep() {

        marker.visibility = View.GONE

        instructionTitle.visibility = View.VISIBLE

        instructionSubtitle.visibility = View.GONE

        radiusBar.visibility = View.GONE

        radiusDescription.visibility = View.GONE

        message.visibility = View.VISIBLE

        instructionTitle.text = getString(R.string.instruction_message_description)

        next.setOnClickListener {

        //    hideKeyboard(requireContext(), message)


            reminder.message = message.text.toString()


            if (reminder.message.isNullOrEmpty()) {

                message.error = getString(R.string.error_required)

            }
            else {
                 addReminder(reminder)
            }

        }

       // message.requestFocusWithKeyboard(requireContext())
           showReminderUpdate()

    }








    private fun showReminderUpdate() {
        map.clear()
        showReminderInMap(requireContext(), map, reminder)
    }



    private fun addReminder(reminder: Reminder) {

        repo.add(reminder,success = {

            //setResult(Activity.RESULT_OK)
            setFragmentResult("REQUEST_KEY", bundleOf("SUCESS" to true))
            requireActivity().onBackPressed()

           // finish()

        },
                failure = {

                    Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()

                })

    }




}











