import * as THREE from './three.module.js';

function init() {
    var scene = new THREE.Scene();

    var box = getBox(1,1,1);
    var plane = getPlane(6);
    var teaPot = getTeaPot(0.5);
    box.position.y = box.geometry.parameters.height / 2;
    teaPot.position.y = 0.5;
    teaPot.position.x = plane.geometry.parameters.width / 4;
    teaPot.position.z = plane.geometry.parameters.width / 3;
    plane.rotation.x = Math.PI / 2;

    var sphere = getSphere(0.5); 
    sphere.position.y = sphere.geometry.parameters.radius;
    sphere.position.x = -plane.geometry.parameters.width / 4;
    sphere.position.z = plane.geometry.parameters.width / 3;

    scene.add(sphere);
    scene.add(box);
    scene.add(plane);
    scene.add(teaPot);
    var camera = new THREE.PerspectiveCamera(
        45,
        window.innerWidth / window.innerHeight,
        1,
        1000
    );

    camera.position.x = 1;
    camera.position.y = 2;
    camera.position.z = 5;

    camera.lookAt(new THREE.Vector3(0,0,0));

    var renderer = new THREE.WebGLRenderer();
    renderer.setSize(window.innerWidth, window.innerHeight);

    document.getElementById("webgl").appendChild(renderer.domElement);
    renderer.render(
        scene,
        camera
    );
}

function getBox(w, h, d){
    var geometry = new THREE.BoxGeometry(w,h,d);
    var material = new THREE.MeshBasicMaterial({
        color: 0x00ff00
    });

    var mesh = new THREE.Mesh(
        geometry,
        material
    );
    return mesh;
}

function getTeaPot(size) {
    var geometry = new THREE.TeapotGeometry(size);
    var material = new THREE.MeshBasicMaterial({
        color: 0xffffff,
    });

    var mesh = new THREE.Mesh(
        geometry,
        material
    );
    return mesh;
}

function get(size) {
    var geometry = new THREE.Tea
    var material = new THREE.MeshBasicMaterial({
        color: 0xff0000,
        side: THREE.DoubleSide
    });

    var mesh = new THREE.Mesh(
        geometry,
        material
    );
    return mesh;
}

function getSphere(radius) {
    var geometry = new THREE.SphereGeometry(radius, 32, 32); // Adjust the segments for smoother sphere
    var material = new THREE.MeshBasicMaterial({
        color: 0x000fff
    });

    var mesh = new THREE.Mesh(geometry, material);
    return mesh;
}

function getPlane(size) {
    var geometry = new THREE.PlaneGeometry(size, size);
    var material = new THREE.MeshBasicMaterial({
        color: 0xff0000,
        side: THREE.DoubleSide
    });

    var mesh = new THREE.Mesh(
        geometry,
        material
    );
    return mesh;
}
init();