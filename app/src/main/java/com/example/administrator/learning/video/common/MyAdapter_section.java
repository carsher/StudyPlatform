//package com.example.administrator.learning.video.common;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.example.administrator.learning.R;
//import com.example.administrator.learning.common.bean.sectionBean;
//import com.example.administrator.learning.video.common.bean.onRebulidList;
//import com.example.administrator.learning.video.common.bean.reList;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created by Administrator on 2018/10/23 0023.
// */
//
//public class MyAdapter_section extends BaseAdapter {
//
//    private Context ctx;
//    private List<sectionBean> list;
//    public MyAdapter_section(Context context, List<sectionBean> list) {
//        this.ctx = context;
//        this.list = list;
//     //   Log.e("cc判断是否有小节", list.size()+"s");
//
//    }
//
//    @Override
//    public int getCount() {
//      //  return list.size();
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = View.inflate(ctx, R.layout.item_section, null);
////        TextView tv_seril = (TextView) view.findViewById(R.id.tv_seril);
////        TextView tv_statu = (TextView) view.findViewById(R.id.tv_statu);
//       TextView tv_knobble = (TextView) view.findViewById(R.id.tv_knobble);
//        sectionBean bean = list.get(position);
//        List<HashMap> list1 = bean.getList();
//        tv_knobble.setText(bean.getSequence() + "" + bean.getKnobble());
//        RelativeLayout linearLayoutsec = (RelativeLayout) view.findViewById(R.id.lin_section);
//        //如果有多条消息
//        int smallsection = Integer.parseInt(bean.getSmallsection());
//        for (int i = 0; i < smallsection; i++) {
//            HashMap maper = list1.get(i);
//            Log.e("判断是否有走", "getView: "+maper.get("title") +"ss");
//            TextView tv = new TextView(ctx);//子View TextView
//            TextView tv2 = new TextView(ctx);
//            ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            RelativeLayout.LayoutParams layoutParams =
//                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//
//            tv.setLayoutParams(vlp);// 设置TextView的布局
//            tv2.setLayoutParams(layoutParams);
//            tv.setText(maper.get("title") + "");
//
//            if (maper.get("isread").equals("read")) {
//                tv2.setText("已看");
//            } else {
//                tv2.setText("未看");
//            }
//            linearLayoutsec.addView(tv);
//            linearLayoutsec.addView(tv2);
//        }
//
//        return view;
//
//    }
//}