package cubex.mahesh.telephonyex_and8am2020

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)
        if(status == PackageManager.PERMISSION_GRANTED){
            readTelephonyInfo()
        }else{
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_PHONE_NUMBERS),
                    123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
             permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED)
        {
            readTelephonyInfo()
        }

    }

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    fun readTelephonyInfo()
    {
            var tManager: TelephonyManager = getSystemService(
                    Context.TELEPHONY_SERVICE) as TelephonyManager
            var values = mutableListOf<String>()
        values.add("IMEI-1 : " + tManager.getImei(0))
        values.add("IMEI-2 : " + tManager.getImei(1))
        values.add("Sim Serial No  : " + tManager.simSerialNumber)
        values.add("Phone No  : " + tManager.line1Number)
        values.add("Operator Name  : " + tManager.networkOperatorName)
        values.add("Country ISO  : " + tManager.simCountryIso)
        values.add("SIMs Count  : " + tManager.phoneCount)


        var myadapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice,values)
        lview.adapter = myadapter

    }
}
