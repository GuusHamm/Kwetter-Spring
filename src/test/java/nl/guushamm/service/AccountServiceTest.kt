package nl.guushamm.service

import nl.guushamm.domain.Account
import nl.guushamm.repository.AccountRepository
import nl.guushamm.utils.testAccount
import nl.guushamm.utils.testAccounts
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import java.util.*

/**
 * Created by guushamm on 8-3-17.
 */
class AccountServiceTest {
    val accountService: AccountService by lazy { AccountServiceImpl() }
    val mockedAccountRepository: AccountRepository by lazy {mock(AccountRepository::class.java)}
    val accounts by lazy { ArrayList<Account>() }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        accounts.addAll(testAccounts())
        accountService.setAccountRepository(mockedAccountRepository)
    }

    @Test
    @Throws(Exception::class)
    fun findAll() {
        `when`(mockedAccountRepository.findAll()).thenReturn(accounts)

        val retrievedAccounts = this.accountService.findAll()

        verify(mockedAccountRepository).findAll()
        assert(retrievedAccounts == accounts)
    }

    @Test
    @Throws(Exception::class)
    fun save() {
        val account: Account = testAccount()
        accountService.save(account)

        verify(mockedAccountRepository).save(account)
    }

    @Test
    @Throws(Exception::class)
    fun findOne() {
        val account: Account = testAccount()
        val id: Long = 0
        `when`(mockedAccountRepository.findOne(id)).thenReturn(account)
        val returnedAccount: Account = accountService.findOne(id)

        verify(mockedAccountRepository).findOne(id)
        assert(account == returnedAccount)
    }

    @Test
    @Throws(Exception::class)
    fun delete() {
        val id: Long = 0

        accountService.delete(id)

        verify(mockedAccountRepository).delete(id)
    }

    @Test
    @Throws(Exception::class)
    fun findByUsername() {
        val account: Account = testAccount()
        val accounts: MutableList<Account> = ArrayList()
        accounts.add(account)

        `when`(mockedAccountRepository.findByUsername(account.username)).thenReturn(accounts)
        val returnedAccounts = accountService.findByUsername(account.username)

        verify(mockedAccountRepository).findByUsername(account.username)
        assert(accounts == returnedAccounts)
    }

}