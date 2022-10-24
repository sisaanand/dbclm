package com.luxoft.clm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.clm.constants.CLMConstants;
import com.luxoft.clm.exception.NaceException;
import com.luxoft.clm.model.NaceDetailRequest;
import com.luxoft.clm.result.Result;
import com.luxoft.clm.service.CLMService;

@RestController
@RequestMapping(path="${rootPath}")
public class CLMController {
	  
   @Autowired
   private CLMService clmService;
	
   @GetMapping(path = "${getNaceDetails}")
   public ResponseEntity getNaceDetails(@PathVariable(value = "orderId") String orderId) throws NaceException
   {	
	   try
	   {
		   if(orderId != null && orderId.trim().length() > 0 && orderId.matches(CLMConstants.REGEX_ONLY_DIGITS))
		   {
			   Result result = clmService.fetchOrderDetails(orderId);
			   if(result != null && result.getData() != null)
				   return ResponseEntity.status(HttpStatus.OK).body(result);
			   else
				   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		   }   
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(CLMConstants.ORDER_NOT_VALID));	
	   }
	   catch(Exception e)
	   {
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(e.getLocalizedMessage()));	
	   }
	}
	
	
   @PutMapping(path="${naceDetails}")
    public ResponseEntity updateNaceDetails(@RequestBody NaceDetailRequest naceDetailRequest) throws NaceException
    {
	   try
	   {
		   if(naceDetailRequest.getNaceDetailList() == null || naceDetailRequest.getNaceDetailList().size() == 0)
			   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(CLMConstants.EMPTY_INPUT));
		   else{
			   Result result = clmService.updateNaceDetailService(naceDetailRequest);
			   return ResponseEntity.status(HttpStatus.OK).body(result);
		   }
	   }catch (Exception e) {
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result(e.getLocalizedMessage()));	
	}
    }
   
}
