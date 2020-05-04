package com.example.libnetwork.cache;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.libcommon.AppGlobals;

@Database(entities = {Cache.class}, version = 1, exportSchema = true)
public abstract class CacheDatabase extends RoomDatabase {

    private static final CacheDatabase database;

    static {
        //内存数据库，当进程被杀，数据库消失
//        Room.inMemoryDatabaseBuilder()
        database = Room.databaseBuilder(AppGlobals.getApplication(), CacheDatabase.class, "pp_joke")//String:数据库名称
                //允许主线程进行数据库的查询操作，默认false
//                .allowMainThreadQueries()
                //room日志模式
//                .setJournalMode()
//                .addCallback()
//                .addMigrations(new Migration() {
//                    @Override
//                    public void migrate(@NonNull SupportSQLiteDatabase database) {
//
//                    }
//                })
//                .fallbackToDestructiveMigration()
//                .fallbackToDestructiveMigrationFrom()
//                .setQueryExecutor()
//                .openHelperFactory()
                .build();
    }

    public CacheDatabase get() {
        return database;
    }
}
