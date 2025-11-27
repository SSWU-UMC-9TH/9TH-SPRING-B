package spring.umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.store.entity.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long>
{
    Optional<Store> findById(Long storeId);

}
