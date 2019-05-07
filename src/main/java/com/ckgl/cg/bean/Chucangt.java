package com.ckgl.cg.bean;

public class Chucangt {
    private Integer id;
    private String kucunid;
    private String ccriqi;
    private Integer xs;
    private Integer s;
    private Integer m;
    private Integer l;
    private Integer xl;
    private Integer xxl;
    private Integer xxxl;
    private String beizhu;

    @Override
    public String toString() {
        return "Chucangt{" +
                "id=" + id +
                ", beizhu='" + beizhu + '\'' +
                ", kucunid='" + kucunid + '\'' +
                ", ccriqi='" + ccriqi + '\'' +
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

    public String getCcriqi() {
        return ccriqi;
    }

    public void setCcriqi(String ccriqi) {
        this.ccriqi = ccriqi;
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

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public void setKucunid(String kucunid) {
        this.kucunid = kucunid;
    }
}
