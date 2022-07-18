package com.tes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.Settings;

public interface SettingRepository extends JpaRepository<Settings, Long>
{

	String query = "SELECT introductory_video_status FROM settings where user_id= :usersId";

	@Query(value = query, nativeQuery = true)
	public String findIntroductoryStatus(@Param("usersId") int usersId);// not used

	@Query("SELECT s FROM Settings s LEFT JOIN s.users u WHERE u.usersId =:usersId")
	public Settings findSettingData(@Param("usersId") int usersId);

	@Transactional
	@Modifying
	@Query("UPDATE Settings s SET s.introductoryVideoStatus=:status WHERE s.users.usersId= :userId")
	public void updateIntroStatus(@Param("status") String status, @Param("userId") int userId);

	@Transactional
	@Modifying
	@Query("UPDATE Settings s SET s.backgroundImage= :image, textColor= :color WHERE s.users.usersId= :userId")
	public void updateBkImageAndColor(@Param("image") int image, @Param("color") String color, @Param("userId") int userId);

	@Override
	public Settings save(Settings settings);

}
