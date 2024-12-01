//IntrusionController
package com.example.SecureNet.Controller;

import com.example.SecureNet.Model.Intrusion;
import com.example.SecureNet.Service.IntrusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intrusions")
public class IntrusionController {

    @Autowired
    private IntrusionService intrusionService;

    @GetMapping
    public List<Intrusion> getIntrusionsByWebsite(@RequestParam String websiteName) {
        System.out.println("Intrusion API called with websiteName: " + websiteName);
        return intrusionService.getIntrusionsByWebsite(websiteName);
    }
}