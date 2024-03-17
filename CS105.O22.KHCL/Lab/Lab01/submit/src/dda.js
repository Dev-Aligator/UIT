function DDAPainter(context, width, height) {
    this.context = context;
    this.imageData = context.createImageData(width, height);
    this.points = [];
    this.now = [-1, -1];
    this.width = width;
    this.height = height;

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
        var dx = Math.abs(x1 - x0), dy = Math.abs(y1 - y0);
        var sx = x0 < x1 ? 1 : -1;
        var sy = y0 < y1 ? 1 : -1;
        var err = dx - dy;
        var e2;
    
        while (true) {
            this.setPixel(x0, y0, rgba);
            if (x0 === x1 && y0 === y1) break;
            e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }
}