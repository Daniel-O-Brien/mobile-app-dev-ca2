package ie.setu.assignment2.models

interface UserStore {
    fun signup(user: UserModel): UserModel?

    fun login(user: UserModel): UserModel?

    fun find(user: UserModel): UserModel?
}