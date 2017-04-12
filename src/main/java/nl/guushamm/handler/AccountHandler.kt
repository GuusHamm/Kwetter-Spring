package nl.guushamm.handler

import nl.guushamm.domain.Account
import org.springframework.data.rest.core.annotation.HandleAfterCreate
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.stereotype.Component
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


/**
 * Created by guushamm on 13-3-17.
 */
@Component
@RepositoryEventHandler(Account::class)
open class AccountHandler {
    @HandleAfterCreate
    fun sendMail(account: Account) {
        val username = "ticketsysteem.mediamarkt@gmail.com"
        val password = "123Welkom"

        val props: Properties = Properties()

        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.smtp.host", "smtp.gmail.com")
        props.put("mail.smtp.port", "587")

        val session: Session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })

        val email: Message = MimeMessage(session)

        var message = "<h4>Hello ${account.firstname}</h4><br>"
        message += "<p>Activate your account now by click <a href=\"http://localhost:8080/kwetter-0.0.1-SNAPSHOT/api/account/${account.id}\">this link</a></p>"

        email.addFrom(arrayOf(InternetAddress("ticketsysteem.mediamarkt@gmail.com")))
        email.setRecipient(Message.RecipientType.TO, InternetAddress.parse("guushamm@gmail.com").component1())


        email.subject = "Welcome to Kwetter ${account.firstname}!"
        email.setContent(message, "text/html; charset=utf-8")

        Transport.send(email)
    }
}
