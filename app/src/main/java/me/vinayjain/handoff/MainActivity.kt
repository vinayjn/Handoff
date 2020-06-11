package me.vinayjain.handoff

import android.bluetooth.*
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelUuid
import java.util.*

class AdCallback: AdvertiseCallback() {
    override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
        super.onStartSuccess(settingsInEffect)
    }

    override fun onStartFailure(errorCode: Int) {
        super.onStartFailure(errorCode)
    }
}

class MainActivity : AppCompatActivity() {

    private var adCallback: AdCallback? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adCallback = AdCallback()
        setupGAP()
    }

    private fun setupGAP() {

        val blAdapter = BluetoothAdapter.getDefaultAdapter()
        val bleAdvertiser = blAdapter.bluetoothLeAdvertiser
        val advertiseSettings = AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED)
                .setConnectable(true)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_LOW)
                .setTimeout(0)
                .build()
        val parcelUuid = ParcelUuid(UUID.fromString(getString(R.string.service_uuid)))

        val advertiseData = AdvertiseData.Builder()
                .setIncludeDeviceName(true)
                .addServiceUuid(parcelUuid)
                .build()
        bleAdvertiser.startAdvertising(advertiseSettings, advertiseData, adCallback)
    }

}