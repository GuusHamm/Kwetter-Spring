package nl.guushamm.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.ToString
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.io.Serializable
import javax.persistence.*

@Entity
@ToString(excludes = arrayOf("password"))
class Account : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var username: String = ""
    @Column(nullable = false) @JsonIgnore
    var password: String = ""
    @Column(nullable = false)
    var firstname: String = ""
    @Column(nullable = false)
    var lastname: String = ""
    var photo: String = ""
    @Column(length = 160)
    var bio: String = ""
    var location: String = ""
    var website: String = ""
    var roles: Array<String> = arrayOf()
    var active: Boolean = false

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "account_kweets",
            joinColumns = arrayOf(JoinColumn(name = "accountId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "kweetId", referencedColumnName = "id"))
    )
    var kweets: Collection<Kweet> = ArrayList()


    @ManyToMany(mappedBy = "following")
    var followers: Collection<Account> = ArrayList()

    @ManyToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
            name = "followers_following",
            joinColumns = arrayOf(JoinColumn(name = "followingId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "followersId", referencedColumnName = "id"))
    )
    var following: Collection<Account> = ArrayList()

    @OneToMany(cascade = arrayOf(CascadeType.MERGE))
    @JoinTable(
            name= "account_hearts",
            joinColumns = arrayOf(JoinColumn(name= "accountId", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "heartId", referencedColumnName = "id"))
    )
    var hearts: Collection<Heart> = ArrayList()

    companion object {
        @JvmStatic fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
    }

    constructor(username: String, password: String, firstname: String, lastname: String) {
        this.username = username
        this.password = password
        this.firstname = firstname
        this.lastname = lastname
    }


    constructor(username: String, password: String, firstname: String, lastname: String, photo: String, bio: String, location: String, website: String, roles: Array<String>) {
        this.username = username
        this.password = password
        this.firstname = firstname
        this.lastname = lastname
        this.photo = photo
        this.bio = bio
        this.location = location
        this.website = website
        this.roles = roles
    }

    constructor() {
//        JPA ONLY

    }
}
