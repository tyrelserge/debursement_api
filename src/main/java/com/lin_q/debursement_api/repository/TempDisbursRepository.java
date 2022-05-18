package com.lin_q.debursement_api.repository;

import java.util.List;

import com.lin_q.debursement_api.entity.TempDisburs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TempDisbursRepository extends JpaRepository<TempDisburs, Integer> {

    @Query(value = "SELECT * FROM temp_disburs WHERE temp_status<>'treated' ORDER BY temp_disb_id DESC", nativeQuery = true)
    public List<TempDisburs> fetchUntreatedTempsDisburs();
    
}
