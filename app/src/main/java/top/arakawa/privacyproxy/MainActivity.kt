package top.arakawa.privacyproxy

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import org.c2h4.privacy.PrivacyManager
import top.arakawa.privacyproxy.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrivacyManager.setGuideMode(false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            GlideApp.with(binding.ivPic)
                .download("http://dev-img-objsave-1254327885.image.myqcloud.com/u/i/cat/1583157951273_underwaterworld_bg_day.jpg")
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .listener(object : RequestListener<File> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<File>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        e?.logRootCauses("arakawa")
                        return false
                    }

                    override fun onResourceReady(
                        resource: File?,
                        model: Any?,
                        target: Target<File>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(object : SimpleTarget<File>() {
                    override fun onResourceReady(resource: File, transition: Transition<in File>?) {
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)

                    }
                })


            Glide.with(binding.ivPic2)
                .load("https://img1.baidu.com/it/u=722430420,1974228945&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
                .into(binding.ivPic2)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}