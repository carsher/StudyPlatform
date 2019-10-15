package com.example.administrator.learning.video;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

public class TestRxJAva {
public void init(){

    rx.Observer<String> observer = new rx.Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }
    };
    //一个实现了 Observer 的抽象类：Subscriber
    final Subscriber<String > stringSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {

        }

        @Override
        public void onStart() {
            super.onStart();
        }
    };
    /*
    * 这里传入了一个 OnSubscribe 对象作为参数。OnSubscribe 会被存储在返回的 Observable 对象中，它的作用相当于一个计划表，
    * 当 Observable 被订阅的时候，OnSubscribe 的 call() 方法会自动被调用，
    * 事件序列就会依照设定依次触发（对于上面的代码，就是观察者Subscriber 将会被调用三次 onNext() 和一次 onCompleted()）。
    * 这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。*/
    //创造事件序列的方法
    rx.Observable<String > observable = rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("HELLO");
            subscriber.onNext("WORLD");
            subscriber.onNext("!");
            subscriber.onCompleted();
        }
    });
    //创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了。代码形式很简单
    observable.subscribe(stringSubscriber);//第一种
   // observable.subscribe(observer);//第二种
    String [] name = {"HELLO","WORLD","!!"};
    rx.Observable.from(name).subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
            Log.e("FFFFFF","name: "+s);
        }
    });

// 注意：这不是 subscribe() 的源码，而是将源码中与性能、兼容性、扩展性有关的代码剔除后的核心代码。
// 如果需要看源码，可以去 RxJava 的 GitHub 仓库下载。
//    public Subscription subscribe(Subscriber subscrib) {
//        subscriber.onStart();
//        onSubscribe.call(subscriber);
//        return subscriber;
//    }

}


}
