package nl.guushamm.domain

import javax.persistence.*

/**
 * Created by guushamm on 1-4-17.
 */
@Entity
class Trend{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(unique = true)
    var name: String = ""

    @ManyToMany()
    @JoinTable(
            name= "kweet_trends",
            joinColumns = arrayOf(JoinColumn(name= "trendID", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "kweetId", referencedColumnName = "id"))
    )
    var kweets: Collection<Kweet> = ArrayList()

    constructor(name: String) {
        this.name = name
    }

    constructor() {
//        JPA ONlY
    }
}