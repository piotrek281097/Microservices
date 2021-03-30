package gateway.repository;

import edu.umd.cs.findbugs.annotations.NonNull;
import gateway.domain.UserData;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserDataRepository extends CrudRepository<UserData, Long> {
    @NonNull
    @Override
    Optional<UserData> findById(@NonNull @NotNull Long aLong);

    Optional<UserData> findByEmail(@NonNull @NotNull String email);

    Optional<UserData> findByUsername(@NonNull @NotNull String username);

}
