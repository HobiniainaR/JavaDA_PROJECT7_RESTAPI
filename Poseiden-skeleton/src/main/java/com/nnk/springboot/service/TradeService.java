package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;
    public List <Trade> findAll() {
        return tradeRepository.findAll();
    }
    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }
    public Trade findById(int id) {
        return tradeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trade does not exists"));
    }

    public void deleteById(int id) {
        tradeRepository.deleteById(id);
    }
}
