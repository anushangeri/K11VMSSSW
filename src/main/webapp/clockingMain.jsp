<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="loginVMSCSS.jsp"%>
<%@page import="java.util.*"%>
<%@page import="net.javatutorial.entity.Clocking"%>
<%
    // --- existing session/server logic retained ---
    String action = request.getParameter("action");
    if ("clear".equals(action)) {
        session.invalidate();
        response.sendRedirect("clockingMain.jsp");
        return;
    }

    String officerNameParam = request.getParameter("officerName");
    String officerNricParam = request.getParameter("officerNric");

    if (officerNameParam != null && officerNricParam != null) {
        if (officerNameParam.trim().isEmpty() || officerNricParam.trim().isEmpty()) {
            response.sendRedirect("clockingMain.jsp?error=Missing+officer+details");
            return;
        }
        session.setAttribute("officerName", officerNameParam.toUpperCase());
        session.setAttribute("officerNric", officerNricParam.toUpperCase());
    }

    String officerName = (String) session.getAttribute("officerName");
    String officerNric = (String) session.getAttribute("officerNric");

    List<Clocking> records = null;
    Object obj = session.getAttribute("clockingRecords");
    if (obj instanceof List<?>) {
        records = (List<Clocking>) obj;
    }

    session.setMaxInactiveInterval(4 * 60 * 60);

    boolean hasRecords = (records != null && !records.isEmpty());
    String errorMessage = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Officer QR Check-In</title>
<link rel="stylesheet" href="css/styles.css">
<script
	src="https://drvic10k.github.io/bootstrap-sortable/Scripts/bootstrap-sortable.js"
	type="text/javascript"></script>
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jsQR library -->
<script src="https://cdn.jsdelivr.net/npm/jsqr/dist/jsQR.js"></script>

<style>
    .scanner-container {
        position: relative;
        width: 100%;
        max-width: 400px;
        margin-top: 20px;
    }
    #cameraFeed {
        width: 100%;
        height: auto;
        display: none;
        background: #000; /* fallback while streaming */
        border-radius: 4px;
    }
    /* overlay sits above the video */
    #overlay {
        position: absolute;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        pointer-events: none; /* click-through */
    }
    #scanButton, #doneButton, #saveButton {
        margin: 10px 0;
        padding: 10px 20px;
        font-size: 16px;
    }
    .disabled-btn {
        pointer-events: none;
        opacity: 0.5;
    }
    #errorPopup {
        position: fixed;
        top: 20px;
        right: 20px;
        background: #f44336;
        color: #fff;
        padding: 15px 20px;
        border-radius: 8px;
        z-index: 9999;
        display: none;
    }
    /* Modal field styling */
    .modal-body dl { margin-bottom: 0; }
    .modal-body dt { font-weight: 600; }
    .modal-body dd { margin-left: 0; margin-bottom: 10px; }
</style>
</head>
<body>
<div class="container">
    <h2 class="text-center">Officer Check-In</h2>
    <div class="justify-content-center">
        <div class="col-md-6">
            <!-- Officer Info Form -->
            <form id="officerForm" method="post">
                <div class="form-group">
                    <label for="officerName">Officer Name:</label>
                    <input type="text" class="form-control" id="officerName" name="officerName"
                           value="<%= officerName != null ? officerName : "" %>"
                           oninput="this.value = this.value.toUpperCase()"
                           <%= officerName != null ? "disabled" : "" %> required>
                </div>
                <div class="form-group">
                    <label for="officerNric">Officer NRIC:</label>
                    <input type="text" class="form-control" id="officerNric" name="officerNric"
                           value="<%= officerNric != null ? officerNric : "" %>"
                           maxlength="9" minlength="9"
                           oninput="this.value = this.value.toUpperCase()"
                           <%= officerNric != null ? "disabled" : "" %> required>
                </div>

                <!-- Save button: disabled after saved -->
                <button type="submit"
                        id="saveButton"
                        class="btn btn-primary btn-lg btn-block <%= (officerName != null && officerNric != null) ? "disabled-btn" : "" %>"
                        <%= (officerName != null && officerNric != null) ? "disabled" : "" %>>
                    Save Officer Info
                </button>

                <!-- Scan button: disabled until officer saved -->
                <button type="button"
                        id="scanButton"
                        class="btn btn-warning btn-lg btn-block <%= (officerName == null || officerNric == null) ? "disabled-btn" : "" %>"
                        <%= (officerName == null || officerNric == null) ? "disabled" : "" %>>
                    Scan QR Code
                </button>
            </form>

            <!-- Scanner area: video + overlay canvas -->
            <div class="scanner-container">
                <video id="cameraFeed" autoplay muted playsinline></video>
                <canvas id="overlay"></canvas>
            </div>

            <br>

            <!-- Done button: server-side enabled if there are records, otherwise disabled.
                 JS fallback checks localStorage.hasScannedOnce to enable it after a scan. -->
            <button type="button"
                    id="doneButton"
                    class="btn btn-danger btn-block <%= hasRecords ? "" : "disabled-btn" %>"
                    <%= hasRecords ? "" : "disabled" %>
                    onclick="window.location.href='clockingMain.jsp?action=clear'">
                Done Clocking
            </button>
            
            <br>
            <button type="button" 
                    class="btn btn-default btn-block"
                    onclick="window.location.href='index.jsp'">
                Back to Homepage
            </button>
        </div>
    </div>

    <% if (records != null && !records.isEmpty()) { %>
        <h3 class="text-center mt-4">Clocking Records This Session</h3>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th>Clocking ID</th>
                <th>Clocking Point</th>
                <th>Site</th>
                <th>Time</th>
                <th>Created By</th>
            </tr>
            </thead>
            <tbody>
            <% for (Clocking c : records) { %>
                <tr>
                    <td><%= c.getClockingId() %></td>
                    <td><%= c.getClockingPointName() %></td>
                    <td><%= c.getSiteName() %></td>
                    <td><%= c.getCreatedDt() %></td>
                    <td><%= c.getCreatedBy() %></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } %>
</div>

<!-- Error Popup -->
<div id="errorPopup"><%= errorMessage != null ? errorMessage : "" %></div>

<!-- ---------- Bootstrap Modal (A1) ---------- -->
<div id="scanModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="scanModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <!-- Keep the close icon but it behaves like Cancel -->
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="scanModalLabel">QR Scan Successful</h4>
      </div>
      <div class="modal-body">
        <p>The QR code was decoded. Review the data below and click <strong>Save Clocking</strong> to send it to the server.</p>
        <dl>
            <dt>Site Name</dt>
            <dd id="modalSiteName">—</dd>
            <dt>Clocking Point</dt>
            <dd id="modalClockingPoint">—</dd>
        </dl>
        <div id="modalNotice" style="display:none;" class="alert alert-info">Saving...</div>
      </div>
      <div class="modal-footer">
        <button type="button" id="modalCancelBtn" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" id="modalSaveBtn" class="btn btn-primary">Save Clocking</button>
      </div>
    </div>
  </div>
</div>

<!-- jQuery + Bootstrap JS (needed for modal) -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
/* ----------------- Scanner & UI logic with overlay guide + dynamic outline ----------------- */
const video = document.getElementById('cameraFeed');
const overlay = document.getElementById('overlay');
const overlayCtx = overlay.getContext('2d');
let videoStream = null;
let lastDecodedData = null; // store decoded for modal review
let scanning = false;

// server-side flag: whether page was rendered with records present
var serverHasRecords = <%= hasRecords ? "true" : "false" %>;

/* ---------- Page load UI setup ---------- */
window.addEventListener('load', function() {
    const errorPopup = document.getElementById("errorPopup");
    if (errorPopup.innerText.trim() !== "") {
        errorPopup.style.display = "block";
        setTimeout(() => { errorPopup.style.display = "none"; }, 3000);
    }

    if (!serverHasRecords) {
        try {
            if (localStorage.getItem('hasScannedOnce') === '1') {
                enableDoneButton();
                localStorage.removeItem('hasScannedOnce');
            }
        } catch (e) {}
    }

    reflectDisabledState();
});

/* ---------- Helper UI functions ---------- */
function addDisabledClass(el) {
    if (!el.classList.contains('disabled-btn')) el.classList.add('disabled-btn');
    el.setAttribute('disabled', 'disabled');
}
function removeDisabledClass(el) {
    el.classList.remove('disabled-btn');
    el.removeAttribute('disabled');
}
function enableDoneButton() {
    const doneBtn = document.getElementById('doneButton');
    removeDisabledClass(doneBtn);
}
function reflectDisabledState() {
    const btns = ['saveButton','scanButton','doneButton'];
    btns.forEach(id => {
        const el = document.getElementById(id);
        if (!el) return;
        if (el.hasAttribute('disabled')) {
            addDisabledClass(el);
        } else {
            removeDisabledClass(el);
        }
    });
}

/* ---------- Officer save: allow normal submit ---------- */
document.getElementById('officerForm').addEventListener('submit', function(e) {
    const saveBtn = document.getElementById('saveButton');
    saveBtn.disabled = true;
});

/* ---------- Scan start ---------- */
document.getElementById('scanButton').addEventListener('click', function() {
    if (this.hasAttribute('disabled')) return;
    startCamera();
});

/* ---------- Robust stopCamera: stop tracks, clear srcObject, pause, hide ---------- */
function stopCamera() {
    try {
        if (videoStream) {
            videoStream.getTracks().forEach(track => {
                try { track.stop(); } catch (err) { console.warn("track stop failed", err); }
            });
        }
    } catch (e) {
        console.warn("stopCamera stopping tracks failed", e);
    }

    try {
        // pause and detach the stream from the video element
        video.pause();
        video.srcObject = null;
        video.removeAttribute('src');
        try { video.load(); } catch (e) { /* ignore */ }
    } catch (e) {
        console.warn("stopCamera clearing video element failed", e);
    }

    // hide video and clear overlay so no black frame remains
    video.style.display = 'none';
    clearOverlay();

    videoStream = null;
    scanning = false;
}

/* ---------- overlay helpers ---------- */
function updateOverlaySize() {
    // Ensure overlay matches the video's displayed size.
    const rect = video.getBoundingClientRect();
    const width = Math.max(1, Math.floor(rect.width));
    const height = Math.max(1, Math.floor(rect.height));
    overlay.width = width;
    overlay.height = height;
    overlay.style.width = width + "px";
    overlay.style.height = height + "px";
    overlay.style.left = rect.left - rect.left + "px"; // keep at 0 within container
    overlay.style.top = rect.top - rect.top + "px";
}

function clearOverlay() {
    overlayCtx.clearRect(0, 0, overlay.width, overlay.height);
}

// draw static center box sized to 50% of the shorter side (S3)
function drawStaticGuide() {
    clearOverlay();
    if (overlay.width === 0 || overlay.height === 0) return;

    const shorter = Math.min(overlay.width, overlay.height);
    const boxSize = Math.floor(shorter * 0.5); // 50% of the shorter side
    const x = Math.floor((overlay.width - boxSize) / 2);
    const y = Math.floor((overlay.height - boxSize) / 2);

    overlayCtx.lineWidth = Math.max(2, Math.floor(boxSize * 0.02));
    overlayCtx.strokeStyle = "rgba(255,255,255,0.7)"; // subtle white
    overlayCtx.setLineDash([6,6]);
    overlayCtx.strokeRect(x + 0.5, y + 0.5, boxSize, boxSize);

    // corners to make it more obvious
    overlayCtx.setLineDash([]);
    overlayCtx.strokeStyle = "rgba(0, 200, 0, 0.9)";
    const cornerLen = Math.max(16, Math.floor(boxSize * 0.12));
    // top-left
    overlayCtx.beginPath();
    overlayCtx.moveTo(x, y + cornerLen);
    overlayCtx.lineTo(x, y);
    overlayCtx.lineTo(x + cornerLen, y);
    overlayCtx.stroke();
    // top-right
    overlayCtx.beginPath();
    overlayCtx.moveTo(x + boxSize - cornerLen, y);
    overlayCtx.lineTo(x + boxSize, y);
    overlayCtx.lineTo(x + boxSize, y + cornerLen);
    overlayCtx.stroke();
    // bottom-left
    overlayCtx.beginPath();
    overlayCtx.moveTo(x, y + boxSize - cornerLen);
    overlayCtx.lineTo(x, y + boxSize);
    overlayCtx.lineTo(x + cornerLen, y + boxSize);
    overlayCtx.stroke();
    // bottom-right
    overlayCtx.beginPath();
    overlayCtx.moveTo(x + boxSize - cornerLen, y + boxSize);
    overlayCtx.lineTo(x + boxSize, y + boxSize);
    overlayCtx.lineTo(x + boxSize, y + boxSize - cornerLen);
    overlayCtx.stroke();
}

// draw dynamic polygon if jsQR detects location
function drawDetectedOutline(location, scaleX, scaleY) {
    if (!location) return;
    overlayCtx.lineWidth = 4;
    overlayCtx.strokeStyle = "rgba(0, 255, 0, 0.95)";
    overlayCtx.beginPath();
    // map corners from image coordinates to overlay coordinates using scale
    const tl = { x: location.topLeftCorner.x * scaleX, y: location.topLeftCorner.y * scaleY };
    const tr = { x: location.topRightCorner.x * scaleX, y: location.topRightCorner.y * scaleY };
    const br = { x: location.bottomRightCorner.x * scaleX, y: location.bottomRightCorner.y * scaleY };
    const bl = { x: location.bottomLeftCorner.x * scaleX, y: location.bottomLeftCorner.y * scaleY };
    overlayCtx.moveTo(tl.x, tl.y);
    overlayCtx.lineTo(tr.x, tr.y);
    overlayCtx.lineTo(br.x, br.y);
    overlayCtx.lineTo(bl.x, bl.y);
    overlayCtx.closePath();
    overlayCtx.stroke();

    // draw small corner circles
    overlayCtx.fillStyle = "rgba(0,255,0,0.95)";
    [tl,tr,br,bl].forEach(pt => {
        overlayCtx.beginPath();
        overlayCtx.arc(pt.x, pt.y, 6, 0, Math.PI * 2);
        overlayCtx.fill();
    });
}

/* ---------- startCamera: request camera and begin scanning ---------- */
async function startCamera() {
    try {
        // reset UI
        lastDecodedData = null;
        document.getElementById('modalNotice').style.display = 'none';
        document.getElementById('modalSaveBtn').disabled = false;

        // show video area
        video.style.display = 'block';
        video.muted = true;
        video.playsInline = true;

        // request a reasonable resolution to help decoding
        videoStream = await navigator.mediaDevices.getUserMedia({
            video: {
                facingMode: "environment",
                width: { ideal: 1280 },
                height: { ideal: 720 }
            }
        });

        video.srcObject = videoStream;
        await video.play().catch(()=>{});
        scanning = true;

        // update overlay sizes to match video display size
        updateOverlaySize();
        // draw initial static guide
        drawStaticGuide();

        // start scanning loop
        scanFrames();
    } catch (err) {
        alert("Cannot access camera. Use HTTPS or allow camera permission.");
        console.error(err);
    }
}

/* ---------- scanFrames: continuous frame capture + jsQR decode + overlay drawing ---------- */
function scanFrames() {
    const canvas = document.createElement('canvas');
    const context = canvas.getContext('2d');

    function scan() {
        if (!scanning || !videoStream) return;

        if (video.readyState === video.HAVE_ENOUGH_DATA) {
            // ensure overlay matches any video size changes
            updateOverlaySize();

            canvas.width = video.videoWidth || 640;
            canvas.height = video.videoHeight || 480;
            // draw current video frame to offscreen canvas for decoding
            context.drawImage(video, 0, 0, canvas.width, canvas.height);
            const imageData = context.getImageData(0, 0, canvas.width, canvas.height);

            const code = jsQR(imageData.data, imageData.width, imageData.height);
            console.log("Decoded:", code ? code.data : "(nothing)");

            // draw static guide every frame first
            drawStaticGuide();

            // If jsQR found a code, draw dynamic outline scaled to overlay size
            if (code && code.location) {
                // scale factors from image (canvas) space to overlay display space
                const scaleX = overlay.width / canvas.width;
                const scaleY = overlay.height / canvas.height;
                drawDetectedOutline(code.location, scaleX, scaleY);
            }

            if (code && code.data) {
                // We have valid data. Stop camera fully and hide video/overlay BEFORE modal
                stopCamera();

                // parse payload (JSON preferred, fallback to key=val;key2=val2)
                let clockingData = {};
                try {
                    clockingData = JSON.parse(code.data);
                } catch (e) {
                    code.data.split(';').forEach(pair => {
                        let [key, val] = pair.split('=');
                        if (key && val) clockingData[key.trim()] = val.trim();
                    });
                }

                if (!clockingData.clockingPointName || !clockingData.siteName) {
                    alert("Invalid QR data: missing clockingPointName or siteName. QR contained: " + code.data);
                    // restart scanning after short delay
                    setTimeout(() => { startCamera(); }, 300);
                    return;
                }

                lastDecodedData = {
                    siteName: clockingData.siteName,
                    clockingPointName: clockingData.clockingPointName
                };

                // populate modal fields
                document.getElementById('modalSiteName').innerText = lastDecodedData.siteName;
                document.getElementById('modalClockingPoint').innerText = lastDecodedData.clockingPointName;
                document.getElementById('modalNotice').style.display = 'none';
                document.getElementById('modalSaveBtn').disabled = false;

                // show modal after tiny pause to ensure overlay/video hidden
                setTimeout(() => {
                    $('#scanModal').modal({ backdrop: 'static', keyboard: false });
                    $('#scanModal').modal('show');
                }, 120);

                return; // exit scan loop until user acts
            }
        }

        requestAnimationFrame(scan);
    }

    scan();
}

/* ---------- Modal Cancel: resume scanning ---------- */
document.getElementById('modalCancelBtn').addEventListener('click', function() {
    lastDecodedData = null;
    setTimeout(() => { startCamera(); }, 300);
});

/* ---------- Modal Save: POST to addClocking ---------- */
document.getElementById('modalSaveBtn').addEventListener('click', function() {
    if (!lastDecodedData) return;
    const saveBtn = this;
    saveBtn.disabled = true;
    document.getElementById('modalNotice').style.display = 'block';
    document.getElementById('modalNotice').innerText = 'Saving...';

    fetch("addClocking", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "clockingPointName=" + encodeURIComponent(lastDecodedData.clockingPointName) +
              "&siteName=" + encodeURIComponent(lastDecodedData.siteName)
    }).then(async resp => {
        if (resp.redirected) {
            window.location.href = resp.url;
            return;
        }

        if (resp.ok) {
            try { localStorage.setItem('hasScannedOnce', '1'); } catch (e) {}
            $('#scanModal').modal('hide');
            alert("Clocking recorded!");
            enableDoneButton();
            lastDecodedData = null;
            reflectDisabledState();
        } else {
            const text = await resp.text().catch(() => "");
            alert("Server returned error while saving: " + resp.status + " " + text);
            saveBtn.disabled = false;
            document.getElementById('modalNotice').style.display = 'none';
        }
    }).catch(err => {
        console.error("Error posting clocking:", err);
        alert("Failed to save clocking: " + err.message);
        saveBtn.disabled = false;
        document.getElementById('modalNotice').style.display = 'none';
    });
});

/* ---------- Ensure modal closing also restarts scanning appropriately ---------- */
$('#scanModal').on('hidden.bs.modal', function () {
    if (lastDecodedData) {
        // If modal closed without saving (e.g., user clicked X/backdrop), treat as cancel
        lastDecodedData = null;
        setTimeout(() => { startCamera(); }, 200);
    }
});

// Clear overlay on page hide/unload
window.addEventListener('beforeunload', function() {
    clearOverlay();
    try { stopCamera(); } catch (e) {}
});
</script>
</body>
</html>