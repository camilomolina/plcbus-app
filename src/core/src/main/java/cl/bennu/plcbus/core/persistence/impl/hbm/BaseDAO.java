package cl.bennu.plcbus.core.persistence.impl.hbm;

import cl.bennu.plcbus.common.Constants;
import cl.bennu.plcbus.common.domain.BaseDomain;
import cl.bennu.plcbus.core.persistence.iface.IBaseDAO;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:17 AM
 */
public abstract class BaseDAO<T extends BaseDomain> extends HibernateDaoSupport implements IBaseDAO<T> {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
    private static final SimpleDateFormat simpleDateFormat_MM_yyyy = new SimpleDateFormat(Constants.DATE_FORMAT_MM_YYYY);
    private static final SimpleDateFormat simpleDateFormat_hhmm = new SimpleDateFormat(Constants.DATETIME_FORMAT);

    private Class<T> persistentClass;

    public BaseDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }


    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public List<T> getAll() {
        return this.getHibernateTemplate().loadAll(getPersistentClass());
    }

    @Override
    public List<T> find(T type) {
        return this.getHibernateTemplate().findByExample(type);
    }

    @Override
    public T get(Long id) {
        return (T) this.getHibernateTemplate().get(getPersistentClass(), id);
    }

    @Override
    public T save(T type) {
        this.getHibernateTemplate().saveOrUpdate(type);
        return type;
    }

    @Override
    public void delete(Long id) {
        String delQuery = "delete from " + getPersistentClass().getName() + " entity where entity.id = :id";
        getSession().createQuery(delQuery).setParameter("id", id).executeUpdate();
    }

    @Override
    public Long count() {
        String delQuery = "select count(*) from " + getPersistentClass().getName();
        return (Long)getSession().createQuery(delQuery).uniqueResult();
    }

    protected Date getLo(Date date) {
        String dateStr = simpleDateFormat.format(date);

        Date dateLo;
        try {
            dateLo = simpleDateFormat_hhmm.parse(dateStr + " 00:00:00");
        } catch (Exception e) {
            dateLo = date;
        }

        return dateLo;
    }

    protected Date getHi(Date date) {
        String dateStr = simpleDateFormat.format(date);

        Date dateHi;
        try {
            dateHi = simpleDateFormat_hhmm.parse(dateStr + " 23:59:59");
        } catch (Exception e) {
            dateHi = date;
        }

        return dateHi;
    }

    protected Date getLo2(Date date) {
        String dateStr = simpleDateFormat_MM_yyyy.format(date);

        Date dateLo;
        try {
            dateLo = simpleDateFormat_hhmm.parse("01/"  +dateStr + " 00:00:00");
        } catch (Exception e) {
            dateLo = date;
        }

        return dateLo;
    }

    protected Date getHi2(Date date) {
        String dateStr = simpleDateFormat_MM_yyyy.format(date);

        Date dateHi;
        try {
            dateHi = simpleDateFormat_hhmm.parse("01/" + dateStr + " 00:00:00");
            Calendar c = Calendar.getInstance();
            c.setTime(dateHi);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.SECOND, -1);
            dateHi = c.getTime();
        } catch (Exception e) {
            dateHi = date;
        }

        return dateHi;
    }

}
