package me.vinayjain.handoff

import android.bluetooth.*
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.content.ClipboardManager
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

    private var serverCallbacks: GATTCallbacks? = null

    private var gattServer: BluetoothGattServer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adCallback = AdCallback()
        setupGAP()
        setupGATT()
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

    private fun setupGATT() {
        val clipManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        serverCallbacks = GATTCallbacks(clipboardManager = clipManager)
        val manager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        gattServer = manager.openGattServer(this, serverCallbacks)

        val service = BluetoothGattService(
                UUID.fromString(getString(R.string.service_uuid)),
                BluetoothGattService.SERVICE_TYPE_PRIMARY
        )

        val characteristic = BluetoothGattCharacteristic(
                UUID.fromString(getString(R.string.char_uuid)),
                BluetoothGattCharacteristic.PROPERTY_WRITE,
                BluetoothGattCharacteristic.PERMISSION_WRITE
        )
        characteristic.value = "Hello".toByteArray()
        service.addCharacteristic(characteristic)

        gattServer!!.addService(service)
    }

}