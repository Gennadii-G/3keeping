package com.home.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @author gennadii_borodin
 *
 * this spring integration don't do some important
 * this code only for example and test
 */
//@Configuration
@Slf4j
public class TestQueueConfig {

    private final static String INPUT_DIR = "tmp\\folder1";
    private final static String OUTPUT_DIR = "tmp\\folder2";

    @PostConstruct
    private void init() throws IOException {
        log.info("post construct is working");

        Path pathFolderOne = Paths.get(INPUT_DIR);
        Path pathFolderTwo = Paths.get(OUTPUT_DIR);

        if(!Files.exists(pathFolderOne) && !Files.exists(pathFolderTwo) ){
            Files.createDirectories(pathFolderOne);
            Files.createDirectories(pathFolderTwo);
            log.info("success existed");
        }
        log.info("end of post construct");
    }

    @Bean
    public IntegrationFlow upcaseFlow() {
        return IntegrationFlows.from(sourceDirectory(), configurer -> configurer.poller(Pollers.fixedDelay(5000)))
                .filter(s -> ((File) s).getName().endsWith(".txt"))
                .handle(targetDirectory())
                // add more components
                .get();
    }

    @Bean
    public MessageSource<File> sourceDirectory() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(INPUT_DIR));
        return messageSource;
    }

    @Bean
    public MessageHandler targetDirectory() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }
}
