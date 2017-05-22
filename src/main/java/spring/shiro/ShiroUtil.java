package spring.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ShiroUtil.class);
	
	public static Subject subject = getSubject();
	
	public static Subject getSubject(){
		return subject == null ? getInitSubject():subject;
	}
	
    private static Subject getInitSubject(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro_permission.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		return SecurityUtils.getSubject();
    }
    
    public static void login(String username,String password){
    	logger.info("开始登录业务{}  密码{}",username,password);
    	UsernamePasswordToken token = new UsernamePasswordToken(username,password);
    	try {
    		subject.login(token);
    		logger.info("登录成功");
		} catch (Exception e) {
			logger.error("登录失败",e);
		}
    }
    
    public static void logout(){
    	subject.logout();
    }
    
    public static boolean checkRole(String role){
    	return subject.hasRole(role);
    }
    
    public static boolean isLoginSuccess(){
    	return subject.isAuthenticated();
    }    
}
