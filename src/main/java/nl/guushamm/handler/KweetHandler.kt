package nl.guushamm.handler

import nl.guushamm.domain.Kweet
import nl.guushamm.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/**
 * Created by guushamm on 13-3-17.
 */
@Component
@RepositoryEventHandler(Kweet::class)
open class KweetHandler {

    @Autowired
    lateinit var accountService: AccountService


    @HandleBeforeCreate
    fun handleKweetBeforeCreate(kweet: Kweet) {
//        Add an account to a kweet
        val username = SecurityContextHolder.getContext().authentication.name
        if (kweet.account == null) {
            kweet.account =  this.accountService.findByUsername(username).first()
        }

//       Filter profanities from kweets
        val profanities = mapOf(Pair("vet", "dik"), Pair("gaaf", "tof"))

        profanities.forEach { kweet.message = kweet.message.replace(String.format("(?)%s", it.key ), it.value) }
    }
}
