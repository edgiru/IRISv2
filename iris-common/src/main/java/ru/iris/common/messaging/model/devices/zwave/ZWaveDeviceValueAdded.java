package ru.iris.common.messaging.model.devices.zwave;

import com.google.gson.annotations.Expose;
import ru.iris.common.database.model.devices.Device;

/**
 * IRISv2 Project
 * Author: Nikolay A. Viguro
 * WWW: iris.ph-systems.ru
 * E-Mail: nv@ph-systems.ru
 * Date: 19.11.13
 * Time: 11:34
 * License: GPL v3
 */
public class ZWaveDeviceValueAdded {
    /**
     * Zwave device
     */
    @Expose
    private Device device;

    @Expose
    private String label;

    @Expose
    private String value;

    public ZWaveDeviceValueAdded set(Device device, String label, String value) {
        this.device = device;
        this.label = label;
        this.value = value;
        return this;
    }

    /**
     * Default constructor for de-serialisation.
     */
    public ZWaveDeviceValueAdded() {
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ZWaveDeviceValueAdded{" +
                "zwaveDevice=" + device.getInternalName() +
                '}';
    }
}
