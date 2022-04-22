package com.tmp.themovieapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmp.themovieapp.dao.MovieDao
import com.tmp.themovieapp.entity.MovieInfo

// 데이터베이스 이전은 이 Codelab의 범위를 벗어나므로 exportSchema는 빌드 경고를 피하기 위해 false로 설정
// 실제 앱에서는 현재 스키마를 버전 제어 시스템으로 확인할 수 있도록 스키마를 내보내는 데 사용할 Room 디렉터리를 설정하는 것이 좋음
@Database(entities = arrayOf(MovieInfo::class), version = 1, exportSchema = false)
abstract class FavoriteRoomDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        fun getDatabase(context: Context): FavoriteRoomDatabase {
            // MovieInfoRoomDatabase 클래스의 애플리케이션 컨텍스트에서 RoomDatabase 객체를 만들고
            // 이름을 "favorite_database"로 지정합니다
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FavoriteRoomDatabase::class.java,
                    "favorite_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
