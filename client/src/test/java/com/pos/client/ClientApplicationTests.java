package com.pos.client;

import com.pos.client.cli.PosCommandLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class ClientApplicationTests {

    @Test
    void showProduct(PosCommandLine commandLine){
        commandLine.fetchProducts();
        for(int i = 0;i<1000;++i) {
            commandLine.showProducts(i);

        }
    }

}
