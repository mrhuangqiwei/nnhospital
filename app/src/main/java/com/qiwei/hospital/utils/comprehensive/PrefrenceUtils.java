package com.qiwei.hospital.utils.comprehensive;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefrenceUtils {

    public static final String LAST_USER_NAME = "last_user_name";



    private static final String DEFAULT_FILE_NAME = "default_prefrence_name";

    public static String getString(Context context, String name, String defaultValue) {
        if(context == null) return "";
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        return prefer.getString(name, defaultValue);
    }

    public static void putString(Context context, String name, String value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        prefer.edit().putString(name, value).commit();
    }

    public static boolean getBoolean(Context context, String name, boolean defaultValue) {
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        return prefer.getBoolean(name, defaultValue);
    }

    public static void putBoolean(Context context, String name, boolean value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        prefer.edit().putBoolean(name, value).commit();
    }

    public static long getLong(Context context, String name, long defaultValue) {
        if(context == null) return -1;
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        return prefer.getLong(name, defaultValue);
    }

    public static void putLong(Context context, String name, long value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        prefer.edit().putLong(name, value).commit();
    }

    public static int getInt(Context context, String name, int defaultValue) {
        if(context == null) return -1;
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        return prefer.getInt(name, defaultValue);
    }

    public static void putInt(Context context, String name, int value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        prefer.edit().putInt(name, value).commit();
    }


    public static String getString(Context context, String fileName, String name, String defaultValue) {
        if(context == null) return "";
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return prefer.getString(name, defaultValue);
    }

    public static void putString(Context context, String fileName, String name, String value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        prefer.edit().putString(name, value).commit();
    }

    public static boolean getBoolean(Context context, String fileName, String name, boolean defaultValue) {
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return prefer.getBoolean(name, defaultValue);
    }

    public static void putBoolean(Context context, String fileName, String name, boolean value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        prefer.edit().putBoolean(name, value).commit();
    }

    public static long getLong(Context context, String fileName, String name, long defaultValue) {
        if(context == null) return -1;
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return prefer.getLong(name, defaultValue);
    }

    public static void putLong(Context context, String fileName, String name, long value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        prefer.edit().putLong(name, value).commit();
    }

    public static int getInt(Context context, String fileName, String name, int defaultValue) {
        if(context == null) return -1;
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return prefer.getInt(name, defaultValue);
    }

    public static void putInt(Context context, String fileName, String name, int value) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        prefer.edit().putInt(name, value).commit();
    }

    public static void remove(Context context, String fileName, String name) {
        if(context == null) return;
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        prefer.edit().remove(name).commit();
    }

    public static SharedPreferences getSharedPreferences(Context context, String fileName) {
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return prefer;
    }

    public static SharedPreferences.Editor getEditor(Context context, String fileName) {
        SharedPreferences prefer = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return prefer.edit();
    }
}
