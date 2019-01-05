package com.jwen.addressbook.service;

import com.jwen.addressbook.domain.Contact;

/**
 * @author jun.wen
 */
public interface ContactService {

    /**
     * 获取联系人信息
     * @param id
     * @return
     */
    public Contact getContactById(int id);

    /**
     * 获取联系人总数
     * @return
     */
    public int getContactCounts();
}
