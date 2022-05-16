//
// Created by Sujit Acharya on 16/05/22.
//

#include <gtest/gtest.h>
#include "../../crypto/ExchangeLog.h"

typedef FILE *(*fopen_type)(const char *, const char *);

FILE *file(fopen_type fopen_func)
{
    FILE *f = fopen_func("abc", "r"); // Call the provided "fopen" function.
    return f; // Let's return the opened file or `NULL`.
}

TEST(ExchangeTest,FileWritePass) {
Exchange::ExchangeLog exchangeLog;
    EXPECT_EQ(exchangeLog.logCryptoData("pathValue", 1652711901358, "btc", 29715), false);
}


TEST(ExchangeTest,FileWriteFail) {
    Exchange::ExchangeLog exchangeLog;
    EXPECT_EQ(exchangeLog.logCryptoData("pathValue", 1652711901358, "btc", 29715), true);
}
