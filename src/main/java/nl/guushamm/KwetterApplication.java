package nl.guushamm;

import nl.guushamm.domain.Account;
import nl.guushamm.service.AccountService;
import nl.guushamm.service.KweetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

import static nl.guushamm.utils.UtilsKt.*;

@SpringBootApplication
public class KwetterApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(KwetterApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(KwetterApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AccountService accountService, KweetService kweetService) {
		return (args) -> {
			ArrayList<Account> accounts = testAccounts();
			accounts.forEach(accountService::save);

			// Login as an admin to create some kweets
			Account account = testAccount();
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(account.getUsername(),account.getPassword(),
							AuthorityUtils.createAuthorityList(account.getRoles()))
			);

			testKweets(50, accounts).forEach(kweetService::save);
			SecurityContextHolder.clearContext();
		};
	}
}