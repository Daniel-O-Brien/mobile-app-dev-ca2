package ie.setu.assignment1.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

//https://www.baeldung.com/kotlin/dates

@Parcelize
data class VideoGameModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var developer: String = "",
                          var image: String = "",
                          var releaseDate: LocalDate = LocalDate.parse("1970-01-01")) : Parcelable