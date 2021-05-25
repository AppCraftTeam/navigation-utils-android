package pro.appcraft.lib.navigation

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.*
import com.github.terrakok.cicerone.androidx.AppNavigator

@Suppress("unused")
open class AppNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : AppNavigator(activity, containerId, fragmentManager) {
    private val handler = Handler(Looper.getMainLooper())

    override fun applyCommands(vararg commands: Command) {
        try {
            super.applyCommands(commands)
        } catch (error: IllegalStateException) {
            handler.postDelayed({
                applyCommands(*commands)
            }, 100)
        }
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is ForwardTo -> activityForwardTo(command)
            is Forward -> forward(command)
            is Replace -> replace(command)
            is ToTop -> fragmentToTop(command)
            is BackTo -> backTo(command)
            is Back -> back()
        }
    }

    private fun fragmentToTop(command: ToTop) {
        val screen = command.screen as FragmentScreen
        commitNewFragmentScreen(
            screen = screen,
            addToBackStack = true
        )
    }

    private fun activityForwardTo(command: ForwardTo) {
        val screen = command.screen
        if ((screen is ActivityScreen) || (!checkTopStackScreen(command))) {
            forward(Forward(screen))
        }
    }

    private fun checkTopStackScreen(command: ForwardTo): Boolean {
        val key = command.screen.screenKey
        val index = localStackCopy.indexOfFirst { it == key }
        val size = localStackCopy.size
        return index != -1 && index == size - 1
    }
}