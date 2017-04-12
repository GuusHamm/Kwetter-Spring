package nl.guushamm.utils

import com.github.javafaker.Faker
import nl.guushamm.domain.Account
import nl.guushamm.domain.Heart
import nl.guushamm.domain.Kweet
import nl.guushamm.domain.Trend
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.Instant
import java.util.*

/**
 * Created by guushamm on 8-3-17.
 */
val random by lazy { Random() }
val faker by lazy { Faker() }
val bCrypt by lazy { BCryptPasswordEncoder() }

fun testAccounts(amount: Int = 20): ArrayList<Account> {
    val accounts: ArrayList<Account> = ArrayList()


    accounts.add(
            Account(username = "JeanGibson",
                    password = bCrypt.encode("1234"),
                    firstname = "Jean",
                    lastname = "Gibson"))

    for (i in 1..amount - 1) {
        val firstname = faker.name().firstName()
        val lastname = faker.name().lastName()



        accounts.add(
                Account(username = "${firstname.capitalize()}${lastname.capitalize()}",
                        password = bCrypt.encode(faker.internet().password()),
                        firstname = firstname,
                        lastname = lastname))
    }

//    Create an admin
    val adminAccount = accounts[random.nextInt(accounts.size)]
    adminAccount.photo = ""
    adminAccount.bio = faker.internet().slug()
    adminAccount.location = faker.address().fullAddress()
    adminAccount.website = faker.internet().url()
    adminAccount.roles += "moderator"

    for (i in 1..amount) {
//        Add followers
        val account = accounts[random.nextInt(accounts.size)]

        for (j in 1..10) {
            val randomAccount = accounts[random.nextInt(accounts.size)]
            if (randomAccount.username !== account.username) {
                account.following += randomAccount
            }
        }

//        Set account to active
        account.active = true
    }

    return accounts
}

fun testAccount(): Account {
    val accounts = testAccounts()
    val account = accounts[random.nextInt(accounts.size)]
    account.roles = arrayOf("moderator")
    return account
}

fun testKweets(amount: Int = 50, accounts: List<Account> = testAccounts()): ArrayList<Kweet> {
    val kweets: ArrayList<Kweet> = ArrayList()

    for (i in 1..amount) kweets.add(Kweet(message = faker.lorem().fixedString(random.nextInt(139)), account = accounts[random.nextInt(accounts.size)]))

    return kweets
}

fun testKweet(): Kweet = Kweet(message = "Testing", account = testAccount())

fun testHearts(amount: Int = 200, accounts: List<Account> = testAccounts(), kweets: List<Kweet> = testKweets()): ArrayList<Heart> {
    val hearts: ArrayList<Heart> = ArrayList()

    for (i in 1..amount) hearts.add(Heart(account = accounts[random.nextInt(accounts.size)], kweet = kweets[random.nextInt(kweets.size)], timestamp = Instant.now().epochSecond))

    return hearts
}


fun testTrends(amount: Int = 10): ArrayList<Trend> {
    val trends: ArrayList<Trend> = ArrayList()

    for (i in 1..amount) trends.add(Trend(name = faker.commerce().productName()))

    return trends
}

fun testKweetTrends(kweets: ArrayList<Kweet> = testKweets(), trends: List<Trend> = testTrends(), maxPopularity: Int = 30): ArrayList<Kweet> {

    trends.forEach({
        for (i in 1..random.nextInt(maxPopularity)) kweets[random.nextInt(kweets.size)].trends += it
    })

    return kweets
}

fun isSameAccount(account: Account, otherAccount: Account): Boolean {
    return account.id == otherAccount.id &&
            account.username == otherAccount.username &&
            account.firstname == otherAccount.firstname &&
            account.lastname == otherAccount.lastname &&
            account.password == otherAccount.password &&
            Arrays.equals(account.roles, otherAccount.roles) &&
            account.bio == otherAccount.bio &&
            account.location == otherAccount.location &&
            account.photo == otherAccount.photo &&
            account.website == otherAccount.website
}

fun isSameKweet(kweet: Kweet, otherKweet: Kweet): Boolean {
    return kweet.id == otherKweet.id &&
            kweet.message == otherKweet.message &&
            kweet.timestamp == otherKweet.timestamp &&
            isSameAccount(account = kweet.account!!, otherAccount = otherKweet.account!!)
}
