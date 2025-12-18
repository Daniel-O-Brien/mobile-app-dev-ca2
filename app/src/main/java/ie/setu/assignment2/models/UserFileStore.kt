package ie.setu.assignment2.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber.i
import java.io.File
import java.time.LocalDate
import kotlin.random.Random

// data class Data (val a : ArrayList<UserModel>)

class UserFileStore(val context: Context): UserStore {

    lateinit var users: ArrayList<UserModel>

    val gson = Gson()
    val typeToken = object : TypeToken<ArrayList<UserModel>>() {}.type

    fun save(data: ArrayList<UserModel>) {
        val file = gson.toJson(data)
        context.openFileOutput("data", Context.MODE_PRIVATE).use {
            it.write(file.toByteArray())
        }
    }

    fun load() {
        if (File(context.filesDir, "data").exists()) {

            val data = context.openFileInput("data").bufferedReader().use { it.readText() }
            i("dataaa" + data)
            if (!data.isEmpty()) {
                users = gson.fromJson<ArrayList<UserModel>>(data, typeToken)
                i("5432" + users + "empty")
            } else
                users = ArrayList<UserModel>()
            i("5432" + users + "loaded")
        }
        else {
            users = ArrayList<UserModel>()
        }
    }

    override fun signup(user: UserModel): UserModel? {
        if (users.none { it.username == user.username }) {
            val newUser = UserModel(id = Random.nextLong(), username = user.username, password = user.password)
            users.add(newUser)
            save(users)
            return newUser
        }
        return null
    }

    override fun login(user: UserModel): UserModel? {
        if(users.any { it.username == user.username && it.password == user.password}){
            return user
        }
        return null
    }

}