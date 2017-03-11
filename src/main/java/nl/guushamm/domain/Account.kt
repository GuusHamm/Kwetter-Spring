package nl.guushamm.domain

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
class Account : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false)
    var username: String? = null
    @Column(nullable = false)
    var password: String? = null
    @Column(nullable = false)
    var firstname: String? = null
    @Column(nullable = false)
    var lastname: String? = null
    var photo: String? = null
    @Column(length = 160)
    var bio: String? = null
    var location: String? = null
    var website: String? = null
    var isModerator: Boolean = false
    @OneToMany()
    private val kweets: Collection<Kweet> = ArrayList()
    @OneToMany()
    var following: Collection<Account> = ArrayList()

    constructor(username: String, password: String, firstname: String, lastname: String) {
        this.username = username
        this.password = password
        this.firstname = firstname
        this.lastname = lastname
    }


    constructor(username: String, password: String, firstname: String, lastname: String, photo: String, bio: String, location: String, website: String, moderator: Boolean) {
        this.username = username
        this.password = password
        this.firstname = firstname
        this.lastname = lastname
        this.photo = photo
        this.bio = bio
        this.location = location
        this.website = website
        this.isModerator = moderator
    }

    constructor() {
//        JPA ONLY

    }


}
