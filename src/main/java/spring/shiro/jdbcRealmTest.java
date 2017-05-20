package spring.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class jdbcRealmTest {

	
	static Logger logger = LoggerFactory.getLogger(jdbcRealmTest.class);
	public static void main(String[] args) {
		String username = "zhangsan";
		String password = "123456";
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		logger.info("开始登录业务{}  密码{}",username,password);
		currentUser.logout();
		logger.error("退出登录");
	}
}
