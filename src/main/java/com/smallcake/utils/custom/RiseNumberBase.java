package com.smallcake.utils.custom;

/**
 * MyApplication --  cq.cake.custom
 * Created by Small Cake on  2017/5/20 11:17.
 */

 interface RiseNumberBase {
     void start();

     RiseNumberTextView withNumber(float number);

     RiseNumberTextView withNumber(float number, boolean flag);

     RiseNumberTextView withNumber(int number);

     RiseNumberTextView setDuration(long duration);

     void setOnEnd(RiseNumberTextView.EndListener callback);
}
