package nl.guushamm.handler

import nl.guushamm.domain.Kweet
import nl.guushamm.domain.Trend
import nl.guushamm.service.AccountService
import nl.guushamm.service.TrendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.time.Instant

/**
 * Created by guushamm on 13-3-17.
 */
@Component
@RepositoryEventHandler(Kweet::class)
open class KweetHandler {

    @Autowired
    lateinit var accountService: AccountService

    @Autowired
    lateinit var trendService: TrendService

    @HandleBeforeCreate
    fun handleKweetBeforeCreate(kweet: Kweet) {
        val securityContext: SecurityContext? = SecurityContextHolder.getContext()
        if (securityContext != null) {
            //        Add an account to a kweet
            val username = securityContext.authentication.name
            if (kweet.account == null) {
                kweet.account = this.accountService.findByUsername(username).first()
            }
        }


//       Filter profanities from kweet
        val profanities = mapOf(Pair("vet", "dik"), Pair("gaaf", "tof"))

        profanities.forEach { kweet.message = kweet.message.replace(String.format("(?)%s", it.key), it.value) }

//        Add trends to kweet
        val matches = Regex("(#\\w+)").findAll(kweet.message).iterator()

        while (matches.hasNext()) {
            val match = matches.next().groups[1]?.value?.substring(1)
            val trend: Trend = trendService.findOrCreateByName(match)
            kweet.trends += trend
        }

//        Set kweet timestamp
        if (kweet.timestamp == 0L) {
            kweet.timestamp = Instant.now().epochSecond

        }
    }
}
