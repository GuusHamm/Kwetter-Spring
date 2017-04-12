package nl.guushamm.domain

import javax.persistence.*

@Entity
class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @OneToOne()
    @JoinTable(
            name= "account_hearts",
            joinColumns = arrayOf(JoinColumn(name= "heartId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "accountId", referencedColumnName = "id"))
    )
    lateinit var account: Account

    @OneToOne()
    @JoinTable(
            name= "kweet_hearts",
            joinColumns = arrayOf(JoinColumn(name= "heartId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "kweetId", referencedColumnName = "id"))
    )
    lateinit var kweet: Kweet
    var timestamp: Long = 0

    constructor(account: Account, kweet: Kweet, timestamp: Long = 0) {
        this.account = account
        this.kweet = kweet
        this.timestamp = timestamp
    }

    constructor() {
//        JPA ONlY
    }
}