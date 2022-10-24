package com.luxoft.clm;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.luxoft.clm.model.NaceDetail;
import com.luxoft.clm.model.NaceDetailRequest;
import com.luxoft.clm.repository.NaceDetailRepository;
import com.luxoft.clm.result.Result;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class DbClmApplicationTests {
	
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	MockMvc mockMvc;
	 
	@Autowired
	NaceDetailRepository repository;
	
	
	/*
	 * Case#1
	 * Order Id should Numeric always. 
	 * Request - Pass a invalid Order ID as Alpha-numeric.
	 * Expected output - Bad request, Order ID is not valid.
	 */

	@Test
    public void testRequestWithInvalidOrderId() throws Exception {
       	NaceDetail expectedRecord = createTestData();    
       	
        Result actualRecord = om.readValue(mockMvc.perform(get("/clm/v1/getNaceDetails/"+expectedRecord.getOrderId()+"AAA")
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString(), Result.class); 
                
            	assertEquals(actualRecord.isCompleted(), false);
            	assertEquals(actualRecord.getErrorMsg(), "OrderID not valid");
    }

	/*
	 * Case#2 
	 * Request - Pass a Order ID, not exists in system.
	 * Expected output - Not Found status with message as No matching order found.
	 */
	
	@Test
    public void testRequestWithNotMatchingOrderId() throws Exception {
       	NaceDetail expectedRecord = createTestData();    
       	
        Result actualRecord = om.readValue(mockMvc.perform(get("/clm/v1/getNaceDetails/"+expectedRecord.getOrderId()+"99")
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString(), Result.class); 
                
            	assertEquals(actualRecord.isCompleted(), false);
            	assertEquals(actualRecord.getErrorMsg(), "No matching Order found");
    }
	
	/*
	 * Case#3
	 * Request - Pass a Order ID, exists in system.
	 * Expected output - Record detail is fetched from DB table record successfully. 
	 */

	@Test
    public void testRequestWithMatchingOrderId() throws Exception {
       	NaceDetail expectedRecord = createTestData();     
       	
        mockMvc.perform(get("/clm/v1/getNaceDetails/"+expectedRecord.getOrderId())
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(jsonPath("$.completed").value("true"))
                .andExpect(jsonPath("$.data.Level").value("1"))
        		.andExpect(jsonPath("$.data.Description").value("AGRICULTURE, FORESTRY AND FISHING"));
    }
	
	
	/*
	 * Case#4
	 * Request - Pass NaceDetail list as null
	 * Expected output - Response as bad request due to empty input.
	 */
	@Test
    public void testUpdateNaceDetailListwithEmpty() throws Exception {
       	NaceDetailRequest expectedRecord = new NaceDetailRequest();
       	expectedRecord.setNaceDetailList(null);
        mockMvc.perform(put("/clm/v1/naceDetails")
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                .andDo(print())
                .andExpect(jsonPath("$.completed").value("false"))
                .andExpect(jsonPath("$.errorMsg").value("Empty input"));
    }

	/*
	 * Case#5
	 * Request - Pass valid NaceDetail list. 
	 * Expected output - Response as success.
	 */
	@Test
    public void testUpdateNaceDetailList() throws Exception {
       	NaceDetailRequest expectedRecord = new NaceDetailRequest();
       	expectedRecord.setNaceDetailList(csvToJson(ResourceUtils.getFile("classpath:NACE.csv"), ResourceUtils.getFile("classpath:NACE.json")));
       	
        mockMvc.perform(put("/clm/v1/naceDetails")
                .contentType("application/json")
                .content(om.writeValueAsString(expectedRecord)))
                //.andDo(print())
                .andExpect(jsonPath("$.completed").value("true"))
                .andExpect(jsonPath("$.data").value("Success"));
    }
	
	/*
	 * Case#6
	 * Request - Pass valid OrderID by directly calling DB layer. 
	 * Expected output - successful response and validate data
	 */

	@Test
	public void whenFindByOrderID_thenReturnNaceDetail() {
	    // when
		NaceDetail naceDetailRequest = createTestData();
	    NaceDetail naceDetailResponse = repository.findByOrderId(naceDetailRequest.getOrderId());
	    
	    //then
    	assertEquals(naceDetailRequest.getOrderLevel(), naceDetailResponse.getOrderLevel());
    	assertEquals(naceDetailRequest.getDescription(), naceDetailResponse.getDescription());

	}
	
	/*
	 * Case#7
	 * Request - Pass not matching OrderID by directly calling DB layer. 
	 * Expected output - response as null for not matching order ID.
	 */
	@Test
	public void whenFindByInvalidOrderID_thenReturnNull() {
	    // when
		NaceDetail naceDetailRequest = createTestData();
	    NaceDetail naceDetailResponse = repository.findByOrderId(new BigInteger("123456789012345"));
	    
	    //then
    	assertNotEquals(naceDetailRequest.getOrderCode(), null);
    	assertNotEquals(naceDetailRequest.getDescription(), null);

	}
	
	@Test
	public void addNaceDetailsCSV() throws FileNotFoundException, IOException {
	    // when
       	NaceDetailRequest expectedRecord = new NaceDetailRequest();
       	expectedRecord.setNaceDetailList(csvToJson(ResourceUtils.getFile("classpath:NACE.csv"), ResourceUtils.getFile("classpath:NACE.json")));
       	List<NaceDetail> responseList = repository.saveAll(expectedRecord.getNaceDetailList());
   
	    //then
    	assertEquals(expectedRecord.getNaceDetailList().size(), responseList.size());
	}

	

	/*
	 * Create a sample Data used by test methods.
	 */

	public static NaceDetail createTestData()
	{
		NaceDetail naceDetail = new NaceDetail();
		naceDetail.setOrderId(new BigInteger("398481"));
		naceDetail.setOrderLevel(1);
		naceDetail.setOrderCode("A");
		naceDetail.setParent("");
		naceDetail.setDescription("AGRICULTURE, FORESTRY AND FISHING");
		naceDetail.setItemIncludes1("This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.");
		naceDetail.setItemIncludes2(null);
		naceDetail.setRulings(null);
		naceDetail.setItemExcludes(null);
		naceDetail.setReferenceISIC("A");
		return naceDetail;
	}
	
	/*
	 * Convert csv to json used by the test methods for insert/update list of nace details.
	 * 
	 */
    public static List<NaceDetail> csvToJson(File csvFile, File jsonFile) throws IOException {
        CsvSchema orderLineSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<NaceDetail> orderLines = csvMapper.readerFor(NaceDetail.class)
            .with(orderLineSchema)
            .readValues(csvFile);
        
        new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .writeValue(jsonFile, orderLines.readAll());
        
        List<NaceDetail> naceDetailList = Arrays.asList(new ObjectMapper().readValue(jsonFile, NaceDetail[].class));
        return naceDetailList;
    }
}