package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicviews.BasicViewsActivity
import com.example.bottomnavigationviews.BottomNavigationActivity
import com.example.empty.EmptyActivity
import com.example.emptyviews.EmptyViewsActivity
import com.example.fragmentviewmodel.FragmentViewModelActivity
import com.example.fullscreenviews.FullscreenActivity
import com.example.gogleadmobadsviews.GoogleAdMobAdsActivity
import com.example.googlemapsviews.GoogleMapsActivity
import com.example.googlepayviews.GooglePayViewsActivity
import com.example.googlewallet.GoogleWalletActivity
import com.example.loginviews.ui.login.LoginActivity
import com.example.navigationdrawerviews.NavigationDrawerActivity
import com.example.primarydetailsviews.ItemDetailHostActivity
import com.example.responsiveviews.ResponsiveViewsActivity
import com.example.scrollingviews.ScrollingActivity
import com.example.settingsviews.SettingsActivity
import com.example.settingsviews2.Settings2Activity
import com.example.tabbedviews.TabbedActivity
import java.util.UUID
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        addUiModule()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myLogic()

    }


    private fun addUiModule() {
        val itemDetailsCheckBox = findViewById<CheckBox>(R.id.show_item_detail)
        val descriptionCheckBox = findViewById<CheckBox>(R.id.show_description)
        val modulesRecyclerView = findViewById<RecyclerView>(R.id.modules_recycler_view)
        modulesRecyclerView.layoutManager = LinearLayoutManager(this)
        val modulesAdapter =
            ModulesAdapter(itemDetailsCheckBox.isChecked, descriptionCheckBox.isChecked)
        modulesRecyclerView.adapter = modulesAdapter
        itemDetailsCheckBox.setOnCheckedChangeListener { _, isChecked: Boolean ->
            modulesAdapter.showItemDetails = isChecked
            modulesAdapter.notifyDataSetChanged()
        }
        descriptionCheckBox.setOnCheckedChangeListener { _, isChecked: Boolean ->
            modulesAdapter.showItemDescription = isChecked
            modulesAdapter.notifyDataSetChanged()
        }
    }

    data class UiModuleInfo(val name: String, val kotlinClass: KClass<*>)

    class ModulesAdapter(var showItemDetails: Boolean, var showItemDescription: Boolean) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val modules = listOf(
            UiModuleInfo(":basicviews", BasicViewsActivity::class),
            UiModuleInfo(":bottomnavigationviews", BottomNavigationActivity::class),
            UiModuleInfo(":empty", EmptyActivity::class),
            UiModuleInfo(":emptyviews", EmptyViewsActivity::class),
            UiModuleInfo(":fragmentviewmodel", FragmentViewModelActivity::class),
            UiModuleInfo(":fullscreenviews", FullscreenActivity::class),
            UiModuleInfo(":gogleadmobadsviews", GoogleAdMobAdsActivity::class),
            UiModuleInfo(":googlemapsviews", GoogleMapsActivity::class),
            UiModuleInfo(":googlepayviews", GooglePayViewsActivity::class),
            UiModuleInfo(":googlewallet", GoogleWalletActivity::class),
            UiModuleInfo(":loginviews", LoginActivity::class),
            UiModuleInfo(":navigationdrawerviews", NavigationDrawerActivity::class),
            UiModuleInfo(":primarydetailsviews", ItemDetailHostActivity::class),
            UiModuleInfo(":responsiveviews", ResponsiveViewsActivity::class),
            UiModuleInfo(":scrollingviews", ScrollingActivity::class),
            UiModuleInfo(":settingsviews", SettingsActivity::class),
            UiModuleInfo(":settingsviews2", Settings2Activity::class),
            UiModuleInfo(":tabbedviews", TabbedActivity::class),
        )

        private var createViewHolderCount = 0
        private val tags = HashMap<String, Int>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            createViewHolderCount++
            val x = object : RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_module, parent, false)
            ) {}
            tags["${x.hashCode()}"] = createViewHolderCount
            return x
        }

        override fun getItemCount(): Int {
            return modules.size
        }

        var bindViewHolderCount = 0

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            bindViewHolderCount++
            holder.itemView.findViewById<TextView>(R.id.item_title).let {
                it.text = modules[position].name
            }
            holder.itemView.findViewById<TextView>(R.id.item_description).let {
                it.text = modules[position].kotlinClass.qualifiedName
                it.visibility = if (showItemDescription) View.VISIBLE else View.GONE
            }
            holder.itemView.findViewById<TextView>(R.id.item_view_info).let {
                it.text =
                    "[position = $position], [bindViewHolderCount = $bindViewHolderCount], [tag = ${tags["${holder.hashCode()}"]}], [$holder], [createViewHolderCount = $createViewHolderCount]."
                it.visibility = if (showItemDetails) View.VISIBLE else View.GONE
            }

            holder.itemView.setOnClickListener {
                try {
                    it.context.startActivity(
                        Intent(it.context, modules[position].kotlinClass.java)
                    )
                    it.setBackgroundColor(Color.LTGRAY)

                } catch (e: Exception) {
                    AlertDialog
                        .Builder(holder.itemView.context)
                        .setTitle(e::class.qualifiedName)
                        .setMessage(e.message)
                        .setCancelable(false)
                        .setPositiveButton("OK") { _, _ -> }
                        .create()
                        .show()
                }
            }
        }

    }

    private fun myLogic() {
        val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        android.util.Log.d("TAG", "ANDROID_ID = $androidId")
        android.util.Log.d("TAG", "ANDROID_UUID = ${UUID.randomUUID()}")
    }
}