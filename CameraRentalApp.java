package Camera_Package;
import java.util.*;

public class CameraRentalApp {
    private static List<Camera> cameras = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCameras();
        initializeUsers();
        System.out.println("=========================================");
        System.out.println("| WELCOME TO CAMERA RENTAL APP |");
        System.out.println("=========================================");
        login();
    }

    private static void initializeCameras() {
        cameras.add(new Camera(1, "Samsung", "DS123", 500.0, "Available"));
        cameras.add(new Camera(2, "Sony", "HD214", 500.0, "Available"));
        cameras.add(new Camera(3, "Panasonic", "XL", 500.0, "Available"));
        cameras.add(new Camera(4, "Canon", "XLR", 500.0, "Available"));
        cameras.add(new Camera(5, "Fujitsu", "J5", 500.0, "Available"));
        cameras.add(new Camera(6, "Sony", "HD226", 500.0, "Available"));
        cameras.add(new Camera(7, "Samsung", "L123", 500.0, "Available"));
        cameras.add(new Camera(8, "LG", "L123", 500.0, "Available"));
        cameras.add(new Camera(9, "Canon", "XPL", 500.0, "Available"));
        cameras.add(new Camera(10, "Chroma", "CT", 500.0, "Available"));
        cameras.add(new Camera(11, "Canon", "Digital", 123.0, "Available"));
        cameras.add(new Camera(12, "Something", "some", 200.0, "Available"));
        cameras.add(new Camera(13, "Canon", "Another", 123.0, "Available"));
        cameras.add(new Camera(14, "NIKON", "DSLR-D7500", 500.0, "Available"));
        cameras.add(new Camera(15, "Sony", "DSLR12", 200.0, "Available"));
        cameras.add(new Camera(17, "Samsung", "SM123", 200.0, "Available"));
        cameras.add(new Camera(19, "SONY", "SONY1234", 123.0, "Available"));
        cameras.add(new Camera(20, "canon", "5050", 25000.0, "Available"));
        cameras.add(new Camera(21, "nikon", "2030", 500.0, "Available"));
    }

    private static void initializeUsers() {
        users.add(new User("admin", "admin123"));
    }

    private static void login() {
        System.out.println("PLEASE LOGIN TO CONTINUE -");
        System.out.print("USERNAME - ");
        String username = scanner.nextLine();
        System.out.print("PASSWORD - ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("\nLOGIN SUCCESSFUL!");
                mainMenu();
                return;
            }
        }

        System.out.println("\nINVALID CREDENTIALS! PLEASE TRY AGAIN.\n");
        login();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n1. MY CAMERA");
            System.out.println("2. RENT A CAMERA");
            System.out.println("3. VIEW ALL CAMERAS");
            System.out.println("4. MY WALLET");
            System.out.println("5. EXIT");
            System.out.print("\nENTER YOUR CHOICE: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nINVALID INPUT! PLEASE ENTER A NUMBER.");
                continue;
            }

            switch (choice) {
                case 1:
                    myCameraMenu();
                    break;
                case 2:
                    rentCamera();
                    break;
                case 3:
                    viewAllCameras();
                    break;
                case 4:
                    myWallet();
                    break;
                case 5:
                    System.out.println("\nTHANK YOU FOR USING CAMERA RENTAL APP!");
                    System.exit(0);
                default:
                    System.out.println("\nINVALID CHOICE! PLEASE TRY AGAIN.");
            }
        }
    }

    private static void myCameraMenu() {
        while (true) {
            System.out.println("\n1. ADD");
            System.out.println("2. REMOVE");
            System.out.println("3. VIEW MY CAMERAS");
            System.out.println("4. GO TO PREVIOUS MENU");
            System.out.print("\nENTER YOUR CHOICE: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nINVALID INPUT! PLEASE ENTER A NUMBER.");
                continue;
            }

            switch (choice) {
                case 1:
                    addCamera();
                    break;
                case 2:
                    removeCamera();
                    break;
                case 3:
                    viewMyCameras();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nINVALID CHOICE! PLEASE TRY AGAIN.");
            }
        }
    }

    private static void addCamera() {
        System.out.print("\nENTER THE CAMERA BRAND - ");
        String brand = scanner.nextLine();
        System.out.print("ENTER THE MODEL - ");
        String model = scanner.nextLine();
        System.out.print("ENTER THE PER DAY PRICE (INR) - ");
        double price;
        try {
            price = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("INVALID AMOUNT. CAMERA NOT ADDED.");
            return;
        }

        int newId = cameras.stream().mapToInt(Camera::getCameraId).max().orElse(0) + 1;
        Camera newCamera = new Camera(newId, brand, model, price, "Available");
        cameras.add(newCamera);

        System.out.println("YOUR CAMERA HAS BEEN SUCCESSFULLY ADDED TO THE LIST.");
    }

    private static void removeCamera() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s", 
            "CAMERA ID", "BRAND", "MODEL", "PRICE(PER DAY)", "STATUS"));
        System.out.println("=".repeat(80));

        for (Camera camera : cameras) {
            System.out.println(camera);
        }
        System.out.println("=".repeat(80));

        System.out.print("\nENTER THE CAMERA ID TO REMOVE - ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("INVALID ID.");
            return;
        }

        Camera toRemove = null;
        for (Camera camera : cameras) {
            if (camera.getCameraId() == id) {
                toRemove = camera;
                break;
            }
        }

        if (toRemove != null) {
            cameras.remove(toRemove);
            System.out.println("CAMERA SUCCESSFULLY REMOVED FROM THE LIST.");
        } else {
            System.out.println("CAMERA NOT FOUND!");
        }
    }

    private static void viewMyCameras() {
        List<Camera> myCameras = new ArrayList<>();
        for (Camera camera : cameras) {
            if (camera.getStatus().equals("Rented")) {
                myCameras.add(camera);
            }
        }

        if (myCameras.isEmpty()) {
            System.out.println("\nYOU HAVE NOT RENTED ANY CAMERAS YET.");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s", 
            "CAMERA ID", "BRAND", "MODEL", "PRICE(PER DAY)", "STATUS"));
        System.out.println("=".repeat(80));

        for (Camera camera : myCameras) {
            System.out.println(camera);
        }
        System.out.println("=".repeat(80));
    }

    private static void rentCamera() {
        System.out.println("\nFOLLOWING IS THE LIST OF AVAILABLE CAMERA(S) -");
        System.out.println("=".repeat(80));
        System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s", 
            "CAMERA ID", "BRAND", "MODEL", "PRICE(PER DAY)", "STATUS"));
        System.out.println("=".repeat(80));

        List<Camera> availableCameras = new ArrayList<>();
        for (Camera camera : cameras) {
            if (camera.getStatus().equals("Available")) {
                availableCameras.add(camera);
                System.out.println(camera);
            }
        }
        System.out.println("=".repeat(80));

        if (availableCameras.isEmpty()) {
            System.out.println("\nNo Data Present at This Moment");
            return;
        }

        System.out.print("\nENTER THE CAMERA ID YOU WANT TO RENT - ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("INVALID ID.");
            return;
        }

        Camera selectedCamera = null;
        for (Camera camera : availableCameras) {
            if (camera.getCameraId() == id) {
                selectedCamera = camera;
                break;
            }
        }

        if (selectedCamera == null) {
            System.out.println("\nINVALID CAMERA ID!");
            return;
        }

        if (currentUser.getWalletBalance() < selectedCamera.getPricePerDay()) {
            System.out.println("\nERROR: TRANSACTION FAILED DUE TO INSUFFICIENT WALLET BALANCE. PLEASE DEPOSIT THE AMOUNT TO YOUR WALLET.");
            return;
        }

        if (currentUser.deductFromWallet(selectedCamera.getPricePerDay())) {
            selectedCamera.setStatus("Rented");
            currentUser.addRentedCamera(selectedCamera);
            System.out.println("\nYOUR TRANSACTION FOR CAMERA - " + selectedCamera.getBrand() + " " + 
                selectedCamera.getModel() + " with rent INR." + selectedCamera.getPricePerDay() + 
                " HAS SUCCESSFULLY COMPLETED.");
        }
    }

    private static void viewAllCameras() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s", 
            "CAMERA ID", "BRAND", "MODEL", "PRICE(PER DAY)", "STATUS"));
        System.out.println("=".repeat(80));

        for (Camera camera : cameras) {
            System.out.println(camera);
        }
        System.out.println("=".repeat(80));
    }

    private static void myWallet() {
        System.out.println("\nYOUR CURRENT WALLET BALANCE IS - INR." + currentUser.getWalletBalance());
        System.out.print("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET?(1.YES 2.NO) - ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("INVALID CHOICE.");
            return;
        }

        if (choice == 1) {
            System.out.print("ENTER THE AMOUNT (INR) - ");
            double amount;
            try {
                amount = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("INVALID AMOUNT.");
                return;
            }

            currentUser.addToWallet(amount);
            System.out.println("AMOUNT DEPOSITED SUCCESSFULLY. CURRENT WALLET BALANCE - INR." + 
                currentUser.getWalletBalance());
        }
    }
}
