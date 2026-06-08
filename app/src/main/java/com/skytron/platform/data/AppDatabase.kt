package com.skytron.platform.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [MessageEntity::class, ReminderEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
   
    abstract fun reminderDao(): ReminderDao
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun get(ctx: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(ctx, AppDatabase::class.java, "skytron.db")
                .fallbackToDestructiveMigration().build().also { INSTANCE = it }
        }
    }
}
