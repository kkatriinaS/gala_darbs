package lv.backend.repos;

import org.springframework.data.repository.CrudRepository;

import lv.backend.models.users.User;

public interface IUserRepo extends CrudRepository <User, Long> {

}
