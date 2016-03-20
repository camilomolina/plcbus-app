package cl.bennu.plcbus.core.persistence.impl.mybatis;

import cl.bennu.plcbus.common.domain.BaseDomain;
import cl.bennu.plcbus.core.persistence.iface.IBaseDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:17 AM
 */
public abstract class BaseDAO<T extends BaseDomain> extends SqlSessionDaoSupport implements IBaseDAO<T> {

    public static final String PREFIX_SELECT_QUERY = "get";

    public static final String PREFIX_GET_BY_ID_QUERY = "get";
    public static final String SUFFIX_GET_BY_ID_QUERY = "byId";

    public static final String PREFIX_GET_BY_NAME_QUERY = "get";
    public static final String SUFFIX_GET_BY_NAME_QUERY = "byName";

    public static final String PREFIX_GET_ALL_QUERY = "getAll";

    public static final String PREFIX_FIND_QUERY = "find";

    public static final String PREFIX_INSERT_QUERY = "insert";

    public static final String PREFIX_UPDATE_QUERY = "update";

    public static final String PREFIX_DELETE_QUERY = "delete";

    public static final String PREFIX_COUNT_QUERY = "count";


    private Class<T> persistentClass;
    private SqlSessionFactory sqlSessionFactory;

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    protected BaseDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public List<T> getAll() {
        SqlSession session = getSqlSessionFactory().openSession();
        List<T> result = null;
        try {
            String query = getPersistentClass() + "." + PREFIX_GET_ALL_QUERY + getPersistentClass();
            result = session.selectList(query);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<T> find(T type) {
        SqlSession session = getSqlSessionFactory().openSession();
        List<T> result = null;
        try {
            String query = getPersistentClass() + "." + PREFIX_FIND_QUERY + getPersistentClass();
            result = session.selectList(query, type);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public T get(Long id) {
        SqlSession session = getSqlSessionFactory().openSession();
        T result = null;
        try {
            String query = getPersistentClass() + "." + PREFIX_GET_BY_ID_QUERY + getPersistentClass() + SUFFIX_GET_BY_ID_QUERY;
            result = session.selectOne(query, id);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public T save(T type) {
        if (type.getId() == null) {
            SqlSession session = getSqlSessionFactory().openSession();
            try {
                String query = getPersistentClass() + "." + PREFIX_INSERT_QUERY + getPersistentClass();
                session.insert(query, type);
            } finally {
                session.close();
            }
        } else {
            SqlSession session = getSqlSessionFactory().openSession();
            try {
                String query = getPersistentClass() + "." + PREFIX_UPDATE_QUERY + getPersistentClass();
                session.update(query, type);
            } finally {
                session.close();
            }

        }
        return type;
    }

    @Override
    public void delete(Long id) {
        SqlSession session = getSqlSessionFactory().openSession();
        try {
            String query = getPersistentClass() + "." + PREFIX_DELETE_QUERY + getPersistentClass();
            session.delete(query, id);
        } finally {
            session.close();
        }
    }

    @Override
    public Long count() {
        SqlSession session = getSqlSessionFactory().openSession();
        Long result = null;
        try {
            String query = getPersistentClass() + "." + PREFIX_COUNT_QUERY + getPersistentClass();
            result = session.selectOne(query);
        } finally {
            session.close();
        }
        return result;
    }
}
