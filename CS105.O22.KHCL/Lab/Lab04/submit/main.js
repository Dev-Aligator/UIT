// import * as THREE from 'three'
import * as THREE from './lib/three.module.js'

// Khai báo các biến global
let scene, camera, renderer, box;

function init() {
    scene = new THREE.Scene();

    box = getBox(1, 1, 1);

    box.position.y = box.geometry.parameters.height / 2;

    scene.add(box);

    camera = new THREE.PerspectiveCamera(
        45,
        window.innerWidth/window.innerHeight,
        1,
        1000
    );

    camera.position.x = 1;
    camera.position.y = 2;
    camera.position.z = 5;

    camera.lookAt(new THREE.Vector3(0, 0, 0));

    renderer = new THREE.WebGLRenderer();
    renderer.setSize(window.innerWidth, window.innerHeight);
    document.getElementById('webgl').appendChild(renderer.domElement);

    // Thêm event listener cho các button
    document.getElementById('rotateLeftButton').addEventListener('click', function() {
        rotateObject('left');
    });
    
    document.getElementById('rotateRightButton').addEventListener('click', function() {
        rotateObject('right');
    });
    
    document.getElementById('rotateUpButton').addEventListener('click', function() {
        rotateObject('up');
    });
    
    document.getElementById('rotateDownButton').addEventListener('click', function() {
        rotateObject('down');
    });

    
    document.getElementById('scaleIncreaseButton').addEventListener('click', function() {
        scaleObject('increase');
    });
    
    document.getElementById('scaleDecreaseButton').addEventListener('click', function() {
        scaleObject('decrease');
    });
    
    
    document.getElementById('translateLeftButton').addEventListener('click', function() {
        translateObject('left');
    });
    
    document.getElementById('translateRightButton').addEventListener('click', function() {
        translateObject('right');
    });
    
    document.getElementById('translateUpButton').addEventListener('click', function() {
        translateObject('up');
    });
    
    document.getElementById('translateDownButton').addEventListener('click', function() {
        translateObject('down');
    });

    
    document.getElementById('changeCameraButton').addEventListener('click', changeCamera);

    update();
}

function getBox(w, h, d) {
    var geometry = new THREE.BoxGeometry(w, h, d);
    var material = new THREE.MeshBasicMaterial({
        color: 0x00ff00
    });
    var mesh = new THREE.Mesh(
        geometry,
        material
    );

    return mesh;
}

function update() {
    renderer.render(scene, camera);
    requestAnimationFrame(update);
}

function rotateObject(direction) {
    // Tốc độ quay
    const rotationSpeed = 0.05;

    // Xác định hướng quay
    switch (direction) {
        case 'left':
            box.rotation.y += rotationSpeed;
            break;
        case 'right':
            box.rotation.y -= rotationSpeed;
            break;
        case 'up':
            box.rotation.x += rotationSpeed;
            break;
        case 'down':
            box.rotation.x -= rotationSpeed;
            break;
        default:
            break;
    }
}

function scaleObject(scaleDirection) {
    // Tốc độ tăng/giảm tỉ lệ
    const scaleSpeed = 0.1;

    // Xác định hướng tăng/giảm tỉ lệ
    switch (scaleDirection) {
        case 'increase':
            box.scale.x += scaleSpeed;
            box.scale.y += scaleSpeed;
            box.scale.z += scaleSpeed;
            break;
        case 'decrease':
            box.scale.x -= scaleSpeed;
            box.scale.y -= scaleSpeed;
            box.scale.z -= scaleSpeed;
            break;
        default:
            break;
    }
}

function translateObject(direction) {
    // Tốc độ tịnh tiến
    const translationSpeed = 0.1;

    // Xác định hướng tịnh tiến
    switch (direction) {
        case 'left':
            box.position.x -= translationSpeed;
            break;
        case 'right':
            box.position.x += translationSpeed;
            break;
        case 'up':
            box.position.y += translationSpeed;
            break;
        case 'down':
            box.position.y -= translationSpeed;
            break;
        default:
            break;
    }
}

function changeCamera() {
    // Lấy giá trị từ các trường input
    const cameraX = parseFloat(document.getElementById('cameraX').value);
    const cameraY = parseFloat(document.getElementById('cameraY').value);
    const cameraZ = parseFloat(document.getElementById('cameraZ').value);
    const lookAtX = parseFloat(document.getElementById('lookAtX').value);
    const lookAtY = parseFloat(document.getElementById('lookAtY').value);
    const lookAtZ = parseFloat(document.getElementById('lookAtZ').value);

    // Thay đổi vị trí của camera
    camera.position.set(cameraX, cameraY, cameraZ);
    
    // Thay đổi điểm nhìn của camera
    camera.lookAt(new THREE.Vector3(lookAtX, lookAtY, lookAtZ));


}


init();