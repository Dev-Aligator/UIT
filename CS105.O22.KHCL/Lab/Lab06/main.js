import * as THREE from './lib/three.module.js'
import * as dat from './lib/dat.gui.module.js';
import { OrbitControls } from './lib/OrbitControls.js';

function init() {
    var scene = new THREE.Scene();
    var gui = new dat.GUI();

    // initalize objects
    var sphereMaterial = getMaterial('standard', 'rgb(255, 255, 255)');
    var sphere = getSphere(sphereMaterial, 1, 24);

    var planeMaterial = getMaterial('standard', 'rgb(255, 255, 255)');
    var plane = getPlane(planeMaterial, 30);

    var lightLeft = getSpotLight(1, 'rgb(255, 220, 180)');
    var lightRight = getSpotLight(1, 'rgb(255, 220, 180)');

    // manipulate objects
    sphere.position.y = sphere.geometry.parameters.radius;
    plane.rotation.x = Math.PI/2;

    lightLeft.position.x = -5;
    lightLeft.position.y = 2;
    lightLeft.position.z = -4;

    lightRight.position.x = 5;
    lightRight.position.y = 2;
    lightRight.position.z = -4;

    // manipulate materials
    // load the cube map
    var path = 'assets/cubemap/';
    var format = '.png';
    var urls = [
        path + 'px' + format, path + 'nx' + format,
        path + 'py' + format, path + 'ny' + format,
        path + 'pz' + format, path + 'nz' + format
    ];
    var reflectionCube = new THREE.CubeTextureLoader().load(urls);
    reflectionCube.format = THREE.RGBAFormat;

    scene.background = reflectionCube;

    var loader = new THREE.TextureLoader();
    planeMaterial.map = loader.load('./assets/textures/concrete.jpg');
    planeMaterial.bumpMap = loader.load('./assets/textures/concrete.jpg');
    planeMaterial.roughnessMap = loader.load('./assets/textures/concrete.jpg');
    planeMaterial.bumpScale = 0.01;
    planeMaterial.metalness = 0.1;
    planeMaterial.roughness = 0.7;
    planeMaterial.envMap = reflectionCube;
    sphereMaterial.roughnessMap = loader.load('./assets/textures/fingerprints.jpg');
    sphereMaterial.envMap = reflectionCube;

    var maps = ['map', 'bumpMap', 'roughnessMap'];
    maps.forEach(function(mapName) {
        var texture = planeMaterial[mapName];
        texture.wrapS = THREE.RepeatWrapping;
        texture.wrapT = THREE.RepeatWrapping;
        texture.repeat.set(15, 15);
    });

    // dat.gui
    var folder1 = gui.addFolder('light_1');
    folder1.add(lightLeft, 'intensity', 0, 10);
    folder1.add(lightLeft.position, 'x', -5, 15);
    folder1.add(lightLeft.position, 'y', -5, 15);
    folder1.add(lightLeft.position, 'z', -5, 15);

    var folder2 = gui.addFolder('light_2');
    folder2.add(lightRight, 'intensity', 0, 10);
    folder2.add(lightRight.position, 'x', -5, 15);
    folder2.add(lightRight.position, 'y', -5, 15);
    folder2.add(lightRight.position, 'z', -5, 15);

    var folder3 = gui.addFolder('materials');
    folder3.add(sphereMaterial, 'roughness', 0, 1);
    folder3.add(planeMaterial, 'roughness', 0, 1);
    folder3.add(sphereMaterial, 'metalness', 0, 1);
    folder3.add(planeMaterial, 'metalness', 0, 1);
    folder3.open();

    // add objects to the scene
    scene.add(sphere);
    scene.add(plane);
    scene.add(lightLeft);
    scene.add(lightRight);

    // camera
    var camera = new THREE.PerspectiveCamera(
        45,
        window.innerWidth/window.innerHeight,
        1,
        1000
    );

    camera.position.x = 1;
    camera.position.y = 2;
    camera.position.z = 5;

    camera.lookAt(new THREE.Vector3(0, 0, 0));

    var renderer = new THREE.WebGLRenderer();
    renderer.shadowMap.enabled = true;
    renderer.setSize(window.innerWidth, window.innerHeight);
    renderer.setClearColor('rgb(120, 120, 120)');
    document.getElementById('webgl').appendChild(renderer.domElement);
    
    var controls = new OrbitControls(camera, renderer.domElement);

    update(renderer, scene, camera, controls);

    return scene;
}

function getBox(w, h, d) {
    var geometry = new THREE.BoxGeometry(w, h, d);
    var material = new THREE.MeshPhongMaterial({
        color: 'rgb(120, 120, 120)'
    });
    var mesh = new THREE.Mesh(
        geometry,
        material
    );
    mesh.castShadow = true;

    return mesh;
}

function getSphere(material, size, segments) {
    var geometry = new THREE.SphereGeometry(size, segments, segments);
    var obj = new THREE.Mesh(geometry, material);
    obj.castShadow = true;

    return obj;
}

function getMaterial(type, color) {
    var selectedMaterial;
    var materialOptions = {
        color: color == undefined ? 'rgb(255, 255, 255)' : color,
    };

    switch (type) {
        case 'basic':
            selectedMaterial = new THREE.MeshBasicMaterial(materialOptions);
            break;
        case 'lambert':
            selectedMaterial = new THREE.MeshLambertMaterial(materialOptions);
            break;
        case 'phong':
            selectedMaterial = new THREE.MeshPhongMaterial(materialOptions);
            break;
        case 'standard':
            selectedMaterial = new THREE.MeshStandardMaterial(materialOptions);
            break;
        default:
            selectedMaterial = new THREE.MeshBasicMaterial(materialOptions);
            break;
    }

    return selectedMaterial;
}

function getSpotLight(intensity, color) {
    color = color === undefined ? 'rgb(255, 255, 255)' : color;
    var light = new THREE.SpotLight(color, intensity);
    light.castShadow = true;
    light.penumbra = 0.5;

    //Set up shadow properties for the light
    light.shadow.mapSize.width = 2048;
    light.shadow.mapSize.height = 2048;
    light.shadow.bias = 0.001;

    return light;
}

function getPlane(material, size) {
    var geometry = new THREE.PlaneGeometry(size, size);
    material.side = THREE.DoubleSide;
    var obj = new THREE.Mesh(geometry, material);
    obj.receiveShadow = true;

    return obj;
}

function getPointLight(intensity) {
    var light = new THREE.PointLight(0xffffff, intensity);
    light.castShadow = true;

    return light;
}

function update(renderer, scene, camera, controls) {
    renderer.render(
        scene,
        camera
    );

    controls.update();

    requestAnimationFrame(function() {
        update(renderer, scene, camera, controls);
    })
}

var scene = init();