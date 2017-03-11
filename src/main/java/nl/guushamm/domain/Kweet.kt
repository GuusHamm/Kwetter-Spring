package nl.guushamm.domain

import org.joda.time.DateTime

import javax.persistence.*

@Entity
class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false, length = 140)
    var message: String? = null
    var timestamp: DateTime? = null
    @ManyToOne()
    lateinit var account: Account

    constructor(message: String, account: Account) {
        this.message = message
        this.timestamp = DateTime.now()
        this.account = account
    }

    constructor() {
//        JPA ONLY
    }
}