package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class CurvePointTests {
	CurvePoint curvePoint;
	@Autowired
	private CurvePointRepository curvePointRepository;

	@Test
	public void curvePointTest() {
		curvePoint = new CurvePoint();
		curvePoint.setValue(0.05);
		curvePoint.setTerm(55.05);
		curvePoint.setCurveId(10);


		curvePoint = curvePointRepository.save(curvePoint);
		Assertions.assertNotNull(curvePoint.getId(), "CurvePoint ID should not be null after save");
		Assertions.assertEquals(10, curvePoint.getCurveId(), "Curve ID should be 10");


		curvePoint.setCurveId(20);
		curvePoint = curvePointRepository.save(curvePoint);
		Assertions.assertEquals(20, curvePoint.getCurveId(), "Curve ID should be updated to 20");


		List<CurvePoint> listResult = curvePointRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0, "CurvePoint list should not be empty");


		Integer id = curvePoint.getId();
		curvePointRepository.delete(curvePoint);
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		Assertions.assertFalse(curvePointList.isPresent(), "CurvePoint should be deleted");
	}
}
