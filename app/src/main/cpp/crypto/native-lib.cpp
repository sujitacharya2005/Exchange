//
// Created by Sujit Acharya on 15/05/22.
//

#include <jni.h>
#include <string>
#include <iostream>
#include <android/log.h>
#include "ExchangeLog.h"

using namespace std;

extern "C"
JNIEXPORT void JNICALL
Java_com_android_exchange_repository_CoinExchangeRateRepositoryImpl_saveLogJNI(JNIEnv *env,
                                       jobject thiz,
                                       jstring path,
                                       jlong time,
                                       jstring symbol,
                                       jdouble usd_price)

{
    const char *coinSymbol = env->GetStringUTFChars(symbol, 0);
    const char *pathValue = env->GetStringUTFChars(path, 0);
    Exchange::ExchangeLog exchangeLog;
    exchangeLog.logCryptoData(pathValue, time, coinSymbol, usd_price);
}
