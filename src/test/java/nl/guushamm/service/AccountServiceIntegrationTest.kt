package nl.guushamm.service

import nl.guushamm.domain.Account
import nl.guushamm.utils.isSameAccount
import nl.guushamm.utils.testAccounts
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

/**
 * Created by guushamm on 10-3-17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AccountServiceIntegrationTest {
    @Autowired
    lateinit var accountService: AccountService
    private val accounts: ArrayList<Account> by lazy { testAccounts() }

    @Test
    @Throws(Exception::class)
    fun assertFindAllRetrievesItems() {
        accounts.forEach { accountService.save(it) }

        val retrievedAccounts = accountService.findAll()

        assert(retrievedAccounts.isNotEmpty())
    }


    @Test
    @Throws(Exception::class)
    fun assertFindAllReturnsSameItems() {
        accounts.forEach { accountService.save(it) }

        val retrievedAccounts = accountService.findAll()

//        Test if the all the accounts are the same
        accounts.forEach { account -> assert(isSameAccount(account = account, otherAccount = retrievedAccounts.first { it.id == account.id })) }
    }

    @Test
    @Throws(Exception::class)
    fun assertSaveSavesAccountToDatabase() {
        val account = accounts.first()
        accountService.save(account)

        val retrievedAccount = accountService.findOne(account.id)

        assert(isSameAccount(account = account, otherAccount = retrievedAccount))
    }

    @Test
    @Throws(Exception::class)
    fun assertFindOneReturnsAccount() {
        val account = accounts.first()
        accountService.save(account)

        val retrievedAccount = accountService.findOne(account.id)

        assert(isSameAccount(account = account, otherAccount = retrievedAccount))
    }

    @Test
    @Throws(Exception::class)
    fun assertDeleteRemovesAccount() {
        val account = accounts.first()
        accountService.save(account)

        accountService.delete(account.id)

        val retrievedAccounts = accountService.findAll()

//        Check that our deleted account is no longer in the database
        assert(retrievedAccounts.filter { it.id == account.id }.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun assertDeleteOnlyRemovesSelectedAccount() {
        accounts.forEach { accountService.save(it) }

        val account = accounts.first()

        accountService.delete(account.id)

        val retrievedAccounts = accountService.findAll()

//        Check that our deleted account is no longer in the database
        assert(retrievedAccounts.filter { it.id == account.id }.isEmpty())

//        Check that the other accounts are still there
        assert(retrievedAccounts.isNotEmpty())
    }
//

    @Test
    @Throws(Exception::class)
    fun findByUsername() {
        val account = accounts.first()
        accountService.save(account)

        val retrievedAccount = accountService.findByUsername(account.username)

        assert(isSameAccount(account = account, otherAccount = retrievedAccount.first()))
    }
}
