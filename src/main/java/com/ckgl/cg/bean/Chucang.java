package com.ckgl.cg.bean;

public class Chucang {
    private Integer id;
    private String kuanhao;
    private Integer ccshuliang;

    @Override
    public String toString() {
        return "Chucang{" +
                "id=" + id +
                ", kuanhao='" + kuanhao + '\'' +
                ", ccshuliang=" + ccshuliang +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKuanhao() {
        return kuanhao;
    }

    public void setKuanhao(String kuanhao) {
        this.kuanhao = kuanhao;
    }

    public Integer getCcshuliang() {
        return ccshuliang;
    }

    public void setCcshuliang(Integer ccshuliang) {
        this.ccshuliang = ccshuliang;
    }
}
