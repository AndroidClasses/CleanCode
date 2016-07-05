package com.cleaner.config;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PatientUtil {
    private static final String TAG = PatientUtil.class.getSimpleName();

    private static Map<String, String> cachePage = new HashMap<>();
    private static Map<String, String> cacheConfigItems = new HashMap<>();
    private static Map<String, List<FirstPageItemListModel>> cachePageItems = new HashMap<>();
    private static Map<String, String> cacheH5Items = new HashMap<>();

    private PatientUtil() {
        // EMPTY
    }

    private static String loadFirstPageItem(Context context, String fileName) {
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

    private static void addCacheConfigItems(String key, String items) {
        if (null != items && !items.isEmpty()) {
            cacheConfigItems.put(key, items);
        }
    }

    private static String getCacheConfigItems(String key) {
        return cacheConfigItems.get(key);
    }

    private static void addCachePageItems(String key, List<FirstPageItemListModel> items) {
        if (null != items && !items.isEmpty()) {
            cachePageItems.put(key, items);
        }
    }

    private static List<FirstPageItemListModel> getCachePageItems(String key) {
        return cachePageItems.get(key);
    }

    private static String loadFirstPageItem(Context context) {
        return loadFirstPageItem(context, "first_page_item");
    }

    private static String loadH5Info(Context context) {
        if (isOnlyShowCards(context)) {
            return loadFirstPageItem(context, "h5_url_info_old");
        } else {
            return loadFirstPageItem(context, "h5_url_info");
        }
    }

    public static List<String> getActivityConfigs(Context context, String key) {
        ArrayList<String> configs;
        String config = loadConfigItem(context, key);
        if (TextUtils.isEmpty(config)) {
            configs = new ArrayList<>();
        } else {
            configs = new ArrayList(Arrays.asList(config.replaceAll(" ", "").split(",")));
        }
        return configs;
    }

    public static List<String> getActivityConfigsArray(Context context, String key) {
        ArrayList<String> configs = new ArrayList<>();
        String config = loadConfigItem(context, key);
        JSONArray jsonArray = JSONObject.parseArray(config);
        if (null != jsonArray) {
            for (int i = 0; i < jsonArray.size(); i++) {
                configs.add(jsonArray.get(i).toString());
            }
        }
        return configs;
    }

    public static String loadConfigItem(Context context, String key) {
        String config = getCacheConfigItems(key);
        if (null == config || config.isEmpty()) {
            String pageItemJsonStr = loadFirstPageItem(context);
            JSONObject body = JSONObject.parseObject(pageItemJsonStr);
            config = body.getString(key);
            if (null != config) {
                addCacheConfigItems(key, config);
            }
        }
        return config;
    }

    private static void addCacheH5Items(String key, String items) {
        if (null != items && !items.isEmpty()) {
            cacheH5Items.put(key, items);
        }
    }

    private static String getCacheH5Items(String key) {
        return cacheH5Items.get(key);
    }

    public static String getH5Item(Context context, String key) {
        String url = loadH5Item(context, key);
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        return url;
    }

    public static String loadH5Item(Context context, String key) {
        String config = getCacheH5Items(key);
        if (null == config || config.isEmpty()) {
            String pageItemJsonStr = loadH5Info(context);
            JSONObject body = JSONObject.parseObject(pageItemJsonStr);
            if (null == body) {
                return null;
            }
            config = body.getString(key);
            if (null != config) {
                addCacheH5Items(key, config);
            }
        }
        return config;
    }

    public static List<FirstPageItemListModel> loadPageItem(Context context, String category) {
        List<FirstPageItemListModel> items = getCachePageItems(category);
        if (null == items || items.isEmpty()) {
            String pageItemJsonStr = loadFirstPageItem(context);
            JSONObject body = JSONObject.parseObject(pageItemJsonStr);
            String itemInfoStr = body.getString(category);
            if (null != itemInfoStr) {
                JSONArray jsonArray = JSONObject.parseArray(itemInfoStr);
                if (null != jsonArray) {
                    items = new ArrayList<>();
                    ArrayList<FirstPageItemInfo> info;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        info = (ArrayList<FirstPageItemInfo>) JSONObject.parseArray(jsonArray.get(i).toString(),
                                FirstPageItemInfo.class);
                        FirstPageItemListModel model = new FirstPageItemListModel();
                        model.setFirst_page_array(info);
                        items.add(model);
                    }
                }
                addCachePageItems(category, items);
            }
        }

        return items;
    }

    public static List<FirstPageItemListModel> loadCardViewPageItem(Context context, String category) {
        List<FirstPageItemListModel> items = getCachePageItems(category);
        if (null == items || items.isEmpty()) {
            String pageItemJsonStr = loadFirstPageItem(context);
            JSONObject body = JSONObject.parseObject(pageItemJsonStr);
            String itemInfoStr = body.getString(category);
            if (null != itemInfoStr) {
                items = (ArrayList<FirstPageItemListModel>) JSONObject.parseArray(itemInfoStr, FirstPageItemListModel.class);
                addCachePageItems(category, items);
            }
        }
        return items;
    }

    public static boolean isOnlyShowCards(Context context) {
        List<String> mActivityConfigs = getActivityConfigs(context, BaseConstantDef.CONFIG_KEY_REGISTRATIONCARD_LIST_STYLE);
        return mActivityConfigs.contains(BaseConstantDef.CONFIG_KEY_REGISTRATIONCARD_LIST_STYLE_ITEM_ONLY_CARD);
    }
}
