package nl.guushamm.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.annotation.CreatedDate
import javax.persistence.*

@Entity
class Kweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false, length = 140)
    var message: String = ""
    @CreatedDate
    var timestamp: Long = 0
    @JsonBackReference
    @OneToOne()
    @JoinTable(
            name = "account_kweets",
            joinColumns = arrayOf(JoinColumn(name = "kweetId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "accountId", referencedColumnName = "id"))
    )
    var account: Account? = null

    @OneToMany(cascade = arrayOf(CascadeType.MERGE))
    @JoinTable(
            name = "kweet_hearts",
            joinColumns = arrayOf(JoinColumn(name = "kweetId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "heartId", referencedColumnName = "id"))
    )
    var hearts: Collection<Heart> = ArrayList()

    @ManyToMany(cascade = arrayOf(CascadeType.MERGE))
    @JoinTable(
            name = "kweet_trends",
            joinColumns = arrayOf(JoinColumn(name = "kweetId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "trendID", referencedColumnName = "id"))
    )
    var trends: Collection<Trend> = ArrayList()

    constructor(message: String, account: Account) {
        this.message = message
        this.account = account
    }

    constructor() {
//        JPA ONLY
    }
}