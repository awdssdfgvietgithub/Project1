package com.example.project_1_home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.project_1_home.`interface`.OnPassData
import com.example.project_1_home.fragments.InsertTaskFragment
import com.example.project_1_home.fragments.StatisticsFragment
import com.example.project_1_home.fragments.TaskListFragment
import com.example.project_1_home.model.TaskModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), OnPassData {
    private lateinit var drawerLayout: DrawerLayout
    lateinit var myToolbar: androidx.appcompat.widget.Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var navController: NavController
    private lateinit var statisticsFragment: StatisticsFragment
    private lateinit var taskListFragment: TaskListFragment
    private lateinit var insertTaskFragment: InsertTaskFragment
    var dataSet = mutableListOf<TaskModel>()
    private var numCompleted: Float? = 0f
    private var numActive: Float? = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment_content_main)
        myToolbar = findViewById(R.id.toolBar_Main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        statisticsFragment = StatisticsFragment()
        taskListFragment = TaskListFragment()
        insertTaskFragment = InsertTaskFragment()
        customToolBar(myToolbar)
        navigationView.setNavigationItemSelectedListener {
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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter_all -> {
            navController.navigate(R.id.taskListFragment, Bundle().apply {
                putString("filter", "filter_all")
            })
            true
        }
        R.id.filter_active -> {
            navController.navigate(R.id.taskListFragment, Bundle().apply {
                putString("filter", "filter_active")
            })
            true
        }
        R.id.filter_completed -> {
            navController.navigate(R.id.taskListFragment, Bundle().apply {
                putString("filter", "filter_completed")
            })
            true
        }
        R.id.more_clear -> {
            navController.navigate(R.id.taskListFragment, Bundle().apply {
                putString("more", "more_clear")
            })
            true
        }
        R.id.more_refresh -> {
            navController.navigate(R.id.taskListFragment, Bundle().apply {
                putString("more", "more_refresh")
            })
            true
        }
        else -> {
            false
        }
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

    fun data(): MutableList<TaskModel> {
        if (dataSet.isEmpty()) {
            dataSet.apply {
                add(
                    TaskModel(
                        "Learning Kotlin",
                        "I'm learning Kotlin, do u want to learn Kotlin too?",
                        true
                    )
                )
                add(
                    TaskModel(
                        "Learning Java",
                        "I'm learning Java, do u want to learn Java too?",
                        true
                    )
                )
                add(
                    TaskModel(
                        "Learning Android",
                        "I'm learning Android, do u want to learn Android too?",
                        false
                    )
                )
            }
            return dataSet
        }
        return dataSet
    }

    fun insert(title: String, body: String = "") {
        Toast.makeText(this, "$title | $body", Toast.LENGTH_SHORT).show()
        dataSet.add(TaskModel(title, body, false))
    }

    fun update(i: Int, title: String, body: String, checkBox: Boolean) {
        dataSet[i] = TaskModel(title, body, checkBox)
    }

    override fun onPassData(title: String, body: String) {
        navController.navigate(R.id.taskListFragment, Bundle().apply {
            putString("title_key", title)
            putString("body_key", body)
        })
    }

    override fun onPassDataToUpdate(i: Int, title: String, body: String, checkBox: Boolean) {
        navController.navigate(R.id.taskListFragment, Bundle().apply {
            putInt("update_i_key", i)
            putString("update_title_key", title)
            putString("update_body_key", body)
            putBoolean("update_checkbox_key", checkBox)
        })
    }

    override fun onPassNumberTasks(numCompleted: Float, numActive: Float) {
        this.numCompleted = numCompleted
        this.numActive = numActive
    }

    fun getNumCompleted() = numCompleted
    fun getNumActive() = numActive
}