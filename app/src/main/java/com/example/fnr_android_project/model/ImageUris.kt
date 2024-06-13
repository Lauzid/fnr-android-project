import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageUris(
    val small: String = "",
    val normal: String = "",
    val large: String = "",
    val png: String = "",
    val art_crop: String = "",
    val border_crop: String = ""
) : Parcelable
