package nl.guushamm.controller

import nl.guushamm.domain.Kweet
import nl.guushamm.service.AccountService
import nl.guushamm.service.KweetService
import nl.guushamm.service.TrendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

/**
 * Created by guushamm on 20-3-17.
 */
@Controller
class StartPageController {

    @Autowired
    lateinit var kweetService: KweetService

    @Autowired
    lateinit var accountService: AccountService

    @Autowired
    lateinit var trendService: TrendService

    @GetMapping(value = "thymeleaf/")
    fun startpage(): ModelAndView {
        val modelAndView = ModelAndView("startpage")

        val kweets: List<Kweet>

//        if (trend !== null && trend.isNotBlank()) {
//            kweets = kweetService.findAllByIdDesc()
//        } else {
//            kweets = kweetService.findByTrendName(trend)
//        }
//
//        modelAndView.addObject("kweets", kweets)

        modelAndView.addObject("kweet", Kweet())

        var trends = trendService.findByKweetCount()

        if (trends.size >= 5) trends = trends.subList(0, 5)

        modelAndView.addObject("trends", trends)

        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null) {
            val username = authentication.name
            val account = this.accountService.findByUsername(username).stream().findFirst().get()
            modelAndView.addObject("account", account)

            val kweets = this.kweetService.findByAccountUsername(username)
            modelAndView.addObject("accountKweets", kweets)

            val latestKweet = kweets.last()
            modelAndView.addObject("latestKweet", latestKweet)
        }

        return modelAndView
    }

    @GetMapping(value = "thymeleaf/kweets")
    fun getKweets(): ModelAndView {
        val modelAndView: ModelAndView = ModelAndView("kweet :: kweetList")
        modelAndView.addObject("kweets", kweetService.findAllByIdDesc())

        return modelAndView
    }

    @GetMapping(value = "thymeleaf/kweets/message/{message}")
    fun getKweets(@PathVariable("message") message: String): ModelAndView {
        print(message)
        val modelAndView: ModelAndView = ModelAndView("kweet :: kweetList")

        val kweets = kweetService.findByMessageContaining(message)
        modelAndView.addObject("kweets", kweets)
        print(kweets)

        return modelAndView
    }

    @GetMapping(value = "thymeleaf/trend/{trend}")
    fun getKweetsByTrend(@PathVariable("trend") message: String): ModelAndView {
        print(message)
        val modelAndView: ModelAndView = ModelAndView("kweet :: kweetList")

        val kweets = kweetService.findByTrendName(message)
        modelAndView.addObject("kweets", kweets)
        print(kweets)

        return modelAndView
    }

    @PostMapping(value = "thymeleaf/")
    fun postKweet(@ModelAttribute kweet: Kweet, attributes: RedirectAttributes): RedirectView {
        val newKweet = kweetService.save(kweet)
        return RedirectView("")
    }
}
