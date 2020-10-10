package pro.appcraft.lib.navigation

import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Command

/**
 * Opens new screen if screen not exists on stack top
 */
class ForwardTo(val screen: Screen?) : Command

/*
 * Opens new flow on top of current with transparent background
 */
class ToTop(val screen: Screen) : Command