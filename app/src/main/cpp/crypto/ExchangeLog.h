
#ifndef EXCHANCE_LOG
#define EXCHANCE_LOG

#include <pthread.h>
#include <unistd.h>
#include <jni.h>
#include <string>
#include <iostream>'
#include <android/log.h>


#define  LOG_TAG    "CryptoNative"


#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define  LOGW(...)  __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define  LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)


namespace Exchange {
    class ExchangeLog {
    public:
        bool logCryptoData(const char *path, long time, const char *symbol, double usdPrice);
    };
}
#endif
