package nl.guushamm.utils

import nl.guushamm.domain.Account
import nl.guushamm.domain.Heart
import nl.guushamm.domain.Kweet
import java.util.*

/**
 * Created by guushamm on 8-3-17.
 */
val random by lazy { Random() }

fun testAccounts(): ArrayList<Account> {
    val accounts: ArrayList<Account> = ArrayList()

    accounts.add(Account(username = "JeanGibson", password = "1234", firstname = "Jean", lastname = "Gibson"))
    accounts.add(Account(username = "mpayne5", password = "wkCO5bydw", firstname = "Marilyn", lastname = "Payne", photo = "base64photo", bio = "Hello", location = "Eindhoven", website = "www.payne.com", moderator = false))
    accounts.add(Account(username = "rsmith3", password = "zxcFmX", firstname = "Roger", lastname = "Smith"))
    accounts.add(Account(username = "jbailey6", password = "HRuHsfzp", firstname = "Joyce", lastname = "Bailey"))
    accounts.add(Account(username = "rwarren4", password = "hzdIgx8", firstname = "Roy", lastname = "Warren"))
    accounts.add(Account(username = "bhunt7", password = "ROSEYIUF2YDs", firstname = "Brenda", lastname = "Hunt"))
    accounts.add(Account(username = "jholmes8", password = "BRu7pORtdZ", firstname = "Johnny", lastname = "Holmes"))
    accounts.add(Account(username = "smyers9", password = "ftWGdzmyf", firstname = "Steve", lastname = "Myers"))
    accounts.add(Account(username = "rjonesa", password = "jj22fRr", firstname = "Russell", lastname = "Jones"))
    accounts.add(Account(username = "mrogersb", password = "yRgVJxUK", firstname = "Michael", lastname = "Rogers"))

    return accounts
}

fun testAccount(): Account = Account(username = "TestMcTest", password = "123456789", firstname = "Test", lastname = "McTest")

fun testKweets(amount: Int = 50, accounts: List<Account> = testAccounts()): ArrayList<Kweet> {
    val kweets: ArrayList<Kweet> = ArrayList()

    for (i in 1..amount) kweets.add(Kweet(message = "Hello World", account = accounts[random.nextInt(accounts.size)]))

    return kweets
}

fun testKweet(): Kweet = Kweet(message = "Testing", account = testAccount())

fun testHearts(amount: Int = 200, accounts: List<Account> = testAccounts()): ArrayList<Heart> {
    val hearts: ArrayList<Heart> = ArrayList()
    val kweets: ArrayList<Kweet> = testKweets()

    for (i in 1..amount) hearts.add(Heart(account = accounts[random.nextInt(accounts.size)], kweet = kweets[random.nextInt(kweets.size)]))

    return hearts
}

fun isSameAccount(account: Account, otherAccount: Account): Boolean {
    return account.id == otherAccount.id &&
            account.username == otherAccount.username &&
            account.firstname == otherAccount.firstname &&
            account.lastname == otherAccount.lastname &&
            account.password == otherAccount.password &&
            account.isModerator == otherAccount.isModerator &&
            account.bio == otherAccount.bio &&
            account.location == otherAccount.location &&
            account.photo == otherAccount.photo &&
            account.website == otherAccount.website
}

fun isSameKweet(kweet: Kweet, otherKweet: Kweet): Boolean {
    return kweet.id == otherKweet.id &&
            kweet.message == otherKweet.message &&
            kweet.timestamp == otherKweet.timestamp &&
            isSameAccount(account = kweet.account!! ,otherAccount = otherKweet.account!!)
}
