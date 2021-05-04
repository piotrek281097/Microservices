package pp.users.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pp.users.domain.UserData;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserDataRepository extends CrudRepository<UserData,Long> {

    Optional<UserData> findByUsername(@NonNull @NotNull String username);

    Optional<UserData> findByEmail(String email);
}
