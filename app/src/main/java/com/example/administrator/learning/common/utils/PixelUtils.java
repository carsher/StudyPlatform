package com.example.administrator.learning.common.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Administrator on 2018/11/10 0010.
 */

public class PixelUtils {
    /**     * The context.     */
    private static Context mContext ;
    public static void initContent(Context context){
        mContext = context;
    }


    /**     * dp转 px.     *     * @param value the value     * @return the int     */
    public static int dp2px(float value) {
        final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);    }
    /**     * dp转 px.     *     * @param value   the value     * @param context the context
     *  * @return the int     */
    public static int dp2px(float value, Context context) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);    }
    /**     * px转dp.     *     * @param value the value     * @return the int     */
    public static int px2dp(float value) {
        final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
        return (int) ((value * 160) / scale + 0.5f);    }     /**     * px转dp.     *
     * @param value   the value     * @param context the context     * @return the int     */
        public static int px2dp(float value, Context context) {
            final float scale = context.getResources().getDisplayMetrics().densityDpi;
            return (int) ((value * 160) / scale + 0.5f);    }
    /**     * sp转px.     *     * @param value the value     * @return the int     */
    public static int sp2px(float value) {
        Resources r;
        if (mContext == null) {
            r = Resources.getSystem();
        } else {
            r = mContext.getResources();
        }
        float spvalue = value * r.getDisplayMetrics().scaledDensity;
        return (int) (spvalue + 0.5f);    }
    /**     * sp转px.     *     * @param value   the value     * @param context the context     * @return the int     */
    public static int sp2px(float value, Context context) {
        Resources r;
        if (context == null) {
            r = Resources.getSystem();
        } else {
            r = context.getResources();
        }
        float spvalue = value * r.getDisplayMetrics().scaledDensity;
        return (int) (spvalue + 0.5f);    }
    /**     * px转sp.     *     * @param value the value     * @return the int     */
    public static int px2sp(float value) {
        final float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / scale + 0.5f);    }
    /**     * px转sp.     *     * @param value   the value     * @param context the context     * @return the int     */
    public static int px2sp(float value, Context context) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / scale + 0.5f);    }


}
