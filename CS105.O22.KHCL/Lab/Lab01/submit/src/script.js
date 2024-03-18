// Initialize
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");

var width = 800;
var height = 600;

var bgRgba = [240, 240, 200, 255];
var pointRgba = [0, 0, 255, 255];
var lineRgba = [0, 0, 0, 255];
var vlineRgba = [255, 0, 0, 255];

canvas.setAttribute("width", width);
canvas.setAttribute("height", height);

var painter = new DDAPainter(context, width, height, context.getImageData(0, 0, width, height));
var storedImageData;

var drawingMethod = "using_point";

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
        // Additional logic if needed, such as clearing canvas or resetting state
        // Remove the 'chosen' class from all options
        document.querySelectorAll('.option').forEach(function(opt) {
            opt.classList.remove('chosen');
        });

        // Add the 'chosen' class to the clicked option
        this.classList.add('chosen');
    });
});

document.querySelectorAll('.method').forEach(function(method) {
    method.addEventListener('click', function() {
        var selectedMethod = this.classList[1]; // Extract the painter type from the class
        drawingMethod = selectedMethod; // Create the corresponding painter
        painter.points = [];
        // Additional logic if needed, such as clearing canvas or resetting state
        // Remove the 'chosen' class from all options
        document.querySelectorAll('.method').forEach(function(met) {
            met.classList.remove('chosen');
        });

        // Add the 'chosen' class to the clicked option
        this.classList.add('chosen');
    });
});





var state = 0;


getPosOnCanvas = function(x, y){
    var bbox = canvas.getBoundingClientRect();
    return [Math.floor(x - bbox.left * (canvas.width / bbox.width ) + 0.5), 
            Math.floor(y - bbox.top * (canvas.height / bbox.height) + 0.5)];
}

// doMouseMove = function(e) {
//     var p = getPosOnCanvas(e.clientX, e.clientY);
//     painter.drawLine(painter.points[painter.points.length - 1], p, lineRgba);
//     painter.addPoint(p);
    
// }


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




doMouseDown = function(e) {
    

    if (e.button != 0 || drawingMethod == "without_point") {
        return;
    }
    
    var p = getPosOnCanvas(e.clientX, e.clientY);
    if (painter.type == "midpoint") {
        if (painter.points.length == 0){
            painter.addPoint(p);
            painter.drawPoint(p, pointRgba);
            return;
        }
        else{
            painter.drawCircle(painter.points[0], p, lineRgba);
            painter.points = [];
            return;
        }
        
    }

    if (state == 0) {
        state = 1;
        painter.addPoint(p);
        painter.drawPoint(p, pointRgba);
        return;
    }


    painter.drawLine(painter.points[painter.points.length - 1 ], p, lineRgba);
    painter.addPoint(p);
    painter.drawPoint(p, pointRgba);
}

doKeyDown = function(e) {
    var keyId = e.keyCode ? e.keyCode : e.which;

    // if (keyId == 27 && state == 1) {
    //     state = 2;
    //     painter.draw(painter.points[painter.points.length - 1]);
    // }

    // if (keyId == 27 && state == -1) {
    //     state = 0;
    // }

    if (keyId == 27) {
        state = 0;
    }
}

// canvas.addEventListener('mousemove', doMouseMove);
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