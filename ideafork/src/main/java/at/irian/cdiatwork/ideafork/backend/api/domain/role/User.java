package at.irian.cdiatwork.ideafork.backend.api.domain.role;

import at.irian.cdiatwork.ideafork.backend.api.domain.BaseEntity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement //wouldn't be needed if we use jackson only
public class User extends BaseEntity {
    private static final long serialVersionUID = 621105763803952204L;

    private String nickName;
    private String email;

    private String firstName;
    private String lastName;

    private User() {
        //needed for data-import
    }

    public User(String nickName, String email) {
        this.nickName = nickName;
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*
     * generated
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (!nickName.equals(user.nickName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nickName.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
