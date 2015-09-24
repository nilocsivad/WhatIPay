package com.iam_vip.c;

/**
 * Created by niloc on 2015/8/26.
 */
public interface C {

    String APP = "com.iam_vip.whatipay";

    String TYPE_PAY_101 = "收入", TYPE_PAY_102 = "支出", TYPE_PAY_103 = "借入", TYPE_PAY_104 = "借出";
    int TYPE_PAY_IN = 101, TYPE_PAY_OUT = 102, TYPE_PAY_BORROW_IN = 103, TYPE_PAY_BORROW_OUT = 104;

    int SIZE = 30;

    int WHAT_OK = 1001, WHAT_DONE = 1002, WHAT_FINISH = 1003;
    int WHAT_CANCLE = 2001, WHAT_QUIT = 2002;

    String DB_NAME = "whatipay.db";

    String APP_FOLDER = "WhatIPay";

}
