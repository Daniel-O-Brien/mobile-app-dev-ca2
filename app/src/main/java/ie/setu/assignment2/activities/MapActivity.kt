package ie.setu.assignment2.activities

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ie.setu.assignment2.R
import ie.setu.assignment2.databinding.ActivityMapBinding
import ie.setu.assignment2.models.ShopModel
import timber.log.Timber.i
import java.util.Locale

//https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderClient

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

//    private val shops = listOf("CeX", "Smyths Toys Superstores", "Ken Black Toys & Nursery", "The Retro Gaming Store plus")

    private val shops = listOf(
        ShopModel("CEX", LatLng(52.338464941659275, -6.461739201940897)),
        ShopModel("CEX", LatLng(52.26023569713962, -7.1116102756176796)),
        ShopModel("Smiths Toys", LatLng(52.249689898304005, -7.118675816142929)),
        ShopModel("Ken Black", LatLng(52.32625698224165, -6.489213593842555)),
        ShopModel("The Retro Gaming Store plus", LatLng(52.33696998042763, -6.46065961635431))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Check for location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }

        // Enable MyLocation Layer of Google Map
        mMap.isMyLocationEnabled = true

        // Get the last known location
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
            }
        }

        for (shop in shops) {
            mMap.addMarker(MarkerOptions().position(shop.location).title(shop.name))
        }

//        for (shopName in shops) {
//            geocodeShop(shopName)
//        }
    }

    //Couldn't get working properly

//    private fun geocodeShop(shopName: String) {
//        val geocoder = Geocoder(this, Locale.getDefault())
//        Thread {
//            try {
//                val addresses = geocoder.getFromLocationName(shopName, 5)!!
//                i("Address: " + addresses + "ShopName: " + shopName)
//                if (addresses.isNotEmpty()) {
//                    val address = addresses[0]
//                    val latLng = LatLng(address.latitude, address.longitude)
//                    runOnUiThread {
//                        mMap.addMarker(MarkerOptions().position(latLng).title(shopName))
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }.start()
//    }

    // Handle permission request response
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permission granted, refresh the map
                onMapReady(mMap)
            } else {
                // Permission denied, handle accordingly
            }
        }
    }
}