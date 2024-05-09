package lv.backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import lv.backend.dto.UserDto;
import lv.backend.models.users.User;

public interface IUserRepo extends JpaRepository <User, Long> {

	User findByUsername(String username);
	
	User save(UserDto userDto);

}
