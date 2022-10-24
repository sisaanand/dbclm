package com.luxoft.clm.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luxoft.clm.dto.NaceDetailDTO;
import com.luxoft.clm.model.NaceDetail;

@Repository
public interface NaceDetailRepository extends JpaRepository<NaceDetail, BigInteger>{

	NaceDetail findByOrderId(BigInteger orderId);

	//void saveAll(List<NaceDetailDTO> naceDetailList);

	//void saveAll(Iterable<NaceDetailDTO> naceDetailList);

	//void saveAll(List<NaceDetailDTO> naceDetailList);

	//Object findByOrderId(Long orderId);


}
