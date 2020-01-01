package com.kid510.vhr;

import com.kid510.vhr.pojo.Menu;
import com.kid510.vhr.utils.FastJsonUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
class VhrApplicationTests {

    @Test
    void contextLoads() {
        Menu menu = new Menu();
        List<Menu> menus = new ArrayList<>();
        menus.add(menu);
        menu.setId(1);
        String s = FastJsonUtils.convertObjectToJSON(menus);
        System.out.println("s = " + s);

    }

    @Test
    void test1() {
        String str = "[{\"children\":[],\"component\":\"\",\"enabled\":false,\"iconCls\":\"\",\"id\":1,\"meta\":null,\"name\":\"\",\"parentId\":0,\"path\":\"\",\"roles\":[],\"url\":\"\"}]";
        List<Menu> menuList = FastJsonUtils.toList(str, Menu.class);
        for (Menu menu : menuList) {
            System.out.println("menu = " + menu);
        }

    }

}
