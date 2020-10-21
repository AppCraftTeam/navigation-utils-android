package pro.appcraft.lib.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppScreen

class AppRouter : Router() {
    fun forwardTo(flow: AppScreen) = executeCommands(ForwardTo(flow))

    fun toTop(flow: AppScreen) = executeCommands(ToTop(flow))
}
