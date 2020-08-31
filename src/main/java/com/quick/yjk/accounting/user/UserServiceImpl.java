package com.quick.yjk.accounting.user;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Service;

import com.quick.yjk.common.constants.RoleType;
import com.quick.yjk.common.exception.CustomException;
import com.quick.yjk.common.utils.EncryptionUtil;
import com.quick.yjk.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	/** 
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * 운용자 정보 Dao
	 */
    private final transient UserDao userDao;

    /**
     * 생성자 
     * @param userDao
     */
    @Autowired
    public UserServiceImpl(final UserDao userDao) {
    	this.userDao = userDao;
    }

    @Override
	public int selectUserListCount(final UserVo userVo) {
		return userDao.selectUserListCount(userVo);
	}
    
	@Override
	public List<UserVo> selectUserList(final UserVo userVo) {
		return userDao.selectUserList(userVo);
	}

	@Override
	public UserVo selectOneUser(final UserVo userVo) {
		return userDao.selectOneUser(userVo);
	}
	
	@Override
	public int checkUser(final UserVo userVo) {
		return userDao.checkUser(userVo);
	}
	
	@Override
	public UserVo goLogin(final UserVo userVo) {
		return userDao.goLogin(userVo);
	}

	@Override
	public int inserUser(UserVo userVo) {
		try {
			userVo.setPasswd(EncryptionUtil.encryptSHA512(userVo.getPasswd()));
			userVo.setPhone(EncryptionUtil.encryptSHA512(userVo.getPhone()));
			userVo.setRole(RoleType.warehouse);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return userDao.inserUser(userVo);
	}

	@Override
	public int updateUser(final UserVo userVo) {
		return userDao.updateUser(userVo);
	}

	@Override
	public void updateUserPassword(final UserVo sessionUserVo, final UserVo userVo) throws Exception {
		userVo.setPasswd(EncryptionUtil.encryptSHA512(userVo.getPasswd()));
		
		try {
			userDao.updateUserPassword(userVo);
		}
		catch (Exception ex) {
			
			throw ex;
		}
	}
	
	@Override
	public void deleteUser(final UserVo sessionUserVo, final UserVo userVo) throws Exception {

		try {
			if (!"root".equals(sessionUserVo.getLoginId())) {
				throw new CustomException("root가 아닌 계정은 인증정보를 삭제할 수 없습니다.");
			}
			
			final String sessionUserId 	= userVo.getLoginId();			
			
			if ("root".equals(sessionUserId)) {
				throw new CustomException("root 계정은 삭제할 수 없습니다.");
    		}
			
			userDao.deleteUser(userVo);

		}
		catch (Exception ex) {
			
			throw ex;
		}
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		userDao.createNewToken(token);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("series",series);
		paramMap.put("tokenValue",tokenValue);
		paramMap.put("lastUsed",lastUsed);

		userDao.updateToken(paramMap);
	}

	@Override
	public UserVo getTokenForSeries(String series) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("series",series);
		return userDao.getTokenForSeries(paramMap);
	}

	@Override
	public void removeUserTokens(String loginId, String series) {
		Map<String,Object> paramMap = new HashMap<>();
		if ( loginId != null ) {
			paramMap.put("username",loginId);
		}
		if ( series != null ) {
			paramMap.put("series",series);
		}
		userDao.removeUserTokens(paramMap);
	}
	
}
