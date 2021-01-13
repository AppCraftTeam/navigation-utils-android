package pro.appcraft.lib.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

fun Navigator.setLaunchScreen(screen: AppScreen) {
    applyCommands(arrayOf(BackTo(null), Replace(screen)))
}

fun Navigator.setLaunchScreenChain(vararg screens: AppScreen) {
    val commands = mutableListOf<Command>()
    commands.add(BackTo(null))
    screens.forEachIndexed { index, screen ->
        commands.add(
            if (index == 0) Replace(screen) else Forward(screen, true)
        )
    }
    applyCommands(commands.toTypedArray())
}

fun KClass<out Fragment>.getFragmentScreen(vararg args: Pair<String, Any?>) =
    FragmentScreen(
        key = java.name,
        fragmentCreator = {
            this.createInstance().apply {
                arguments = bundleOf(*args)
            }
        }
    )