package com.akliars.widgetskullanicietkilesimi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import com.akliars.widgetskullanicietkilesimi.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var kontrol = false
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonResim1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.resim1)
        }

        binding.buttonResim2.setOnClickListener {
          //  binding.imageView.setImageResource(R.drawable.resim2)
            binding.imageView.setImageResource(resources.getIdentifier("resim2","drawable",packageName))
        }

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                Log.e("Sonuç","ON")
            }else{
                Log.e("Sonuç","Off")
            }
        }

        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }

        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, ilerleme: Int, fromUser: Boolean) {
                binding.textViewSonuc.text = ilerleme.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        val ulkeler = ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İtalya")
        ulkeler.add("Japonya")

        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,ulkeler)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
           kontrol = isChecked
            id=checkedId
            if (isChecked){
                val secilenButton = findViewById<Button>(checkedId)
                val buttonYazi = secilenButton.text.toString()
                Log.e("Sonuç","Kategori : $buttonYazi")
            }
        }

        binding.buttonSaat.setOnClickListener {
            val tp = MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            tp.show(supportFragmentManager,"")

            tp.addOnPositiveButtonClickListener {
                binding.editTextSaat.setText("${tp.hour} :  ${tp.minute}")
            }
        }

        binding.buttonTarih.setOnClickListener {
            val dp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")
                .build()

            dp.show(supportFragmentManager,"")

            dp.addOnPositiveButtonClickListener {
                val df = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.getDefault())
                val tarih = df.format(it)//Tarih bilgisi ms türünde
                binding.editTextTarih.setText(tarih)
            }
        }

        binding.buttonToast.setOnClickListener {
            Toast.makeText(this,"Merhaba",Toast.LENGTH_SHORT)

                .show()
        }

        binding.buttonSnackbar.setOnClickListener {
            Snackbar.make(it,"Silmek ister misiniz?",Snackbar.LENGTH_SHORT)
                .setAction("EVET"){
                    Snackbar.make(it,"Silindi",Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.RED)
                        .show()
                }
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLUE)
                .setActionTextColor(Color.RED)
                .show()
        }

        binding.buttonAlert.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Başlık")
                .setMessage("İçerik")
                .setPositiveButton("Tamam"){d,i ->
                  Toast.makeText(this,"Tamam Seçildi",Toast.LENGTH_SHORT).show()
                }
                    .setNegativeButton("İptal"){d,i ->
                        Toast.makeText(this,"İptal Seçildi",Toast.LENGTH_SHORT).show()

                    }
                .show()
        }

        binding.buttonGoster.setOnClickListener {
            Log.e("Sonuç","Switch Durum: ${binding.switch1.isChecked}")
            Log.e("Sonuç","Switch Durum: ${binding.slider.progress}")
            Log.e("Sonuç","Ülke Durum: ${binding.autoCompleteTextView.text}")

           if(kontrol){
               val secilenButton = findViewById<Button>(id)
               val buttonYazi = secilenButton.text.toString()
                 Log.e("Sonuç","Kategori : ${buttonYazi}")
            }else{

            }

        }




    }
}