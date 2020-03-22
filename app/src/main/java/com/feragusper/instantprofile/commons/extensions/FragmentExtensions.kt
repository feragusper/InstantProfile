import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.feragusper.instantprofile.R

fun Fragment.goToSendMessage() {
    val intent = Intent(
        Intent.ACTION_SENDTO,
        Uri.parse(resources.getString(R.string.mail_to))
    )
    startActivity(intent)
}

fun Fragment.goToScheduleACall() {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://calendly.com/feragusper")
    )
    startActivity(intent)
}
