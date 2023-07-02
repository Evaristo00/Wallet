package Wallet.entity;

import Wallet.enums.EnumRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    private EnumRole name;

    private String descripcion;
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<User> users;

    @Column(name = "creation_date",nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date",nullable = false)
    private LocalDateTime updateDate;

    public Role(EnumRole role) {
        this.name = role;
    }


    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.updateDate = this.creationDate;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }

}
