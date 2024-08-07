package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }
    public CurvePoint save(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public CurvePoint findById(Integer id) {
        return curvePointRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("CurvePoint does not exists"));
    }

    public void deleteById(Integer id) {
         curvePointRepository.deleteById(id);
    }
}
