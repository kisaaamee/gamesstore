package pl.cd.project.gamesstore.model.pg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.cd.project.gamesstore.model.common.TypeOfUserEnum;
import pl.cd.project.gamesstore.model.common.UserPermission;

import javax.persistence.*;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
public class UserApp extends BaseEntity implements UserDetails {

    private UUID user_uuid;
    private boolean active;
    @ElementCollection(targetClass = TypeOfUserEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_typeOfUser",
            joinColumns = @JoinColumn(name = "userApp_id"))
    @Enumerated(EnumType.STRING)
    private Set<TypeOfUserEnum> typeOfUser = new HashSet<>();
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    private String password;
    @ElementCollection(targetClass = UserPermission.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_permission",
    joinColumns = @JoinColumn(name = "userApp_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserPermission> permissions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<GameBox> products = new ArrayList<>();
    @OneToOne(cascade = CascadeType.REMOVE)
    private ShoppingBucket bucket;

    @Override
    public int hashCode() {return 1341515;}

    public void generateUuid() { this.user_uuid = UUID.randomUUID(); }

    //Secutiry
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return typeOfUser;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public boolean isAdmin() {
        return typeOfUser.contains(TypeOfUserEnum.ADMIN);
    }

    public interface Existing {}

    public interface New {}

}
