package ie.setu.assignment2.models

interface UserStore {
    fun signup(user: UserModel)

    fun login(user: UserModel)
}