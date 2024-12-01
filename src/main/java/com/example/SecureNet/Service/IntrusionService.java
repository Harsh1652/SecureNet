//IntrusionService
package com.example.SecureNet.Service;

import com.example.SecureNet.Model.Intrusion;
import com.example.SecureNet.Repository.IntrusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntrusionService {

    @Autowired
    private IntrusionRepository intrusionRepository;

    public List<Intrusion> getIntrusionsByWebsite(String websiteName) {
        System.out.println("Fetching intrusions for website: " + websiteName);
        List<Intrusion> intrusions = intrusionRepository.findByTargetUrlContaining(websiteName);
        System.out.println("Intrusions found: " + intrusions.size());
        return intrusions;
    }

}
