package com.example.springefk;

import com.example.springefk.model.Quote;
import lombok.extern.slf4j.Slf4j;
import org.fluentd.logger.FluentLogger;
import org.komamitsu.fluency.Fluency;
import org.komamitsu.fluency.FluencyBuilder;
import org.komamitsu.fluency.fluentd.FluencyBuilderForFluentd;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LogGenerator implements CommandLineRunner {

    private static Random random = new Random();
    private final RestTemplate restTemplate = new RestTemplate();

    // https://github.com/komamitsu/fluency
    // https://medium.com/@komamitsu/introduction-of-fluency-high-throughput-data-ingestion-logger-to-fluentd-aws-s3-and-treasure-118d64b98a47
    private static Fluency fluency = new FluencyBuilderForFluentd().build();

    @Override
    public void run(String... args) throws Exception {
        String levelMessage = "";
        Map<String, Object> message;
        while (true) {
            switch (random.nextInt(5)) {
                case 0:
                    levelMessage = "ERROR";
                    message = randomMessage(levelMessage);
                    fluency.emit(levelMessage, message);
                    log.error(message.get(levelMessage).toString());
                    break;
                case 1:
                    levelMessage = "DEBUG";
                    message = randomMessage(levelMessage);
                    fluency.emit(levelMessage, message);
                    log.debug(message.get(levelMessage).toString());
                    break;
                case 2:
                    levelMessage = "WARN";
                    message = randomMessage(levelMessage);
                    fluency.emit(levelMessage, message);
                    log.warn(message.get(levelMessage).toString());
                    break;
                case 3:
                    levelMessage = "TRACE";
                    message = randomMessage(levelMessage);
                    fluency.emit(levelMessage, message);
                    log.trace(message.get(levelMessage).toString());
                    break;
                default:
                    levelMessage = "INFO";
                    message = randomMessage(levelMessage);
                    fluency.emit(levelMessage, message);
                    log.info(message.get(levelMessage).toString());
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, Object> randomMessage(String levelMessage) {
        // http://api.icndb.com/jokes/random
        // {"type":"success","value":{"id":3,"quote":"Spring has come quite a ways in ..."}}
        Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        // log(String tag, Map<String, Object> data)
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(levelMessage, quote);
        return data;
    }

}
