package com.example.springefk.controller;

import com.example.springefk.exception.QuoteNotFoundException;
import com.example.springefk.model.Quote;
import com.example.springefk.service.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fluentd.logger.FluentLogger;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/quotes")
public class QuoteController {
//    {"type":"success","value":{"id":10,"quote":"Really loving Spring Boot, makes stand alone Spring apps easy."}}

    private QuoteService quoteService;
    private static FluentLogger fluentLogger = FluentLogger.getLogger("QuoteController", "localhost", 24224);

    @GetMapping
    public List<Quote> getAllQuotes(HttpServletRequest request) {
        Map<String, Object> dataLog = new TreeMap<>();
        dataLog.put("REQUEST ===>", String.format("time: %s, url: %s, ip: %s, user: %s",
                LocalDateTime.now().toString(),
                request.getRequestURL().toString(),
                request.getRemoteHost(),
                request.getRemoteUser()));
        List<Quote> list = quoteService.getAllQuotes();
        dataLog.put("RESPONSE ===>", list.stream().map(Object::toString).collect(Collectors.joining(", ")));
        fluentLogger.log("getAllQuotes" , dataLog);
        return list;
    }

    @PostMapping
    public Quote addQoute(@RequestBody Quote quote, HttpServletRequest request) throws QuoteNotFoundException {
        Map<String, Object> dataLog = new TreeMap<>();
        dataLog.put("REQUEST ===>", String.format("time: %s, url: %s, ip: %s, user: %s, quote: %s",
                LocalDateTime.now().toString(),
                request.getRequestURL().toString(),
                request.getRemoteHost(),
                request.getRemoteUser(),
                quote.toString()));
        Quote quoteFromDB = quoteService.addQuote(quote);
        dataLog.put("RESPONSE ===>", quoteFromDB.toString());
        fluentLogger.log("addQuote" , dataLog);
        return quoteFromDB;
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public Quote getQuoteByID(@PathVariable Long id, HttpServletRequest request) throws QuoteNotFoundException {
        Map<String, Object> dataLog = new TreeMap<>();
        dataLog.put("REQUEST ===>", String.format("time: %s, url: %s, ip: %s, user: %s, id: %d",
                LocalDateTime.now().toString(),
                request.getRequestURL().toString(),
                request.getRemoteHost(),
                request.getRemoteUser(),
                id));
        Quote quoteFromDB = quoteService.getQuoteByID(id);
        dataLog.put("RESPONSE ===>", quoteFromDB.toString());
        fluentLogger.log("getQuoteByID" , dataLog);
        return quoteFromDB;
    }

}

