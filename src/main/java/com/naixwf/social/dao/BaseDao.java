/*
 * All rights reserved.
 * @author wangfei
 */
package com.naixwf.social.dao;


import com.naixwf.gm.util.Page;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.hql.classic.QueryTranslatorImpl;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.*;


/**
 * Hibernate的基础Dao
 *
 * @author zhaolei
 * @version 1.0
 * @created 2011-9-9
 */
public class BaseDao<T> {

    /**
     * spring 封装的hibernateTemplate,protected是为了用不同的数据源
     */
    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    protected HibernateTemplate hibernateTemplate;
    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        @SuppressWarnings("rawtypes")
        Class clazz = getClass();

        while (clazz != Object.class) {
            Type t = clazz.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType) t).getActualTypeArguments();
                if (args[0] instanceof Class) {
                    this.clazz = (Class<T>) args[0];
                    break;
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * 同步缓存到数据库,等待commit
     *
     * @author zhaolei
     * @created 2011-9-9
     */
    public void flush() {
        hibernateTemplate.flush();
    }

    /**
     * merge方法.在对象不在事务内部时使用.避免覆盖别人已经修改的字段.而自己本不想修改的数据.特殊地方调用.
     *
     * @param pojo
     * @author zhaolei
     * @created 2011-11-11
     */
    public void merge(T pojo) {
        hibernateTemplate.merge(pojo);
    }

    /**
     * merge方法.在对象不在事务内部时使用.避免覆盖别人已经修改的字段.而自己本不想修改的数据.特殊地方调用.
     *
     * @param pojos
     * @author zhaolei
     * @created 2011-11-11
     */
    public void merge(Collection<T> pojos) {
        for (T t : pojos) {
            merge(t);
        }
    }

    /**
     * 插入数据
     *
     * @param pojo 持久化对象
     * @author zhaolei
     * @created 2011-9-9
     */
    public T insert(T pojo) {
        hibernateTemplate.save(pojo);
        return pojo;
    }

    /**
     * 插入一组数据
     *
     * @param pojos 一组持久化对象
     * @author zhaolei
     * @created 2011-9-9
     */
    public void insert(T... pojos) {
        for (T t : pojos) {
            hibernateTemplate.save(t);
        }
    }

    /**
     * 插入一组数据
     *
     * @param pojos
     * @author zhaolei
     * @created 2011-9-9
     */
    public void insert(Collection<T> pojos) {
        for (T t : pojos) {
            hibernateTemplate.save(t);
        }
    }

    /**
     * 更新持久化对象
     *
     * @param pojo
     * @author zhaolei
     * @created 2011-9-9
     */
    public void update(T pojo) {
        hibernateTemplate.update(pojo);
    }

    /**
     * 更新一组数据
     *
     * @param pojos 一组持久化对象
     * @author zhaolei
     * @created 2011-9-9
     */
    public void update(T... pojos) {
        for (T t : pojos) {
            hibernateTemplate.update(t);
        }
    }

    /**
     * 更新一组数据
     *
     * @param pojos
     * @author zhaolei
     * @created 2011-9-9
     */
    public void update(Collection<T> pojos) {
        for (T t : pojos) {
            hibernateTemplate.update(t);
        }
    }

    /**
     * 保存或更新.(会根据unsaved-value判断是保存还是更新,所以,在语义明确的情况下.不要使用这个方法)
     *
     * @param pojo 持久化对象
     * @author zhaolei
     * @created 2011-9-9
     */
    public void saveOrUpdate(T pojo) {
        hibernateTemplate.saveOrUpdate(pojo);
    }

    /**
     * 保存或更新一组数据(会根据unsaved-value判断是保存还是更新,所以,在语义明确的情况下.不要使用这个方法)
     *
     * @param pojos
     * @author zhaolei
     * @created 2011-9-9
     */
    public void saveOrUpdate(T... pojos) {
        hibernateTemplate.saveOrUpdateAll(Arrays.asList(pojos));
    }

    /**
     * 保存或更新一组数据(会根据unsaved-value判断是保存还是更新,所以,在语义明确的情况下.不要使用这个方法)
     *
     * @param pojos
     * @author zhaolei
     * @created 2011-9-9
     */
    public void saveOrUpdate(Collection<T> pojos) {
        hibernateTemplate.saveOrUpdateAll(pojos);
    }

    /**
     * 删除对象
     *
     * @param pojo
     * @author zhaolei
     * @created 2011-9-9
     */
    public void delete(T pojo) {
        hibernateTemplate.delete(pojo);
    }

    /**
     * 删除一组对象
     *
     * @param pojos
     * @author zhaolei
     * @created 2011-9-9
     */
    public void delete(T... pojos) {
        hibernateTemplate.deleteAll(Arrays.asList(pojos));
    }

    /**
     * 删除一组对象
     *
     * @param pojos
     * @author zhaolei
     * @created 2011-9-9
     */
    public void delete(Collection<T> pojos) {
        hibernateTemplate.deleteAll(pojos);
    }

    /**
     * 根据主键删除对象
     *
     * @param id
     * @author zhaolei
     * @created 2011-9-9
     */
    public void deleteById(Serializable id) {
        T pojo = findById(id);
        if (pojo != null) {
            delete(pojo);
        }
    }

    /**
     * 根据某个属性删除对象,必须是等于
     *
     * @param propertyName 属性名称
     * @param value        值
     * @return 更新的数据量
     * @author zhaolei
     * @created 2011-9-9
     */
    public int deleteByProperty(String propertyName, Object value) {
        String hql = "delete from " + clazz.getSimpleName() + " where " + propertyName + "=?";
        return hibernateTemplate.bulkUpdate(hql, value);
    }

    /**
     * 通过hql语句批量删除或跟新数据
     *
     * @param hql    hql语句
     * @param values 参数值
     * @return 更新的数据量
     * @author zhaolei
     * @created 2011-9-9
     */
    public int updateOrDeleteByHql(String hql, Object... values) {
        return hibernateTemplate.bulkUpdate(hql, values);
    }

    /**
     * 根据主键查找对象
     *
     * @param id
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    public T findById(Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        List list = hibernateTemplate.findByCriteria(criteria);
        return list;
    }

    /**
     * 指定返回实体对象的类型.根据主键查询
     *
     * @param <E>
     * @param clazz
     * @param id
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    public <E> E find(Class<E> clazz, Serializable id) {
        return hibernateTemplate.get(clazz, id);
    }

    /**
     * 根据属性查询,必须是等于
     *
     * @param propertyName 属性名称
     * @param value        值
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByProperty(String propertyName, Object value) {
        String hql = "from " + clazz.getSimpleName() + " where " + propertyName + "=?";
        return hibernateTemplate.find(hql, value);
    }

    /**
     * 根据sql查询
     *
     * @param sql
     * @return
     * @author zhaolei
     * @created 2011-11-30
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected List<Object[]> findBySql(final String sql) {
        return (List<Object[]>) hibernateTemplate.executeFind(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                return sqlQuery.list();
            }
        });
    }

    /**
     * 根据hql查询
     *
     * @param hql
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql) {
        return hibernateTemplate.find(hql);
    }

    /**
     * 根据hql和参数查询
     *
     * @param hql
     * @param values 占位符对应的值
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(String hql, Object... values) {
        return hibernateTemplate.find(hql, values);
    }

    /**
     * 根据命名参数查询
     *
     * @param hql      带有命名参数的hql
     * @param valueMap 命名参数的map.key是命名参数的名称(比如:name) valueMap.put("name","wing")
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected List<T> find(final String hql, final Map<String, Object> valueMap) {
        return (List<T>) hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                query = setParams(query, valueMap);
                return query.list();
            }
        });
    }

    /**
     * 根据动态查询条件查询
     *
     * @param criteria 动态查询条件
     * @return
     * @author zhaolei
     * @created 2011-9-13
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(DetachedCriteria criteria) {
        List<T> list = hibernateTemplate.findByCriteria(criteria);
        if (list == null) {
            return new ArrayList<T>();
        }
        return list;
    }

//    /**
//     * 根据某个属性分组成map.key是属性值,value是对象list
//     *
//     * @author zhaolei
//     * @created 2011-11-5
//     *
//     * @param <C>
//     *            分组属性的泛型
//     * @param criteria
//     *            动态查询条件对象
//     * @param propertyName
//     *            属性名称
//     * @return
//     */
//    protected <C> Map<C, List<T>> findMap4ListByProperty(DetachedCriteria criteria,
//                                                         String propertyName) {
//        return groupList2MapByProperty(find(criteria), propertyName);
//    }
//
//    /**
//     * 根据某个属性分组成map.key是属性值,value是对象
//     *
//     * @author zhaolei
//     * @created 2011-11-5
//     *
//     * @param <C>
//     *            分组属性的泛型
//     * @param criteria
//     *            动态查询条件对象
//     * @param propertyName
//     *            属性名称
//     * @return
//     */
//    protected <C> Map<C, T> findMapByProperty(DetachedCriteria criteria, String propertyName) {
//        return group2MapByProperty(find(criteria), propertyName);
//    }

    /**
     * 根据动态查询条件,分段查询,并在分页对象你写入分页数据
     *
     * @param criteria 动态查询条件
     * @param page     分页对象
     * @return
     * @author zhaolei
     * @created 2011-9-13
     */
    @SuppressWarnings("unchecked")
    protected List<T> find(DetachedCriteria criteria, Page page) {
        return hibernateTemplate.findByCriteria(criteria, page.getStart(), page.getPageSize());
    }

    /**
     * hql转换成sql
     *
     * @param hql
     * @return
     * @author zhaolei
     * @created 2011-11-30
     */
    protected String hql2Sql(String hql) {
        String h = "" + hql;
        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(h, h, Collections.EMPTY_MAP,
                (org.hibernate.engine.SessionFactoryImplementor) getSessionFaction());
        queryTranslator.compile(Collections.EMPTY_MAP, false);
        return queryTranslator.getSQLString();
    }

//    /**
//     * 组装结果根据某个属性分组,装载成map.如果有重复的.则追加到value的list内,属性值为null,不进入map
//     *
//     * @author zhaolei
//     * @created 2011-11-5
//     *
//     * @param <C>
//     *            分组属性的类型
//     * @param list
//     *            分组后的属性值
//     * @param propertyName
//     *            分组依据的属性名称
//     * @return
//     * @throws AsDataAccessException
//     */
//    private <C> Map<C, List<T>> groupList2MapByProperty(List<T> list, String propertyName)
//            throws AsDataAccessException {
//        Map<C, List<T>> map = new HashMap<C, List<T>>();
//        for (T t : list) {
//            try {
//                Method m = clazz.getDeclaredMethod("get"
//                        + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));
//                @SuppressWarnings("unchecked")
//                C c = (C) m.invoke(t);
//                if (c != null) {
//                    if (!map.containsKey(c)) {
//                        map.put(c, new ArrayList<T>());
//                    }
//                    map.get(c).add(t);
//                }
//            } catch (Exception e) {
//                throw new AsDataAccessException(e.getMessage(), e);
//            }
//        }
//        return map;
//    }

//    /**
//     * 组装结果根据某个属性分组,装载成map.如果有重复的.则覆盖前面的值,属性值为null,不进入map
//     *
//     * @author zhaolei
//     * @created 2011-11-5
//     *
//     * @param <C>
//     *            分组属性的类型
//     * @param list
//     *            分组后的属性值
//     * @param propertyName
//     *            分组依据的属性名称
//     * @return
//     * @throws AsDataAccessException
//     */
//    private <C> Map<C, T> group2MapByProperty(List<T> list, String propertyName)
//            throws AsDataAccessException {
//        Map<C, T> map = new HashMap<C, T>();
//        for (T t : list) {
//            try {
//                Method m = clazz.getDeclaredMethod("get"
//                        + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1));
//                @SuppressWarnings("unchecked")
//                C c = (C) m.invoke(t);
//                if (c != null) {
//                    map.put(c, t);
//                }
//            } catch (Exception e) {
//                throw new AsDataAccessException(e.getMessage(), e);
//            }
//        }
//        return map;
//    }

    /**
     * 为Query设置命名参数值
     *
     * @param query
     * @param valueMap
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    @SuppressWarnings("rawtypes")
    private Query setParams(Query query, Map<String, Object> valueMap) {
        if (valueMap == null)
            return query;
        // 设置查询参数
        for (Map.Entry<String, Object> entry : (Set<Map.Entry<String, Object>>) valueMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Collection)
                query.setParameterList(entry.getKey(), (Collection) value);
            else if (value instanceof Object[])
                query.setParameterList(entry.getKey(), (Object[]) value);
            else
                query.setParameter(entry.getKey(), value);
        }
        return query;
    }

    /**
     * 得到Hibernate SessionFactory
     *
     * @return
     * @author zhaolei
     * @created 2011-9-9
     */
    private SessionFactory getSessionFaction() {
        return hibernateTemplate.getSessionFactory();
    }

}
