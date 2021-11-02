package com.example.mycleanarchitectureapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import com.example.mycleanarchitectureapp.R
import com.example.mycleanarchitectureapp.databinding.ActivityMainBinding
import com.example.mycleanarchitectureapp.model.MovieDb
import com.example.mycleanarchitectureapp.ui.common.CoroutineScopeActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainActivity : CoroutineScopeActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        launch {
            val location = getLocation()
            val movies = MovieDb.service.listPopularMovies(
                getString(R.string.api_key),
                getRegionFromLocation(location)
            )
            val adapter = MovieAdapter(this@MainActivity, movies.results)
            binding.recycler.adapter = adapter
        }
    }

    private fun getRegionFromLocation(location: Location?): String {
        val geocoder = Geocoder(this@MainActivity)
        val fromLocation = location?.let {
            geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1
            )
        }
        return fromLocation?.firstOrNull()?.countryCode ?: "US"
    }

    private suspend fun getLocation(): Location? {
        val permissionResponse = requestCoarseLocationPermission()
        return if (permissionResponse) findLastLocation() else null
    }

    @SuppressLint("MissingPermission")
    private suspend fun findLastLocation(): Location? {
       return suspendCancellableCoroutine<Location?> {
                fusedLocationClient.lastLocation.addOnCompleteListener { taskLocation ->
                    it.resume(taskLocation.result)
                }
        }
    }

    private suspend fun requestCoarseLocationPermission() = suspendCancellableCoroutine<Boolean> {
        Dexter
            .withContext(this@MainActivity)
            .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            .withListener(object : BasePermissionListener() {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    super.onPermissionGranted(p0)
                    it.resume(true)
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    super.onPermissionDenied(p0)
                    it.resume(false)
                }
            }).check()
    }
}

