package com.example.roomdemo.data.source;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdemo.WordRoomDatabase;
import com.example.roomdemo.data.Word;

import java.util.List;

//将database的实现细节隐藏，将database与dao进行联系,将数据库的操作放到另一个线程之中
public class WordRepository {
    private WordDao mDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mDao = db.wordDao();
        mAllWords = mDao.getAlphabetizedWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTack(mDao).execute(word);
    }

    public static class insertAsyncTack extends AsyncTask<Word, Void, Void> {

        WordDao dao;

        public insertAsyncTack(WordDao mDao) {
            dao = mDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            dao.insert(words[0]);
            return null;
        }
    }

}
//    private WordDao mWordDao;
//    private LiveData<List<Word>> mAllWords;
//
//    public WordRepository(Application application) {
//        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
//        mWordDao = db.wordDao();
//        mAllWords = mWordDao.getAlphabetizedWords();
//    }
//
//    //add a wrapper for getAlphabetizedWords()
//    public LiveData<List<Word>> getAllWords() {
//        return mAllWords;
//    }
//
//
//    public void insert(Word word) {
//        new insertAsyncTask(mWordDao).execute(word);
//    }
//
//    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {
//
//        private WordDao mAsyncTaskDao;
//
//        insertAsyncTask(WordDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final Word... params) {
//            mAsyncTaskDao.insert(params[0]);
//            return null;
//        }
//    }
