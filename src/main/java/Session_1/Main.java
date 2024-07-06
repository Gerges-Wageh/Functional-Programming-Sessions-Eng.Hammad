package Session_1;

public class Main {
    public static void main(String[] args) {

        // OOP example execution
        // You can notice that th execution of getZ() depends on the execution of updateX() and updateY()
        // Which makes the testing process much painful

        OOPExample obj = new OOPExample(5, 10);
        System.out.println(obj.getZ(3));
        obj.updateX(4);
        System.out.println(obj.getZ(3));
    }
}
