package com.lmj.entity;

import java.io.Serializable;

/**
 * @ClassName: Aqi
 * @Description:
 * @author jiangya
 * @date 2019年8月14日 下午4:37:25
 *
 */
public class Aqi implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name = "—";

    private Integer aqi;

    public Aqi(){}

    public Aqi(String name, Integer aqi) {
        super();
        this.name = name;
        this.aqi = aqi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = (name == null)? "—":name;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }
}
