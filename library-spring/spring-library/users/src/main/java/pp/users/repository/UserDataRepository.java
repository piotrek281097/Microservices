package pp.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pp.users.domain.UserData;

@Repository
public interface UserDataRepository extends CrudRepository<UserData,Long> {

}
