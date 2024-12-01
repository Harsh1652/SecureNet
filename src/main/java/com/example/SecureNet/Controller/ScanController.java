//ScanController
package com.example.SecureNet.Controller;

import com.example.SecureNet.Model.Intrusion;
import com.example.SecureNet.Model.Vulnerability;
import com.example.SecureNet.Service.IntrusionService;
import com.example.SecureNet.Service.VulnerabilityScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/scans")
public class ScanController {

    @Autowired
    private IntrusionService intrusionService;

    @Autowired
    private VulnerabilityScanService vulnerabilityScanService;

    @GetMapping("/intrusions/{websiteName}")
    public List<Intrusion> getIntrusionsByWebsite(@PathVariable String websiteName) {
        System.out.println("Fetching intrusions from ScanController for: " + websiteName);
        return intrusionService.getIntrusionsByWebsite(websiteName);
    }

    @PostMapping("/scan")
    public Map<String, Object> getScanResults(@RequestParam String websiteName) {
        System.out.println("Triggering scan for: " + websiteName);
        List<Intrusion> intrusions = intrusionService.getIntrusionsByWebsite(websiteName);
        List<Vulnerability> vulnerabilities = vulnerabilityScanService.getVulnerabilitiesByWebsite(websiteName);

        Map<String, Object> response = new HashMap<>();
        response.put("intrusions", intrusions);
        response.put("vulnerabilities", vulnerabilities);

        return response;
    }
}
