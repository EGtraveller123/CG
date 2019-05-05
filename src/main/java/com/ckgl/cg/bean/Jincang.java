package com.ckgl.cg.bean;

public class Jincang {
    private Integer id;
    private String kuanhao;
    private Integer jcshuliang;

    @Override
    public String toString() {
        return "Jincang{" +
                "id=" + id +
                ", kuanhao='" + kuanhao + '\'' +
                ", jcshuliang=" + jcshuliang +
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

    public Integer getJcshuliang() {
        return jcshuliang;
    }

    public void setJcshuliang(Integer jcshuliang) {
        this.jcshuliang = jcshuliang;
    }
}
