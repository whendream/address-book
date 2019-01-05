package com.jwen.addressbook.service.impl;

import com.jwen.addressbook.domain.Contact;
import com.jwen.addressbook.domain.ContactExample;
import com.jwen.addressbook.mapper.ContactMapper;
import com.jwen.addressbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jun.wen
 */

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactMapper contactMapper;

    @Override
    public Contact getContactById(int id) {

        Contact contact = contactMapper.selectByPrimaryKey(id);

        if (contact == null) {
            return null;
        }
        return contact;
    }

    @Override
    public int getContactCounts() {

        ContactExample example = new ContactExample();

        int count = contactMapper.countByExample(example);

        return count;

    }
}
