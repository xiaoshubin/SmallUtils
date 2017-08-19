package com.smallcake.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.smallcake.utils.test", appContext.getPackageName());
    }
    @Test
    public void SdTest(){
        Context appContext = InstrumentationRegistry.getTargetContext();

        try {
            L.i("sd卡路径=="+ SdUtils.getPathFile().getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        L.i("下载路径=="+ SdUtils.getDownloadPathFile().getPath());

    }
    @Test
    public void MobileTest(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        L.i("手机名称=="+MobileUtils.getMobileName());
        L.i("手机Mac地址名称=="+MobileUtils.getMacAddrNew());
//        L.i("手机唯一标示=="+MobileUtils.getMobileUnique(appContext));
    }


}
