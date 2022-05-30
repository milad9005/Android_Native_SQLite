#include <jni.h>
#include <string>
#include "sqlite/sqlite3.h"
#include <android/log.h>

static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
    int i;
    for(i = 0; i<argc; i++) {
        __android_log_print(ANDROID_LOG_ERROR, "TRACKERS",  "%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL" );
    }
    printf("\n");
    return 0;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_androiod_room_1cpp_MainActivity_readDataBase(
        JNIEnv* env,
        jobject /* this */,
        jstring path) {

    const char *nativeString = env->GetStringUTFChars(path, 0);
    sqlite3 *db;
    char *zErrMsg = 0;
    int rc;
    char *sql;
    const char* data = "Callback function called";

    /* Open database */
    rc = sqlite3_open(nativeString, &db);

    if( rc ) {
        __android_log_print(ANDROID_LOG_ERROR, "TRACKERS",  "Can't open database: %s\n", sqlite3_errmsg(db));
        return(0);
    } else {
        __android_log_print(ANDROID_LOG_ERROR, "TRACKERS",  "Opened database successfully\n");
    }

    /* Create SQL statement */
    sql = "SELECT * from Sample";

    /* Execute SQL statement */
    rc = sqlite3_exec(db, sql, callback, (void*)data, &zErrMsg);

    if( rc != SQLITE_OK ){
        fprintf(stderr, "SQL error: %s\n", zErrMsg);
        sqlite3_free(zErrMsg);
    } else {
        fprintf(stdout, "Table created successfully\n");
    }
    sqlite3_close(db);
    return 0;
}

