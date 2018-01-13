package com.hendisantika.springbootfilter.web;

import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-filter
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/01/18
 * Time: 08.13
 * To change this template use File | Settings | File Templates.
 */

@Or({
        @Spec(params = "name", path = "firstName", spec = LikeIgnoreCase.class),
        @Spec(params = "name", path = "lastName", spec = LikeIgnoreCase.class)
})
public interface CustomerByNameSpecification extends NotDeletedCustomerSpecification {
}
