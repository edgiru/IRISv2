package ru.iris.common.messaging.model.devices;

import com.google.gson.annotations.Expose;
import ru.iris.common.messaging.model.Advertisement;

/**
 * IRISv2 Project
 * Author: Nikolay A. Viguro
 * WWW: iris.ph-systems.ru
 * E-Mail: nv@ph-systems.ru
 * Date: 19.11.13
 * Time: 11:34
 * License: GPL v3
 */
public class GetInventoryAdvertisement extends Advertisement {
    /**
     * Device UUID
     */
    @Expose
    private String deviceUUID;

    public GetInventoryAdvertisement set(String deviceUUID)
    {
        this.deviceUUID = deviceUUID;
        return this;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    @Override
    public String toString() {
        return "GetInventoryAdvertisement { UUID: " + deviceUUID + " }";
    }
}
