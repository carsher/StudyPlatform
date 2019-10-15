package com.example.administrator.learning.common.rxbus;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by asdf on 2018/10/16.
 * Rxjava 封装使用
 */


public class RxDao implements IRxDao{
    private Set<Object> msubscript;
    private Subscription chainsubscript;
    public static RxDao instance = null;

    private RxDao(){
        msubscript = new CopyOnWriteArraySet<>();  //线程安全?
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  10:10
     *  @方法说明：双重锁校验 线程安全
     */
    public static synchronized RxDao getInstance(){
        if (instance == null){
            synchronized (RxDao.class){
                //双重校验锁
                if (instance==null){
                    instance = new RxDao();
                    Log.i("Rxdao实例","我被创建了一次了");
                }
            }
        }
        return instance;
    }


    public void ChainProcess(Func1 func){
        chainsubscript = rx.Observable.just("").subscribeOn(Schedulers.computation())  //被观察者(接下来的方法) 在计算线程池中调用
                .map(func)  //被观察者 线程池调用的是计算线程池
                .observeOn(AndroidSchedulers.mainThread())  //观察者 在ui线程中执行，即 subscribe 事件
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object data) {
                        //扫描注解，回调presenter中的方法 当执行完map中的方法的时候，会返回一个数据 就是data
                        for (Object trage: msubscript
                                ) {
                            //扫描当前 集合中存储的对象 其中的方法是否包含注解  target 目标执行对象
                            Log.i("开始扫描注解","扫描注解");
                            sweepAnnotion(trage,data);

                        }

                    }
                });
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  10:20
     *  @方法说明：扫描注解，回调方法
     */
    public void sweepAnnotion(Object traget, Object data){
        if(data==null){
            return;
        }
        Log.i("开支","扫描注解");
        Method[] methodArray = traget.getClass().getDeclaredMethods();
        Log.i("开始扫描方法","方法数量："+methodArray.length + "扫描对象名称"+ traget.getClass().getName());
        Method[] methodArr =  traget.getClass().getDeclaredMethods();
        for (int i = 0; i < methodArr.length; i++){
            try {
                if (methodArr[i].isAnnotationPresent(Rx_Register.class)){
                    if(data.getClass().getName().equals(methodArr[i].getParameterTypes()[0].getName())){
                        Log.e("方法嗲用好了", "调用那个方法了");
                        methodArr[i].invoke(traget,new Object[]{data});
                        break;
                    }
                }
            }catch (Exception e){
                Log.i("注解扫描出现异常","" + e.getMessage());
            }

        }



    }



    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  10:21
     *  @方法说明：绑定对象
     */
    @Override
    public void register(Object subscript) {
        msubscript.add(subscript);
    }

    /**
     *  @创建用户 somafish
     *  @创建时间 2018/10/16  10:22
     *  @方法说明：注销绑定
     */
    @Override
    public void unregister(Object subscript) {
        Log.i("Rxdao。我被调用了一次","unregister");
        msubscript.remove(subscript);
    }
}
