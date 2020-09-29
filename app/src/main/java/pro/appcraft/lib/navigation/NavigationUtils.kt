package pro.appcraft.lib.navigation

import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

fun Navigator.setLaunchScreen(screen: SupportAppScreen) {
    applyCommands(arrayOf(BackTo(null), Replace(screen)))
}

fun Navigator.setLaunchScreenChain(vararg screens: SupportAppScreen) {
    val commands = arrayOfNulls<Command>(screens.size + 1)
    commands[0] = BackTo(null)
    screens.forEachIndexed { index, screen ->
        if (index == 0)
            commands[1] = (Replace(screen))
        else
            commands[index + 1] = (Forward(screen))
    }
    applyCommands(commands)
}