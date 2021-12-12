package com.example.wildifecare_mobile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wildifecare_mobile.databinding.ActivityMapsBinding
import com.example.wildifecare_mobile.model.Animal
import com.example.wildifecare_mobile.model.NationalPark
import com.example.wildifecare_mobile.service.AnimalService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var nationalPark : NationalPark
    //Aberdare
    public val latitude = arrayOf(-0.41640388574487475, -0.4569022963354209, -0.39491733839090776, -0.40681116796771183, -0.39661958189708724, -0.31628362031284396, -0.5449299607814018, -0.29980436667576305, );
    public val longitude = arrayOf(36.667437472833164, 36.674056399435756, 36.64047350037442, 36.6869172104318, 36.78117707986083, 36.63011508298949, 36.726932091998926, 36.56728702395146);

    //Askania-Nova
    // public latitude : number[] = [46.45891564072584, 46.45992548222519, 46.45965524478609];
    // public longitude : number[] = [33.86569793382252, 33.86858852587495, 33.87040546945076];

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bundle : Bundle? = intent.extras
        nationalPark = (bundle?.getSerializable("nationalPark") as NationalPark?)!!
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val park = LatLng(nationalPark.latitude, nationalPark.longitude)
        map.moveCamera(CameraUpdateFactory.newLatLng(park))
        val animalService: AnimalService =
            RetrofitClient.getClient().create(AnimalService::class.java);
        var Animals = arrayListOf<Animal>();

        animalService.getAnimals(nationalPark.id).enqueue(object : Callback<ArrayList<Animal>> {
            override fun onFailure(call: Call<ArrayList<Animal>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ArrayList<Animal>>,
                response: Response<ArrayList<Animal>>
            ) {
                Animals = response.body()!!
                var markers = arrayListOf<Marker?>();
                var i = 0;
                for (animal in Animals) {
                    val marker = map.addMarker(
                        MarkerOptions()
                            .position(LatLng(latitude[i], longitude[i]))
                            .title(animal.name)
                            .snippet(animal.species)
                            .icon(BitmapDescriptorFactory.fromResource(resources.getIdentifier(animal.species.lowercase(), "drawable", packageName))))
                    if (marker != null) {
                        marker.tag = animal
                    };
                    i++;
                }
            }
        })
    }
}