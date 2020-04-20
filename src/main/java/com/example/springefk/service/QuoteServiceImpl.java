package com.example.springefk.service;

import com.example.springefk.exception.QuoteNotFoundException;
import com.example.springefk.model.Quote;
import com.example.springefk.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    @Override
    public Quote addQuote(Quote quote) {
        log.info(String.format("QUOTE_SERVICE ===> time: %s, method: %s, value: %s, status: %s",
                LocalDateTime.now().toString(),
                "addQuote",
                quote.toString(),
                "ADD"));

        Quote quoteFromDB = quoteRepository.save(quote);

        log.info(String.format("QUOTE_SERVICE ===> time: %s, method: %s, value: %s, status: %s",
                LocalDateTime.now().toString(),
                "addQuote",
                quote.toString(),
                "SUCCESS"));

        return quoteFromDB;
    }

    @Override
    public Quote getQuoteByID(Long id) throws QuoteNotFoundException {
        log.info(String.format("QUOTE_SERVICE ===> time: %s, method: %s, value: %d, status: %s",
                LocalDateTime.now().toString(),
                "getQuoteByID",
                id,
                "GET"));

        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(
                "QUOTE_SERVICE ===>", "getQuoteByID", "Cannot find qoute with id:" + id));

        log.info(String.format("QUOTE_SERVICE ===> time: %s, method: %s, value: %s, status: %s",
                LocalDateTime.now().toString(),
                "getQuoteByID",
                quote.toString(),
                "SUCCESS"));

        return quote;
    }

    @Override
    public List<Quote> getAllQuotes() {
        log.info(String.format("QUOTE_SERVICE ===> time: %s, method: %s, value: %s, status: %s",
                LocalDateTime.now().toString(),
                "getAllQuotes",
                null,
                "GET"));

        List<Quote> list = quoteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        log.info(String.format("QUOTE_SERVICE ===> time: %s, method: %s, value: %s, status: %s",
                LocalDateTime.now().toString(),
                "getAllQuotes",
                list.stream().map(Object::toString).collect(Collectors.joining(", ")),
                "SUCCESS"));

        return list;
    }
}
