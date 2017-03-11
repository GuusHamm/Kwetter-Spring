package nl.guushamm.domain

import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import nl.guushamm.utils.testAccounts
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*


/**
 * Created by guushamm on 22-2-17.
 */
class AccountTest {
    val accounts by lazy { ArrayList<Account>() }
    val random by lazy { Random() }

    @BeforeEach
    fun setUp() {
       accounts.addAll(testAccounts())
    }

    @Test
    fun accountShouldBeAbleToFollowOtherUser() {
        val account = accounts[random.nextInt(accounts.size)]
        for (i in 0..6) {
            val randomAccount = accounts[random.nextInt(accounts.size)]
            if (!account.following.contains(randomAccount)){
                account.following += randomAccount
            }
        }
        assertTrue(account.following.isNotEmpty())
    }

    @Test
    fun accountShouldHaveUsername() {
        val randomAccount = this.accounts[random.nextInt(this.accounts.size)]

        assertNotNull(randomAccount.username)
    }
}