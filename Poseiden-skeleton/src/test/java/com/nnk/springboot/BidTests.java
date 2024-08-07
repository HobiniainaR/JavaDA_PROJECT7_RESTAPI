package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class BidTests {
	BidList bid;
	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {

		bid = new BidList();
		bid.setBidListId(1);
		bid.setCreationDate(new Timestamp(new Date().getTime()));
		bid.setBidQuantity(1.0);
		bid.setAskQuantity(1.0);
		bid.setBid(1.0);
		bid.setAccount("Account Test");
		bid.setType("Type Test");


		bid = bidListRepository.save(bid);
		Assertions.assertNotNull(bid.getBidListId(), "BidList ID should not be null after save");
		Assertions.assertEquals(10d, bid.getBidQuantity(), 0.001, "Bid Quantity should be 10d");


		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		Assertions.assertEquals(20d, bid.getBidQuantity(), 0.001, "Bid Quantity should be updated to 20d");


		List<BidList> listResult = bidListRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0, "BidList should not be empty");


		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assertions.assertFalse(bidList.isPresent(), "BidList should be deleted");
	}
}
