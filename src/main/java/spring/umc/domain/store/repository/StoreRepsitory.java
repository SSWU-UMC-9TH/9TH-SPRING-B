package spring.umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.store.entity.Store;

public interface StoreRepsitory extends JpaRepository<Store, Long> {

}
