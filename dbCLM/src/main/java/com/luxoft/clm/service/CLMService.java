package com.luxoft.clm.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luxoft.clm.constants.CLMConstants;
import com.luxoft.clm.exception.NaceException;
import com.luxoft.clm.model.NaceDetail;
import com.luxoft.clm.model.NaceDetailRequest;
import com.luxoft.clm.repository.NaceDetailRepository;
import com.luxoft.clm.result.Result;

@Service
public class CLMService {
	
   @Autowired
   private NaceDetailRepository naceDetailRepository;
	
   public Result fetchOrderDetails(String orderId) throws NaceException{
	   try{
		   BigInteger bigOrderId = new BigInteger(orderId);
		   NaceDetail naceDetail = naceDetailRepository.findByOrderId(bigOrderId);
		   if(naceDetail != null)
		   {
			   Result result = new Result(naceDetail);
			   return result;
			   //throw new NaceException("Exception thrown");
		   }
		   return new Result(CLMConstants.NO_MATCHING_ORDER_FOUND);
	   	}
	   	catch (Exception e) {
	   		throw new NaceException(e.getLocalizedMessage());
	   	}

	}
      
   public Result updateNaceDetailService(NaceDetailRequest naceDetailRequest) throws NaceException {
		try{
		   naceDetailRepository.saveAll(naceDetailRequest.getNaceDetailList());
		   Object obj = CLMConstants.SUCCESS;
		   Result result = new Result(obj);
		   return result;
		 }
		catch (Exception e) {
			throw new NaceException(e.getLocalizedMessage());
		}
	}

}
