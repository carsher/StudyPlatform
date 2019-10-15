package com.example.administrator.learning.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class SpUtils {
    private static SharedPreferences sp;
    /**
     * @param context
     * @param key
     * @param values
     * �洢����
     */
    public static void putString(Context context, String key, String values){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        sp.edit().putString(key, values).commit();
    }
    public static String getString(Context context, String key){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        String result = sp.getString(key, "null");
        return result;
    }

    public static void putBoolean(Context context, String key, Boolean values){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        sp.edit().putBoolean(key, values).commit();
    }
    public static Boolean getBoolean(Context context, String key){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        boolean result = sp.getBoolean(key, false);
        return result;
    }

    public static void putInt(Context context, String key, int values){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        sp.edit().putInt(key, values).commit();
    }

    public static int getInt(Context context, String key){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        int result = sp.getInt(key, 0);
        return result;
    }

    public static void remove(Context context,String key){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        sp.edit().remove(key).commit();
    }

    public static void removeAll(Context context){
        if(sp==null){
            sp = context.getSharedPreferences(StaticCost.CONFIG,0);
        }
        sp.edit().clear().commit();
    }



}
