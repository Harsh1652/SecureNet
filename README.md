# SecureNet: Real-Time Intrusion Detection and Vulnerability Scanning System 🔒

SecureNet is a web-based application that provides real-time intrusion detection and vulnerability scanning for web applications. It integrates *Snort* for detecting intrusions and *Nikto* for scanning vulnerabilities, delivering actionable insights through a user-friendly dashboard.

---

## 🚀 Features

1. *Intrusion Detection*:
   - Parses Snort logs to detect threats like SQL injections, XSS attacks, and other malicious activities.
   - Extracts details like intrusion type, source IP, and target URL.
   
2. *Vulnerability Scanning*:
   - Uses Nikto to scan websites for vulnerabilities such as weak SSL configurations, outdated server software, and directory indexing issues.
   - Real-time scanning capability integrated with the dashboard.

3. *Real-Time Updates*:
   - Delivers live updates via WebSocket, ensuring low latency and instant threat notifications.

4. *User-Friendly Dashboard*:
   - Input a website name to fetch and display intrusions and vulnerabilities dynamically.
   - Modern and responsive interface built with HTML, CSS, and JavaScript.

---

## 💻 Tech Stack

### *Backend*
- *Framework*: Spring Boot (RESTful APIs, WebSocket)
- *Database*: MySQL
- *Tools*: Snort (Intrusion Detection), Nikto (Vulnerability Scanning)
- *Parsing*: Regex for log parsing
- *Scripting*: PowerShell for automating Nikto scans

### *Frontend*
- *Technologies*: HTML, CSS, JavaScript
- *Real-Time Communication*: WebSocket for live data updates

---

## 📂 Project Structure

### *1. Backend*
- *Configuration*:
  - WebSocket configuration for real-time updates.
  - Spring Security setup (currently allows all requests).
- *Controllers*:
  - IntrusionController: Fetches intrusion data by website name.
  - VulnerabilityController: Fetches vulnerability data by website name.
  - ScanController: Triggers real-time Nikto scans and combines results.
- *Services*:
  - LogParsingService: Parses Snort logs and saves intrusions.
  - VulnerabilityScanService: Executes Nikto scans and parses results.
- *Repositories*:
  - IntrusionRepository: Handles database queries for intrusion data.
  - VulnerabilityRepository: Handles database queries for vulnerability data.
- *Models*:
  - Intrusion: Represents intrusion details (type, source IP, target URL, timestamp).
  - Vulnerability: Represents vulnerability details (severity, description, target URL, timestamp).

### *2. Frontend*
- *HTML*:
  - Input form for website name.
  - Sections for displaying intrusions and vulnerabilities.
- *JavaScript*:
  - Fetches data from REST APIs and updates the dashboard dynamically.
  - Triggers WebSocket connections for real-time updates.
- *CSS*:
  - Styles the dashboard with a clean, modern design.

---

## 🌟 Key Challenges and Solutions

### *Challenge 1: Parsing Large Log Files*
- *Solution*: Used optimized regex patterns to efficiently extract critical information from Snort and Nikto logs.

### *Challenge 2: Real-Time Communication*
- *Solution*: Leveraged WebSocket for low-latency updates, with error handling to manage dropped connections.

### *Challenge 3: Delayed Log Updates*
- *Solution*: Implemented a retry mechanism to handle delays in log file updates for Nikto scans.

---

## 🔧 Installation and Setup

### *Prerequisites*
1. Java (JDK 17 or later)
2. MySQL (or any relational database)
3. Snort and Nikto installed and configured:
   - *Snort*: Generates intrusion logs.
   - *Nikto*: Scans websites for vulnerabilities.
4. Node.js (optional for serving static assets).

---

### *Steps*

1. *Clone the Repository*:
   ```bash
   git clone https://github.com/Harsh1652/SecureNet.git
   cd SecureNet
   
Set Up the Database:

1) Create a MySQL database named securenet.
2) Update the database credentials in application.properties:

```
spring.datasource.url=jdbc:mysql://localhost:3306/securenet
spring.datasource.username=your-username
spring.datasource.password=your-password
Run the Backend:
```



```
./mvnw spring-boot:run
Start Snort and Nikto:
```

1) Ensure Snort is configured to generate logs.
2) Verify that Nikto is operational (Perl and PowerShell must be installed).

# Access the Dashboard:

 Open index.html in your browser or serve it through a local server (e.g., Node.js or Python).

 📖 API Endpoints
# Intrusions
GET /api/intrusions/{websiteName}: Fetch intrusions for a given website.

# Vulnerabilities
GET /api/vulnerabilities/{websiteName}: Fetch vulnerabilities for a given website.

# Real-Time Scan
POST /api/scan?websiteName={websiteName}: Trigger a Nikto scan for a given website.

# WebSocket
URL: /ws/updates: Pushes real-time intrusion and vulnerability data.

📊 Example Output
Intrusions
json
```
[
    {
        "id": 1,
        "type": "SQL Injection",
        "sourceIp": "192.168.1.100",
        "targetUrl": "www.example.com",
        "timestamp": "2024-11-19T12:34:56"
    }
]
```
Vulnerabilities
json
```
[
    {
        "id": 1,
        "severity": "Medium",
        "description": "Outdated server software detected.",
        "targetUrl": "www.example.com",
        "timestamp": "2024-11-19T12:34:56"
    }
]
```

# 🎯 Real-World Applications
1) Threat Detection: Helps organizations monitor network traffic for suspicious activity.
2) Web Security: Scans for vulnerabilities to prevent exploitation.
3) Centralized Monitoring: A unified platform for managing intrusions and vulnerabilities.

# 🤝 Contributing
  Contributions are welcome! Please follow these steps:

# Fork the repository.
1) Create a feature branch (git checkout -b feature-name).
2) Commit your changes (git commit -m "Add feature-name").
3) Push to the branch (git push origin feature-name).
4) Open a pull request.
