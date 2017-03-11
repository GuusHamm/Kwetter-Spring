package nl.guushamm.domain

import nl.guushamm.utils.testAccounts
import nl.guushamm.utils.testKweets
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by guushamm on 8-3-17.
 */
class KweetTest {
    val kweets by lazy { ArrayList<Kweet>() }
    val accounts by lazy { ArrayList<Account>() }
    val random by lazy { Random() }
    @Before
    fun setUp() {
        accounts.addAll(testAccounts())
        kweets.addAll(testKweets())
    }

    @Test
    fun kweetShouldHaveAnAccount() {
        val randomKweet = kweets[random.nextInt(kweets.size)]

        assert(randomKweet.account != null)
    }

    @Test
    fun kweetShouldHaveADateTime() {
        val randomKweet = kweets[random.nextInt(kweets.size)]

        assert(randomKweet.timestamp != null)
    }

}