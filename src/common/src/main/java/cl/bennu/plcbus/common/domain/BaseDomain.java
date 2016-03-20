package cl.bennu.plcbus.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:47 AM
 */
public class BaseDomain implements Serializable {

    private Long id;
    private Date create;
    private String creatorUser;
    private Date update;
    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(String creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
