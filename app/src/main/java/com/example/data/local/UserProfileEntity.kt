package com.example.data.local

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "رضا رضایی",
    val email: String = "reza.rezaei@example.com",
    val planName: String = "اشتراک پیشرفته Gojo Pro",
    val creditsRemaining: Int = 850,
    val pendingFiles: Int = 3,
    val isLoggedIn: Boolean = true,
    val customApiKey: String = ""
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(user: UserProfileEntity)

    @Update
    suspend fun updateUserProfile(user: UserProfileEntity)
}
