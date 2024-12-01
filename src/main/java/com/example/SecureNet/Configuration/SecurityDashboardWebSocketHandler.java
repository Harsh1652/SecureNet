// SecurityDashboardWebSocketHandler.java
package com.example.SecureNet.Configuration;

import com.example.SecureNet.Model.Intrusion;
import com.example.SecureNet.Model.Vulnerability;
import com.example.SecureNet.Service.IntrusionService;
import com.example.SecureNet.Service.VulnerabilityScanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;



import java.io.IOException;
import java.util.List;

public class SecurityDashboardWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private IntrusionService intrusionService;

    @Autowired
    private VulnerabilityScanService vulnerabilityService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String websiteName = message.getPayload();

        // Fetch intrusion and vulnerability data
        List<Intrusion> intrusions = intrusionService.getIntrusionsByWebsite(websiteName);
        List<Vulnerability> vulnerabilities = vulnerabilityService.getVulnerabilitiesByWebsite(websiteName);

        // Combine and send data as JSON
        String jsonResponse = objectMapper.writeValueAsString(new RealTimeData(intrusions, vulnerabilities));
        session.sendMessage(new TextMessage(jsonResponse));
    }

    private static class RealTimeData {
        public List<Intrusion> intrusions;
        public List<Vulnerability> vulnerabilities;

        public RealTimeData(List<Intrusion> intrusions, List<Vulnerability> vulnerabilities) {
            this.intrusions = intrusions;
            this.vulnerabilities = vulnerabilities;
        }
    }
}
