package Camera_Package;
import java.util.*;


class User {
private String username;
private String password;
private double walletBalance;
private List<Camera> rentedCameras;


public User(String username, String password) {
this.username = username;
this.password = password;
this.walletBalance = 0.0;
this.rentedCameras = new ArrayList<>();
}


public String getUsername() {
return username;
}


public String getPassword() {
return password;
}


public double getWalletBalance() {
return walletBalance;
}


public void addToWallet(double amount) {
this.walletBalance += amount;
}


public boolean deductFromWallet(double amount) {
if (walletBalance >= amount) {
walletBalance -= amount;
return true;
}
return false;
}


public List<Camera> getRentedCameras() {
return rentedCameras;
}


public void addRentedCamera(Camera camera) {
rentedCameras.add(camera);
}


public void removeRentedCamera(Camera camera) {
rentedCameras.remove(camera);
}
}
