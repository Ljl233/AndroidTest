package com.example.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxJavaBackPressureTest();
            }
        });
//        Log.d(TAG, Thread.currentThread().getName());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                rxJavaThreadTest();
//            }
//        }).start();
    }

    void rxJavaBackPressureTest() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                int i = 0;
                while (i < 1000) {
                    emitter.onNext(i);
                    Log.e(TAG, "emit  " + i);

                    i++;
                }
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "accept   " + integer);
            }
        };
        Consumer<Integer> consumer2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, " accept      from     2  " + integer);
            }
        };

       Disposable disposable= observable.observeOn(Schedulers.newThread())
                .doOnNext(consumer2)
                .subscribeOn(Schedulers.io())
                .subscribe(consumer);

        //disposable.dispose();
    }

    void rxJavaChangeThreadTest() {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext(1);
                Log.e(TAG, "observable emit--->" + 1);
                Log.e(TAG, "after the thread is---->" + Thread.currentThread().getName());

                emitter.onNext(2);
                Log.e(TAG, "observable emit--->" + 2);
                Log.e(TAG, "after the thread is---->" + Thread.currentThread().getName());
            }
        });
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer o) throws Exception {
                Log.e(TAG, "consumer accept---->" + o +
                        "        the thread is----->" + Thread.currentThread().getName());
            }
        };

        observable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .doOnNext(consumer)
                .observeOn(Schedulers.io())
                .subscribe(consumer);
    }

    void rxJavaThreadTest() {
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, Thread.currentThread().getName());
                Log.d(TAG, "accept" + integer);
            }
        };
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                Log.d(TAG, Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "Observable:" + Thread.currentThread().getName());
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
            }
        });

        observable.subscribeOn(Schedulers.newThread())
                .doOnNext(consumer)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(consumer)
                .observeOn(Schedulers.io())
                .doOnNext(consumer)
                .subscribe(consumer);
    }

    void rxJavaTest() {//创建被观察者
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emitter" + 1);
                emitter.onNext(1);
                Log.d(TAG, "emitter complete");
                emitter.onComplete();
                Log.d(TAG, "emitter" + 2);
                emitter.onNext(2);
                Log.d(TAG, "emitter" + 3);
                emitter.onNext(3);
                Log.d(TAG, "emitter" + 1);
                emitter.onNext(1);
                Log.d(TAG, "emitter" + 2);
                emitter.onNext(2);
                Log.d(TAG, "emitter" + 3);
                emitter.onNext(3);
                Log.d(TAG, Thread.currentThread().getName());

            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, Thread.currentThread().getName());

                Log.d(TAG, "onNext" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.d(TAG, "completed");
            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
//                Log.d(TAG, "dispose");
//                disposable.dispose();
//                Log.d(TAG, "isDisposed:" + disposable.isDisposed());
            }
        });
//        new Observer<Integer>() {
//            Disposable mDisposable;
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe");
////                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//                Log.d(TAG, "onNext" + integer);
//                if (integer == 2) {
//                    Log.d(TAG, "dispose");
//                    mDisposable.dispose();
//                    Log.d(TAG, "isDisposed:" + mDisposable.isDisposed());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete");
//            }
//        }

    }
}