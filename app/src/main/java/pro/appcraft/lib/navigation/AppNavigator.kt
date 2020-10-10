package pro.appcraft.lib.navigation

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.*

@Suppress("UNUSED_PARAMETER")
open class AppNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {
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
            is Forward -> activityForward(command)
            is Replace -> activityReplace(command)
            is ToTop -> fragmentToTop(command)
            is BackTo -> backTo(command)
            is Back -> fragmentBack()
        }
    }

    private fun fragmentToTop(command: ToTop) {
        val screen = command.screen as AppScreen
        val fragment = createFragment(screen)

        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransaction(
            command,
            fragmentManager.findFragmentById(containerId),
            fragment,
            fragmentTransaction
        )

        fragmentTransaction
            .add(containerId, fragment!!)
            .addToBackStack(screen.screenKey)
            .commit()
        localStackCopy?.add(screen.screenKey)
    }

    private fun activityForwardTo(command: ForwardTo) {
        val screen = command.screen as SupportAppScreen
        val activityIntent = screen.getActivityIntent(activity)

        if (activityIntent != null || !checkTopStackScreen(command)) {
            activityForward(Forward(screen))
        }
    }

    private fun checkTopStackScreen(command: ForwardTo): Boolean {
        val key = command.screen?.screenKey
        val index = localStackCopy?.indexOf(key) ?: -1
        val size = localStackCopy?.size ?: 0
        return index != -1 && index == size - 1
    }
}
