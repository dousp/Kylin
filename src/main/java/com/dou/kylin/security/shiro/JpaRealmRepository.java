/**
 * <p>Copyright (c) 2014 ZhaoQian.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">ZhaoQian</a>
 */
package com.dou.kylin.security.shiro;

import org.springframework.stereotype.Repository;

/*import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;*/

/**
 *
 * @author ZhaoQian
 * @date: 2014年7月30日
 */
@Repository
public class JpaRealmRepository
{
/*
	@PersistenceContext
    EntityManager em;

	public User findUserByName(String username) {

		String jpql="select u from User u where u.username=:username";
		List<User> results = this.em.createQuery(jpql, User.class)
                        .setParameter("username", username).setMaxResults(1)
						.getResultList();
		if ( !results.isEmpty() )
		{
			return results.get(0);
		}
		return null;
	}

	public void mergeRole(Role role) {

		this.em.merge(role);
	}

	public User loadUser(Object id) {

		return this.em.find(User.class, id);
	}

	public User mergeUser(User user) {

		return this.em.merge(user);
	}*/
}
