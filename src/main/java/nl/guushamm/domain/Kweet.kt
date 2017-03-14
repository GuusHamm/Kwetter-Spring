package nl.guushamm.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.joda.time.DateTime

import javax.persistence.*

@Entity
class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false, length = 140)
    var message: String = ""
    var timestamp: DateTime = DateTime.now()
    @ManyToOne()
    var account: Account? = null
    @Version @JsonIgnore var version: Long = 0

    constructor(message: String, account: Account) {
        this.message = message
        this.account = account
    }

    constructor() {
//        JPA ONLY
    }
}