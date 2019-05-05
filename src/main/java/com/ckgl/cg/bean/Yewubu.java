package com.ckgl.cg.bean;


public class Yewubu {
    private Integer id;
    private String kuanhao;
    private String kehu;
    private Integer ywbshuliang;
    private String mianliao;
    private String chriqi;

    public Yewubu(Integer id, String kuanhao, String kehu, Integer ywbshuliang, String mianliao, String chriqi) {
        this.id = id;
        this.kuanhao = kuanhao;
        this.kehu = kehu;
        this.ywbshuliang = ywbshuliang;
        this.mianliao = mianliao;
        this.chriqi = chriqi;
    }

    public Yewubu() {
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

    public String getKehu() {
        return kehu;
    }

    public void setKehu(String kehu) {
        this.kehu = kehu;
    }

    public Integer getYwbshuliang() {
        return ywbshuliang;
    }

    public void setYwbshuliang(Integer ywbshuliang) {
        this.ywbshuliang = ywbshuliang;
    }

    public String getMianliao() {
        return mianliao;
    }

    public void setMianliao(String mianliao) {
        this.mianliao = mianliao;
    }

    public String getChriqi() {
        return chriqi;
    }

    public void setChriqi(String chriqi) {
        this.chriqi = chriqi;
    }

    @Override
    public String toString() {
        return "Yewubu{" +
                "id=" + id +
                ", kuanhao='" + kuanhao + '\'' +
                ", kehu='" + kehu + '\'' +
                ", ywbshuliang=" + ywbshuliang +
                ", mianliao='" + mianliao + '\'' +
                ", chriqi='" + chriqi + '\'' +
                '}';
    }
}
