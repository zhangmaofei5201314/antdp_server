package test;

import com.donbala.AntdpServerApplication;
import com.donbala.loginManagement.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 *
 * @date 2020/8/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AntdpServerApplication.class)
public class TestC {

    @Autowired
    private LoginService loginService;

    @Test
    public void test1(){
        List<Map<String, Object>> list = loginService.getAntdUserMenuByCode("001");

        System.out.println(list.toString());

    }


}
