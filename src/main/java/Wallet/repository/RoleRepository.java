package Wallet.repository;

import Wallet.entity.Role;
import Wallet.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, EnumRole> {
    Optional<Role> findByName(EnumRole name);
}
