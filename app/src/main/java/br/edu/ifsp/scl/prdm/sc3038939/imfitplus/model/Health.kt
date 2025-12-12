import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Health (
    var imc: Double,
    var categoria: String,
    var pesoIdeal: Double,
    var gastoCalorico: Double,
    var recAgua: Double,
    var fcMax: Int
): Parcelable