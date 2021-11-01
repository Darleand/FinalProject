package by.webproject.entity;

import java.util.Set;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "role")
    private Set<User> users;

    public Role(){
    }

    public Role(Long id){
        this.id = id;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String getAuthority() {
        return null;
    }
}
