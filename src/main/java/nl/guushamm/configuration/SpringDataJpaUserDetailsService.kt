package nl.guushamm.configuration

import nl.guushamm.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

/**
 * Created by guushamm on 13-3-17.
 */
@Component
class SpringDataJpaUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var accountService: AccountService

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(name: String): UserDetails {
        val account = this.accountService.findByUsername(name).first()

        return User(account.username, account.password, AuthorityUtils.createAuthorityList(*account.roles))
    }
}
