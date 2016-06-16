package com.haokukeji.autoapk;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ChannelUtil {

    /**
     * 获取渠道名
     *
     * @param context 上下文
     * @return 渠道名
     */
    public static String getChannel(Context context) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        String sourceDir = appInfo.sourceDir;
        String result = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("META-INF/channel")) {
                    result = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] split = result.split("_");
        if (split != null && split.length >= 2) {
            return result.substring(split[0].length() + 1);
        } else {
            return "";
        }
    }
}
