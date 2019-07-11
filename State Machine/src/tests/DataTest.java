package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Data;
import model.State;
import model.Transition;

class DataTest extends Data{

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testSetConnections() {
		State s1 = new State();
		State s2 = new State();
		ArrayList<State> sl = new ArrayList<State>();
		sl.add(s1);
		sl.add(s2);
		Transition t1 = new Transition(1, "s1 to s2", 2, 1, s2, s1);
		Transition t2 = new Transition(2, "s2 to s1", 1, 2, s1, s2);
		super.setStates(sl);
		s1.getStateTransitions().add(1);
		s2.getStateTransitions().add(2);
		super.getTransitions().add(t1);
		super.getTransitions().add(t2);
		ArrayList<Transition> tl = new ArrayList<Transition>();
		tl.add(t1);
		super.setConnections();
		Assert.assertEquals(tl,s1.getTransitions());
	}

}
