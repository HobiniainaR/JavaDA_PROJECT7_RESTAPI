package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }
    public Rating findById(int id) {
        return ratingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("rating does not exists"));
    }

    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }
}
