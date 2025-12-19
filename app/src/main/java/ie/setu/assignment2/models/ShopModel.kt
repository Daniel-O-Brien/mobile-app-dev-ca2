package ie.setu.assignment2.models

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopModel(var name: String = "",
                     var location: LatLng = LatLng(0.0, 0.0)) : Parcelable