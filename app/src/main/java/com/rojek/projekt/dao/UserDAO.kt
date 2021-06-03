package com.rojek.projekt.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rojek.projekt.entities.User

@Dao
interface UserDAO {

    @Query("Select * from users")
    fun getAllUsers(): List<User>?

    @Query("Select * from users where username= :username LIMIT 1")
    fun getUserByUsername(username: String): User

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    fun insertUser(user: User)
}