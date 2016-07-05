package com.cleaner.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangfeng on 15-7-1.
 */
public final class AssetsUtils {
    private static final String TAG = AssetsUtils.class.getSimpleName();

    private static Map<String, String> cachePage = new HashMap<>();

    private AssetsUtils() {
        //Utility classes should not have a public or default constructor.
    }

    public static String loadFirstPageItem(Context context, String fileName) {
        if (cachePage.containsKey(fileName)) {
            return cachePage.get(fileName);
        }

        InputStream is = null;
        BufferedReader br = null;
        try {
            is = context.getResources().getAssets().open(fileName);
            if (is != null) {
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer buf = new StringBuffer();
                String line;
                while (null != (line = br.readLine())) {
                    buf.append(line).append('\n');
                }

                cachePage.put(fileName, buf.toString());
                br.close();
                is.close();
                return buf.toString();
            }
        } catch (IOException e) {
            Log.e(TAG, "loadFirstPageItem: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "loadFirstPageItem: " + e.getMessage());
            }
        }
        return null;
    }
}
