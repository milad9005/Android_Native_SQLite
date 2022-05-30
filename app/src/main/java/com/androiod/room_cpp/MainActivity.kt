package com.androiod.room_cpp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androiod.room_cpp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val rep :Repository=Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Thread{
            rep.insert()
            readDataBase(rep.db.openHelper.writableDatabase.path)
        }.start()

    }

    /**
     * A native method that is implemented by the 'room_cpp' native library,
     * which is packaged with this application.
     */
    external fun readDataBase(a:String): String
    companion object {
        // Used to load the 'room_cpp' library on application startup.
        init {
            System.loadLibrary("room_cpp")
        }
    }
}