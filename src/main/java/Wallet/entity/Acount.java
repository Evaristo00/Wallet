package Wallet.entity;

import Wallet.enums.Currencies;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "acount")
public class Acount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull@NotBlank
    @Column(nullable = false)
    private Currencies currency;
    @NotNull@NotBlank
    @Column(nullable = false)
    private Double transactionLimit;

    @NotNull@NotBlank
    @Column(nullable = false)
    private Double balance;

    @NotNull@NotBlank
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @NotNull@NotBlank
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

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
