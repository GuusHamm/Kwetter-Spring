package nl.guushamm;

import nl.guushamm.domain.Account;
import nl.guushamm.domain.Kweet;
import nl.guushamm.service.AccountService;
import nl.guushamm.service.KweetService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static nl.guushamm.utils.UtilsKt.getKwetterGoAccount;

/**
 * Created by guushamm on 3-5-17.
 */
@Component
public class Receiver {
	@Autowired
	private KweetService kweetService;

	@Autowired
	private AccountService accountService;


	@RabbitListener(queues = "kwetter")
	public void processKweet(byte[] data) throws InterruptedException {
		String dataString = new String(data, StandardCharsets.UTF_8);
		BasicJsonParser jsonParser = new BasicJsonParser();
		Map incoming = jsonParser.parseMap(dataString);

		Account kwetterGoAccount = accountService.findOrSave(getKwetterGoAccount());
		Kweet kweet = new Kweet(incoming.get("message").toString(), kwetterGoAccount);
		kweetService.save(kweet);
	}

}
