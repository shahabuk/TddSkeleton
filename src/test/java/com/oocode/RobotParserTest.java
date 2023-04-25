package com.oocode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class RobotParserTest {

    Robot robot = mock(Robot.class);
    Parser parser = new Parser(robot);
    @Test
    public void shouldVerifyMoveOnto() {
        parser.parse("move 7 onto 4");
        verify(robot).moveOnto(7, 4);
    }

    @Test
    public void shouldVerifyMoveOntoWithAnyValue() {
        parser.parse("move 9 onto 1");
        verify(robot).moveOnto(9, 1);
    }

    @Test
    public void shouldVerifyMoveOver() {
        parser.parse("move 8 over 1");
        verify(robot).moveOver(8, 1);
    }

    @Test
    public void shouldVerifyPileOnto() {
        parser.parse("pile 8 onto 1");
        verify(robot).pileOnto(8, 1);
    }

    @Test
    public void shouldVerifyPileOver() {
        parser.parse("pile 8 over 1");
        verify(robot).pileOver(8, 1);
    }

    @Test
    public void shouldParseMultipleCommands() {
        parser.parse("move 9 onto 1\n" +
                "move 8 over 1\n" +
                "move 7 over 1\n" +
                "move 6 over 1\n" +
                "pile 8 over 6\n" +
                "pile 8 over 5\n" +
                "move 2 over 1\n" +
                "move 4 over 9");
        verify(robot, times(1)).moveOnto(any(), any());
        verify(robot, times(5)).moveOver(any(), any());
        verify(robot, times(2)).pileOver(any(), any());
    }

    @Test
    public void sneaky() {
        FakeRobot fakeRobot = new FakeRobot();
        Parser parser = new Parser(fakeRobot);
        String input = "move 9 onto 1\n" +
                "move 8 over 1\n" +
                "move 7 over 1\n" +
                "move 6 over 1\n" +
                "pile 8 over 6\n" +
                "pile 8 over 5\n" +
                "move 2 over 1\n" +
                "move 4 over 9";
        parser.parse(input);

        assertThat(fakeRobot.stuff.toString().trim(), equalTo(input));
    }

    private static class FakeRobot implements Robot {
        public final StringBuilder stuff = new StringBuilder();

        @Override
        public void moveOnto(Integer from, Integer to) {
            stuff.append("move " + from + " onto " + to+"\n");
        }

        @Override
        public void moveOver(Integer from, Integer to) {
            stuff.append("move " + from + " over " + to+"\n");

        }

        @Override
        public void pileOnto(Integer from, Integer to) {
            stuff.append("pile " + from + " onto " + to+"\n");

        }

        @Override
        public void pileOver(Integer from, Integer to) {
            stuff.append("pile " + from + " over " + to+"\n");

        }
    }
}
