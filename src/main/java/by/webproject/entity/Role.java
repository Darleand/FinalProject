package by.webproject.entity;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role{

    @Id
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(){
    }

    public Role(String str){
        this.name = str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<by.webproject.entity.User> users) {
        this.users = users;
    }

}
