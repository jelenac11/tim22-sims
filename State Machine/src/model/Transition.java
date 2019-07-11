package model;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("Transition")
public class Transition {
	
	@XStreamAsAttribute
	private int transitionId;
	private String lifecycleName;
	private int transitionOnSucceed;
	private int transitionOnFail;
	@XStreamOmitField
	private State stateTo;
	@XStreamOmitField
	private State stateFrom;
	
	public Transition() {
		
	}

	public Transition(int transitionId, String lifecycleName, int transitionOnSucceed, int transitionOnFail,
			State stateTo, State stateFrom) {
		this.transitionId = transitionId;
		this.lifecycleName = lifecycleName;
		this.transitionOnSucceed = transitionOnSucceed;
		this.transitionOnFail = transitionOnFail;
		this.stateTo = stateTo;
		this.stateFrom = stateFrom;
	}

	public int getTransitionId() {
		return transitionId;
	}

	public void setTransitionId(int transitionId) {
		this.transitionId = transitionId;
	}

	public String getLifecycleName() {
		return lifecycleName;
	}

	public void setLifecycleName(String lifecycleName) {
		this.lifecycleName = lifecycleName;
	}

	public int getTransitionOnSucceed() {
		return transitionOnSucceed;
	}

	public void setTransitionOnSucceed(int transitionOnSucceed) {
		this.transitionOnSucceed = transitionOnSucceed;
	}

	public int getTransitionOnFail() {
		return transitionOnFail;
	}

	public void setTransitionOnFail(int transitionOnFail) {
		this.transitionOnFail = transitionOnFail;
	}

	public State getStateTo() {
		return stateTo;
	}

	public void setStateTo(State stateTo) {
		this.stateTo = stateTo;
	}

	public State getStateFrom() {
		return stateFrom;
	}

	public void setStateFrom(State stateFrom) {
		this.stateFrom = stateFrom;
	}
	
}