package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Document;
import model.Field;
import model.FieldType;
import model.State;

class DocumentTest extends Document{

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testCheckMandatoryFields() {
		State s = new State();
		ArrayList<FieldType> l = new ArrayList<FieldType>();
		l.add(FieldType.ACCESS_PERMIT_DATE_TIME);
		l.add(FieldType.ACCESS_PERMIT_DENYING_REASON);
		s.setStateMandatoryFields(l);
		Field f1 = new Field(FieldType.ACCESS_PERMIT_DATE_TIME, "date time", "30.06.2019.");
		Field f2 = new Field(FieldType.ACCESS_PERMIT_DENYING_REASON, "denying reason", "not published");
		ArrayList<Field> fl = new ArrayList<Field>();
		fl.add(f1);
		fl.add(f2);
		super.setFields(fl);
		assertEquals(true, super.checkMandatoryFields(s));
	}

}
