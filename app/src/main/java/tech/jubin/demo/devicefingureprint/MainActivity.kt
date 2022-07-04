package tech.jubin.demo.devicefingureprint

import android.media.MediaDrm
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var tvOutput: TextView

    private val wideVineUuid = UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L)
    private val wvDrm = MediaDrm(wideVineUuid)
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOutput = findViewById(R.id.tv_output)
        findViewById<Button>(R.id.btn_demo).setOnClickListener {
            showId()
        }
    }

    private fun showId() {
        val wideVineIdArray: ByteArray =
            wvDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
        val wideVineId = Base64.encodeToString(wideVineIdArray, Base64.NO_WRAP)
        if (count == 6) {
            tvOutput.text = wideVineId.toString()
            count = 0
        } else {
            tvOutput.text = "${tvOutput.text}\n$wideVineId"
            count++
        }
    }
}