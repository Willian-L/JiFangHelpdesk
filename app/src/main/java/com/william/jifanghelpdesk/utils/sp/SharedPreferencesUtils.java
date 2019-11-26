package com.william.jifanghelpdesk.utils.sp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.william.jifanghelpdesk.context.MyApplication;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesUtils {
    private SharedPreferences share;
    private SharedPreferences.Editor editor;
    private String SHARED_NAME = "user_info";//sp的文件名

    // MyAPP.getContext() 是你的Application里面的一个Context
    private SharedPreferencesUtils() {
        share = MyApplication.getInstance().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        editor = share.edit();
    }

    /**
     * 单例模式
     */
    private static SharedPreferencesUtils instance;//单例模式 双重检查锁定
    public static SharedPreferencesUtils getInstance() {
        if (instance == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (instance == null) {
                    instance = new SharedPreferencesUtils();
                }
            }
        }
        return instance;
    }


    /**
     * ------- Int ---------
     */
    public void putInt(String spName, int value) {
        editor.putInt(spName, value);
        editor.commit();
    }

    public int getInt(String spName, int defaultvalue) {
        return share.getInt(spName, defaultvalue);
    }

    /**
     * ------- String ---------
     */
    public void putString(String spName, String value) {
        editor.putString(spName, value);
        editor.commit();
    }

    public String getString(String spName, String defaultvalue) {
        return share.getString(spName, defaultvalue);
    }

    public String getString(String spName) {
        return share.getString(spName, "");
    }


    /**
     * ------- boolean ---------
     */
    public void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return share.getBoolean(key, defValue);
    }

    /**
     * ------- float ---------
     */
    public void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(String key, float defValue) {
        return share.getFloat(key, defValue);
    }


    /**
     * ------- long ---------
     */
    public void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key, long defValue) {
        return share.getLong(key, defValue);
    }

    /**
     * ------- Map集合 ---------
     */
    public <K,T> void putMap(String key, Map<K,T> map){
        if (map == null || map.isEmpty() || map.size() < 1){
            return;
        }
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        editor.putString(key, strJson);
        editor.commit();
    }

    public <K,T> Map<K,T> getMap(String key){
        Map<K,T> map = new HashMap<>();
        String strJson = share.getString(key, null);
        if (strJson == null){
            return map;
        }
        Gson gson = new Gson();
        map = gson.fromJson(strJson,new TypeToken<Map<K,T>>(){}.getType());
        return map;
    }

    /**
     * 清空SP里所有数据
     */
    public void clear() {
        editor.clear();//清空
        editor.commit();//提交
    }

    /**
     * 删除SP里指定key对应的数据项
     *
     * @param key
     */
    public void remove(String key) {
        editor.remove(key);//删除掉指定的值
        editor.commit();//提交
    }

    /**
     * 查看sp文件里面是否存在此 key
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        return share.contains(key);
    }

}
