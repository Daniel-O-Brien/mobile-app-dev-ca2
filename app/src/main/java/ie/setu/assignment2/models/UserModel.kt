package ie.setu.assignment2.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(var id: Long = 0,
                     var user: String = "",
                     var password: String = "") : Parcelable
