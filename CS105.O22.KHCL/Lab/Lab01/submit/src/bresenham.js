function BresenhamPainter(context, width, height, imageData) {
    this.type = "bresenham";
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
        
        var x1 = p0[0], y1 = p0[1];
        var x2 = p1[0], y2 = p1[1];
        var dx = Math.abs(x2 - x1);
        var dy = Math.abs(y2 - y1);
        var steep = dy > dx;

        if (steep) {
            [x1, y1] = [y1, x1];
            [x2, y2] = [y2, x2];
        }
        if (x1 > x2) {
            [x1, x2] = [x2, x1];
            [y1, y2] = [y2, y1];
        }
        dx = x2 - x1;
        dy = Math.abs(y2 - y1);
        var p = 2 * dy - dx;
        var y = y1;

        for (var x = x1; x <= x2; x++) {
            if (steep) {
                this.setPixel(y, x, rgba);
            } else {
                this.setPixel(x, y, rgba);
            }
            if (p < 0) {
                p += 2 * dy;
            } else {
                y += (y1 < y2 ? 1 : -1);
                p += 2 * dy - 2 * dx;
            }
        }
    }
    
    
}