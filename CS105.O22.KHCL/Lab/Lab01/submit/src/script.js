// Initialize
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");


var screenWidth = window.innerWidth;   
screenWidth = screenWidth > 2560 ? 2560 : screenWidth; /// Max value for screen width is 2560px


// Determine the desired aspect ratio (e.g., 5:3)
var aspectRatio = 5 / 3;

// Set the width based on the screen width
var width = Math.round(screenWidth * 0.7); // Adjust as needed, here 70% of the screen width
// Calculate the corresponding height based on the aspect ratio
var height = Math.round(width / aspectRatio);

var bgRgba = [240, 240, 200, 255];
var pointRgba = [0, 0, 255, 255];
var lineRgba = [0, 0, 0, 255];
var vlineRgba = [255, 0, 0, 255];



canvas.setAttribute("width", width);
canvas.setAttribute("height", height);
canvas.style.backgroundColor = bgRgba;
var painter = new DDAPainter(context, width, height, context.getImageData(0, 0, width, height)); // set DDA as the default drawing algorithm
var storedImageData;   
var tempImageData;
var notificationTimeout;
var drawingMethod = "using_2_points"; 
methodInstructionNotification(drawingMethod);   

function createPainter(painterType) {   /// Create painter corresponding to user's selection
    storedImageData = context.getImageData(0, 0, width, height); // Save current image data
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
        if (painterType == "midpoint") {   /// Force to use 2 points method when select Midpoint to draw circle
            document.querySelector('.method.using_2_points').click();
        }
        changeVisible(document.querySelector('.method.using_many_points')); /// Conditionally hide or show the "using_many_points" option 

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
        // Remove the 'chosen' class from all options
        document.querySelectorAll('.method').forEach(function(met) {
            met.classList.remove('chosen');
        });

        methodInstructionNotification(drawingMethod); /// Display the instruction
        terminateAction();                             // Terminate current action when switching to a new drawing style 
        painter.points = []; 
        // Add the 'chosen' class to the clicked option
        this.classList.add('chosen');
    });
});

function changeVisible(element) {   //// This function check if midpoint is used then hide "using_many_points" method
    if (element.classList.contains('disabled')) {
        if (painter.type != "midpoint")
        element.classList.remove('disabled'); // Remove the disabled class
    } else if (painter.type == "midpoint") {
        element.classList.add('disabled'); // Add the disabled class
    }
}


var state = 0;    // Initial state, when there is no point in the painter.points


getPosOnCanvas = function(x, y){
    var bbox = canvas.getBoundingClientRect();
    return [Math.floor(x - bbox.left * (canvas.width / bbox.width ) + 0.5), 
            Math.floor(y - bbox.top * (canvas.height / bbox.height) + 0.5)];
}

copyImageData = function(imageData) {
    return new ImageData(
        new Uint8ClampedArray(imageData.data), width, height
    )
}




/// Implementing Drawing Using 2 Points - Click to Create Starting Point then Select the Second Point
doMouseDown = function(e) {
    if (e.button != 0 || drawingMethod == "using_many_points") { /// Only Left Button and When Drawing Method is set to "using_2_points"
        return;
    }
    
    var p = getPosOnCanvas(e.clientX, e.clientY);   /// Get Mouse Position
    
    
    if (painter.type == "midpoint") {               /// Draw Circle if painter type is MidPoint 
        if (painter.points.length == 0){            /// Draw the center point then wait for the second point
            painter.addPoint(p);
            painter.drawPoint(p, pointRgba);
            tempImageData = copyImageData(painter.imageData);      // Here, we save the imageData AFTER drawing the starting point
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
    if (painter.points.length == 0) {         // When there is no starting point
        painter.addPoint(p);                 // Set this point as the starting point
        painter.drawPoint(p, pointRgba);     // Draw this point


        /* Here, we save the imageData AFTER drawing the starting point
           Then, wherever the mouse goes, we draw a virtual line
          But before draw the current virtual line, we need to erase the
          old one, so we restore the imageData when we draw starting point */
                      
        tempImageData = copyImageData(painter.imageData);                                     
        canvas.addEventListener('mousemove', doMouseMoveDrawV);
        state = 2;                                                 // State = 2 - We repeatedly draw a virtual line then wait for user to select the end point
    }   
    else {                                  // Here is when user select the end point 
        painter.drawLine(painter.points[0], p , lineRgba);      // Draw a line from the starting point to the selected point 
        painter.drawPoint(p, pointRgba);                       // Draw the selected point
        painter.points = [];                                    // Set the painter.points to empty ( Waiting for the next starting point for a new line)
        canvas.removeEventListener('mousemove', doMouseMoveDrawV);  
        state = 1;                                             // Set the state back to 1
    }
}

function doMouseMoveDrawV(e) {                              // This function is used to draw virtual lines
    if (state != 2 || painter.type == "midpoint") return;   // Only works if state = 2 and painter.type is either DDA or Bresenham
    painter.imageData = copyImageData(tempImageData);                     // Restore the old imageData ( after draw the starting point )
    let v_point = getPosOnCanvas(e.clientX, e.clientY);
    painter.drawLine(painter.points[0], v_point, vlineRgba);  // Draw a virtual line
}

function doMouseMoveDrawCircleV(e) {                        // This function is used to draw virtual circles
    if (state != 2 || painter.type != "midpoint" ) return;  // Only works if state = 2 and painter.type is MidPoint
    painter.imageData = copyImageData(tempImageData);                   // Same as before
    let v_point = getPosOnCanvas(e.clientX, e.clientY);
    painter.drawCircle(painter.points[0], v_point, vlineRgba); // Draw a virtual circle
}

doKeyDown = function(e) {                                 // This function is used to Terminate a drawing action
    var keyId = e.keyCode ? e.keyCode : e.which;          

    if (keyId == 27) {                     // KeyId = 27 --> ESC
        terminateAction();
    }
}

terminateAction = function() {
    if (state == 2) {
        state = 0; 
        painter.imageData = copyImageData(tempImageData)   // Restore the old imageData ( after draw the starting point )
        painter.context.putImageData(painter.imageData, 0,0);   
        painter.drawPoint(painter.points[0], bgRgba);      // Fill the starting point with blank space
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




//// From here We implement unnecessary functions, you can ignore them


window.addEventListener('resize', function() {
    // Call the showNotification function when a resize event is detected
    showNotification("Phát hiện bạn vừa resize cửa sổ, để có trải nghiệm tốt nhất hãy refresh lại trang web", 7000);
  });
  
function methodInstructionNotification(method) {
    if(method == "using_2_points") {
        if (painter.type == "midpoint") {
            showNotification("Để vẽ hình tròn, đầu tiên nhấp chuột để vẽ tâm, sau đó nhấp một lần nữa để chọn bán kính, Nhấn ESC để hủy hình tròn hiện tại ", 300000);
        }
        else {
            showNotification("Để vẽ, hãy nhấp chuột để tạo điểm bắt đầu, sau đó di chuột để chọn điểm cuối - Nhấn ESC để hủy đường đang vẽ", 300000);
        }
    }

    if(method == "using_many_points") {
        showNotification("Đè chuột trái và di chuột để vẽ, thả chuột để dừng vẽ", 300000);
    }
}

function showNotification(text, timeout=30000) {
    var notification = document.getElementById('notification');
    notification.style.display = 'block';
    notification.innerHTML = text;
  
    // Hide the notification after 3 seconds (3000 milliseconds)
    if (notificationTimeout) {
        clearTimeout(notificationTimeout);
    }

    // Set the timeout for the new notification
    notificationTimeout = setTimeout(function() {
        closeNotification();
    }, timeout);
  }
  
  function closeNotification() {
    var notification = document.getElementById('notification');
    notification.style.display = 'none';
  }
  
  var colorPicker = new Huebee('#colorPickerInput', {
    notation: 'hex',
    saturations: 2,
    setText: true,
    defaultColor: 'rgba(0, 0, 0, 1)'
});

// Implementing Drawing When Pressing The Mouse and Stop when releasing it
// Additional drawing style, Can only draw lines, When drawing circles using midpoint, We disable this style

// ONLY IF drawingMethod == "using_many_points"
var isMouseDown = false;

canvas.addEventListener('mousedown', function(e) {
    isMouseDown = true;
    if (drawingMethod == "using_many_points"){
        var p = getPosOnCanvas(e.clientX, e.clientY);
        painter.addPoint(p);
    }
});

canvas.addEventListener('mousemove', function(e) {
    if (isMouseDown && drawingMethod == "using_many_points") {
        var p = getPosOnCanvas(e.clientX, e.clientY);
        painter.drawLine(painter.points[painter.points.length - 1], p, lineRgba);
        painter.addPoint(p);
    }
});

window.addEventListener('mouseup', function(e) {
    isMouseDown = false;
});

//



colorPicker.on('change', function(color) {
    // Convert the hex color to RGBA
    var rgbaColor = hexToRgba(color);
    // Update lineRgba
    lineRgba = rgbaColor;
});

function hexToRgba(hex) {
    // Remove the '#' if present
    hex = hex.replace(/^#/, '');
    // Parse the hexadecimal values
    var bigint = parseInt(hex, 16);
    // Extract the RGBA values
    var r = (bigint >> 16) & 255;
    var g = (bigint >> 8) & 255;
    var b = bigint & 255;
    // Return the RGBA values as an array
    return [r, g, b, 255];
}


