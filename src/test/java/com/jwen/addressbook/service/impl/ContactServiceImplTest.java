package com.jwen.addressbook.service.impl;

import com.jwen.addressbook.service.ContactService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceImplTest {

    @Autowired
    ContactServiceImpl contactService;

    @Test
    public void getContactById() {
        System.out.println(contactService.getContactById(1));
    }

    @Test
    public void getContactCounts() {
        int contactCounts = contactService.getContactCounts();
        Assert.assertEquals(0,contactCounts);
    }
}