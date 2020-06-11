package me.vinayjain.handoff

import android.bluetooth.*
import android.util.Log

class GATTCallbacks: BluetoothGattServerCallback() {
    override fun onDescriptorReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        descriptor: BluetoothGattDescriptor?
    ) {
        super.onDescriptorReadRequest(device, requestId, offset, descriptor)
    }

    override fun onNotificationSent(device: BluetoothDevice?, status: Int) {
        super.onNotificationSent(device, status)
    }

    override fun onMtuChanged(device: BluetoothDevice?, mtu: Int) {
        super.onMtuChanged(device, mtu)
    }

    override fun onPhyUpdate(device: BluetoothDevice?, txPhy: Int, rxPhy: Int, status: Int) {
        super.onPhyUpdate(device, txPhy, rxPhy, status)
    }

    override fun onExecuteWrite(device: BluetoothDevice?, requestId: Int, execute: Boolean) {
        super.onExecuteWrite(device, requestId, execute)
    }

    override fun onCharacteristicWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        characteristic: BluetoothGattCharacteristic?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        super.onCharacteristicWriteRequest(
            device,
            requestId,
            characteristic,
            preparedWrite,
            responseNeeded,
            offset,
            value
        )

        if (device != null) {
            Log.e("BLE", "Characteristics write request by ${device.address}")
            if (characteristic != null) {
                Log.e("BLE", String(characteristic?.value))
            } else {
                Log.e("BLE", "Characteristic is null")
            }

        } else {
            Log.e("BLE", "Device is null when writing")
        }
    }

    override fun onCharacteristicReadRequest(
        device: BluetoothDevice?,
        requestId: Int,
        offset: Int,
        characteristic: BluetoothGattCharacteristic?
    ) {
        super.onCharacteristicReadRequest(device, requestId, offset, characteristic)
        if (device != null) {
            Log.e("BLE", "Characteristics read request by ${device.address}")
        } else {
            Log.e("BLE", "Device is null when reading")
        }
    }

    override fun onConnectionStateChange(device: BluetoothDevice?, status: Int, newState: Int) {
        super.onConnectionStateChange(device, status, newState)
    }

    override fun onPhyRead(device: BluetoothDevice?, txPhy: Int, rxPhy: Int, status: Int) {
        super.onPhyRead(device, txPhy, rxPhy, status)
    }

    override fun onDescriptorWriteRequest(
        device: BluetoothDevice?,
        requestId: Int,
        descriptor: BluetoothGattDescriptor?,
        preparedWrite: Boolean,
        responseNeeded: Boolean,
        offset: Int,
        value: ByteArray?
    ) {
        super.onDescriptorWriteRequest(
            device,
            requestId,
            descriptor,
            preparedWrite,
            responseNeeded,
            offset,
            value
        )
    }

    override fun onServiceAdded(status: Int, service: BluetoothGattService?) {
        super.onServiceAdded(status, service)
    }
}