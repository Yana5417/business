package com.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zuozheng.hzz
 */
public abstract class BaseDBEntity implements Serializable {

    private static final long serialVersionUID = -704724071825309726L;

    public Long id;
    public Date createdTime = new Date();
    public Date modifiedTime = new Date();
    public String creator = "admin";
    public String modifier = "admin";
    public String isDeleted = "N";

    /**
     * 1. 初始化一个整形变量，为此变量赋予一个非零的常数值，比如int result = 17;
     * 2. 选取equals方法中用于比较的所有域，然后针对每个域的属性进行计算：
     * (1) 如果是boolean值，则计算f ? 1:0
     * (2) 如果是byte\char\short\int,则计算(int)f
     * (3) 如果是long值，则计算(int)(f ^ (f >>> 32))
     * (4) 如果是float值，则计算Float.floatToIntBits(f)
     * (5) 如果是double值，则计算Double.doubleToLongBits(f)，然后返回的结果是long,再用规则(3)去处理long,得到int
     * (6) 如果是对象应用，如果equals方法中采取递归调用的比较方式，那么hashCode中同样采取递归调用hashCode的方式。
     * 否则需要为这个域计算一个范式，比如当这个域的值为null的时候，那么hashCode 值为0
     * (7) 如果是数组，那么需要为每个元素当做单独的域来处理。如果你使用的是1.5及以上版本的JDK，那么没必要自己去
     * 重新遍历一遍数组，java.util.Arrays.hashCode方法包含了8种基本类型数组和引用数组的hashCode计算，算法同上
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        if (id != null) {
            result = result * 31 + (int)(id ^ (id >>> 32));
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseDBEntity)) {
            return false;
        }

        BaseDBEntity that = (BaseDBEntity)o;

        if (id != null && id.equals(that.id)) {
            return true;
        }
        return false;
    }

    public boolean isDeleted() {
        return !"N".equalsIgnoreCase(isDeleted);
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
