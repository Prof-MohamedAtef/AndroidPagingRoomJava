package mo.ed.aad.pagingroomimpl.room;

import android.content.Context;

public class AppDatabaseInitializer {
    public static AppDatabase appDatabase=null;
    private static AppExecutors mAppExecutors;

    public static AppDatabase getAppDatabase(Context context){
        if (appDatabase==null){
            appDatabase =new AppDatabase() {
                @Override
                public PersonDao personDao() {
                    return null;
                }

                @Override
                public void clearAllTables() {

                }
            };
            mAppExecutors = new AppExecutors();
            appDatabase= AppDatabase.getAppDatabase(context,mAppExecutors);

        }
        return  appDatabase;
    }
}
