import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Animal {
    private String name;
    private String description;
    private String sound;
    
    public Animal(){

    }
    public Animal(String name, String description, String sound) {
        this.name = name;
        this.description = description;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSound() {
        return sound;
    }

    public void makeSound() {
        System.out.println(sound);
    }
}

class Dog extends Animal {
    public Dog() {
        super("Dog", "A dog has sharp teeth so that it can eat flesh very easily, it has four legs, two ears, two eyes, a tail, a mouth, and a nose. It is a ", "Gau gau");
    }

    public void run() {
        System.out.println("The dog is running");
    }
}

class Cat extends Animal {
    public Cat() {
        super("Cat", "Cats are domestic animals. They are small in size. Their bodies are covered with smooth fur.", "Meow Meow");
    }

    public void run() {
        System.out.println("The cat is running");
    }
}

class Tiger extends Animal {
    public Tiger() {
        super("Tiger", "Tigers are powerful hunters with sharp teeth, strong jaws and agile bodies.", "Grw Grw");
    }

    public void run() {
        System.out.println("The tiger is running");
    }

    public void growl() {
        System.out.println("The tiger is growling");
    }
}

class Dolphin extends Animal {
    public Dolphin() {
        super("Dolphin", "Dolphins have smooth skin, flippers, and a dorsal fin.", "Eeeee");
    }

    public void swim() {
        System.out.println("The dolphin is swimming");
    }
}

class Crocodile extends Animal {
    public Crocodile() {
        super("Crocodile", "Crocodiles have powerful jaws with many conical teeth and short legs with clawed webbed toes.", "Hmi Hmi");
    }

    public void swim() {
        System.out.println("The crocodile is swimming");
    }

    public void crawl() {
        System.out.println("The crocodile is crawling");
    }
}



class Hornbill extends Animal {
    public Hornbill(){
        super("Hornbull", "Hornbills are omnivorous birds, eating fruit, insects and small animals. They cannot swallow food caught at the tip of the beak as their tongues are too short", "gok gok");
    }
    public void fly(){
        System.out.println("The Hornbill is flying");
    }

}

class Peafowl extends Animal{
    public Peafowl(){
        super("Peafowl", "a large bird, the male of which has very long tail feathers that it can spread out to show bright colours and patterns", "hiu hiu");
    }

    public void fly(){
        System.out.println("The Peafowl is flying");
    }
    public void run(){
        System.out.println("The Peafowl is running");
    }
    public void swim(){
        System.out.println("The Peafowl is swimming");
    }

}


class Cage {
    private String code;
    private List<Animal> animals;

    public Cage(String code) {
        this.code = code;
        this.animals = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(String name) {
        for (Animal animal : animals){
            if (animal.getName().equals(name)){
                animals.remove(animal);
                return;
            }
            System.out.println("There is no" + name + " in this cage");
        }
    }

    public void displayCage(){
        System.out.println("Cage: " + code + ": ");
        for(Animal animal : animals){
            System.out.println("- " + animal.getName() + " (" + animal.getDescription() + "): " + animal.getSound());
        }
    }
}

class Zoo {
    private ArrayList<Cage> cages;
    
    // constructor
    public Zoo() {
        cages = new ArrayList<Cage>();
    }
    
    // add a new cage to the zoo
    public void addCage(String cageId) {
        cages.add(new Cage(cageId));
    }
    
    // remove a cage from the zoo
    public void removeCage(String cageId) {
        for (Cage cage : cages) {
            if (cage.getCode().equals(cageId)) {
                cages.remove(cage);
                break;
            }
        }
    }
    
    // add an animal to a cage
    public void addAnimal(String cageId, Animal animal) {
        for (Cage cage : cages) {
            if (cage.getCode().equals(cageId)) {
                cage.addAnimal(animal);
                break;
            }
        }
    }
    
    // remove an animal from a cage
    public void removeAnimal(String cageId, String name) {
        for (Cage cage : cages) {
            if (cage.getCode().equals(cageId)) {
                cage.removeAnimal(name);
                break;
            }
        }
    }
    
    public void displayCage(String cageId) {
        for (Cage cage : cages) {
            if (cage.getCode().equals(cageId)) {
                System.out.println("Cage code: " + cage.getCode());
            System.out.println("Animals in the cage:");
            for (Animal animal : cage.getAnimals()) {
                System.out.println("- " + animal.getName() + ": " + animal.getDescription());
                System.out.println("   Sound: " + animal.getSound());
                if (animal instanceof Dog) {
                    System.out.println("   Run");
                } else if (animal instanceof Cat) {
                    System.out.println("   Run");
                } else if (animal instanceof Tiger) {
                    System.out.println("   Run, growl");
                } else if (animal instanceof Dolphin) {
                    System.out.println("   Swim");
                } else if (animal instanceof Crocodile) {
                    System.out.println("   Swim, crawl");
                } else if (animal instanceof Hornbill) {
                    System.out.println("   Fly");
                } else if (animal instanceof Peafowl) {
                    System.out.println("   Fly, run, swim");
                }
            }
            return;
            }
        }
    }
    
    // display the information of all cages in the zoo
    public void displayZoo() {
        System.out.println("Zoo:");
        for (Cage cage : cages) {
            cage.displayCage();
        }
    }
}




public class Problem_3{
    // main method to run the program
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Add a new cage");
            System.out.println("2. Remove a cage");
            System.out.println("3. Add an animal to a cage");
            System.out.println("4. Remove an animal from a cage");
            System.out.println("5. Display the information of a cage");
            System.out.println("6. Display the information of the whole zoo");
            System.out.println("7. Quit");
            System.out.print("Enter your choice (1-7): ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter the cage ID: ");
                    String cageId = scanner.next();
                    zoo.addCage(cageId);
                    break;
                    
                case 2:
                    System.out.print("Enter the cage ID: ");
                    cageId = scanner.next();
                    zoo.removeCage(cageId);
                    break;
                    
                case 3:
                    System.out.print("Enter the cage ID: ");
                    cageId = scanner.next();
                    System.out.print("Enter the animal type (1 = Dog, 2 = Cat, 3 = Tiger, 4 = Dolphin, 5 = Crocodile, 6 = Hornbill, 7 = Kingfisher): ");
                    int animalType = scanner.nextInt();
                    Animal animal = null;
                    
                    switch (animalType) {
                        case 1:
                            animal = new Dog();
                            break;
                        case 2:
                            animal = new Cat();
                            break;
                        case 3:
                            animal = new Tiger();
                            break;
                        case 4:
                            animal = new Dolphin();
                            break;
                        case 5:
                            animal = new Crocodile();
                            break;
                        case 6:
                            animal = new Hornbill();
                            break;
                        case 7:
                            animal = new Peafowl();
                            break;
                        default:
                            System.out.println("Invalid animal type");
                            break;
                    }
                    
                    if (animal != null) {
                        zoo.addAnimal(cageId, animal);
                    }
                    
                    break;
                case 4:
                    System.out.print("Enter the cage ID: ");
                    cageId = scanner.next();
                    System.out.print("Enter the animal name: ");
                    String animalName = scanner.next();
                    zoo.removeAnimal(cageId, animalName);
                    break;
                case 5:
                    System.out.print("Enter the cage ID: ");
                    cageId = scanner.next();
                    zoo.displayCage(cageId);
                    break;
                case 6:
                    zoo.displayZoo();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
                    break;
                    
            }
        }
    }
}

