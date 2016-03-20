package cl.bennu.plcbus.common.domain;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 08-07-13
 * Time: 08:41 AM
 */
public class Map extends BaseDomain {

    private String name;
    private String desc;
    private String info;
    private String path;
    private Boolean active;
    private Level level;
    private List<Coordinate> coordinateList;
    private Long order;

    private File image;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public void setCoordinateList(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
