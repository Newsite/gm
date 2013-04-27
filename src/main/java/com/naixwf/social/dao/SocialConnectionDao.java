package com.naixwf.social.dao;

import com.naixwf.social.domain.SocialConnection;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-18
 * Time: 下午5:04
 */
public class SocialConnectionDao extends BaseDao<SocialConnection> {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SocialConnectionDao.class);

    public List<SocialConnection> findByUserId(String userId) {
        DetachedCriteria crit = DetachedCriteria.forClass(SocialConnection.class);
        crit.add(Restrictions.eq("userId", userId))
                .addOrder(Order.asc("providerId")).addOrder(Order.asc("rank"));
        return find(crit);
    }

    public List<SocialConnection> findByUserIdAndProviderId(String userId, String providerId) {
        DetachedCriteria crit = DetachedCriteria.forClass(SocialConnection.class);
        crit.add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("userId", userId))
                .addOrder(Order.asc("rank"));
        return find(crit);
    }

    public List<SocialConnection> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds) {
        DetachedCriteria crit = DetachedCriteria.forClass(SocialConnection.class);
        crit.add(Restrictions.eq("userId", userId));
        String providerUserSql = "0";
        for (String providerId : providerUserIds.keySet()) {
            List<String> providerUserIdList = providerUserIds.get(providerId);
            for (String providerUserId : providerUserIdList) {
                providerUserSql = providerUserSql + " or " + String.format("(su.providerId=%s and su.providerUserId=%s)", providerId, providerUserId);
            }
        }
        String hql = "select SocialConnection as su where su.userId=? and (" + providerUserSql + ")";
        logger.debug(hql);
        return find(hql);
    }

    public SocialConnection get(String userId, String providerId, String providerUserId) {
        DetachedCriteria crit = DetachedCriteria.forClass(SocialConnection.class);
        crit.add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("providerUserId", providerUserId));
        List<SocialConnection> list = find(crit);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public SocialConnection findPrimaryByUserIdAndProviderId(String userId, String providerId) {
        DetachedCriteria crit = DetachedCriteria.forClass(SocialConnection.class);
        crit.add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("userId", userId)).add(Restrictions.eq("rank", 1));
        List<SocialConnection> SocialConnectionList = find(crit);
        if (SocialConnectionList.isEmpty()) {
            return null;
        }
        return SocialConnectionList.get(0);
    }

    public List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId) {
        DetachedCriteria crit = DetachedCriteria.forClass(SocialConnection.class);
        crit.add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("providerUserId", providerUserId))
                .addOrder(Order.asc("providerId")).addOrder(Order.asc("providerUserId"));
        List<SocialConnection> SocialConnectionList = find(crit);
        List<String> list = new ArrayList<String>();
        if (SocialConnectionList != null) {
            for (SocialConnection user : SocialConnectionList) {
                list.add(user.getUserId());
            }
        }
        return list;
    }

    public List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds) {
        if (providerId == null || providerUserIds == null || providerUserIds.isEmpty()) {
            throw new IllegalArgumentException("providerId,providerUserIds can't be empty");
        }
        DetachedCriteria crit = DetachedCriteria.forClass(SocialConnection.class);
        crit.add(Restrictions.eq("providerId", providerId)).add(Restrictions.in("providerUserId", providerUserIds))
                .addOrder(Order.asc("providerId")).addOrder(Order.asc("providerUserId"));
        List<SocialConnection> SocialConnectionList = find(crit);
        List<String> list = new ArrayList<String>();
        if (SocialConnectionList != null) {
            for (SocialConnection user : SocialConnectionList) {
                list.add(user.getUserId());
            }
        }
        return list;
    }

}
