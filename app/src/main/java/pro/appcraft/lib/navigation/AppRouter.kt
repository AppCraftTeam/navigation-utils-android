package pro.appcraft.lib.navigation

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.commands.BackTo

class AppRouter : Router() {
    fun startFlow(flow: AppScreen) {
        navigateTo(flow)
    }

    fun newRootFlow(flow: AppScreen) {
        newRootScreen(flow)
    }

    fun forwardTo(flow: AppScreen) {
        executeCommands(ForwardTo(flow))
    }

    fun backTo(flow: AppScreen) {
        executeCommands(BackTo(flow))
    }

    fun toTop(flows: AppScreen) {
        executeCommands(ToTop(flows))
    }
}
