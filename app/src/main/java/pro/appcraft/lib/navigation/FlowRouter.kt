package pro.appcraft.lib.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppScreen

class FlowRouter(private val appRouter: AppRouter) : Router() {
    fun startFlow(screen: AppScreen) = appRouter.navigateTo(screen)

    fun finishFlow() = appRouter.exit()

    fun newRootFlow(flow: AppScreen) = appRouter.newRootScreen(flow)

    fun replaceFlow(flow: AppScreen) = appRouter.replaceScreen(flow)

    fun forwardTo(flow: AppScreen) = appRouter.forwardTo(flow)

    fun toTop(flow: AppScreen) = appRouter.toTop(flow)
}
