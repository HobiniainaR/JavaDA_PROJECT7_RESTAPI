package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    public void save(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }

    public RuleName findById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
        }
    public void deleteById(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
