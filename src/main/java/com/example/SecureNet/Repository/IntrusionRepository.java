package com.example.SecureNet.Repository;

import com.example.SecureNet.Model.Intrusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntrusionRepository extends JpaRepository<Intrusion, Long> {
    List<Intrusion> findByTargetUrlContaining(String websiteName);
}
