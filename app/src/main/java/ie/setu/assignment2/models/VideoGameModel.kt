package ie.setu.assignment2.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

//https://www.baeldung.com/kotli1n/dates

@Parcelize
data class VideoGameModel(var id: Long = 0,
                          var title: String = "",
                          var description: String = "",
                          var developer: String = "",
                          var image: String = "",
                          var releaseDate: LocalDate = LocalDate.parse("1970-01-01")) : Parcelable