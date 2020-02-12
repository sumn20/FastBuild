package com.seeyou.toolkit.utils;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

/**
 * @author xialei
 * date 2019/8/2
 */
public class CameraUtils {


    /**
     * 检查设备是否有摄像头
     *
     * @return
     */
    public static boolean hasCamera() {
        CameraManager manager = (CameraManager) Utils.getApp().getSystemService(Context.CAMERA_SERVICE);
        String[] cameraIds;
        try {
            cameraIds = manager.getCameraIdList();
            if (cameraIds.length > 0) {
                //后置摄像头存在
                if (cameraIds[0] != null) {
                    return true;
                }

                if (cameraIds[1] != null) {
                    return true;
                }
            } else {
                return false;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


}
