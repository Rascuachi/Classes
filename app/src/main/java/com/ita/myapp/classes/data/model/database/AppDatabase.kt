package com.ita.myapp.classes.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ita.myapp.classes.data.model.ServiceEntity
import com.ita.myapp.classes.data.model.dao.ServiceDao

// Define la base de datos con Room
@Database(entities = [ServiceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // Declara el DAO asociado a la base de datos
    abstract fun serviceDao(): ServiceDao
}
