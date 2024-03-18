function DDAPainter(context, width, height, imageData) {
    this.type = "dda";
    this.context = context;
    // this.imageData = context.createImageData(width, height);
    this.points = [];
    this.now = [-1, -1];
    this.width = width;
    this.height = height;
    if (imageData){
        this.imageData = imageData;
    }
    else {
        this.imageData = context.createImageData(width, height);
    }

    this.getPixelIndex = function(x, y) {
        if (x < 0 || y < 0  || x > this.width || y > this.height)
            return -1;
        return (x + y*width) << 2;

    }
    this.addPoint = function(p) {
        this.points.push(p);
    }
    this.setPixel = function(x, y, rgba) {

        pixelIndex = this.getPixelIndex(x, y);
        if (pixelIndex == -1) return;
        for (var i = 0; i < 4; i++) {
            this.imageData.data[pixelIndex + i] = rgba[i];
        }
        // console.log("work!!");

    }

    this.drawPoint = function(p, rgba){
        var x = p[0];
        var y = p[1];

        for (var i=-1; i <= 1; i++) {
            for (var j = -1; j <= 1; j++){
                this.setPixel(x + i, y + j, rgba);
            }
        }
        this.context.putImageData(this.imageData, 0, 0);
    }

    this.drawLine = function(p0, p1, rgba) {
        if (p0 == undefined) return;
        var x0 = p0[0], y0 = p0[1];
        var x1 = p1[0], y1 = p1[1];
        var dx = x1 - x0, dy = y1 - y0;
        if (Math.abs(dy) <= Math.abs(dx)) {
            if (x1 < x0) {
                var tx = x0; x0 = x1; x1 = tx;
                var ty = y0; y0 = y1; y1 = ty;
            }

            var k = dy / dx;
            var y = y0;

            for (var x = x0; x <= x1; x++) {
                this.setPixel(x, Math.floor(y + 0.5), rgba);
                y = y + k;
            }
        }

        else {
            if (y1 < y0) {
                var tx = x0; x0 = x1; x1 = tx;
                var ty = y0; y0 = y1; y1 = ty;
            }

            var k = dx / dy;
            var x = x0;

            for (var y = y0; y <= y1; ++y){
                this.setPixel(Math.floor(x + 0.5), y, rgba);
                x = x + k;
            }
        }
        this.context.putImageData(this.imageData, 0, 0);
    }
}