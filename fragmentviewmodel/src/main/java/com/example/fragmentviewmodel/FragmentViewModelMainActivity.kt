package com.example.fragmentviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentviewmodel.ui.main.MainFragment

class FragmentViewModelMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_viewmodel_fragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}