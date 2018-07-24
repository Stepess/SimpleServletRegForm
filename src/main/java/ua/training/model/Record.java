package ua.training.model;

import java.util.Map;

public class Record {

    private static int count = 0;
    private int id;
    private String login;
    private String firstName;
    private String email;

    public Record() {
        this.id = count++;
        this.login = "";
        this.firstName = "";
        this.email = "";
    }

    public Record(String login, String firstName, String email) {
        this.login = login;
        this.firstName = firstName;
        this.email = email;
        this.id = count++;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setAll(Map<String, String> data){
        this.login = data.get("login");
        this.firstName = data.get("firstName");
        this.email = data.get("email");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Record record = (Record)obj;
        return ((record.login!=null) && login.equals(record.login)) ||
                ((record.email!=null) && email.equals(record.email));
    }
}
