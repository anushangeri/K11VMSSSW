<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Generate Clocking QR Code</title>
    <link rel="stylesheet" href="css/styles.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/qrcodejs/1.0.0/qrcode.min.js"></script>
    <style>
        #qrcode {
            margin-top: 20px;
            text-align: center;
        }
        #downloadBtn {
            margin-top: 15px;
            display: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">QR Code Generator</h2>
    <div class="justify-content-center">
        <div class="col-md-6">
            <form id="qrForm">
                <div class="form-group">
                    <label for="siteName">Tag ID:</label>
                    <input type="text" class="form-control" id="siteName" name="siteName" required>
                </div>
                <div class="form-group">
                    <label for="clockingPointName">Clocking Point:</label>
                    <input type="text" class="form-control" id="clockingPointName" name="clockingPointName" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Generate QR Code</button>
            </form>

            <div id="qrcode"></div>
            <button id="downloadBtn" class="btn btn-success btn-block">Download QR Code</button>
            
             <br>
			<button type="button" 
			        class="btn btn-default btn-block"
			        onclick="window.location.href='index.jsp'">
			    Back to Homepage
			</button>
        </div>
    </div>
</div>

<script>
const form = document.getElementById("qrForm");
const qrcodeDiv = document.getElementById("qrcode");
const downloadBtn = document.getElementById("downloadBtn");
let qr;

form.addEventListener("submit", function(e) {
    e.preventDefault();

    const siteName = document.getElementById("siteName").value.trim();
    const clockingPointName = document.getElementById("clockingPointName").value.trim();

    if (!siteName || !clockingPointName) {
        alert("Please enter both Site Name and Clocking Point.");
        return;
    }

    // Make fields readonly
    document.getElementById("siteName").setAttribute("readonly", true);
    document.getElementById("clockingPointName").setAttribute("readonly", true);

    // Clear any previous QR
    qrcodeDiv.innerHTML = "";

    // Prepare JSON payload
    const qrData = JSON.stringify({
        siteName: siteName,
        clockingPointName: clockingPointName
    });

    // Generate QR code
    qr = new QRCode(qrcodeDiv, {
        text: qrData,
        width: 256,
        height: 256
    });

    // Show download button
    downloadBtn.style.display = "block";
    downloadBtn.onclick = function() {
        // Get QR image as <img>
        const img = qrcodeDiv.querySelector("img") || qrcodeDiv.querySelector("canvas");

        let qrURL;
        if (img.tagName.toLowerCase() === "canvas") {
            qrURL = img.toDataURL("image/png");
        } else {
            // fallback if it's <img>
            qrURL = img.src;
        }

        // Create a download link
        const a = document.createElement("a");
        a.href = qrURL;
        a.download = siteName + "_" + clockingPointName + ".png";
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);

        // Reset form
        form.reset();
        document.getElementById("siteName").removeAttribute("readonly");
        document.getElementById("clockingPointName").removeAttribute("readonly");
        qrcodeDiv.innerHTML = "";
        downloadBtn.style.display = "none";
    };
});
</script>
</body>
</html>