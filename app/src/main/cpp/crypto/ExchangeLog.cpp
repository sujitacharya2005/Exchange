#include "ExchangeLog.h"


namespace Exchange {
    bool ExchangeLog::logCryptoData(const char *path,
                                    long time,
                                    const char *symbol,
                                    double usdPrice) {

        FILE *file = fopen(path, "a");

        if (file != NULL) {
            fprintf(file, "<%ld> <%s> <%lf>\n", time, symbol, usdPrice);
            fflush(file);
            fclose(file);
        } else {
            LOGE("Error in File open");
            return false;
        }

        return true;

    }
}