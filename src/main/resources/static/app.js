//app.js

// Wait for the DOM to fully load
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("websiteForm");
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        const websiteName = document.getElementById("website-name").value.trim();
        if (!websiteName) {
            alert("Please enter a website name.");
            return;
        }
        updateLoadingStatus(true); // Display loading status
        fetchDataFromApi(encodeURIComponent(websiteName));
    });
});

// Function to fetch intrusion and vulnerability data from the API
function fetchDataFromApi(websiteName) {
    console.log("Fetching details for website:", websiteName);

    // Fetch intrusions
    const intrusionsUrl = `/api/intrusions?websiteName=${websiteName}`;
    console.log("Intrusions API URL:", intrusionsUrl);

    fetch(intrusionsUrl)
        .then(response => {
            if (!response.ok) {
                console.error("Error fetching intrusions:", response.statusText);
                throw new Error(`Intrusions fetch failed with status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("Intrusions data received:", data);
            displayIntrusions(data);
        })
        .catch(error => {
            console.error("Error fetching intrusions:", error);
            displayError("Error fetching intrusions. Please try again later.");
        });

    // Fetch vulnerabilities
    const vulnerabilitiesUrl = `/api/vulnerabilities?websiteName=${websiteName}`;
    console.log("Vulnerabilities API URL:", vulnerabilitiesUrl);

    fetch(vulnerabilitiesUrl)
        .then(response => {
            if (!response.ok) {
                console.error("Error fetching vulnerabilities:", response.statusText);
                throw new Error(`Vulnerabilities fetch failed with status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log("Vulnerabilities data received:", data);
            displayVulnerabilities(data);
        })
        .catch(error => {
            console.error("Error fetching vulnerabilities:", error);
            displayError("Error fetching vulnerabilities. Please try again later.");
        })
        .finally(() => updateLoadingStatus(false));
}

// Function to display intrusions on the page
function displayIntrusions(data) {
    const intrusionList = document.getElementById("intrusion-list");
    intrusionList.innerHTML = ''; // Clear existing entries

    if (Array.isArray(data) && data.length > 0) {
        data.forEach(intrusion => {
            const listItem = document.createElement("li");
            listItem.textContent = `${intrusion.type} from ${intrusion.sourceIp}`;
            intrusionList.appendChild(listItem);
        });
    } else {
        intrusionList.innerHTML = '<li>No intrusions found yet.</li>';
    }
}

// Function to display vulnerabilities on the page
function displayVulnerabilities(data) {
    console.log("Received vulnerabilities data:", data); // Debugging log
    const vulnerabilityList = document.getElementById("vulnerability-list");
    vulnerabilityList.innerHTML = ''; // Clear existing entries

    if (Array.isArray(data) && data.length > 0) {
        data.forEach(vulnerability => {
            const listItem = document.createElement("li");
            listItem.textContent = `${vulnerability.severity} - ${vulnerability.description} on ${vulnerability.targetUrl}`;
            vulnerabilityList.appendChild(listItem);
        });
    } else {
        vulnerabilityList.innerHTML = '<li>No vulnerabilities found yet.</li>';
    }
}

// Function to display an error message
function displayError(message) {
    const statusElement = document.getElementById("loading-status");
    statusElement.textContent = message;
    statusElement.style.color = "red";
}

// Function to update the loading status message
function updateLoadingStatus(isLoading) {
    const statusElement = document.getElementById("loading-status");
    statusElement.textContent = isLoading ? "Loading data..." : "";
    statusElement.style.color = isLoading ? "blue" : "";
}

