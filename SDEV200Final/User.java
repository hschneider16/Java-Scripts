public abstract class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public void login(String username, String password) {
        System.out.println("Logging in as: " + username);
    }
}
