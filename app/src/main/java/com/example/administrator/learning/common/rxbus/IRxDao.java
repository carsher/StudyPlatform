package com.example.administrator.learning.common.rxbus;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public interface IRxDao {
    void register(Object subscript);
    void unregister(Object subscript);
}
