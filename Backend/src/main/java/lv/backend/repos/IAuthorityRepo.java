package lv.backend.repos;

import org.springframework.data.repository.CrudRepository;

import lv.backend.models.users.Authorities;

public interface IAuthorityRepo extends CrudRepository <Authorities, Long>{

}
