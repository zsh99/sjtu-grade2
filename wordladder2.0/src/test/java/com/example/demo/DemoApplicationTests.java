package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Stack;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    ladder main = new ladder();

    @Test
    public void testPrepareLadderStacks() {
        Stack<String> ladder = new Stack<>();
        ladder.push("source");
        Stack<Stack<String>> ladders = new Stack<>();
        ladders.push(ladder);
        Assert.assertEquals(ladders, main.prepareLadderStacks("source"));
    }
}
