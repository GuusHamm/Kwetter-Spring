package nl.guushamm.domain

import org.joda.time.DateTime

import javax.persistence.*

@Entity
class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @ManyToOne
    var account: Account? = null
    @ManyToOne
    var kweet: Kweet? = null
    var timestamp: DateTime? = null

    constructor(account: Account, kweet: Kweet) {
        this.account = account
        this.kweet = kweet
        this.timestamp = DateTime.now()
    }

    constructor() {
//        JPA ONlY
    }
}