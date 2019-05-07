package com.ckgl.cg.bean;

public class Jincangt {
    private Integer id;
    private Integer kucunid;
    private String beizhu;
    private String jcriqi;
    private Integer xs;
    private Integer s;
    private Integer m;
    private Integer l;
    private Integer xl;
    private Integer xxl;
    private Integer xxxl;

    @Override
    public String toString() {
        return "Jincangt{" +
                "id=" + id +
                ", kucunid='" + kucunid + '\'' +
                ", beizhu='" + beizhu + '\'' +
                ", jcriqi='" + jcriqi + '\'' +
                ", xs=" + xs +
                ", s=" + s +
                ", m=" + m +
                ", l=" + l +
                ", xl=" + xl +
                ", xxl=" + xxl +
                ", xxxl=" + xxxl +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getJcriqi() {
        return jcriqi;
    }

    public void setJcriqi(String jcriqi) {
        this.jcriqi = jcriqi;
    }

    public Integer getXs() {
        return xs;
    }

    public void setXs(Integer xs) {
        this.xs = xs;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getXl() {
        return xl;
    }

    public void setXl(Integer xl) {
        this.xl = xl;
    }

    public Integer getXxl() {
        return xxl;
    }

    public void setXxl(Integer xxl) {
        this.xxl = xxl;
    }

    public Integer getXxxl() {
        return xxxl;
    }

    public void setXxxl(Integer xxxl) {
        this.xxxl = xxxl;
    }

    public Integer getKucunid() {
        return kucunid;
    }

    public void setKucunid(Integer kucunid) {
        this.kucunid = kucunid;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
    public String getBeizhu() {
        return beizhu;
    }
}
