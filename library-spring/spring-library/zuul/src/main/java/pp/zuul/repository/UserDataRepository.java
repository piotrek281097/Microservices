package pp.zuul.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pp.zuul.domain.UserData;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserDataRepository extends CrudRepository<UserData, Long> {

    Optional<UserData> findById(@NonNull @NotNull Long aLong);

    Optional<UserData> findByEmail(@NonNull @NotNull String email);

    Optional<UserData> findByUsername(@NonNull @NotNull String username);

}
