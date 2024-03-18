// Initialize
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");

// var width = 1000;
// var height = 800;


var screenWidth = window.innerWidth;
screenWidth = screenWidth > 2560 ? 2560 : screenWidth; /// Max value for screen width is 2560px


// Determine the desired aspect ratio (e.g., 4:3)
var aspectRatio = 5 / 3; // You can adjust this to your desired aspect ratio

// Set the width based on the screen width
var width = Math.round(screenWidth * 0.7); // Adjust as needed, here 80% of the screen width
// Calculate the corresponding height based on the aspect ratio
var height = Math.round(width / aspectRatio);

var bgRgba = [240, 240, 200, 255];
var pointRgba = [0, 0, 255, 255];
var lineRgba = [0, 0, 0, 255];
var vlineRgba = [255, 0, 0, 255];

canvas.setAttribute("width", width);
canvas.setAttribute("height", height);
canvas.style.backgroundColor = bgRgba;
var painter = new DDAPainter(context, width, height, context.getImageData(0, 0, width, height));
var storedImageData;
var tempImageData;

var drawingMethod = "using_point";
methodInstructionNotification(drawingMethod);

function createPainter(painterType) {
    storedImageData = context.getImageData(0, 0, width, height);
    if (painterType === 'dda') {
        painter = new DDAPainter(context, width, height, storedImageData);
    } else if (painterType === 'bresenham') {
        painter = new BresenhamPainter(context, width, height, storedImageData);
    } else if (painterType === 'midpoint') {
        painter = new MidPointPainter(context, width, height, storedImageData);
    }
}

// Event listener for option clicks
document.querySelectorAll('.option').forEach(function(option) {
    option.addEventListener('click', function() {
        var painterType = this.classList[1]; // Extract the painter type from the class
        createPainter(painterType); // Create the corresponding painter
        // Remove the 'chosen' class from all options
        if (painterType == "midpoint") {   /// Force to use points method when select Midpoint to draw circle
            document.querySelector('.method.using_point').click();
        }
        changeVisible(document.querySelector('.method.without_point')); /// Conditionally hide or show the "without_point" option 

        methodInstructionNotification(drawingMethod); /// Display the instruction
        
        document.querySelectorAll('.option').forEach(function(opt) {
            opt.classList.remove('chosen');
        });

        // Add the 'chosen' class to the clicked option
        this.classList.add('chosen');
    });
});

document.querySelectorAll('.method').forEach(function(method) {
    method.addEventListener('click', function() {
        var selectedMethod = this.classList[1]; // Extract the method type from the class
        drawingMethod = selectedMethod; // Set the corresponding method
        painter.points = [];
        // Remove the 'chosen' class from all options
        document.querySelectorAll('.method').forEach(function(met) {
            met.classList.remove('chosen');
        });

        methodInstructionNotification(drawingMethod); /// Display the instruction

        // Add the 'chosen' class to the clicked option
        this.classList.add('chosen');
    });
});

function changeVisible(element) {   //// This function check if midpoint is used then hide "without_point method"
    if (element.classList.contains('disabled')) {
        if (painter.type != "midpoint")
        element.classList.remove('disabled'); // Remove the disabled class
    } else if (painter.type == "midpoint") {
        element.classList.add('disabled'); // Add the disabled class
    }
}


var state = 0;


getPosOnCanvas = function(x, y){
    var bbox = canvas.getBoundingClientRect();
    return [Math.floor(x - bbox.left * (canvas.width / bbox.width ) + 0.5), 
            Math.floor(y - bbox.top * (canvas.height / bbox.height) + 0.5)];
}


// Implementing Drawing When Pressing The Mouse and Stop when releasing it

var isMouseDown = false;

canvas.addEventListener('mousedown', function(e) {
    isMouseDown = true;
    if (drawingMethod == "without_point"){
        var p = getPosOnCanvas(e.clientX, e.clientY);
        painter.addPoint(p);
    }
});

canvas.addEventListener('mousemove', function(e) {
    if (isMouseDown && drawingMethod == "without_point") {
        var p = getPosOnCanvas(e.clientX, e.clientY);
        painter.drawLine(painter.points[painter.points.length - 1], p, lineRgba);
        painter.addPoint(p);
    }
});

canvas.addEventListener('mouseup', function(e) {
    isMouseDown = false;
});

////


/// Implementing Drawing Using Point - Click to Create Point then Use Points to Make Line, Circle
doMouseDown = function(e) {
    if (e.button != 0 || drawingMethod == "without_point") { /// Only Left Button and When Drawing Method is set to "without_point"
        return;
    }
    
    var p = getPosOnCanvas(e.clientX, e.clientY);   /// Get Mouse Position
    if (painter.type == "midpoint") {               /// Draw Circle if painter type is MidPoint 
        if (painter.points.length == 0){            /// Draw the center point then wait for the second point
            painter.addPoint(p);
            painter.drawPoint(p, pointRgba);
            tempImageData = new ImageData(
                new Uint8ClampedArray(painter.imageData.data),
                painter.imageData.width,
                painter.imageData.height
            )
            canvas.addEventListener('mousemove', doMouseMoveDrawCircleV);
            state = 2;
            return;
        }
        else{
            painter.drawCircle(painter.points[0], p, lineRgba);   /// When we have 2 points, draw the circle
            painter.points = [];
            state = 1;
            canvas.removeEventListener('mousemove', doMouseMoveDrawCircleV);
            return;
        }
        
    }

    //// Here we draw lines using DDA and Bresenham
    if (painter.points.length == 0) {
        painter.addPoint(p);
        painter.drawPoint(p, pointRgba);
        tempImageData = new ImageData(
            new Uint8ClampedArray(painter.imageData.data),
            painter.imageData.width,
            painter.imageData.height
        )
        canvas.addEventListener('mousemove', doMouseMoveDrawV);
        state = 2;
    }   
    else {
        painter.drawLine(painter.points[painter.points.length-1], p , lineRgba);
        painter.drawPoint(p, pointRgba);
        painter.points = [];
        canvas.removeEventListener('mousemove', doMouseMoveDrawV);
        state = 1;
    }
}

function doMouseMoveDrawV(e) {
    if (state != 2 || painter.type == "midpoint") return;
    painter.imageData = new ImageData(
        new Uint8ClampedArray(tempImageData.data),
        tempImageData.width,
        tempImageData.height
    );
    let v_point = getPosOnCanvas(e.clientX, e.clientY);
    painter.drawLine(painter.points[0], v_point, vlineRgba);
}

function doMouseMoveDrawCircleV(e) {
    if (state != 2 || painter.type != "midpoint" ) return;
    painter.imageData = new ImageData(
        new Uint8ClampedArray(tempImageData.data),
        tempImageData.width,
        tempImageData.height
    );
    let v_point = getPosOnCanvas(e.clientX, e.clientY);
    painter.drawCircle(painter.points[0], v_point, vlineRgba);
}

doKeyDown = function(e) {
    var keyId = e.keyCode ? e.keyCode : e.which;

    if (keyId == 27 && state == 2) { /// Press ESC to stop connect the lastet point to the new one - draw a new path
        state = 0;
        painter.imageData = new ImageData(
            new Uint8ClampedArray(tempImageData.data),
            tempImageData.width,
            tempImageData.height
        );
        painter.context.putImageData(painter.imageData, 0,0);
        painter.drawPoint(painter.points[0], bgRgba);
        painter.points = [];
    }
}
// canvas.addEventListener('mousemove', doMouseMoveDrawV);
// canvas.addEventListener('mousemove', doMouseMoveDrawCircleV);
canvas.addEventListener('mousedown', doMouseDown);
document.addEventListener('keydown', doKeyDown);


function clearCanvas() {
    context.clearRect(0, 0, canvas.width, canvas.height);
}

var clearButton = document.getElementById("reset");
clearButton.addEventListener("click", function() {
    clearCanvas();
    painter.points = [];
    state = 0;
    painter.imageData = context.createImageData(canvas.width, canvas.height);
});

window.addEventListener('resize', function() {
    // Call the showNotification function when a resize event is detected
    showNotification("Phát hiện bạn vừa resize cửa sổ, để có trải nghiệm tốt nhất hãy refresh lại trang web", 7000);
  });
  
function methodInstructionNotification(method) {
    if(method == "using_point") {
        if (painter.type == "midpoint") {
            showNotification("Để vẽ hình tròn, đầu tiên nhấp chuột để vẽ tâm, sau đó nhấp một lần nữa để chọn bán kính, ", 300000);
        }
        else {
            showNotification("Để vẽ, hãy nhấp chuột để tạo điểm bắt đầu, sau đó di chuột để chọn điểm cuối - Nhấn ESC để hủy đường đang vẽ", 300000);
        }
    }

    if(method == "without_point") {
        showNotification("Đè chuột trái và di chuột để vẽ, thả chuột để dừng vẽ", 300000);
    }
}

function showNotification(text, timeout=30000) {
    var notification = document.getElementById('notification');
    notification.style.display = 'block';
    notification.innerHTML = text;
  
    // Hide the notification after 3 seconds (3000 milliseconds)
    setTimeout(function() {
      closeNotification();
    }, timeout);
  }
  
  function closeNotification() {
    var notification = document.getElementById('notification');
    notification.style.display = 'none';
  }
  
