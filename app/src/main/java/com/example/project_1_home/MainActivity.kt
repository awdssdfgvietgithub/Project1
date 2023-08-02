package com.example.project_1_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.FrameLayout
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.project_1_home.fragments.StatisticsFragment
import com.example.project_1_home.fragments.TaskListFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    lateinit var myToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var statisticsFragment: StatisticsFragment
    private lateinit var taskListFragment: TaskListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        myToolbar = findViewById(R.id.toolBar_Main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        statisticsFragment = StatisticsFragment()
        taskListFragment = TaskListFragment()
        customToolBar(myToolbar)

        navigationView.setNavigationItemSelectedListener() {
            when (it.itemId) {
                R.id.nav_tasklist -> {
                    navController.navigate(R.id.taskListFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_statistic -> {
                    navController.navigate(R.id.statisticsFragment)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_tasklist, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun customToolBar(toolbar: androidx.appcompat.widget.Toolbar) {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_open,
            R.string.navigation_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}