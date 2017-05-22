package spring.shiro;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class jdbcRealmTest {
	
	static Logger logger = LoggerFactory.getLogger(jdbcRealmTest.class);
	public static void main(String[] args) {
		/**登录成功 注入subject登录信息*/
		String username = "zhangsan";
		String password = "123456";
		ShiroUtil.login(username, password);
		boolean authenticated = ShiroUtil.isLoginSuccess();
		System.err.println("是否登录："+authenticated);
		
		
		/**验证角色*/
		System.err.println(ShiroUtil.checkRole("role1"));
		boolean[] roles = ShiroUtil.subject.hasRoles(Arrays.asList("role1","role2","role3"));
		System.err.println(Arrays.toString(roles));
//		ShiroUtil.subject.checkRoles("role1","role2");
		
		
		/**验证权限控制*/
		logger.info("验证权限控制");
		boolean[] permitted = ShiroUtil.subject.isPermitted("user:select","student:select","student:add");
		System.err.println(Arrays.toString(permitted));
		ShiroUtil.subject.checkPermission("student:select");
		ShiroUtil.subject.checkPermissions("");
		
	}
}
