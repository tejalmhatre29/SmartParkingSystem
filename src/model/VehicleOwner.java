package model;

public class VehicleOwner extends User {

    public VehicleOwner(int userId, String name, String email, String password) {
        super(userId, name, email, password);
    }

    @Override
    public String getRole() {
        return "OWNER";
    }
}