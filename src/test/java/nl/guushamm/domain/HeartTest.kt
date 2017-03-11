package nl.guushamm.domain

import nl.guushamm.utils.testAccounts
import nl.guushamm.utils.testHearts
import nl.guushamm.utils.testKweets
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Created by guushamm on 8-3-17.
 */
class HeartTest {
    val kweets by lazy { ArrayList<Kweet>() }
    val accounts by lazy { ArrayList<Account>() }
    val hearts by lazy { ArrayList<Heart>() }
    val random by lazy { Random() }

    @Before
    fun setUp() {
        accounts.addAll(testAccounts())
        kweets.addAll(testKweets())
        hearts.addAll(testHearts())
    }

    @Test
    fun heartShouldHaveKweet() {
        val heart = hearts[random.nextInt(hearts.size)]

        assert(heart.kweet != null)
    }

    @Test
    fun heartShouldHaveAccount() {
        val heart = hearts[random.nextInt(hearts.size)]

        assert(heart.account != null)
    }

}