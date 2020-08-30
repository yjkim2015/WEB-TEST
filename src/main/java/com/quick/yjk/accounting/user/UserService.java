package com.quick.yjk.accounting.user;

import java.util.Date;
import java.util.List;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import com.quick.yjk.vo.UserVo;


/**
 * 운용자 정보관리  Service Interface
 * @author 배수현
 *
 */
public interface UserService {
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
	 * @param sessionUserVo
	 * @param userVo
	 * @throws Exception
	 */
	public void updateUserPassword(UserVo sessionUserVo, UserVo userVo) throws Exception;
	
	/**
	 * 운용자 삭제
	 * @param sessionUserVo
	 * @param userVo
	 * @throws Exception
	 */
	public void deleteUser(UserVo sessionUserVo, UserVo userVo) throws Exception;
	
	
	public void createNewToken(PersistentRememberMeToken token);
	
	public void updateToken(String series, String tokenValue, Date lastUsed);
	
	public UserVo  getTokenForSeries(String seriesId);
	
	public void removeUserTokens(String loginId);
}
