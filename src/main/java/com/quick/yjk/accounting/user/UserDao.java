package com.quick.yjk.accounting.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quick.yjk.vo.UserVo;


/**
 * 운용자 정보 Dao
 * @author 배수현
 *
 */
@Repository
public interface UserDao {
	/**
	 * 운용자 리스트 수 조회
	 * @param userVo
	 * @return
	 */
	public int selectUserListCount(UserVo userVo);
	
	/**
	 * 운용자 리스트 조회
	 * @param userVo
	 * @return
	 */
	public List<UserVo> selectUserList(UserVo userVo);
	
	/**
	 * 특정 운용자 조회
	 * @param userVo
	 * @return
	 */
	public UserVo selectOneUser(UserVo userVo);
	
	/**
	 * 중복 아이디 조회
	 * @param userVo
	 * @return
	 */
	public int checkUser(UserVo userVo);
	
	/**
	 * 로그인 처리
	 * @param userVo
	 * @return
	 */
	public UserVo goLogin(UserVo userVo);
	
	/**
	 * 운용자 추가
	 * @param userVo
	 * @return
	 */
	public int inserUser(UserVo userVo);
	
	/**
	 * 운용자 변경
	 * @param userVo
	 * @return
	 */
	public int updateUser(UserVo userVo);
	
	/**
	 * 운용자 패스워드 변경
	 * @param userVo
	 * @return
	 */
	public int updateUserPassword(UserVo userVo);
	
	/**
	 * 운용자 삭제
	 * @param userVo
	 * @return
	 */
	public int deleteUser(UserVo userVo);
}
