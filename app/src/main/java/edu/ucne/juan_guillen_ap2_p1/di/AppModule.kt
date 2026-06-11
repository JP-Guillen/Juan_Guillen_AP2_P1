package edu.ucne.juan_guillen_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.juan_guillen_ap2_p1.data.db.AmonestacionDb
import edu.ucne.juan_guillen_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.juan_guillen_ap2_p1.data.repository.AmonestacionRepositoryImpl
import edu.ucne.juan_guillen_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAmonestacionDb(@ApplicationContext appContext: Context): AmonestacionDb =
        Room.databaseBuilder(
            appContext,
            AmonestacionDb::class.java,
            "AmonestacionDb"
        ).fallbackToDestructiveMigration(dropAllTables = false)
            .build()

    @Provides
    @Singleton
    fun provideAmonestacionDao(db: AmonestacionDb): AmonestacionDao = db.amonestacionDao()

    @Provides
    @Singleton
    fun provideAmonestacionRepositoryImpl(dao: AmonestacionDao): AmonestacionRepositoryImpl =
        AmonestacionRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAmonestacionRepository(impl: AmonestacionRepositoryImpl): AmonestacionRepository = impl
}