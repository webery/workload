package dlnu.workload.module.authority.credential;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author weber 2015.4.18
 *
 */
public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
	
	private static Logger logger = Logger.getLogger(RetryLimitHashedCredentialsMatcher.class);
	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		String account = (String) token.getPrincipal();
		logger.debug(" account:" + account);
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(account);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(account, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(account);
		}
		return matches;
	}
}
