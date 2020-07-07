package pro.appcraft.lib.navigation

import ru.terrakok.cicerone.Router

class FlowRouter(private val appRouter: AppRouter) : Router() {
    fun startFlow(flows: AppScreen) {
        appRouter.navigateTo(flows)
    }

    fun replaceFlow(flow: AppScreen) {
        appRouter.replaceScreen(flow)
    }

    fun newRootFlow(flow: AppScreen) {
        appRouter.newRootScreen(flow)
    }

    fun newRootFlowChain(vararg flows: AppScreen) {
        appRouter.newRootChain(*flows)
    }

    fun finishFlow() {
        appRouter.exit()
    }

    fun backTo(flow: AppScreen) {
        appRouter.backTo(flow)
    }

    fun forwardTo(flow: AppScreen) {
        appRouter.forwardTo(flow)
    }

    fun toTop(flow: AppScreen) {
        appRouter.toTop(flow)
    }
}
