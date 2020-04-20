package com.example.springefk.service;

import com.example.springefk.exception.QuoteNotFoundException;
import com.example.springefk.model.Quote;

import java.util.List;

public interface QuoteService {

    Quote addQuote(Quote quote);
    Quote getQuoteByID(Long id) throws QuoteNotFoundException;
    List<Quote> getAllQuotes();

}
