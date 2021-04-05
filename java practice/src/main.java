import java.io.FileReader;

class Animal{
    protected String name;
    public void display(){
        System.out.println("I can eat");
    }
}

class Dog extends Animal{
    public void get(){
        System.out.println("My name is "+name);
    }
}

class main {
    public static void main(String[] args){
      Dog lab= new Dog();
      lab.name= "Rohu";
      lab.display();

      lab.get();

    }
}
