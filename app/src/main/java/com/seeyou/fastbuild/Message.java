package com.seeyou.fastbuild;

import com.seeyou.toolkit.base.BaseModel;

import java.util.List;

/**
 * @author sumn
 * date 2019/10/21
 */
public class Message extends BaseModel<List<Message>> {

    private String gmtCreate;
    private Object gmtModified;
    private String nid;
    private String ncontent;
    private String ntitle;

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Object getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Object gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    @Override
    public String toString() {
        return "Message{" +
                "gmtCreate='" + gmtCreate + '\'' +
                ", gmtModified=" + gmtModified +
                ", nid='" + nid + '\'' +
                ", ncontent='" + ncontent + '\'' +
                ", ntitle='" + ntitle + '\'' +
                '}';
    }
}
