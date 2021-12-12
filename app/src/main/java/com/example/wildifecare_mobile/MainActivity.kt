package com.example.wildifecare_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.example.wildifecare_mobile.model.Animal
import com.example.wildifecare_mobile.model.NationalPark
import com.example.wildifecare_mobile.service.NationalParkService
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToWorkSpaceButton: Button = findViewById (R.id.goToWorkSpaceButton)

        goToWorkSpaceButton.setOnClickListener() {
            val nationalParkService : NationalParkService = RetrofitClient.getClient().create(NationalParkService::class.java)
            val i = Intent(this, LoginActivity::class.java)
            val workspaceUrl : EditText = findViewById(R.id.workspaceUrl);
            var getUrl : Int = workspaceUrl.getText().toString().toInt();
            var nationalPark : NationalPark? = null;
            nationalParkService.getNationalPark(getUrl).enqueue(object: Callback<NationalPark> {
                override fun onResponse(
                    call: Call<NationalPark>,
                    response: Response<NationalPark>
                ) {
                    nationalPark = response.body()!!
                    val park = response.body()
                    if (park != null) {
                        nationalPark = park
                    }
                    println("park " + park)
                    println(nationalPark)
                    var bundle : Bundle = Bundle();
                    bundle.putSerializable("nationalPark", nationalPark);
                    i.putExtras(bundle);
                    startActivity(i);
                }

                override fun onFailure(call: Call<NationalPark>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
    }
}