package nl.guushamm.service

import nl.guushamm.utils.isSameKweet
import nl.guushamm.utils.testAccount
import nl.guushamm.utils.testAccounts
import nl.guushamm.utils.testKweets
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created by guushamm on 10-3-17.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class KweetServiceIntegrationTest {
    @Autowired
    lateinit var accountService: AccountService
    private val accounts by lazy { testAccounts() }

    @Autowired
    lateinit var kweetService: KweetService
    private val kweets by lazy { testKweets(accounts=accounts)}

    @Before
    fun setup() {
        accounts.forEach { accountService.save(it) }
        val account = testAccount()
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(account.username,account.password,
                AuthorityUtils.createAuthorityList(*account.roles))
    }

    @Test
    @Throws(Exception::class)
    fun assertFindAllRetrievesItems() {
        kweets.forEach { kweetService.save(it) }

        val retrievedKweets = kweetService.findAll()

        assert(retrievedKweets.isNotEmpty())
    }


    @Test
    @Throws(Exception::class)
    fun assertFindAllReturnsSameItems() {
        kweets.forEach { kweetService.save(it) }

        val retrievedKweets = kweetService.findAll()

//        Test if the all the kweets are the same
        kweets.forEach { kweet -> assert(isSameKweet(kweet = kweet, otherKweet = retrievedKweets.first { it.id == kweet.id })) }
    }

    @Test
    @Throws(Exception::class)
    fun assertSaveSavesKweetToDatabase() {
        val kweet = kweets.first()
        kweetService.save(kweet)

        val retrievedKweet = kweetService.findOne(kweet.id)

        assert(isSameKweet(kweet = kweet, otherKweet = retrievedKweet))
    }

    @Test
    @Throws(Exception::class)
    fun assertFindOneReturnsKweet() {
        val kweet = kweets.first()
        kweetService.save(kweet)

        val retrievedKweet = kweetService.findOne(kweet.id)

        assert(isSameKweet(kweet = kweet, otherKweet = retrievedKweet))
    }

    @Test
    @Throws(Exception::class)
    fun assertDeleteRemovesKweet() {
        val kweet = kweets.first()
        kweetService.save(kweet)

        kweetService.delete(kweet.id)

        val retrievedKweets = kweetService.findAll()

//        Check that our deleted kweet is no longer in the database
        assert(retrievedKweets.filter { it.id == kweet.id }.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun assertDeleteOnlyRemovesSelectedKweet() {
        kweets.forEach { kweetService.save(it) }
        val kweet = kweets.first()

        kweetService.delete(kweet.id)

        val retrievedKweets = kweetService.findAll()

//        Check that our deleted kweet is no longer in the database
        assert(retrievedKweets.filter { it.id == kweet.id }.isEmpty())

//        Check that the other kweets are still there
        assert(retrievedKweets.isNotEmpty())
    }
//

    @Test
    @Throws(Exception::class)
    fun findByAccountUsername() {
//        Choose an account and filter out all it's tweets
        val account = accounts.first()
        val kweetsByAccount = kweets.filter { it.account!!.id == account.id }

        kweets.forEach { kweetService.save(it) }

        val retrievedKweets = kweetService.findByAccountUsername(account.username)

        //        Test if the all the kweets are the same and present
        kweetsByAccount.forEach { kweet -> assert(isSameKweet(kweet = kweet, otherKweet = retrievedKweets.first { it.id == kweet.id })) }
    }
}
