package nl.guushamm.controller

import nl.guushamm.service.AccountService
import nl.guushamm.service.KweetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

/**
 * Created by guushamm on 22-3-17.
 */
@Controller
class AccountController {
    @Autowired
    lateinit var kweetService: KweetService

    @Autowired
    lateinit var accountService: AccountService

    @RequestMapping("thymeleaf/account")
    fun accountPage(@RequestParam(value = "username") username: String): ModelAndView {
        val modelAndView = ModelAndView("account")

        val account = accountService.findByUsername(username).first()
        modelAndView.addObject("account", account)

        val kweets = kweetService.findByAccountUsername(username)
        modelAndView.addObject("kweets", kweets)

        return modelAndView
    }
}